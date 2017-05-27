DELETE FROM tag_book;
DELETE FROM tag;
DELETE FROM borrowed;
DELETE FROM contact;
DELETE FROM book;
DELETE FROM reader;
DELETE FROM department;
DELETE FROM institution;
DELETE FROM address;

SELECT setval('address_id_seq', 1, false);
SELECT setval('book_id_seq', 1, false);
SELECT setval('borrowed_id_seq', 1, false);
SELECT setval('department_id_seq', 1, false);
SELECT setval('reader_id_seq', 1, false);
SELECT setval('institution_id_seq', 1, false);

INSERT INTO address (apart_nr, build_nr, city, code, country, province, street) VALUES
  (NULL , '98','Wrocław','07-880','Poland','Dolnośląkie','Sztabowa'),
  (NULL , '51-55', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Eluarda'),
  (NULL , '43', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Morelowskiego'),
  (NULL , '105', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Wieczysta'),
  (NULL , '8','Wrocław','07-880','Poland','Dolnośląkie','Namysłowska'),
  (NULL , '21', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Lwowska'),
  (NULL , '3', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Suwalska'),
  (NULL , '9-11', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Głogowska'),
  (NULL , '16','Wrocław','07-880','Poland','Dolnośląkie','Wielkopolska'),
  (NULL , '7', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Jelenia'),
  (NULL , '286', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Krzywoustego'),
  (NULL , '1', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Rękodzielnicza'),
  (NULL , '17','Wrocław','07-880','Poland','Dolnośląkie','Kolista'),
  (NULL , '8', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Gałczyńskiego'),
  (NULL , '236a', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Grabiszyńska'),
  ('/1a' , '34', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Majakowskiego'),
  (NULL , '8-10','Wrocław','07-880','Poland','Dolnośląkie','Chociebuska'),
  (NULL , '29-31', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Ikara'),
  (NULL , '85a', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Olszewskiego'),
  (NULL , '13', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Łokietka'),
  (NULL , '1-3','Wrocław','07-880','Poland','Dolnośląkie','Reja'),
  (NULL , '88', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Strachocińska'),
  (NULL , '26', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', '9 Maja'),
  (NULL , '54a', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Sempołowskiej'),
  (NULL , '59','Wrocław','07-880','Poland','Dolnośląkie','Przybyszewskiego'),
  (NULL , '48', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Sołtysowicka'),
  (NULL , '5a', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Serbska'),
  (NULL , '210', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Powstańców Śl.'),
  (NULL , '82', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Traugutta'),
  (NULL , '1', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Tarnogórska'),
  (NULL , '4','Wrocław','07-880','Poland','Dolnośląkie','Polna'),
  (NULL , '13', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Hercena'),
  (NULL , '22', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Jesienna'),
  (NULL , '2', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Zielińskiego'),
  (NULL , '78', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Szewska '),
  (NULL , '5', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Teatralny'),
  (NULL , '127','Wrocław','07-880','Poland','Dolnośląkie','Osobowicka'),
  (NULL , '1-5', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Wróblewskiego'),
  (NULL , '10', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Wittiga');

INSERT INTO institution (name, contact, type, url) VALUES
  ('Biblioteka Publiczna Wrocław','070088074 zadzwoń teraz','l','katalog.biblioteka.wroc.pl'),
  ('Biblioteka PWr','Jak będziesz chciał to znajdziesz numer','l','katalog.bg.pwr.wroc.pl');

INSERT INTO department (code, address_id, institution_id) VALUES
  ('01', '1', '1'),
  ('02', '2', '1'),
  ('03', '3', '1'),
  ('04', '4', '1'),
  ('05', '5', '1'),
  ('06', '6', '1'),
  ('07', '7', '1'),
  ('08', '8', '1'),
  ('09', '9', '1'),
  ('10', '10', '1'),
  ('11', '11', '1'),
  ('15', '12', '1'),
  ('16', '13', '1'),
  ('17', '14', '1'),
  ('18', '15', '1'),
  ('20', '16', '1'),
  ('22', '17', '1'),
  ('23', '18', '1'),
  ('26', '19', '1'),
  ('27', '20', '1'),
  ('29', '21', '1'),
  ('31', '22', '1'),
  ('32', '23', '1'),
  ('37', '24', '1'),
  ('40', '25', '1'),
  ('41', '26', '1'),
  ('42', '27', '1'),
  ('44', '28', '1'),
  ('45', '29', '1'),
  ('46', '30', '1'),
  ('47', '31', '1'),
  ('53', '32', '1'),
  ('54', '33', '1'),
  ('55', '34', '1'),
  ('57', '35', '1'),
  ('58', '36', '1'),
  ('64', '37', '1'),
  ('69', '38', '1'),
  ('70', '39', '1');

INSERT INTO reader (name, surname, login, password, email, salt, address_id) VALUES
  ('Adam', '' ,'Adam' ,'Adam' ,'Adam@gmail.com','',NULL),
  ('Ewa', '' ,'Ewa' ,'Ewa' ,'Ewa@gmail.com','','3'),
  ('Kain', '' ,'Kain' ,'Kain' ,'Kain@gmail.com','','5'),
  ('Abel', '' ,'Abel' ,'Abel' ,'Abel@gmail.com','','7'),
  ('Waldek', 'Dusza' ,'Waldek' ,'Waldek' ,'Waldek@gmail.com','',NULL),
  ('Janusz', 'Golara' ,'Janusz' ,'Janusz' ,'Janusz@gmail.com','','9'),
  ('Julia', '' ,'Julia' ,'Julia' ,'Julia@gmail.com','','11'),
  ('Anita', '' ,'Anita' ,'Anita' ,'Anita@gmail.com','','13'),
  ('Monika', '' ,'Monika' ,'Monika' ,'Monika@gmail.com','',NULL),
  ('Dominika', '' ,'Dominika' ,'Dominika' ,'Dominika@gmail.com','','15'),
  ('Andrzej', '' ,'Andrzej' ,'Andrzej' ,'Andrzej@gmail.com','','17'),
  ('Tomek', '' ,'Tomek' ,'Tomek' ,'Tomek@gmail.com','','19'),
  ('Piotrek', '' ,'Piotrek' ,'Piotrek' ,'Piotrek@gmail.com','',NULL),
  ('Filip', '' ,'Filip' ,'Filip' ,'Filip@gmail.com','','21'),
  ('Antoni', '' ,'Antoni' ,'Antoni' ,'Antoni@gmail.com','','23'),
  ('Ela', '' ,'Ela' ,'Ela' ,'Ela@gmail.com','','25'),
  ('Helga', '' ,'Helga' ,'Helga' ,'Helga@gmail.com','',NULL),
  ('Fiona', '' ,'Fiona' ,'Fiona' ,'Fiona@gmail.com','','27'),
  ('Tomek', '' ,'Tomek' ,'Tomek' ,'Tomek@gmail.com','','29'),
  ('Hilda', '' ,'Hilda' ,'Hilda' ,'Hilda@gmail.com','','31');

INSERT INTO book (title, author, path, status, format, owner_type, user_id, department_id) VALUES
  ('Krzyżacy', 'Henryk Sienkiewicz' ,'półka' ,'TRUE','b' ,'l','1',NULL),
  ('Krzyżacy', 'Henryk Sienkiewicz' ,'półka' ,'FALSE','b' ,'l','2',NULL),
  ('Krzyżacy', 'Henryk Sienkiewicz' ,'półka' ,'TRUE','b' ,'u',NULL,'3'),
  ('Potop', 'Henryk Sienkiewicz' ,'NULL' ,'TRUE','e' ,'u',NULL,'5'),
  ('Potop', 'Henryk Sienkiewicz' ,'NULL' ,'FALSE','e' ,'l','3',NULL),
  ('Potop', 'Henryk Sienkiewicz' ,'półka' ,'FALSE','b' ,'l','4',NULL),
  ('Potop', 'Henryk Sienkiewicz' ,'półka' ,'TRUE','b' ,'u',NULL,'7'),
  ('Lalka', 'Bolesław Prus' ,'półka' ,'FALSE','b' ,'u',NULL,'9'),
  ('Lalka', 'Bolesław Prus' ,'NULL' ,'TRUE','e' ,'l','5',NULL),
  ('Lalka', 'Bolesław Prus' ,'NULL' ,'FALSE','e' ,'l','6',NULL),
  ('Lalka', 'Bolesław Prus' ,'półka' ,'TRUE','b' ,'u',NULL,'11'),
  ('Dziady', 'Adam Mickiewicz' ,'półka' ,'TRUE','b' ,'u',NULL,'13'),
  ('Dziady', 'Adam Mickiewicz' ,'półka' ,'FALSE','b' ,'l','7',NULL),
  ('Dziady', 'Adam Mickiewicz' ,'NULL' ,'FALSE','e' ,'l','8',NULL),
  ('Wiedźmin', 'Andrzej Sapkowski' ,'NULL' ,'TRUE','e' ,'u',NULL,'15'),
  ('Wiedźmin', 'Andrzej Sapkowski' ,'półka' ,'FALSE','b' ,'u',NULL,'17'),
  ('Wiedźmin', 'Andrzej Sapkowski' ,'półka' ,'TRUE','b' ,'l','9',NULL),
  ('Wiedźmin', 'Andrzej Sapkowski' ,'półka' ,'FALSE','b' ,'l','10',NULL),
  ('Wiedźmin', 'Andrzej Sapkowski' ,'NULL' ,'TRUE','e' ,'u',NULL,'19'),
  ('Wiedźmin', 'Andrzej Sapkowski' ,'NULL' ,'TRUE','e' ,'u',NULL,'21'),
  ('Docker', 'Docker', 'https://drive.google.com/open?id=0Bzr8nT5GxpuDRnE3RmVLdUdDM1U', 'FALSE', 'e', 'u', '1', NULL ),
  ('Java8', 'Java8', 'https://drive.google.com/open?id=0Bzr8nT5GxpuDSEtQQi1PZy1tUDg', 'FALSE', 'e', 'u', '1', NULL );

INSERT INTO borrowed (book_id, borrower_id, name, email, facebook, message, date_start, date_stop) VALUES
  ('1', '2', NULL , NULL ,NULL , NULL,'2017-04-14','2017-05-14'),
  ('2', '3', NULL , NULL ,NULL , NULL, '2017-05-08','2017-06-08'),
  ('4', NULL, 'Ewa' ,'Ewa@gmail.com' ,'EwaTy' ,'NULL','2017-05-02','2017-06-02'),
  ('5', '4', NULL , NULL ,NULL , 'Oddasz jak przyjdą szfedy','2017-04-26','2017-05-26'),
  ('6', '5', NULL , NULL ,NULL , NULL,'2017-05-15','2017-06-15'),
  ('8', NULL, 'Monika' ,'Monika@gmail.com' ,'MoniTo' ,'NULL','2017-05-15','2017-06-15'),
  ('9', '6', NULL , NULL ,NULL , NULL,'2017-04-26','2017-05-26'),
  ('10', '7', NULL , NULL ,NULL ,'Tylko nie ubródź','2017-05-02','2017-06-02'),
  ('12', NULL, 'Hubert' ,'Hubert@gmail.com' ,'HubKul' ,'NULL','2017-05-08','2017-06-08'),
  ('15', '8', NULL , NULL ,NULL , NULL,'2017-04-21','2017-05-21');

INSERT INTO friend (friend1_id, friend2_id, friend1allow, friend2allow, friendship_confirmed) VALUES
  (1,2,FALSE, FALSE, FALSE),
  (1,3,TRUE , FALSE, FALSE),
  (1,4,FALSE, TRUE, FALSE),
  (1,5,FALSE, FALSE, TRUE),
  (1,6,TRUE, FALSE, TRUE),
  (1,7,FALSE, TRUE, TRUE),
  (1,8,FALSE, TRUE, TRUE),
  (2,9,FALSE, FALSE, FALSE),
  (3,9,TRUE , FALSE, FALSE),
  (4,9,FALSE, TRUE, FALSE),
  (5,9,FALSE, FALSE, TRUE),
  (6,9,TRUE, FALSE, TRUE),
  (7,9,FALSE, TRUE, TRUE),
  (1,9,TRUE, TRUE, TRUE);