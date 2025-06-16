package com.database.mongo.project.api.controller;

import com.database.mongo.project.api.dto.CustomerRequestDTO;
import com.database.mongo.project.api.dto.CustomerResponseDTO;
import com.database.mongo.project.domain.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/mongodb")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public ResponseEntity<List<CustomerResponseDTO>> getAll(){
        return ResponseEntity.ok().body(customerService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerResponseDTO> findById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(customerService.get(id));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> post(@RequestBody CustomerRequestDTO customerRequestDTO){
        customerService.post(customerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Void> update(@PathVariable(value = "id") String id, @RequestBody CustomerRequestDTO customerRequestDTO){
        customerService.update(id, customerRequestDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") String id) {
        customerService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
