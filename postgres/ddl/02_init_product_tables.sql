CREATE TABLE IF NOT EXISTS app.product (
    id UUID,
    creation_time TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    last_updated TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    title TEXT NOT NULL,
    weight INT NOT NULL,
    calories INT NOT NULL,
    proteins DECIMAL(6,1) NOT NULL,
    fats DECIMAL(6,1) NOT NULL,
    carbohydrates DECIMAL(6,1) NOT NULL,
    CONSTRAINT product_pk_id PRIMARY KEY (id)
);