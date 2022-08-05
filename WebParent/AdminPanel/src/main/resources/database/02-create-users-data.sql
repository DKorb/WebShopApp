--liquibase formatted SQL
--changeset DKorb:2

INSERT INTO `users` VALUES (1,'admin.admin@java.com','Dawid','Korbecki','$2a$10$IvDvFQg99hg0uJl6B0pxNeFD4YdyaBNRzZwK3PfXTRy3TsmQ85xiq',1);
INSERT INTO `users` VALUES (2,'jan.kowalski@java.com','Jan','Kowalski','$2a$10$IvDvFQg99hg0uJl6B0pxNeFD4YdyaBNRzZwK3PfXTRy3TsmQ85xiq',0);
INSERT INTO `users` VALUES (3,'pawel.nowak@java.com','Pawel','Nowak','$2a$10$IvDvFQg99hg0uJl6B0pxNeFD4YdyaBNRzZwK3PfXTRy3TsmQ85xiq', 1);
INSERT INTO `users` VALUES (4,'marcin.kaczmarek@java.com','Marcin','Kaczmarek','$2a$10$IvDvFQg99hg0uJl6B0pxNeFD4YdyaBNRzZwK3PfXTRy3TsmQ85xiq',1);
INSERT INTO `users` VALUES (5,'wojtek.hajto@java.com','Wojtek','Hajto','$2a$10$IvDvFQg99hg0uJl6B0pxNeFD4YdyaBNRzZwK3PfXTRy3TsmQ85xiq',1);
INSERT INTO `users` VALUES (6,'maciek.kowal@java.com','Maciek','Kowal','$2a$10$IvDvFQg99hg0uJl6B0pxNeFD4YdyaBNRzZwK3PfXTRy3TsmQ85xiq',0);
INSERT INTO `users` VALUES (7,'kamil.dlugosz@java.com','Kamil','Długosz','$2a$10$IvDvFQg99hg0uJl6B0pxNeFD4YdyaBNRzZwK3PfXTRy3TsmQ85xiq',0);
INSERT INTO `users` VALUES (8,'robert.wolski@java.com','Robert','Wolski','$2a$10$IvDvFQg99hg0uJl6B0pxNeFD4YdyaBNRzZwK3PfXTRy3TsmQ85xiq',1);
INSERT INTO `users` VALUES (9,'jakub.polak@java.com','Jakub','Polak','$2a$10$IvDvFQg99hg0uJl6B0pxNeFD4YdyaBNRzZwK3PfXTRy3TsmQ85xiq',0);
INSERT INTO `users` VALUES (10,'wiktor.sciana@java.com','Wiktor','Ściana','$2a$10$IvDvFQg99hg0uJl6B0pxNeFD4YdyaBNRzZwK3PfXTRy3TsmQ85xiq',1);
INSERT INTO `users` VALUES (11,'olga.brzoza@java.com','Olga','Brzoza','$2a$10$IvDvFQg99hg0uJl6B0pxNeFD4YdyaBNRzZwK3PfXTRy3TsmQ85xiq',1);
INSERT INTO `users` VALUES (12,'marian.kowalski@java.com','Marian','Kowalski','$2a$10$IvDvFQg99hg0uJl6B0pxNeFD4YdyaBNRzZwK3PfXTRy3TsmQ85xiq',0);
INSERT INTO `users` VALUES (13,'michał.nowak@java.com','Michał','Nowak','$2a$10$IvDvFQg99hg0uJl6B0pxNeFD4YdyaBNRzZwK3PfXTRy3TsmQ85xiq',1);
INSERT INTO `users` VALUES (14,'bartosz.kaczmarek@java.com','Bartosz','Kaczmarek','$2a$10$IvDvFQg99hg0uJl6B0pxNeFD4YdyaBNRzZwK3PfXTRy3TsmQ85xiq',1);
INSERT INTO `users` VALUES (15,'zofia.hajto@java.com','Zofia','Hajto','$2a$10$IvDvFQg99hg0uJl6B0pxNeFD4YdyaBNRzZwK3PfXTRy3TsmQ85xiq',0);
INSERT INTO `users` VALUES (16,'mariola.kowal@java.com','Mariola','Kowal','$2a$10$IvDvFQg99hg0uJl6B0pxNeFD4YdyaBNRzZwK3PfXTRy3TsmQ85xiq',1);
INSERT INTO `users` VALUES (17,'tadeusz.dlugosz@java.com','Tadeusz','Długosz','$2a$10$IvDvFQg99hg0uJl6B0pxNeFD4YdyaBNRzZwK3PfXTRy3TsmQ85xiq',0);
INSERT INTO `users` VALUES (18,'wojciech.wolski@java.com','Wojciech','Wolski','$2a$10$IvDvFQg99hg0uJl6B0pxNeFD4YdyaBNRzZwK3PfXTRy3TsmQ85xiq',1);
INSERT INTO `users` VALUES (19,'szymon.polak@java.com','Szymon','Polak','$2a$10$IvDvFQg99hg0uJl6B0pxNeFD4YdyaBNRzZwK3PfXTRy3TsmQ85xiq',1);
INSERT INTO `users` VALUES (20,'piotr.sciana@java.com','Piotr','Ściana','$2a$10$IvDvFQg99hg0uJl6B0pxNeFD4YdyaBNRzZwK3PfXTRy3TsmQ85xiq',0);
INSERT INTO `users` VALUES (24,'maciek.laurman@java.com','Maciek','Laurman','$2a$10$IvDvFQg99hg0uJl6B0pxNeFD4YdyaBNRzZwK3PfXTRy3TsmQ85xiq',1);

INSERT INTO `roles` VALUES (1, 'Supplier');
INSERT INTO `roles` VALUES (2, 'Admin');

INSERT INTO `users_roles` VALUES (1,1);
INSERT INTO `users_roles` VALUES (4,1);
INSERT INTO `users_roles` VALUES (5,1);
INSERT INTO `users_roles` VALUES (6,1);
INSERT INTO `users_roles` VALUES (7,1);
INSERT INTO `users_roles` VALUES (8,1);
INSERT INTO `users_roles` VALUES (9,1);
INSERT INTO `users_roles` VALUES (10,1);
INSERT INTO `users_roles` VALUES (11,1);
INSERT INTO `users_roles` VALUES (14,1);
INSERT INTO `users_roles` VALUES (15,1);
INSERT INTO `users_roles` VALUES (16,1);
INSERT INTO `users_roles` VALUES (17,1);
INSERT INTO `users_roles` VALUES (18,1);
INSERT INTO `users_roles` VALUES (19,1);
INSERT INTO `users_roles` VALUES (20,1);
INSERT INTO `users_roles` VALUES (24,1);
INSERT INTO `users_roles` VALUES (1,2);
INSERT INTO `users_roles` VALUES (2,2);
INSERT INTO `users_roles` VALUES (3,2);
INSERT INTO `users_roles` VALUES (11,2);
INSERT INTO `users_roles` VALUES (12,2);
INSERT INTO `users_roles` VALUES (13,2);