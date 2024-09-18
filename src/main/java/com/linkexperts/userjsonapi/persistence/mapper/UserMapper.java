package com.linkexperts.userjsonapi.persistence.mapper;

import com.linkexperts.userjsonapi.domain.dto.UserDTO;
import com.linkexperts.userjsonapi.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "name", target = "nameDTO")
    @Mapping(source = "username", target = "usernameDTO")
    @Mapping(source = "email", target = "emailDTO")
    @Mapping(source = "phone", target = "phoneDTO")
    @Mapping(source = "website", target = "websiteDTO")
    UserDTO toDTO(User user);

    @Mapping(source = "nameDTO", target = "name")
    @Mapping(source = "usernameDTO", target = "username")
    @Mapping(source = "emailDTO", target = "email")
    @Mapping(source = "phoneDTO", target = "phone")
    @Mapping(source = "websiteDTO", target = "website")
    User toUser(UserDTO userDTO);
}
