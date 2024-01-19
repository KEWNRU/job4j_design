create table type(
id serial primary key,
name text
);

create table product(
id serial primary key,
name text,
expired_date date,
price float,
type_id int references type(id)
);

insert into type(name) values ('Сыр'), ('Молоко'), ('Крупа')
insert into product(name, expired_date, price, type_id) values ('Сыр Российский', '19.01.2024', 54.50, 1);
insert into product(name, expired_date, price, type_id) values ('Сыр Адыгейский', '15.02.2024', 78.50, 1);
insert into product(name, expired_date, price, type_id) values ('Сыр Моцарелла', '15.01.2024', 120.99, 1);

insert into product(name, expired_date, price, type_id) values ('Молоко цельное', '15.02.2024', 24.99, 2);
insert into product(name, expired_date, price, type_id) values ('Молоко 2,5', '15.01.2024', 20.99, 2);
insert into product(name, expired_date, price, type_id) values ('Молоко 3,2', '12.01.2024', 22.99, 2);
insert into product(name, expired_date, price, type_id) values ('Мороженое', '12.01.2024', 12.99, 2);

insert into product(name, expired_date, price, type_id) values ('Гречка', '15.02.2024', 24.99, 3);
insert into product(name, expired_date, price, type_id) values ('Рис', '15.01.2024', 20.99, 3);
insert into product(name, expired_date, price, type_id) values ('Овсянка', '12.01.2024', 22.99, 3);

select p.name, p.expired_date, p.price, p.type_id
from product p
join type t
on p.type_id = t.id where t.name = 'Сыр';

select * from product where name like '%Мороженое%'

select * from product where expired_date < current_date;

select max(price) from product;

select t.name,
count(p.type_id)
from product as p
join type as t
on t.id = p.type_id
group by t.name

select p.name
from product p
join type t
on p.type_id = t.id where t.name = 'Сыр' or t.name = 'Молоко';

select t.name,
count(p.type_id)
from product as p
join type as t
on t.id = p.type_id
group by t.name
having count(t.id) < 10

select t.name, p.name
from type as t
join product p
on p.type_id = t.id
