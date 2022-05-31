package com.example.demo;

import com.example.demo.service.InterpreterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Lab4Application {

	private static Logger LOG = LoggerFactory.getLogger(Lab4Application.class);
	private static InterpreterService interpreterService;

	@Autowired
	public Lab4Application(InterpreterService interpreterService){
		Lab4Application.interpreterService = interpreterService;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		SpringApplication.run(Lab4Application.class, args);
		String input;
		while (true){
			input = scanner.next();
			try {
				interpreterService.interpret(input);
			} catch (Exception e) {
				System.out.println(e.getMessage());;
				System.exit(1);
			}
		}

	}

}
