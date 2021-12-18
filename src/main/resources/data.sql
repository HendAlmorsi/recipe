INSERT IGNORE INTO db_test.role (id, name) VALUES (1, 'USER');
INSERT IGNORE INTO db_test.role (id, name) VALUES (6, 'ADMIN');

-- Password 1234
-- USER
INSERT IGNORE INTO db_test.users (id, email, first_name, last_name, password) VALUES (5, 'test5@test.com', 'bebe', 'Khalifa', '$2a$10$sWdIzKUpmaUBLG/6Xm0qdO1jidcwmg18QqYCz1J4OrRkuPe6V9v56');
-- USER, ADMIN
INSERT IGNORE INTO db_test.users (id, email, first_name, last_name, password) VALUES (6, 'test7@test.com', 'bebe', 'Khalifa', '$2a$10$9EiVBMO82ksyjgo8LooNmOMtDJPMmJ40QNJEtHB/CaNlBjGOA7b0u');

INSERT IGNORE INTO db_test.user_role (user_id, role_id) VALUES (5, 1);
INSERT IGNORE INTO db_test.user_role (user_id, role_id) VALUES (6, 1);
INSERT IGNORE INTO db_test.user_role (user_id, role_id) VALUES (6, 6);