INSERT INTO app.user_role (role)
VALUES ('ADMIN'), ('USER');

INSERT INTO app.user_status (status)
VALUES ('ACTIVATED'), ('DEACTIVATED'), ('WAITING_ACTIVATION');

INSERT INTO app.user
VALUES ('128fbf51-9bdc-4a8e-ab59-2e73cad49551',
        '2023-02-22 17:08:10.346499+03',
        '2023-02-22 17:08:10.346499+03',
        'gandalfdude@gmail.com',
        '$2a$12$7IdHqs/DUYl6TL7AI/ClgOWICrFxQvbXlXOdorF8i0y4TLL2T0Eo2',
        'admin',
        1,
        1);