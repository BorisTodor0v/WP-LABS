package mk.finki.ukim.mk.lab;
// 191096 - Todorov Boris

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class B02Application {
	public static void main(String[] args) {
		SpringApplication.run(B02Application.class, args);
	}

}
