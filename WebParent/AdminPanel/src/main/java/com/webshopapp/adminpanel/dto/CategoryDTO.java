package com.webshopapp.adminpanel.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {

    private Integer id;

    private String name;

    public CategoryDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}