package com.ristorante.beristorante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@SpringBootApplication
public class BeRistoranteApplication {

	private static final Logger logger = LogManager.getLogger(BeRistoranteApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BeRistoranteApplication.class, args);
		/*for (int i = 0; i < 10000; i++) {
			logger.info("Messaggio di prova per il LOG");	
		}*/
	}

}
