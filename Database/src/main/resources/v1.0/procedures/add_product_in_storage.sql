CREATE OR REPLACE FUNCTION add_product_in_storage() RETURNS TRIGGER AS
$$
DECLARE
    product_id numeric(15);
BEGIN
    IF TG_OP = 'INSERT' THEN
        product_id = NEW.id;
        INSERT INTO storage(product_id, amount) VALUES (product_id, 0);
        RETURN NEW;
    END IF;
END;
$$ LANGUAGE plpgsql;