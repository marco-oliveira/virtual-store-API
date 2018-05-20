package com.marco.virtualstore;

import com.marco.virtualstore.domains.*;
import com.marco.virtualstore.domains.enums.TipoCliente;
import com.marco.virtualstore.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class VirtualStoreApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public static void main(String[] args) {
        SpringApplication.run(VirtualStoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");

        Produto p1 = new Produto(null, "Computado", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2));

        this.produtoRepository.saveAll(Arrays.asList(p1,p2,p3));


        Estado est1 = new Estado(null, "Minas Gerais");

        Cidade c1 = new Cidade(null, "Uberlândia",est1);

        this.estadoRepository.saveAll(Arrays.asList(est1));
        est1.getCidades().addAll(Arrays.asList(c1));

        this.cidadeRepository.saveAll(Arrays.asList(c1));

        Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com",
                "36378912377", TipoCliente.PESSOAFISICA);

        cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

        this.clienteRepository.save(cli1);
        Endereco e1 = new Endereco(null, "Rua Flores", "300",
                "Apto 203", "Jardim", "38220834", c1, cli1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105",
                "Sala 800", "Centro", "38777012", c1, cli1);


        this.enderecoRepository.saveAll(Arrays.asList(e1, e2));


    }
}
