CREATE SCHEMA IF NOT EXISTS app;

CREATE TABLE IF NOT EXISTS app.user (
    id UUID,
    creation_time TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    last_updated TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    mail TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    full_name TEXT NOT NULL,
    role VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT user_pk_id PRIMARY KEY (id),
    CONSTRAINT user_role_enum CHECK (role IN ('ADMIN', 'USER')),
    CONSTRAINT user_status_enum CHECK (status
        IN ('ACTIVATED', 'DEACTIVATED', 'WAITING_ACTIVATION'))
);
