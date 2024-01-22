create table departments(
id serial primary key,
name varchar(255)
);

create table employees(
id serial primary key,
name varchar(255),
departments_id int references departments(id)
);

insert into departments(name) values ('Team Leader'),('Senior'),('Middle'),('Junior'),('HR'), ('trainee');

insert into employees(name, departments_id) values ('Кирилл', 1);
insert into employees(name, departments_id) values ('Сергей', 2);
insert into employees(name, departments_id) values ('Иван', 3);
insert into employees(name, departments_id) values ('Владимир', 4);
insert into employees(name, departments_id) values ('Наталья', 5);
insert into employees(name, departments_id) values ('Денис', null);

select * from employees as e
left join departments as d
on e.departments_id = d.id;

select * from employees as e
right join departments as d
on d.id = e.departments_id;

select * from employees as e
full join departments as d
on d.id = e.departments_id;

select * from departments d
cross join employees e

select d.name from departments as d
left join employees as e
on e.departments_id = d.id where e.name is null

select * from employees as e
    left join departments as d
    on e.departments_id = d.id
    where d."name" is not null;

select * from employees as e
    right join departments as d
    on e.departments_id = d.id
    where e."name" is not null;


create table teens(
id serial primary key,
name varchar(255),
gender text
);

insert into teens (name, gender) values ('Ваня', 'Муж');
insert into teens (name, gender) values ('Наташа', 'Жен');
insert into teens (name, gender) values ('Вова', 'Муж');
insert into teens (name, gender) values ('Аня', 'Жен');


select m.name as male,
f.name as female
from teens
m cross join teens f
where m.gender > f.gender;