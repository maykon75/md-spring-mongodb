package com.database.mongo.project.infrastructure.repository;

import com.database.mongo.project.api.dto.CustomerRequestDTO;
import com.database.mongo.project.api.dto.CustomerResponseDTO;

import java.util.List;

public interface ICustomer {

    CustomerResponseDTO get(String id);

    List<CustomerResponseDTO> getAll();

    void post(CustomerRequestDTO requestDto);

    void delete(String id);

    void update(String id, CustomerRequestDTO request);
}
