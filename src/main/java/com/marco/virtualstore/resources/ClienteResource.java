package com.marco.virtualstore.resources;

import com.marco.virtualstore.domains.Cliente;
import com.marco.virtualstore.dtos.ClienteDto;
import com.marco.virtualstore.dtos.NewClienteDto;
import com.marco.virtualstore.services.ClienteService;
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
 * Created by Marco Antônio on 19/05/2018
 */
@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @ApiOperation(value="Busca Cliente por Id")
    @GetMapping("{id}")
    public ResponseEntity<Cliente> find(@PathVariable Long id){
        Cliente cliente = this.clienteService.find(id);

        return ResponseEntity.ok(cliente);
    }

    @ApiOperation(value="Insere Cliente")
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody NewClienteDto newClienteDto){
        Cliente cliente = this.clienteService.fromDto(newClienteDto);
        cliente = this.clienteService.insert(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value="Atualiza Cliente")
    @PutMapping("{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDto clienteDto, @PathVariable Long id){
        Cliente cliente = this.clienteService.fromDto(clienteDto);
        cliente.setId(id);
        this.clienteService.update(cliente);

        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value="Remove Cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Não é possível excluir uma cliente que possui pedidos"),
            @ApiResponse(code = 404, message = "Código inexistente") })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.clienteService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @ApiOperation(value="Retorna Todos Clientes")
    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll(){
        List<Cliente> clientes = this.clienteService.findAll();
        List<ClienteDto> listDto = clientes.stream().map(cliente -> new ClienteDto(cliente)).collect(Collectors.toList());
        return ResponseEntity.ok(listDto);
    }

    @ApiOperation(value="Retorna Todos Clientes Paginados")
    @GetMapping("page")
    public ResponseEntity<Page<ClienteDto>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "24") Integer size,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
    ){
        Page<Cliente> clientes = this.clienteService.findPage(page, size, direction, orderBy);
        Page<ClienteDto> pageClientes = clientes.map(cliente -> new ClienteDto(cliente));
        return ResponseEntity.ok(pageClientes);
    }
}

