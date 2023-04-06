insert into chat."User"(login, password) values
('vika', '123'),
('danila', '456'),
('julia', '789'),
('molecula', 'lol'),
('ass', 'memor');

insert into chat."ChatRoom"(name, owner) values
('trip', 1),
('study', 3),
('game', 4),
('project', 5),
('festival', 2);

insert into chat."Message"(chat_id, author, message_text, time_date) values
(1, 1, 'go to the trip', '2023-03-10 18:14:00'),
(1, 2, 'lets go', '2023-03-10 19:10:00'),
(2, 3, 'we need to do sis admin', '2023-03-01 15:10:00'),
(2, 1, 'fuck this is shit', '2023-03-01 15:15:10'),
(3, 4, 'go to play to dota', '2023-02-10 18:10:00'),
(3, 1, 'molecula ya ne hochu play v dota', '2023-02-10 19:00:00'),
(4, 1, 'what is this', '2023-02-01 13:10:00'),
(4, 5, 'hmm idk just for fun', '2023-02-01 13:15:00'),
(5, 2, 'go to signal', '2023-02-13 12:10:00'),
(5, 1, 'yeeeeahh go', '2023-02-13 12:11:00');