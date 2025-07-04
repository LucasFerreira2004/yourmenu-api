-- Inserção do administrador
INSERT INTO administrator (id, first_name, last_name, email, password)
VALUES ('a1b2c3d4-e5f6-7890-abcd-1234567890ef', 'Maria', 'do Carmo da Silva', 'teste@gmail.com', '$2a$10$M9rTnXuHmoriTuujr.rEBeDBwV0jKFsIPNXhnlhZO6U2nXHRBmciq');

-- Inserção do restaurante
INSERT INTO restaurant (id, administrator_id, slug, name, delivery_time_min, delivery_time_max, is_open, profile_pic_url, banner_pic_url)
VALUES ('f305fdc2-49e6-491e-b898-fe5842857b9c', 'a1b2c3d4-e5f6-7890-abcd-1234567890ef', 'delicias-do-rancho', 'delicias do rancho', 20, 40, false, NULL, NULL);

-- Inserção da categoria
INSERT INTO category (id, restaurant_id, name)
VALUES (1, 'f305fdc2-49e6-491e-b898-fe5842857b9c', 'Pratos principais');

-- Inserção de zona de entrega
INSERT INTO delivery_zone (id, restaurant_id, zone, delivery_fee)
VALUES (1, 'f305fdc2-49e6-491e-b898-fe5842857b9c', 'Centro', 5.00);

-- Inserção de opção de tamanho
INSERT INTO size_option (id, magnitude, measure_unit)
VALUES (default, '1', 'KILOGRAMA'),
       (default, '0.5', 'KILOGRAMA');

-- Inserção de prato (dish)
INSERT INTO dish (id, restaurant_id, category_id, name, description, is_available, image_url)
VALUES (1, 'f305fdc2-49e6-491e-b898-fe5842857b9c', 1, 'Feijoada Completa', 'Feijoada com arroz, farofa e couve.', true, NULL),
       (2, 'f305fdc2-49e6-491e-b898-fe5842857b9c', 1, 'Maccaronada', 'Macarronada com parmesão', true, NULL);

-- Inserção de tamano de prato com preço (dish_size_option)
INSERT INTO dish_size_option (id, price, dish_id, size_option_id)
VALUES (1, 22, 1, 1),
       (2, 18, 1, 2),
       (3, 15, 1, 1),
       (4, 12, 1, 2);


-- Inserção de pedido
INSERT INTO orders (id, restaurant_id, date_time, price, status)
VALUES (default, 'f305fdc2-49e6-491e-b898-fe5842857b9c', '2025-06-25 00:23:04', 79.90, 'PENDING'),
       (default, 'f305fdc2-49e6-491e-b898-fe5842857b9c', '2025-06-25 01:23:04', 79.90, 'PENDING'),
       (default, 'f305fdc2-49e6-491e-b898-fe5842857b9c', '2025-06-25 00:23:04', 79.90, 'CONFIRMED'),
       (default, 'f305fdc2-49e6-491e-b898-fe5842857b9c', '2025-06-26 00:23:04', 50.00, 'PENDING'),
       (default, 'f305fdc2-49e6-491e-b898-fe5842857b9c', '2025-06-26 01:23:04', 50.00, 'PENDING'); .
