
INSERT INTO category_tb (id_category, name) VALUES
(1, 'bebida'),
(2, 'entrada'),
(3, 'prato principal'),
(4, 'sobremesa');

ALTER TABLE category_tb
ADD CONSTRAINT unique_category_name UNIQUE (name);


INSERT INTO product_tb (id_product, name, price, id_category) VALUES
(1, 'Sushi de Salmão', 20.00, 3),
(2, 'Tempurá de Camarão', 15.00, 2),
(3, 'Uramaki de Filadélfia', 18.00, 3),
(4, 'Missoshiro', 5.00, 1),
(5, 'Guaraná', 3.50, 1),
(6, 'Harumaki de Legumes', 12.00, 2),
(7, 'Sashimi Misto', 25.00, 3),
(8, 'Água Mineral', 2.50, 1),
(9, 'Mochi de Matcha', 8.00, 4),
(10, 'Dorayaki', 10.00, 4),
(11, 'Yakult', 4.00, 1),
(12, 'Tempurá de Legumes', 12.00, 2),
(13, 'Nigiri de Atum', 22.00, 3),
(14, 'Sorvete de Gergelim', 7.00, 4),
(15, 'Chá Verde', 3.00, 1);

UPDATE hibernate_sequence SET next_val = 16;


