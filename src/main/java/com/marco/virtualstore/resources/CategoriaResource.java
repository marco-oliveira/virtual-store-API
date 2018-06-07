package com.marco.virtualstore.resources;

import com.marco.virtualstore.domains.Categoria;
import com.marco.virtualstore.dtos.CategoriaDto;
import com.marco.virtualstore.services.CategoriaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Marco Antônio on 18/05/2018
 */

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    /**
     * Busca categoria por id, e lista seus produtos
     *
     * @param id
     * @return Categoria no formato Json
     */
    @ApiOperation(value="Busca por id")
    @GetMapping("{id}")
    public ResponseEntity<Categoria> find(@PathVariable Long id){

        Categoria categoria = this.categoriaService.find(id);
        return ResponseEntity.ok(categoria);
    }

    /**
     * Não tem o corpo como resposta, apenas a URI no cabeçalho --> Boa prática REST
     *
     * @param categoriaDto
     * @return 201
     */
    @ApiOperation(value="Insere Categoria")
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDto categoriaDto){
        Categoria categoria = this.categoriaService.fromDto(categoriaDto);
        categoria = this.categoriaService.insert(categoria);
        //Boa prática uri no cabeçalho da resposta 201
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Atualiza Categoria
     *
     * @param id
     * @param categoriaDto
     * @return 204
     */
    @ApiOperation(value="Atualiza Categoria")
    @PutMapping("{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDto categoriaDto, @PathVariable Long id){

        Categoria categoria = this.categoriaService.fromDto(categoriaDto);
        categoria.setId(id);

        this.categoriaService.update(categoria);

        return ResponseEntity.noContent().build();
    }

    /**
     * Apaga uma categoria, erro interceptado por ResourceExceptionHandrer
     *
     * @param id
     * @return 204
     */
    @ApiOperation(value="Remove Categoria")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Não é possível excluir uma categoria que possui produtos"),
            @ApiResponse(code = 404, message = "Código inexistente") })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }


    /**
     * Lista todas as categorias padronizadas com DTO
     *
     * @return ResponseEntity<List<CategoriaDto>>
     */
    @ApiOperation(value="Retorna Todas Categorias")
    @GetMapping
    public ResponseEntity<List<CategoriaDto>> findAll(){

        List<Categoria> categorias = this.categoriaService.findAll();
        List<CategoriaDto> listDto = categorias.stream().map(categoria ->
                new CategoriaDto(categoria)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    /**
     * Retorna uma lista de Categorias paginada
     *
     * @param page
     * @param size
     * @param direction
     * @param orderBy
     * @return ResponseEntity<Page<CategoriaDto>>
     */
    @ApiOperation(value="Retorna Todas Categorias Paginadas")
    @GetMapping("page")
    public ResponseEntity<Page<CategoriaDto>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "24") Integer size,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
    ){
        Page<Categoria> categorias = this.categoriaService.findPage(page, size, direction, orderBy);
        Page<CategoriaDto> pageListDto = categorias.map(categoria -> new CategoriaDto(categoria));

        return ResponseEntity.ok(pageListDto);
    }

}
