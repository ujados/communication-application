package com.connecivity.comunication;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ComunicationApplication {
  private ComunicationApplication(){
    //Sonar not work propertly with java 25 and not uses public static void main
  }

  static void main(String[] args) {
    Dotenv dotenv = Dotenv.load();
    dotenv.entries()
      .forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

    SpringApplication.run(ComunicationApplication.class, args);
  }

}
