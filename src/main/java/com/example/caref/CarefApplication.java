package com.example.caref;

import com.example.caref.files.services.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
@Slf4j
public class CarefApplication  implements CommandLineRunner {
	@Resource
	FileService storageService;
	public static void main(String[] args) {
		SpringApplication.run(CarefApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Initilize folder for uploarding file");
		/* storageService.deleteOneFile();
		storageService.init(); */
	}
}
