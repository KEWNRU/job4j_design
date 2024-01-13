create table users (
id serial primary key,
users_name text,
roles_id int refernces roles(id)
);

create table roles (
id serial primary key,
roles_name text,
);

create table rules (
id serial primary key,
rules_name text
);

create table roles_rules(
id serial primary key,
roles_id int references roles(id),
rules_id int references rules(id)
);

create table items (
id serial primary key,
items_name text,
users_id int references users(id),
categories_id int references categories(id),
states_id int references states(id)
);

create table comments (
id serial primary key,
comments_name text,
items_id int references items(id)
);

create table attachs (
id serial primary key,
attachs_name text,
items_id int references items(id)
);

create table states (
id serial primary key,
states_name text,
);

create table categories (
id serial primary key,
categories_name text,
);
