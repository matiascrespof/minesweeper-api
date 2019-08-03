package com.minesweeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.minesweeper.repository.MinesweeperRepository;
import com.minesweeper.repository.MinesweeperRepositoryImpl;

@SpringBootApplication
public class MinesweeperServiceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MinesweeperServiceApplication.class, args);
	}

	@Bean
	public MinesweeperRepository minesweeperRepository() {
		return new MinesweeperRepositoryImpl();
	}
}
