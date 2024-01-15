insert into users(users_name, roles_id) VALUES ('Ivan', 1);
insert into roles(roles_name) VALUES ('ADMIN');
insert into rules(rules_name) VALUES ('ALL');
insert into roles_rules(roles_id, rules_id) values (1, 1);
insert into items(items_name, users_id, categories_id, states_id) VALUES ('Refactoring java cod');
insert into comments(comments_name, comments_id) VALUES ('Needed yesterday!');
insert into attachs(attachs_name, items_id_id) VALUES ('C:\java.zip');
insert into states(states_name) VALUES ('at work');
insert into categories(categories_name) VALUES ('urgently');