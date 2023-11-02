INSERT INTO person (ID,name, second_name, age, family) VALUES
    (1,'Анастасия', 'Фомина',23,'Не женат'),
    (2,'Павел', 'Перевалов',65,'Женат'),
    (3,'Алексей', 'Махмудов',34,'Не женат');
	
delete from cell
INSERT INTO cell (ID,name, level, hash, loc, product, location_id, product_id ) VALUES
    (1,'1', 1, '1234567', 'Склад А', 'Наушники',1,3),
    (2,'2', 2, '9876543', 'Склад Б', 'Пульт',1,2),
    (3,'3', 3, '3456789', 'Склад Г', 'Телефон',2,1);
	
INSERT INTO location (ID,name, description, address, cell_id, person_id) VALUES
    (1,'Склад А', 'злая собака','Россия, г. Москва, Зеленый пер., д. 22 кв.5', 1,1),
    (2,'Склад Б', 'злой директор','Россия, г. Москва, Зеленый пер., д. 23 кв.5', 2,2),
    (3,'Склад Г', 'все плохо','Россия, г. Москва, Мирная ул., д. 7 кв.197', 3,3);


INSERT INTO product (ID,name, article, quantity, price, person_id) VALUES
    (1,'Проводная гарнитура Perfeo ALPHA желтый', 'AB853864', 23, 99, 1),
    (2,'Чехол Aceline LightPods, AirPods, AirPods 2 Aceline Basic Carabin синий', 'CF123456', 43, 123, 2),
    (3,'Проводная гарнитура Gembird MHS-780 белый', 'RE345267',56, 1222, 3;
	
select * from cell

INSERT INTO transport (ID,name, number, description, person_id , location_id) VALUES
    (1,'Kia Rio', 'A987ВА124', '4 места, 3 аварии', 1,1),
    (2,'Lada Granta','О987ОА124', '4 места, 0 аварии', 2,2),
    (3,'Lada Vesta', 'П987ВЗ124', '4 места, 2 аварии', 3,3);