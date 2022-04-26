BEGIN TRANSACTION;

DROP TABLE IF EXISTS book_info, users, users_books CASCADE;

DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE book_info (
    book_id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
	isbn VARCHAR(13),
	difficulty int,
	genre VARCHAR(50)
);

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	family_id varchar(255) NOT NULL,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	email varchar(255),
	username varchar(50) UNIQUE NOT NULL,
	password_hash varchar(255) NOT NULL,
	role varchar(50) NOT NULL,
	total_minutes int,
	points_balance int,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE users_books (
	user_id int NOT NULL,
	book_id int NOT NULL,
	minutes_read int,
	reading_format varchar (50) NOT NULL,
	times_read int,
	review varchar (150),
	date_logged timestamp(0) without time zone NOT NULL,
	session_points int,
	CONSTRAINT PK_users_books PRIMARY KEY(user_id, date_logged),
    FOREIGN KEY(book_id) REFERENCES book_info(book_id)

);

INSERT INTO users (username, family_id, first_name, last_name, email, password_hash, role, total_minutes, points_balance)
VALUES
	('user', 2, 'Colin', 'Davis', 'cd@gmail.com','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_PARENT', 90, 100),
	('admin', 2, 'Nolan', 'Tsai', 'nt@gmail.com','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_CHILD', 2450, 2100),
	('regreg', 2, 'Reginald', 'Arnedo', 'reg@hotmail.com', '$2a$10$D6LUTeihoDMiJTtw2zsWTuy8qaxS5MZkFA7sRwa538FDGu4/T/qOK', 'ROLE_CHILD', 20, 110),
	('indigowolf', 2, 'Kai Indigo', 'Wolf', 'kai.wolf@gmail.com', '$2a$10$CemCJcwUc/yxFjK20VKQS.7NxjkM/iR/liyQtzfXtgQjfBxV/EUgm', 'ROLE_PARENT', 40, 999),
	('niles', 2, 'Niles', 'Xavier', 'nx@aol.com', '$2a$10$afmIx5OHly1g2UlW0.iW0O6CBqy4.2STcOJapa97Jw3DjUyU40gke', 'ROLE_CHILD', 70, 1450),
	('lovely', 2, 'Piper', 'Lovelace', 'pip@gmail.com', '$2a$10$niS2RFa/pz/iGOOANcliBOogp30yhVf6P5xoVpPKoV5alItlu2Coq', 'ROLE_CHILD', 115, 1000),
	('jess', 2, 'Jessica', 'Wolf', 'jw@gmail.com', '$2a$10$i7jJUMMotAVXqGj.q22y8OiKILcBiP9pAgJZc8u1ilLz9jK9mvcxm' , 'ROLE-CHILD', 90, 150),
	('snape', 2, 'Severus', 'Snape', 'snape@slytherin.com', '$2a$10$Ud8pVA/eQWCFRwWJVhREbu9gDOLOBGv8BBQ6nuzw3RpWbNiaKUR1.', 'ROLE-CHILD', 20, 150),
	('fanofdepp', 2, 'Stella', 'Depp', 'sdepp@johnny.com', '$2a$10$52hZgsBIboDv/nje3saBXuIIcEAw2N5F5Aj5tbK/FQN/2getqNEwu', 'ROLE-CHILD', 150, 2000),
	('artguy', 2, 'Scout', 'Handford', 'scout@dreamworks.com', '$2a$10$Ud8pVA/eQWCFRwWJVhREbu9gDOLOBGv8BBQ6nuzw3RpWbNiaKUR1.', 'ROLE_PARENT', 90, 2090);


INSERT INTO book_info (title, author, isbn, difficulty, genre)
VALUES
	('How to Play Your Best Golf All the Time', 'Tommy Armour', 9780684813790, 5, 'Reference'),
	('The Rainbow Fish', 'J Alison James', 1558580093, 1, 'Early Reader'),
	('1984', 'George Orwell', 9780140009729, 5, 'Classic'),
	('The Boy Who Loved Math', 'Deborah Heiligman', 9781596433076, 4, 'Early Reader'),
	('Macbeth', 'William Shakespeare', 9780333419991, 5, 'Classic'),
	('If You Give a Mouse a Cookie', 'Felicia Bond', 9780064434096, 2, 'Early Reader'),
	('Inside Out & Back Again', 'Thanhha Lai', 9780545508971, 3, 'Young Adult'),
	('Where the Wild Things Are', 'Maurice Sendak', 9780064431781, 2, 'Early Reader'),
	('The Giver', 'Louis Lowry', 9780395645666, 4, 'Young Adult'),
	('Charlotte''s Web', 'Jennifer Frantz', 9780060882846, 3, 'Early Reader' ),
	('Atlas of Adventures: Wonders of the World', 'Ben Handicott', 9781786033444, 3, 'Reference'),
	('The Very Hungry Caterpillar', 'Eric Carle', 9780399226908, 1, 'Early Reader'),
	('Strictly No Elephants', 'Taeeun Yoo', 9781481416474, 2, 'Early Reader'),
	('Harry Potter and the Deathly Hollows', 'J. K. Rowling', 9780545139700, 5, 'Young Adult'),
	('Fancy Nancy: Poet Extraordinaire!', 'Jane O''Connor', 9780061896439, 3, 'Early Reader'),
	('An Illustrated Book of Bad Arguments', 'Ali Almossawi', 9780989931205, 5, 'Reference'),
	('We Are Grateful: Otsaliheliga', 'Traci Sorell', 9781580897723, 3, 'Non-Fiction'),
	('Native Homes', 'Bobbie Kalman', 9780778703716, 4, 'Non-Fiction');


INSERT INTO users_books (user_id, book_id, minutes_read, reading_format, times_read, review, date_logged, session_points)
VALUES
	(1, 1, 30, 'Paper', 0, 'Definitely good for sleepy time.', '2022-01-22 19:10:25', 10),
	(1, 2, 30, 'Paper', 1, '','2022-01-23 17:10:21', 10),
	(1, 5, 60, 'Digital', 0, '','2022-01-23 13:25:25', 20),
	(1, 3, 60, 'Paper', 1, '','2022-01-23 14:30:25', 20),
	(1, 6, 90, 'Digital', 0, '','2022-01-24 15:40:25', 30),
	(1, 4, 90, 'Paper', 1, '','2022-01-24 16:10:25', 30),
	(1, 8, 30, 'Paper', 0, '','2022-01-24 17:10:25', 10),
	(1, 9, 60, 'Paper', 1, '','2022-01-24 18:10:25', 20),
	(1, 7, 60, 'Read-Aloud', 0, '','2022-01-25 19:10:25', 20),
    (1, 14, 90, 'Read-Aloud', 1, '','2022-01-25 11:10:25', 30),
    (1, 13, 90, 'Digital', 0, '','2022-01-25 12:10:25', 30),
    (1, 12, 30, 'Read-Aloud', 1, '','2022-01-25 13:10:25', 10),
    (1, 11, 60, 'Read-Aloud', 0, '','2022-01-25 14:10:25', 20),
    (1, 10, 90, 'Paper', 1, '','2022-01-26 15:10:25', 30),
    (1, 15, 90, 'Audiobook', 0, '','2022-01-26 16:10:25', 30),
    (1, 17, 30, 'Audiobook', 1, '','2022-01-26 17:10:25', 10),
    (1, 18, 30, 'Read-Aloud', 0, '','2022-01-26 18:10:25', 10),
    (1, 16, 30, 'Read-Aloud', 1, 'Definitely for older kids with difficult vocabulary and deep concepts.', '2022-01-26 19:10:25', 10),
	(2, 7, 60, 'Audiobook', 0, '','2022-01-26 11:10:25', 20),
    (2, 14, 90, 'Paper', 1, '','2022-01-27 12:10:25', 30),
    (2, 13, 90, 'Paper', 0, '','2022-01-27 13:10:25', 30),
    (2, 12, 30, 'Audiobook', 1, '','2022-01-27 14:10:25', 10),
    (2, 11, 60, 'Digital', 0, '','2022-01-27 15:10:25', 20),
    (2, 10, 90, 'Paper', 1, '','2022-01-27 16:10:25', 30),
    (2, 15, 90, 'Paper', 0, '','2022-01-27 17:10:25', 30),
    (2, 17, 30, 'Digital', 1,  '', '2022-01-28 18:10:25', 10),
    (2, 18, 30, 'Read-Aloud', 0, '','2022-01-28 19:10:25', 10),
    (2, 16, 30, 'Read-Aloud', 1, '', '2022-01-28 11:10:25', 10),
	(3, 1, 30, 'Paper', 0, 'Zonked right out.','2022-01-28 12:10:25', 10),
	(3, 2, 30, 'Paper', 1, '','2022-01-29 13:10:25', 10),
	(3, 5, 60, 'Paper', 0,  'What a wordy book!','2022-01-29 14:10:25', 20),
	(3, 3, 60, 'Paper', 1, '','2022-01-29 15:10:25', 20),
	(3, 6, 90, 'Digital', 0,  '','2022-01-29 16:10:25', 30),
	(3, 4, 90, 'Audiobook', 1, '', '2022-01-29 17:10:25',30),
	(3, 8, 30, 'Paper', 0, '','2022-01-29 18:10:25', 10),
	(3, 9, 60, 'Paper', 1, 'A favorite for sure.', '2022-01-30 19:10:25', 20),
	(3, 7, 60, 'Paper', 0, '','2022-01-30 12:10:25', 20),
    (3, 14, 90, 'Audiobook', 1,  '', '2022-01-30 13:10:25',30),
	(8, 1, 30, 'Paper', 0,  'Definitely good for sleepy time.','2022-01-30 19:10:25', 10),
	(6, 2, 30, 'Audiobook', 1,  '','2022-01-30 14:10:25', 10),
	(7, 5, 60, 'Audiobook', 0,  'Why are the words so boring?','2022-01-30 19:10:25', 20),
	(5, 3, 60, 'Audiobook', 1,  '','2022-02-01 15:10:25', 20),
	(4, 6, 90, 'Digital', 0,  '', '2022-02-01 16:10:25',30),
	(5, 4, 90, 'Audiobook', 1,  '','2022-02-01 17:10:25', 30),
	(4, 8, 30, 'Read-Aloud', 0,  '','2022-02-01 18:10:25', 10),
	(5, 9, 60, 'Digital', 1, 'Lets read this one again and again','2022-02-01 19:10:25', 20),
	(4, 7, 60, 'Audiobook', 0, '', '2022-02-02 12:10:25',20),
    (5, 14, 90, 'Paper', 1, '', '2022-02-02 13:10:25',30),
	(5, 11, 60, 'Paper', 0,  '','2022-02-02 16:10:25', 20),
    (5, 10, 90, 'Audiobook', 1, '','2022-02-02 15:10:25', 30),
    (6, 15, 90, 'Read-Aloud', 0,  '', '2022-02-02 11:10:25',30),
    (6, 17, 30, 'Audiobook', 1, '','2022-02-03 14:10:25', 10),
    (7, 18, 30, 'Paper', 0,  '','2022-02-03 15:10:25', 10),
    (8, 16, 30, 'Paper', 1, 'Definitely for older kids with difficult vocabulary and deep concepts.','2022-02-03 19:10:25', 10),
	(5, 7, 60, 'Paper', 0, '','2022-02-03 13:10:25', 20),
    (4, 14, 90, 'Paper', 1,  '','2022-02-03 17:10:25', 30),
    (3, 13, 90, 'Paper', 0,  '', '2022-02-04 16:10:25',30),
    (8, 12, 30, 'Audiobook', 1,  '','2022-02-04 12:10:25', 10),
	(6, 1, 30, 'Digital', 0,  'Definitely good for sleepy time.','2022-02-04 16:10:25', 10),
	(6, 5, 60, 'Read-Aloud', 1,  'Not a fan.','2022-02-04 17:10:25', 20),
	(8, 3, 60, 'Read-Aloud', 0, '','2022-02-05 13:10:25', 20),
	(6, 6, 90, 'Read-Aloud', 1,  '','2022-02-05 18:10:25', 30),
	(8, 4, 90, 'Paper', 0, '', '2022-02-05 15:10:25',30),
	(7, 8, 30, 'Paper', 1,  '', '2022-02-05 17:10:25',10),
	(7, 9, 60, 'Paper', 0,  'My favorite!!!!', '2022-02-05 14:10:25', 20),
	(8, 7, 60, 'Digital', 1, '', '2022-02-05 15:45:25', 20),
	(1, 1, 30, 'Paper', 0, 'Definitely good for sleepy time.', '2022-01-24 19:10:25', 10),
    (1, 2, 30, 'Paper', 1, '','2022-01-26 17:10:21', 10),
    (1, 5, 60, 'Digital', 0, '','2022-01-27 13:25:25', 20),
    (1, 3, 60, 'Paper', 1, '','2022-01-25 14:30:25', 20),
    (1, 6, 90, 'Digital', 0, '','2022-01-28 15:40:25', 30);



--- USER SETUP (Do Not Modify)
DROP USER IF EXISTS final_capstone_owner;
DROP USER IF EXISTS final_capstone_appuser;

CREATE USER final_capstone_owner
WITH PASSWORD 'finalcapstone';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO final_capstone_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_owner;

CREATE USER final_capstone_appuser
WITH PASSWORD 'finalcapstone';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO final_capstone_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_appuser;

COMMIT TRANSACTION;