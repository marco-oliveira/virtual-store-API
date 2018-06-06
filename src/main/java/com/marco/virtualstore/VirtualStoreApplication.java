package com.marco.virtualstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class VirtualStoreApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(VirtualStoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {}
}
