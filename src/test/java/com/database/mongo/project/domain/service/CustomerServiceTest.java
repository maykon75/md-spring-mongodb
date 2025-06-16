package com.database.mongo.project.domain.service;

import com.database.mongo.project.api.dto.CustomerRequestDTO;
import com.database.mongo.project.api.dto.CustomerResponseDTO;
import com.database.mongo.project.api.mapper.CustomerMapper;
import com.database.mongo.project.domain.model.Customer;
import com.database.mongo.project.exception.NotFoundException;
import com.database.mongo.project.infrastructure.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerMapper mapper;

    @InjectMocks
    private CustomerService service;

    private Customer customer;
    private CustomerRequestDTO requestDTO;
    private CustomerResponseDTO responseDTO;

    @BeforeEach
    void setUp() {

        customer = new Customer();
        customer.setId("1");
        customer.setName("test");

        requestDTO = new CustomerRequestDTO();
        requestDTO.setName("test");

        responseDTO = new CustomerResponseDTO();
        responseDTO.setId("1");
        responseDTO.setName("test");
    }

    @Test
    void testGetSuccess() {
        when(repository.findById("1")).thenReturn(Optional.of(customer));
        when(mapper.customerResponseDTO(customer)).thenReturn(responseDTO);

        CustomerResponseDTO result = service.get("1");

        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("test", result.getName());
    }

    @Test
    void testGetAllSuccess() {
        when(repository.findAll()).thenReturn(List.of(customer));
        when(mapper.customerResponseDtoList(List.of(customer))).thenReturn(List.of(responseDTO));

        List<CustomerResponseDTO> result = service.getAll();

        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getId());

    }

    @Test
    void testGetAllEmpty() {
        when(repository.findAll()).thenReturn(List.of());
        when(mapper.customerResponseDtoList(List.of())).thenReturn(List.of());

        assertThrows(NotFoundException.class, () -> service.getAll());

    }

    @Test
    void testPost() {
        Customer entity = new Customer();
        when(mapper.customer(requestDTO)).thenReturn(entity);

        service.post(requestDTO);

        verify(repository).save(entity);
        verify(mapper).customer(requestDTO);
    }

    @Test
    void testDeleteSuccess() {
        when(repository.existsById("1")).thenReturn(true);

        service.delete("1");

        verify(repository).existsById("1");
        verify(repository).deleteById("1");
    }

    @Test
    void testDeleteNotFoundException() {
        when(repository.existsById("1")).thenReturn(false);

        assertThrows(NotFoundException.class, () -> service.delete("1"));

        verify(repository).existsById("1");
        verify(repository, never()).deleteById(any());
    }

    @Test
    void testUpdateSuccess() {
        when(repository.existsById("1")).thenReturn(true);
        Customer entity = new Customer();
        when(mapper.customer(requestDTO)).thenReturn(entity);

        service.update("1", requestDTO);

        verify(repository).existsById("1");
        verify(repository).save(entity);
        verify(mapper).customer(requestDTO);
    }

    @Test
    void testUpdateNotFoundException() {
        when(repository.existsById("1")).thenReturn(false);

        assertThrows(NotFoundException.class, () -> service.update("1", requestDTO));
    }
}
