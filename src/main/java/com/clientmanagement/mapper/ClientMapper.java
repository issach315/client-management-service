package com.clientmanagement.mapper;

import com.clientmanagement.dto.request.ClientRequestDto;
import com.clientmanagement.dto.response.ClientResponseDto;
import com.clientmanagement.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientRequestDto requestDto);

    ClientResponseDto toResponseDto(Client client);

    List<ClientResponseDto> toResponseDtoList(List<Client> clients);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(ClientRequestDto requestDto, @MappingTarget Client client);
}