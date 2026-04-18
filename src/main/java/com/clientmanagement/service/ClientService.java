package com.clientmanagement.service;

import com.clientmanagement.dto.request.ClientRequestDto;
import com.clientmanagement.dto.response.ClientResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {

    ClientResponseDto createClient(ClientRequestDto requestDto);

    ClientResponseDto updateClient(String id, ClientRequestDto requestDto);

    ClientResponseDto getClientById(String id);

    Page<ClientResponseDto> getAllClients(Pageable pageable);

    void deleteClient(String id);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}