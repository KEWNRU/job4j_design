create table phone(
id serial primary key,
number int,
model varchar
);

create table people(
id serial primary key,
name varchar(255),
phone_id int references phone(id) unique
);

insert into phone(number, models) values (8953, 'iphone 5');
insert into phone(number, models) values (8903, 'pocos3');
insert into phone(number, models) values (8909, 'samsung s20');

insert into people(name, phone_id) values ('Ivan', 1);
insert into people(name, phone_id) values ('Sergey', 2);
insert into people(name, phone_id) values ('Vova', 3);

select pp.name, p.model, p.number
from people as pp join phone as p on pp.phone_id = p.id;

select pp.name as Имя, p.model as Модель, p.number as Номер
from people as pp join phone as p on pp.phone_id = p.id;

select pp.name as "Имя владельца", p.model Модель, p.number Номер
from people pp join phone p on pp.phone_id = p.id;
