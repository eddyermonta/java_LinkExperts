package com.linkexperts.userjsonapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Company {
    private String name;
    private String catchPhrase;
    private String bs;
}
