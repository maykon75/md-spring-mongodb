package com.database.mongo.project.domain.service;

import com.database.mongo.project.api.dto.CustomerRequestDTO;
import com.database.mongo.project.api.dto.CustomerResponseDTO;
import com.database.mongo.project.api.mapper.CustomerMapper;
import com.database.mongo.project.domain.model.Customer;
import com.database.mongo.project.exception.NotFoundException;
import com.database.mongo.project.infrastructure.repository.CustomerRepository;
import com.database.mongo.project.infrastructure.repository.ICustomer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomer {

    private final CustomerRepository repository;

    private final CustomerMapper mapper;

    public CustomerService(final CustomerRepository repository, final CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public CustomerResponseDTO get(String id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, "Id not found"));
        return mapper.customerResponseDTO(customer);
    }

    @Override
    public List<CustomerResponseDTO> getAll() {
        List<CustomerResponseDTO> listCustomer = mapper.customerResponseDtoList(repository.findAll());
        if(listCustomer.isEmpty()){
            throw new NotFoundException(null, "No customers found");
        }
        return listCustomer;
    }

    @Override
    public void post(CustomerRequestDTO requestDto) {
        repository.save(mapper.customer(requestDto));
    }

    @Override
    public void delete(String id) {
        if(!repository.existsById(id)){
            throw new NotFoundException(id, "Id does not exist");
        }
        repository.deleteById(id);
    }

    @Override
    public void update(String id, CustomerRequestDTO request) {
        if(!repository.existsById(id)){
            throw new NotFoundException(id, "Id not found");
        }
        Customer person = mapper.customer(request);
        person.setId(id);
        repository.save(person);
    }
}
