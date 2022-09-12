package com.example.caref.files.services.impl;

import com.example.caref.files.dto.CreateFileDto;
import com.example.caref.files.dto.FileDto;
import com.example.caref.files.entities.CarefFile;
import com.example.caref.files.entities.enumeration.DocType;
import com.example.caref.files.repositories.FileRepository;
import com.example.caref.files.services.FileService;
import com.example.caref.files.webs.FilesResource;
import com.example.caref.models.Car;
import com.example.caref.models.Garage;
import com.example.caref.models.User;
import com.example.caref.repository.CarRepository;
import com.example.caref.repository.GarageRepository;
import com.example.caref.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
@Primary
public class FileServiceImpl implements FileService {

    private final Path root = Paths.get("uploads");
    @Autowired
    CarRepository carRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GarageRepository garageRepository;
    @Autowired
    FileRepository fileRepository;

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    @Transactional
    public void save(CreateFileDto dto, MultipartFile filePart) throws Exception {
        String filename = filePart.getOriginalFilename();
        String ext = filename.substring(filename.lastIndexOf('.') + 1);

        CarefFile carefFile = CarefFile.CarefFileBuilder.aCarefFile()
                .withType(dto.getType())
                .withFileType(dto.getFileType())
                .build();
        try {
            switch (dto.getEntity()) {
                case CAR: {
                    Optional<Car> car = carRepository.findById(dto.getCarId());
                    if (car.isPresent()) {
                        filename = "CAREF_MEDIA_" + dto.getEntity().name() + "_" + car.get().getId() + "_" + Calendar.getInstance().getTimeInMillis() + "." + ext;
                        carefFile.setCar(car.get());
                    } else {
                        throw new Exception("CAR Element not found with code : " + dto.getCarId());
                    }
                    String url = MvcUriComponentsBuilder
                            .fromMethodName(FilesResource.class, "getFile", filename).build().toString();
                    Files.copy(filePart.getInputStream(), this.root.resolve(filename));
                    carefFile.setFileName(filename);
                    carefFile.setUrl(url);
                    fileRepository.save(carefFile);

                }
                break;
                case GARAGE: {
                    Optional<Garage> garage = garageRepository.findById(dto.getGarageId());
                    if (garage.isPresent()) {
                        filename = "CAREF_MEDIA_" + dto.getEntity().name() + "_" + garage.get().getId() + "_" + Calendar.getInstance().getTimeInMillis() + "." + ext;
                        carefFile.setGarage(garage.get());
                    } else {
                        throw new Exception("GARAGE Element not found with code : " + dto.getCarId());
                    }
                    String url = MvcUriComponentsBuilder
                            .fromMethodName(FilesResource.class, "getFile", filename).build().toString();
                    Files.copy(filePart.getInputStream(), this.root.resolve(filename));
                    carefFile.setFileName(filename);
                    carefFile.setUrl(url);
                    fileRepository.save(carefFile);
                }
                break;
                case USER: {
                    Optional<User> user = userRepository.findById(dto.getUserId());
                    if (user.isPresent()) {
                        filename = "CAREF_MEDIA_" + dto.getEntity().name() + "_" + user.get().getId() + "_" + Calendar.getInstance().getTimeInMillis() + "." + ext;
                        carefFile.setUser(user.get());
                    } else {
                        throw new Exception("USER Element not found with code : " + dto.getCarId());
                    }
                    String url = MvcUriComponentsBuilder
                            .fromMethodName(FilesResource.class, "getFile", filename).build().toString();
                    Files.copy(filePart.getInputStream(), this.root.resolve(filename));
                    carefFile.setFileName(filename);
                    carefFile.setUrl(url);
                    fileRepository.save(carefFile);
                }
            }
        }catch(Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteOneFile() {
        List<CarefFile> files = fileRepository.findAll();
        FileSystemUtils.deleteRecursively(root.toFile());
        files.forEach(file -> fileRepository.delete(file));
    }

    @Override
    public void deleteOneFile(String filename) throws IOException {
        CarefFile file = fileRepository.findByFileName(filename);
        fileRepository.delete(file);
        Path oldFile = root.resolve(filename);
        FileSystemUtils.deleteRecursively(oldFile);
    }

    @Override
    public List<FileDto> findCarFile(Long carId) {
        return fileRepository.findByCar(carRepository.getOne(carId)).stream().map(buildFileDtoCollection()).collect(Collectors.toList());
    }

    private Function<CarefFile, FileDto> buildFileDtoCollection() {
        return carefFile -> buildFileDto(carefFile);
    }

    private FileDto buildFileDto(CarefFile carefFile) {
        if (Objects.nonNull(carefFile)) {
            return FileDto.FileDtoBuilder.aFileDto()
                    .withId(carefFile.getId())
                    .withFileName(carefFile.getFileName())
                    .withFileType(carefFile.getFileType())
                    .withUrl(carefFile.getUrl())
                    .withType(carefFile.getType())
                    .withCardId(Objects.nonNull(carefFile.getCar()) ? carefFile.getCar().getId() : null)
                    .withUserId(Objects.nonNull(carefFile.getUser()) ? carefFile.getUser().getId() : null)
                    .withGarageCode(Objects.nonNull(carefFile.getGarage()) ? carefFile.getGarage().getId() : null)
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public List<FileDto> findGarageFile(Long garageId) {
        return fileRepository.findByGarage(garageRepository.getOne(garageId)).stream().map(buildFileDtoCollection()).collect(Collectors.toList());
    }

    @Override
    public FileDto findUserFile(Long userId) {
        return buildFileDto(fileRepository.findByUserAndType(userRepository.getOne(userId), DocType.PROFILE_IMAGE));
    }

}

