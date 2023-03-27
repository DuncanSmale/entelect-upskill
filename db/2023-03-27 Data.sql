USE library;

INSERT INTO authors (first_name, last_name, country_of_residence, email_address)
VALUES 	('Nolwazi', 'Ndlovu', 'South Africa', 'n.d@gmail.com'),
		('Duncan', 'Smale', 'South Africa', 'd.s@gmail.com'),
        ('Peter', 'Ryan', 'UK', 'p.r@gmail.com');

INSERT INTO books(author_id, title, published_date, publisher, ISBN)
VALUES 	(1, 'Happy Peter and the Wizard of Escabar', '2021-01-03', 'Penguin Books', '0-2487-9445-0'),
		(2, 'The Face of the Eight', '2022-04-03', 'Exclusive Books', '0-8665-9950-9'),
        (3, 'Tapped for Sorrow', '2019-09-27', 'Jonathan Ball', '0-6351-1148-9')