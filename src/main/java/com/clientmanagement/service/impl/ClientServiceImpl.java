package com.clientmanagement.service.impl;

import com.clientmanagement.dto.request.ClientRequestDto;
import com.clientmanagement.dto.response.ClientResponseDto;
import com.clientmanagement.entity.Client;
import com.clientmanagement.exception.DuplicateResourceException;
import com.clientmanagement.exception.ResourceNotFoundException;
import com.clientmanagement.mapper.ClientMapper;
import com.clientmanagement.repository.ClientRepository;
import com.clientmanagement.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository,
                             ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientResponseDto createClient(ClientRequestDto requestDto) {

        if (clientRepository.existsByEmail(requestDto.getEmail())) {
            throw new DuplicateResourceException("Email already exists: " + requestDto.getEmail());
        }

        if (clientRepository.existsByPhoneNumber(requestDto.getPhoneNumber())) {
            throw new DuplicateResourceException("Phone number already exists: " + requestDto.getPhoneNumber());
        }

        Client client = clientMapper.toEntity(requestDto);
        Client savedClient = clientRepository.save(client);

        return clientMapper.toResponseDto(savedClient);
    }

    @Override
    public ClientResponseDto updateClient(String id, ClientRequestDto requestDto) {

        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client not found with id: " + id));

        clientMapper.updateEntityFromDto(requestDto, existingClient);

        Client updatedClient = clientRepository.save(existingClient);

        return clientMapper.toResponseDto(updatedClient);
    }

    @Override
    public ClientResponseDto getClientById(String id) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client not found with id: " + id));

        return clientMapper.toResponseDto(client);
    }

    @Override
    public Page<ClientResponseDto> getAllClients(Pageable pageable) {

        Page<Client> clientsPage = clientRepository.findAll(pageable);

        return clientsPage.map(clientMapper::toResponseDto);
    }

    @Override
    public void deleteClient(String id) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client not found with id: " + id));

        clientRepository.delete(client);
    }

    @Override
    public boolean existsByEmail(String email) {
        return clientRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return clientRepository.existsByPhoneNumber(phoneNumber);
    }
}