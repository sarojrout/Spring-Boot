package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
import java.util.Collection;

@SpringBootApplication
public class BookingServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(BookingServiceApplication.class, args);
	}
}
//@RepositoryRestResource
//interface BookingRepository extends JpaRepository<Booking, Long> {
//	@RestResource(path="by-name")
//	Collection<Booking> findByBookingName(@Param("bn") String bn);
//}
//@Entity
//class Booking{
//
//	@GeneratedValue
//	private long id;
//	private String name;
//
//	public Booking(String name) {
//		this.name = name;
//	}
//
//	public Booking() {
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//}