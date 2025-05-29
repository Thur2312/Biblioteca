package com.Biblioteca.APP.Biblioteca.Manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Biblioteca.APP.Biblioteca.Manager.controller.UserController;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
		UserController userController = new UserController();
        userController.iniciar();
	}

}
