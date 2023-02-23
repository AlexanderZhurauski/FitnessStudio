CREATE TABLE IF NOT EXISTS app.recipe (
    id UUID,
    creation_time TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    last_updated TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    title TEXT NOT NULL,
    weight INT NOT NULL,
    calories INT NOT NULL,
    proteins DECIMAL(6,1) NOT NULL,
    fats DECIMAL(6,1) NOT NULL,
    carbohydrates DECIMAL(6,1) NOT NULL,
    CONSTRAINT recipe_pk_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS app.recipe_product (
    recipe_id UUID,
    product_id UUID,
    CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id)
        REFERENCES app.recipe (id),
    CONSTRAINT fk_product_instance_id FOREIGN KEY (product_id)
        REFERENCES app.product_instance (id)
);

