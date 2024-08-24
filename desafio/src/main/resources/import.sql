insert into customers (name, email, adress) values ('Marco','marco@gmail.com','San salvador');
insert into customers (name, email, adress) values ('Luis','luis@gmail.com','en su casa');

insert into categories (category_name, category_type) values ('Alimento','Lacteos');
insert into categories (category_name, category_type) values ('Perifericos','Electronico');

insert into deliveries (type, status) values ('Expres','en espera');
insert into deliveries (type, status) values ('Economico','en camino');

insert into products (product_name, category_id) values ('Leche',1);

INSERT INTO orders (order_date,delivery_id,customer_id) values ('2024-08-23 00:00:00',1,1);

insert into order_product (order_id, product_id) values (1,1);
