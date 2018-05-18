package com.marco.virtualstore;

import com.marco.virtualstore.domains.Categoria;
import com.marco.virtualstore.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class VirtualStoreApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public static void main(String[] args) {
        SpringApplication.run(VirtualStoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoriaRepository.saveAll(Arrays.asList(new Categoria(null, "Informática"),
                                                        new Categoria(null, "Escritório")));

    }
}
