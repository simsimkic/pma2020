--add user (pass 1111)
insert into user(id, name, password, email, address, username ) values (1, 'Pera Peric', 'DQawLrwajQ9APpZsw+Lcqyody9+LT4O73xLwwQns7fM=$uo4jiQlGuiXH3pRpWpk9zAsZ0PDUBEotcwUWui/5r08=', 'pera@gmail.com', 'Nikole Tesle 4', 'pera');
insert into user(id, name, password, email, address , username) values (2,'Petar Petrovic', 'DQawLrwajQ9APpZsw+Lcqyody9+LT4O73xLwwQns7fM=$uo4jiQlGuiXH3pRpWpk9zAsZ0PDUBEotcwUWui/5r08=', 'petar@gmail.com', 'Nikole Tesle 6', 'petar');
insert into user(id, name, password, email, address , username) values (3, 'Mila Milic', 'DQawLrwajQ9APpZsw+Lcqyody9+LT4O73xLwwQns7fM=$uo4jiQlGuiXH3pRpWpk9zAsZ0PDUBEotcwUWui/5r08=', 'mila@gmail.com','Nikole Tesle 8', 'mila');
insert into user(id, name, password, email, address , username) values (4, 'Milica Tadic', 'DQawLrwajQ9APpZsw+Lcqyody9+LT4O73xLwwQns7fM=$uo4jiQlGuiXH3pRpWpk9zAsZ0PDUBEotcwUWui/5r08=', 'milica.t@gmail.com', 'Nikole Tesle 10', 'milica');


--add friendship
insert into friendship(id, approved, send_request, requestor_id, requestee_id, delete) values (1, true, '2019-12-12', 1, 2 , false);
insert into friendship(id, approved, send_request, requestor_id, requestee_id, delete) values (2, true, '2019-12-12', 1, 4 , false);
insert into friendship(id, approved, send_request, requestor_id, requestee_id, delete) values (3, false , '2020-05-06', 3, 1, false );
insert into friendship(id, approved, send_request, requestor_id, requestee_id, delete) values (4, true, '2019-12-12', 2, 3, false );
insert into friendship(id, approved, send_request, requestor_id, requestee_id, delete) values (5, true, '2019-12-12', 3, 4, false );
