package br.com.irdbr.springbootionic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.irdbr.springbootionic.services.S3Service;

@SpringBootApplication
public class SpringBootIonicApplication implements CommandLineRunner {
	
	@Autowired
	private S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Teste de envio de imagem
		//s3Service.uploadFile("C:\\temp\\fotos\\banana.jpg");
	}

}
