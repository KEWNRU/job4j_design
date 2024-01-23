create table number(
id serial primary key,
num int
);

create table phone(
id serial primary key,
name text
);

create table people(
id serial primary key,
name varchar(255),
phone_id int references phone(id)
);

create table provider(
id serial primary key,
name text,
number_id int references number(id),
people_id int references people(id)
);

insert into number(num) values (111), (123), (232), (113)

insert into phone(name) values ('iphon'), ('samsung'), ('lg'), ('poco')

insert into people(name, phone_id) values ('Ваня', 1), ('Наташа', 2), ('Сергей', 3), ('Вова', 4)

insert into provider(name, number_id, people_id) values ('МТС', 1, 1), ('Билайн', 2, 2), ('Теле2', 3, 3), ('Мегафон', 4, 4)

create view output_of_all_tables
as
select pe.name as "Имена",
n.num as "Номер",
pr.name as "Провайдер",
ph.name as "Модель"
from people pe
left join number as n on pe.phone_id = n.id
left join provider as pr  on pe.phone_id = pr.id
left join phone as ph  on ph.id = pr.id

select * from output_of_all_tables