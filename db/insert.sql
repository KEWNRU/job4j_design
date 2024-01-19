insert into users(users_name, roles_id) VALUES ('Ivan', 1);
insert into roles(roles_name) VALUES ('ADMIN');
insert into rules(rules_name) VALUES ('ALL');
insert into roles_rules(roles_id, rules_id) values (1, 1);
insert into categories(categories_name) VALUES ('urgently');
insert into states(states_name) VALUES ('at work');
insert into items(items_name, users_id, categories_id, states_id) VALUES ('Refactoring java cod', 1 ,1, 1);
insert into comments(comments_name, items_id) VALUES ('Needed yesterday!', 1);
insert into attachs(attachs_name, items_id) VALUES ('C:\java.zip', 1);
