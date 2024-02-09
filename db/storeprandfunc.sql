create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

insert into products (name, producer, count, price)
        values ('Наушники', 'HyperX', 2, 5599);

insert into products (name, producer, count, price)
        values ('Наушники', 'Logitech', 3, 4499);

insert into products (name, producer, count, price)
        values ('Наушники', 'Logitech G PRO', 0, 13999);

insert into products (name, producer, count, price)
        values ('Наушники', 'HyperX Cloud Alpha', 0, 8999);

create
or replace procedure delet_data(i_count integer)
language 'plpgsql'
as $$
BEGIN
delete from products where count = 0;
END;
$$

call delet_data (0);


insert into products (name, producer, count, price)
        values ('Наушники', 'Logitech G PRO', 0, 13999);

insert into products (name, producer, count, price)
        values ('Наушники', 'HyperX Cloud Alpha', 0, 8999);

create or replace function func_data(f_count integer)
returns integer
language 'plpgsql'
as
$$
declare
result integer;
BEGIN
delete from products where count = 0;
return result;
END;
$$;

select func_data(0);
