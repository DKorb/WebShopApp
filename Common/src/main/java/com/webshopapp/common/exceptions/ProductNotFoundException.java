package com.webshopapp.common.exceptions;

public class ProductNotFoundException extends Throwable {
    public ProductNotFoundException(String message) {
        super(message);
    }
}