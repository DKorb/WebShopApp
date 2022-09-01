
<p align="center">
  <img src="https://user-images.githubusercontent.com/45924037/183071293-ac4e8765-cac5-40f1-b80c-d017d91f7b25.png" />
</p>

> Repository for practical part of an engineering thesis (design and implementation e-store management system).

## Table of contents
* [General info](#General-info)
* [Online version](#online-version)
* [Technologies](#Technologies)
* [Features](#Features)

## General info
The application is designed to manage an electronics online shop. It allows you to manage users, categories, brands, products, orders and customers. Two panels are available. In admin panel you can manage users, categories, brands, customers, orders, products and view detailed data. Customers can browse products on the homepage and go to explore specific products through categories. By clicking on a product, customers can see its details and add it to their shopping cart. Option to register new consumers is also available. 

**The application was developed based on multi-module MVC architecture.**

## Online version (Heroku)
```sh
coming soon :)
```

|Role|E-mail|Password|
|:---|:---|:---:|
|Admin|admin.admin@java.com|Pa$$w0rd!|

## Technologies
* Java 11
* Spring Boot 5
* Spring Security 5
* Thymeleaf
* Maven
* Tomcat
* MySQL
* Liquibase
* JUnit
* Lombok
* DevTools
* Bootstrap 5 (Material Design)
* JQuery
* HTML, CSS

## Features

<p align="center">
  <img src="https://user-images.githubusercontent.com/45924037/183072964-28f812eb-4f0d-467b-8c71-98a3292d73ba.png" />
</p>
<b>Admin module:</b>

* Users management - User overview (set first name, last name, e-mail, password, role, status), possibility to create user, edit data, delete user.
* Categories management - Category overview (set name, alias, image, status), possibility to create category, edit data, delete category.
* Brands management - Brand overview (set name, logo, categories), possibility to create brand, edit data, delete brand.
* Products management - Product overview (set name, category, price, stock, status, description (richtext), image, details) possibility to create product, edit data, delete product.
* Customers management - Customers overview (set first name, last name, e-mail, password, phone number, address, city), possibility to edit data, delete customer.
* Orders managment - Orders can be overview in admin module where you can change order status and delivery date.

<p align="center">
  <img src="https://user-images.githubusercontent.com/45924037/183072996-e52925c3-87b2-4992-acff-49aeb4f2a078.png" />
</p>
<b>Customer module:</b>

* Browsing products.
* Viewing product details.
* Adding products to shopping cart and placing orders for specific products.
* Register new customer.
