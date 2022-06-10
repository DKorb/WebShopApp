# Webshop management application
> Repository for practical part of an engineering thesis (design and implementation e-store management system).

## Table of contents
* [General info](#General-info)
* [Online version](#online-version)
* [Technologies](#Technologies)
* [Features](#Features)

## General info
The application is designed to manage an electronics online shop. It allows you to manage users, categories, brands, products and all data. Two panels are available, for administrators and customers. As an administrator you can manage users, categories, brands, products and view detailed data. Customers can browse products on the homepage and go to explore specific products through categories. By clicking on a product, customers can see its details and add it to their shopping cart.

**The application was developed based on multi-module MVC architecture.**

## Online version (Heroku)
```sh
coming soon :)
```
Default administration account -
Email: dawid.korbecki@java.com Password: Pa$$w0rd!

## Technologies
* Java 11
* Spring Boot 5
* Spring Security 5
* Thymeleaf
* Maven
* Tomcat
* MySQL
* JUnit
* Lombok
* DevTools
* Bootstrap 5 (Material Design)
* JQuery
* HTML, CSS

## Features
### Administration panel:
* Users Management - User overview (set first name, last name, e-mail, password, role, status), possibility to create user, edit data, delete user.
* Categories Management - Category overview (set name, alias, image, status), possibility to create category, edit data, delete category.
* Brands Management - Brand overview (set name, logo, categories), possibility to create brand, edit data, delete brand.
* Products Management - Product overview (set name, category, price, stock, status, description (richtext), image, details) possibility to create product, edit data, delete product.
### Customer panel:
* Browsing products, viewing product details, adding to shopping cart.
