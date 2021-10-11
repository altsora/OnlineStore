INSERT INTO product (name, price, is_available, description)
VALUES ('Галстук', 456.12, true, 'Прекрасный качественный галстук'),
       ('Лыжи', 5999.99, true, 'Супер лыжи!'),
       ('Подгузник Х5', 3124.56, true, 'Будьте спокойны'),
       ('Подгузник Х3', 1234.56, false, 'Чистый'),
       ('Супер компьютер', 120000.00, true, 'Быстрее скорости света'),
       ('Кресло', 15555.55, true, 'Удобненько'),
       ('Чёрная футболка', 1200.00, true, 'Красивая');

INSERT INTO product (name, price, is_available, description)
VALUES (SUBSTR(MD5(RANDOM()::TEXT), 0, 11), round(CAST(random() * (100000 - 1) + 1 as numeric), 2), random() < 0.5, SUBSTR(MD5(RANDOM()::TEXT), 0, 21)),
       (SUBSTR(MD5(RANDOM()::TEXT), 0, 11), round(CAST(random() * (100000 - 1) + 1 as numeric), 2), random() < 0.5, SUBSTR(MD5(RANDOM()::TEXT), 0, 21)),
       (SUBSTR(MD5(RANDOM()::TEXT), 0, 11), round(CAST(random() * (100000 - 1) + 1 as numeric), 2), random() < 0.5, SUBSTR(MD5(RANDOM()::TEXT), 0, 21)),
       (SUBSTR(MD5(RANDOM()::TEXT), 0, 11), round(CAST(random() * (100000 - 1) + 1 as numeric), 2), random() < 0.5, SUBSTR(MD5(RANDOM()::TEXT), 0, 21)),
       (SUBSTR(MD5(RANDOM()::TEXT), 0, 11), round(CAST(random() * (100000 - 1) + 1 as numeric), 2), random() < 0.5, SUBSTR(MD5(RANDOM()::TEXT), 0, 21)),
       (SUBSTR(MD5(RANDOM()::TEXT), 0, 11), round(CAST(random() * (100000 - 1) + 1 as numeric), 2), random() < 0.5, SUBSTR(MD5(RANDOM()::TEXT), 0, 21)),
       (SUBSTR(MD5(RANDOM()::TEXT), 0, 11), round(CAST(random() * (100000 - 1) + 1 as numeric), 2), random() < 0.5, SUBSTR(MD5(RANDOM()::TEXT), 0, 21)),
       (SUBSTR(MD5(RANDOM()::TEXT), 0, 11), round(CAST(random() * (100000 - 1) + 1 as numeric), 2), random() < 0.5, SUBSTR(MD5(RANDOM()::TEXT), 0, 21)),
       (SUBSTR(MD5(RANDOM()::TEXT), 0, 11), round(CAST(random() * (100000 - 1) + 1 as numeric), 2), random() < 0.5, SUBSTR(MD5(RANDOM()::TEXT), 0, 21)),
       (SUBSTR(MD5(RANDOM()::TEXT), 0, 11), round(CAST(random() * (100000 - 1) + 1 as numeric), 2), random() < 0.5, SUBSTR(MD5(RANDOM()::TEXT), 0, 21)),
       (SUBSTR(MD5(RANDOM()::TEXT), 0, 11), round(CAST(random() * (100000 - 1) + 1 as numeric), 2), random() < 0.5, SUBSTR(MD5(RANDOM()::TEXT), 0, 21)),
       (SUBSTR(MD5(RANDOM()::TEXT), 0, 11), round(CAST(random() * (100000 - 1) + 1 as numeric), 2), random() < 0.5, SUBSTR(MD5(RANDOM()::TEXT), 0, 21)),
       (SUBSTR(MD5(RANDOM()::TEXT), 0, 11), round(CAST(random() * (100000 - 1) + 1 as numeric), 2), random() < 0.5, SUBSTR(MD5(RANDOM()::TEXT), 0, 21)),
       (SUBSTR(MD5(RANDOM()::TEXT), 0, 11), round(CAST(random() * (100000 - 1) + 1 as numeric), 2), random() < 0.5, SUBSTR(MD5(RANDOM()::TEXT), 0, 21)),
       (SUBSTR(MD5(RANDOM()::TEXT), 0, 11), round(CAST(random() * (100000 - 1) + 1 as numeric), 2), random() < 0.5, SUBSTR(MD5(RANDOM()::TEXT), 0, 21)),
       (SUBSTR(MD5(RANDOM()::TEXT), 0, 11), round(CAST(random() * (100000 - 1) + 1 as numeric), 2), random() < 0.5, SUBSTR(MD5(RANDOM()::TEXT), 0, 21)),
       (SUBSTR(MD5(RANDOM()::TEXT), 0, 11), round(CAST(random() * (100000 - 1) + 1 as numeric), 2), random() < 0.5, SUBSTR(MD5(RANDOM()::TEXT), 0, 21)),
       (SUBSTR(MD5(RANDOM()::TEXT), 0, 11), round(CAST(random() * (100000 - 1) + 1 as numeric), 2), random() < 0.5, SUBSTR(MD5(RANDOM()::TEXT), 0, 21)),
       (SUBSTR(MD5(RANDOM()::TEXT), 0, 11), round(CAST(random() * (100000 - 1) + 1 as numeric), 2), random() < 0.5, SUBSTR(MD5(RANDOM()::TEXT), 0, 21)),
       (SUBSTR(MD5(RANDOM()::TEXT), 0, 11), round(CAST(random() * (100000 - 1) + 1 as numeric), 2), random() < 0.5, SUBSTR(MD5(RANDOM()::TEXT), 0, 21)),
       (SUBSTR(MD5(RANDOM()::TEXT), 0, 11), round(CAST(random() * (100000 - 1) + 1 as numeric), 2), random() < 0.5, SUBSTR(MD5(RANDOM()::TEXT), 0, 21));

