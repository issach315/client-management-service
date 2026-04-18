package com.clientmanagement.controller;

import com.clientmanagement.dto.request.ClientRequestDto;
import com.clientmanagement.dto.response.ClientResponseDto;
import com.clientmanagement.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientResponseDto> createClient(
            @RequestBody ClientRequestDto requestDto) {

        ClientResponseDto response =
                clientService.createClient(requestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> getClientById(
            @PathVariable String id) {

        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ClientResponseDto>> getAllClients(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        if (pageable.getPageSize() > 100) {
            throw new IllegalArgumentException("Page size cannot exceed 100");
        }

        return ResponseEntity.ok(clientService.getAllClients(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDto> updateClient(
            @PathVariable String id,
            @RequestBody ClientRequestDto requestDto) {

        return ResponseEntity.ok(
                clientService.updateClient(id, requestDto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(
            @PathVariable String id) {

        clientService.deleteClient(id);

        return ResponseEntity.ok("Client deleted successfully");
    }
}