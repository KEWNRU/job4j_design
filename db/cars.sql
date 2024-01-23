create table car_bodies(
    id serial primary key,
    name varchar(255)
);

create table car_engines(
    id serial primary key,
    name varchar(255)
);

create table car_transmissions(
    id serial primary key,
    name varchar(255)
);

create table car(
    id serial primary key,
    name varchar(255),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies (name) values ('седан'), ('хэтчбек'), ('пикап'), ('кабриолет');

insert into car_engines (name) values ('1.0'), ('1.5'), ('2.0');

insert into car_transmissions (name) values ('АКПП'), ('МКПП');

insert into car (name, body_id, engine_id, transmission_id)
values ('toyota', 1, 1, 1),
        ('bmw', 1, 3, 2),
        ('ford', 3, 2, 1),
        ('audi', 2, 2, 2),
        ('tesla', 1, null, 1),
        ('nissan', 1, 3, null);

select c.id, c.name as "car_name",
cb.name as "body_name",
ce.name as "engine_name",
ct.name as "transmission_name"
from car c
left join car_bodies as cb on c.body_id = cb.id
left join car_engines as ce on c.engine_id = ce.id
left join car_transmissions as ct on c.transmission_id = ct.id

select cb.name
from car_bodies cb
left join car c
on cb.id = c.body_id
where c.body_id is null

select ce.name
from car_engines ce
left join car as c
on ce.id = c.engine_id
where c.engine_id is null

select ct.name
from car_transmissions ct
left join car c
on ct.id = c.transmission_id
where c.transmission_id is null