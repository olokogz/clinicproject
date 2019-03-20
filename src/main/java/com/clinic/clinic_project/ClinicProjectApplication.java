package com.clinic.clinic_project;

import com.clinic.clinic_project.controller.CarRepository;
import com.clinic.clinic_project.model.Car;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class ClinicProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicProjectApplication.class, args);
	}

	@Bean
	ApplicationRunner init(CarRepository repository)
	{
		return args -> {
			Stream.of("Ferrari", "Jaguar", "Porsche", "Lamborghini", "Bugatti",
					"AMC Gremlin", "Triumph Stag", "Ford Pinto", "Yugo GV").forEach(name->{
						Car car = new Car();
						car.setName(name);
						repository.save(car);
			});
			repository.findAll().forEach(System.out::println);
		};
	}
}
