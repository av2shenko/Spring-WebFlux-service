package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SpringApplication.run(DemoApplication.class, args);
		JDBConnectivity jdbConnectivity = new JDBConnectivity();
		jdbConnectivity.ConnectDB();
		FileHandler fileHandler = new FileHandler();
		fileHandler.CreateFile();
	}

}
