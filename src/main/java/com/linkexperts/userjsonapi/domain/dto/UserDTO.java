package com.linkexperts.userjsonapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String nameDTO;
    private String usernameDTO;
    private String emailDTO;
    private String phoneDTO;
    private String websiteDTO;
}

