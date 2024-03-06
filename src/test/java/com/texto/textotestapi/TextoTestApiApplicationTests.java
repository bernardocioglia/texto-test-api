package com.texto.textotestapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import com.texto.textotestapi.components.CSVCommandLineRunner;

@ActiveProfiles("test")
@SpringBootTest
class TextoTestApiApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	void runnersNotLoaded() {
		Assertions.assertThrows(NoSuchBeanDefinitionException.class,
				() -> this.context.getBean(CSVCommandLineRunner.class),
				"CommandLineRunner should not be loaded during this integration test");
	}
}
