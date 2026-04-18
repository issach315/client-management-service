package com.clientmanagement.service;

import com.clientmanagement.dto.request.ClientFilterParams;
import com.clientmanagement.dto.request.ClientRequestDto;
import com.clientmanagement.dto.response.ClientResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    Page<ClientResponseDto> searchClients(ClientFilterParams filters, Pageable pageable);

    ClientResponseDto createClient(ClientRequestDto requestDto);

    ClientResponseDto updateClient(String id, ClientRequestDto requestDto);

    ClientResponseDto getClientById(String id);

    void deleteClient(String id);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}