package com.example.caref.controllers;

import com.example.caref.models.Car;
import com.example.caref.models.Image;
import com.example.caref.repository.CarRepository;
import com.example.caref.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path="/api/test/image/")
public class ImageController {
    public static String uploadDirectory = System.getProperty("user.dir")+"/uploads/";

    @Autowired
    ImageRepository imageRepository;

    // @PathVariable(value = "id") Long carId

    @Autowired
    CarRepository carRepository;

    @PostMapping("/upload/{carId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadImage(@RequestParam("imageFile") MultipartFile[] files,
                            @PathVariable Long carId) {

        Image image = new Image();
        StringBuilder fileNames = new StringBuilder();
        for (MultipartFile file : files) {
            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename() + " ");
            try {
                Image img = new Image(file.getOriginalFilename(),
                        file.getContentType(),compressBytes(file.getBytes()));
                img.setName(fileNames.toString());
                img.setPicByte(file);
                img.setPath(fileNameAndPath.toString());
                Files.write(fileNameAndPath, file.getBytes(), StandardOpenOption.CREATE);
                Car car = carRepository.findById(carId).orElseThrow(Exception::new);
                img.setCar(car);

                img = imageRepository.save(img);
            }
            catch (IOException e){
                e.fillInStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @GetMapping("/images")
    public List<Image> getAllImages() {
        return imageRepository.findAll(); }

    @GetMapping ("/get/{imageName}")
    public Image getImage(@PathVariable("imageName") String imageName)
            throws IOException {
        final Optional<Image> retrievedImage = imageRepository.findByName(imageName);
        Image img = new Image(retrievedImage.get().getName(),
                retrievedImage.get().getType(),
                decompressBytes(retrievedImage.get().getPicByte()));
        return img;
    }

    // Compress the image bytes before storing it in the database

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Sizee -" +
                outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application

    public static byte[] decompressBytes(MultipartFile data) {
        Inflater inflater = new Inflater();
        //inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0 , count);
            }
            outputStream.close();
        }catch (IOException ioe) {
        }catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}

