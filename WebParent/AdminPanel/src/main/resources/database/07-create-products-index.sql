--liquibase formatted SQL
--changeset DKorb:7

ALTER TABLE products ADD FULLTEXT(`name`, `full_description`);