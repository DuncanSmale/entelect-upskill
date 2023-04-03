INSERT INTO authors (`firstName`, `lastName`, `countryOfResidence`, `emailAddress`)
VALUES 	('Nolwazi', 'Ndlovu', 'South Africa', 'n.d@gmail.com'),
          ('Duncan', 'Smale', 'South Africa', 'd.s@gmail.com'),
          ('Peter', 'Ryan', 'UK', 'p.r@gmail.com'),
          ('Dave', 'Martin', 'Switzerland', 'x.m@gmail.com');

INSERT INTO books(`authorId`, `title`, `publishedDate`, `publisher`, `ISBN`)
VALUES 	(1, 'Happy Peter and the Wizard of Escabar', '2021-01-03', 'Penguin Books', '0-2487-9445-0'),
          (2, 'The Face of the Eight', '2022-04-03', 'Exclusive Books', '0-8665-9950-9'),
          (3, 'Tapped for Sorrow', '2019-09-27', 'Jonathan Ball', '0-6351-1148-9'),
          (1, 'The Fall of the Orb', '2018-09-27', 'Penguin Books', '0-5276-3614-2'),
          (2, 'An Inconvenient Attachment', '2019-08-27', 'JExclusive Books', '0-5217-2518-6'),
          (1, 'Clue of the Artificial Staircase', '2019-09-22', 'Penguin Books', '0-7962-2368-8'),
          (2, 'Sign of the Crooked Footprint', '2013-01-21', 'Exclusive Books', '0-9354-1374-X'),
          (1, 'The Oracle in the Dusk', '2016-09-27', 'Penguin Books', '0-6582-6326-9'),
          (2, 'Crime of the Misshapen Librarian', '2012-06-26', 'Jonathan Ball', '0-7520-6750-8')