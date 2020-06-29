--add user (pass 1111)
insert into user(id, name, password, email, address, username ) values (1, 'Pera Peric', 'DQawLrwajQ9APpZsw+Lcqyody9+LT4O73xLwwQns7fM=$uo4jiQlGuiXH3pRpWpk9zAsZ0PDUBEotcwUWui/5r08=', 'pera@gmail.com', 'Nikole Tesle 4', 'pera');
insert into user(id, name, password, email, address , username) values (2,'Petar Petrovic', 'DQawLrwajQ9APpZsw+Lcqyody9+LT4O73xLwwQns7fM=$uo4jiQlGuiXH3pRpWpk9zAsZ0PDUBEotcwUWui/5r08=', 'petar@gmail.com', 'Nikole Tesle 6', 'petar');
insert into user(id, name, password, email, address , username) values (3, 'Mila Milic', 'DQawLrwajQ9APpZsw+Lcqyody9+LT4O73xLwwQns7fM=$uo4jiQlGuiXH3pRpWpk9zAsZ0PDUBEotcwUWui/5r08=', 'mila@gmail.com','Nikole Tesle 8', 'mila');
insert into user(id, name, password, email, address , username) values (4, 'Milica Tadic', 'DQawLrwajQ9APpZsw+Lcqyody9+LT4O73xLwwQns7fM=$uo4jiQlGuiXH3pRpWpk9zAsZ0PDUBEotcwUWui/5r08=', 'milica.t@gmail.com', 'Nikole Tesle 10', 'milica');



insert into goal(id, archived, distance, duration, end_time, timestampe, title, user_id) values (1, true, 2.0, 5.0, '2019-12-12', '2020-12-12', 'Naziv', 1);

insert into friendship(id, status, send_request, requestor_id, requestee_id) values (3, 'SEND_REQUEST' , '2020-05-06', 3, 1);

--add friends
insert into friends(id, user1_id, user2_id) values (1, 1, 2);
insert into friends(id, user1_id, user2_id) values (2, 3, 2);
insert into friends(id, user1_id, user2_id) values (3, 4, 1);
insert into friends(id, user1_id, user2_id) values (4, 4, 3);

--USER SETTINGS
insert into user_settings (id, accepted_activity, accepted_friendship, activity_request, canceled_activity, friendship_request, goal_privacy, new_comments, new_likes, night_theme, post_privacy, user_info_privacy, user_id)
                values (1, true, true, true, true, true, 0, true, true, false, 0, 0, 1);
insert into user_settings (id, accepted_activity, accepted_friendship, activity_request, canceled_activity, friendship_request, goal_privacy, new_comments, new_likes, night_theme, post_privacy, user_info_privacy, user_id)
                values (2, true, true, true, true, true, 0, true, true, false, 1, 0, 2);
insert into user_settings (id, accepted_activity, accepted_friendship, activity_request, canceled_activity, friendship_request, goal_privacy, new_comments, new_likes, night_theme, post_privacy, user_info_privacy, user_id)
                values (3, true, true, true, true, true, 0, true, true, false, 0, 0, 3);
insert into user_settings (id, accepted_activity, accepted_friendship, activity_request, canceled_activity, friendship_request, goal_privacy, new_comments, new_likes, night_theme, post_privacy, user_info_privacy, user_id)
                values (4, true, true, true, true, true, 0, true, true, false, 2, 0, 4);


--save activity

insert into activity (id, date_time, distance, duration, encoded_map, steps, user_id) values
(1, '2020-06-06 12:23', 0.0, 23.0,  'bsdej',0,2);

-- --post
insert into post(id, date, description, like_num, visibility, activity_id, user_id ) values (1, '2020-06-06 12:23', 'Running is cool!!', 3, 2, 1, 4);
-- insert into post(id, date, description, like_num, visibility, activity_id, user_id ) values (2, '2020-06-06 12:23', 'Running is cool!!', 13, 0, 2, 1);
-- insert into post(id, date, description, like_num, visibility, activity_id, user_id ) values (3, '2020-06-06 12:23', 'Running is cool!!', 23, 1, 3, 2);

