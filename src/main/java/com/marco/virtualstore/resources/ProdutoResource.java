package com.marco.virtualstore.resources;

import com.marco.virtualstore.domains.Produto;
import com.marco.virtualstore.dtos.ProdutoDto;
import com.marco.virtualstore.resources.utils.URL;
import com.marco.virtualstore.services.ProdutoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @ApiOperation(value="Busca por Id")
    @GetMapping("{id}")
    public ResponseEntity<Produto> find(@PathVariable Long id){
        Produto produto = this.produtoService.find(id);
        return ResponseEntity.ok(produto);
    }

    @ApiOperation(value="Retorna Produtos Paginados")
    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> findPage(
            @RequestParam(name = "nome", defaultValue = "") String nome,
            @RequestParam(name = "categorias", defaultValue = "") String categorias,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "24") Integer size,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "orderBy", defaultValue = "nome") String orderBy){

        String nomeDecode = URL.decodeParam(nome);
        List<Long> ids = URL.decodeLongList(categorias);
        Page<Produto> produtos = this.produtoService.search(nomeDecode, ids, page, size, direction, orderBy);
        Page<ProdutoDto> listDto = produtos.map(produto -> new ProdutoDto(produto));
        return ResponseEntity.ok().body(listDto);
    }
}
