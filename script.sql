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

INSERT INTO address (id,apart_nr, build_nr, city, code, country, province, street) VALUES
  ('1', NULL , '98','Wrocław','07-880','Poland','Dolnośląkie','Sztabowa'),
  ('2', NULL , '51-55', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Eluarda'),
  ('3', NULL , '43', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Morelowskiego'),
  ('4', NULL , '105', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Wieczysta'),
  ('5', NULL , '8','Wrocław','07-880','Poland','Dolnośląkie','Namysłowska'),
  ('6', NULL , '21', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Lwowska'),
  ('7', NULL , '3', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Suwalska'),
  ('8', NULL , '9-11', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Głogowska'),
  ('9', NULL , '16','Wrocław','07-880','Poland','Dolnośląkie','Wielkopolska'),
  ('10', NULL , '7', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Jelenia'),
  ('11', NULL , '286', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Krzywoustego'),
  ('12', NULL , '1', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Rękodzielnicza'),
  ('13', NULL , '17','Wrocław','07-880','Poland','Dolnośląkie','Kolista'),
  ('14', NULL , '8', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Gałczyńskiego'),
  ('15', NULL , '236a', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Grabiszyńska'),
  ('16', '/1a' , '34', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Majakowskiego'),
  ('17', NULL , '8-10','Wrocław','07-880','Poland','Dolnośląkie','Chociebuska'),
  ('18', NULL , '29-31', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Ikara'),
  ('19', NULL , '85a', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Olszewskiego'),
  ('20', NULL , '13', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Łokietka'),
  ('21', NULL , '1-3','Wrocław','07-880','Poland','Dolnośląkie','Reja'),
  ('22', NULL , '88', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Strachocińska'),
  ('23', NULL , '26', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', '9 Maja'),
  ('24', NULL , '54a', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Sempołowskiej'),
  ('25', NULL , '59','Wrocław','07-880','Poland','Dolnośląkie','Przybyszewskiego'),
  ('26', NULL , '48', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Sołtysowicka'),
  ('27', NULL , '5a', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Serbska'),
  ('28', NULL , '210', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Powstańców Śl.'),
  ('29', NULL , '82', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Traugutta'),
  ('30', NULL , '1', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Tarnogórska'),
  ('31', NULL , '4','Wrocław','07-880','Poland','Dolnośląkie','Polna'),
  ('32', NULL , '13', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Hercena'),
  ('33', NULL , '22', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Jesienna'),
  ('34', NULL , '2', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Zielińskiego'),
  ('35', NULL , '78', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Szewska '),
  ('36', NULL , '5', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Teatralny'),
  ('37', NULL , '127','Wrocław','07-880','Poland','Dolnośląkie','Osobowicka'),
  ('38', NULL , '1-5', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Wróblewskiego'),
  ('39', NULL , '10', 'Wrocław', '07-880', 'Poland', 'Dolnośląkie', 'Wittiga');

INSERT INTO institution (name, contact, type, url) VALUES
  ('BibliotekaPublicznaWrocław','070088074 zadzwoń teraz','l','katalog.biblioteka.wroc.pl'),
  ('BibliotekaPWr','Jak będziesz chciał to znajdziesz numer','l','katalog.bg.pwr.wroc.pl');

INSERT INTO department (id, code, address_id, institution_name) VALUES
  ('1', '01', '1', 'BibliotekaPublicznaWrocław'),
  ('2', '02', '2', 'BibliotekaPublicznaWrocław'),
  ('3', '03', '3', 'BibliotekaPublicznaWrocław'),
  ('4', '04', '4', 'BibliotekaPublicznaWrocław'),
  ('5', '05', '5', 'BibliotekaPublicznaWrocław'),
  ('6', '06', '6', 'BibliotekaPublicznaWrocław'),
  ('7', '07', '7', 'BibliotekaPublicznaWrocław'),
  ('8', '08', '8', 'BibliotekaPublicznaWrocław'),
  ('9', '09', '9', 'BibliotekaPublicznaWrocław'),
  ('10', '10', '10', 'BibliotekaPublicznaWrocław'),
  ('11', '11', '11', 'BibliotekaPublicznaWrocław'),
  ('12', '15', '12', 'BibliotekaPublicznaWrocław'),
  ('13', '16', '13', 'BibliotekaPublicznaWrocław'),
  ('14', '17', '14', 'BibliotekaPublicznaWrocław'),
  ('15', '18', '15', 'BibliotekaPublicznaWrocław'),
  ('16', '20', '16', 'BibliotekaPublicznaWrocław'),
  ('17', '22', '17', 'BibliotekaPublicznaWrocław'),
  ('18', '23', '18', 'BibliotekaPublicznaWrocław'),
  ('19', '26', '19', 'BibliotekaPublicznaWrocław'),
  ('20', '27', '20', 'BibliotekaPublicznaWrocław'),
  ('21', '29', '21', 'BibliotekaPublicznaWrocław'),
  ('22', '31', '22', 'BibliotekaPublicznaWrocław'),
  ('23', '32', '23', 'BibliotekaPublicznaWrocław'),
  ('24', '37', '24', 'BibliotekaPublicznaWrocław'),
  ('25', '40', '25', 'BibliotekaPublicznaWrocław'),
  ('26', '41', '26', 'BibliotekaPublicznaWrocław'),
  ('27', '42', '27', 'BibliotekaPublicznaWrocław'),
  ('28', '44', '28', 'BibliotekaPublicznaWrocław'),
  ('29', '45', '29', 'BibliotekaPublicznaWrocław'),
  ('30', '46', '30', 'BibliotekaPublicznaWrocław'),
  ('31', '47', '31', 'BibliotekaPublicznaWrocław'),
  ('32', '53', '32', 'BibliotekaPublicznaWrocław'),
  ('33', '54', '33', 'BibliotekaPublicznaWrocław'),
  ('34', '55', '34', 'BibliotekaPublicznaWrocław'),
  ('35', '57', '35', 'BibliotekaPublicznaWrocław'),
  ('36', '58', '36', 'BibliotekaPublicznaWrocław'),
  ('37', '64', '37', 'BibliotekaPublicznaWrocław'),
  ('38', '69', '38', 'BibliotekaPublicznaWrocław'),
  ('39', '70', '39', 'BibliotekaPublicznaWrocław');

INSERT INTO reader (id, name, surname, login, password, email, salt, address_id) VALUES
  ('1', 'Adam', '' ,'Adam' ,'Adam' ,'Adam@gmail.com','',NULL),
  ('2', 'Ewa', '' ,'Ewa' ,'Ewa' ,'Ewa@gmail.com','','3'),
  ('3', 'Kain', '' ,'Kain' ,'Kain' ,'Kain@gmail.com','','5'),
  ('4', 'Abel', '' ,'Abel' ,'Abel' ,'Abel@gmail.com','','7'),
  ('5', 'Waldek', 'Dusza' ,'Waldek' ,'Waldek' ,'Waldek@gmail.com','',NULL),
  ('6', 'Janusz', 'Golara' ,'Janusz' ,'Janusz' ,'Janusz@gmail.com','','9'),
  ('7', 'Julia', '' ,'Julia' ,'Julia' ,'Julia@gmail.com','','11'),
  ('8', 'Anita', '' ,'Anita' ,'Anita' ,'Anita@gmail.com','','13'),
  ('9', 'Monika', '' ,'Monika' ,'Monika' ,'Monika@gmail.com','',NULL),
  ('10', 'Dominika', '' ,'Dominika' ,'Dominika' ,'Dominika@gmail.com','','15'),
  ('11', 'Andrzej', '' ,'Andrzej' ,'Andrzej' ,'Andrzej@gmail.com','','17'),
  ('12', 'Tomek', '' ,'Tomek' ,'Tomek' ,'Tomek@gmail.com','','19'),
  ('13', 'Piotrek', '' ,'Piotrek' ,'Piotrek' ,'Piotrek@gmail.com','',NULL),
  ('14', 'Filip', '' ,'Filip' ,'Filip' ,'Filip@gmail.com','','21'),
  ('15', 'Antoni', '' ,'Antoni' ,'Antoni' ,'Antoni@gmail.com','','23'),
  ('16', 'Ela', '' ,'Ela' ,'Ela' ,'Ela@gmail.com','','25'),
  ('17', 'Helga', '' ,'Helga' ,'Helga' ,'Helga@gmail.com','',NULL),
  ('18', 'Fiona', '' ,'Fiona' ,'Fiona' ,'Fiona@gmail.com','','27'),
  ('19', 'Tomek', '' ,'Tomek' ,'Tomek' ,'Tomek@gmail.com','','29'),
  ('20', 'Hilda', '' ,'Hilda' ,'Hilda' ,'Hilda@gmail.com','','31');

INSERT INTO book (id, title, author, path, status, format, owner_type, user_id, department_id) VALUES
  ('1', 'Krzyżacy', 'Henryk Sienkiewicz' ,'półka' ,'TRUE','b' ,'l','1',NULL),
  ('2', 'Krzyżacy', 'Henryk Sienkiewicz' ,'półka' ,'FALSE','b' ,'l','2',NULL),
  ('3', 'Krzyżacy', 'Henryk Sienkiewicz' ,'półka' ,'TRUE','b' ,'u',NULL,'3'),
  ('4', 'Potop', 'Henryk Sienkiewicz' ,'NULL' ,'TRUE','e' ,'u',NULL,'5'),
  ('5', 'Potop', 'Henryk Sienkiewicz' ,'NULL' ,'FALSE','e' ,'l','3',NULL),
  ('6', 'Potop', 'Henryk Sienkiewicz' ,'półka' ,'FALSE','b' ,'l','4',NULL),
  ('7', 'Potop', 'Henryk Sienkiewicz' ,'półka' ,'TRUE','b' ,'u',NULL,'7'),
  ('8', 'Lalka', 'Bolesław Prus' ,'półka' ,'FALSE','b' ,'u',NULL,'9'),
  ('9', 'Lalka', 'Bolesław Prus' ,'NULL' ,'TRUE','e' ,'l','5',NULL),
  ('10', 'Lalka', 'Bolesław Prus' ,'NULL' ,'FALSE','e' ,'l','6',NULL),
  ('11', 'Lalka', 'Bolesław Prus' ,'półka' ,'TRUE','b' ,'u',NULL,'11'),
  ('12', 'Dziady', 'Adam Mickiewicz' ,'półka' ,'TRUE','b' ,'u',NULL,'13'),
  ('13', 'Dziady', 'Adam Mickiewicz' ,'półka' ,'FALSE','b' ,'l','7',NULL),
  ('14', 'Dziady', 'Adam Mickiewicz' ,'NULL' ,'FALSE','e' ,'l','8',NULL),
  ('15', 'Wiedźmin', 'Andrzej Sapkowski' ,'NULL' ,'TRUE','e' ,'u',NULL,'15'),
  ('16', 'Wiedźmin', 'Andrzej Sapkowski' ,'półka' ,'FALSE','b' ,'u',NULL,'17'),
  ('17', 'Wiedźmin', 'Andrzej Sapkowski' ,'półka' ,'TRUE','b' ,'l','9',NULL),
  ('18', 'Wiedźmin', 'Andrzej Sapkowski' ,'półka' ,'FALSE','b' ,'l','10',NULL),
  ('19', 'Wiedźmin', 'Andrzej Sapkowski' ,'NULL' ,'TRUE','e' ,'u',NULL,'19'),
  ('20', 'Wiedźmin', 'Andrzej Sapkowski' ,'NULL' ,'TRUE','e' ,'u',NULL,'21'),
  ('21', 'Docker', 'Docker', 'https://drive.google.com/open?id=0Bzr8nT5GxpuDRnE3RmVLdUdDM1U', 'FALSE', 'e', 'u', '1', NULL ),
  ('22', 'Java8', 'Java8', 'https://drive.google.com/open?id=0Bzr8nT5GxpuDSEtQQi1PZy1tUDg', 'FALSE', 'e', 'u', '1', NULL );

INSERT INTO borrowed (id, book_id, borrower_id, name, email, facebook, message, date_start, date_stop) VALUES
  ('1','1', '2', NULL , NULL ,NULL , NULL,'2017-04-14','2017-05-14'),
  ('2','2', '3', NULL , NULL ,NULL , NULL, '2017-05-08','2017-06-08'),
  ('3','4', NULL, 'Ewa' ,'Ewa@gmail.com' ,'EwaTy' ,'NULL','2017-05-02','2017-06-02'),
  ('4','5', '4', NULL , NULL ,NULL , 'Oddasz jak przyjdą szfedy','2017-04-26','2017-05-26'),
  ('5','6', '5', NULL , NULL ,NULL , NULL,'2017-05-15','2017-06-15'),
  ('6','8', NULL, 'Monika' ,'Monika@gmail.com' ,'MoniTo' ,'NULL','2017-05-15','2017-06-15'),
  ('7','9', '6', NULL , NULL ,NULL , NULL,'2017-04-26','2017-05-26'),
  ('8','10', '7', NULL , NULL ,NULL ,'Tylko nie ubródź','2017-05-02','2017-06-02'),
  ('9','12', NULL, 'Hubert' ,'Hubert@gmail.com' ,'HubKul' ,'NULL','2017-05-08','2017-06-08'),
  ('10','15', '8', NULL , NULL ,NULL , NULL,'2017-04-21','2017-05-21');

INSERT INTO friend (friend1_id, friend2_id, friend1allow, friend2allow, friend_confirm) VALUES
  (1,2,FALSE, FALSE, FALSE),
  (1,3,TRUE , FALSE, FALSE),
  (1,4,FALSE, TRUE, FALSE),
  (1,5,FALSE, FALSE, TRUE),
  (1,6,TRUE, FALSE, TRUE),
  (1,7,FALSE, TRUE, TRUE),
  (1,8,FALSE, TRUE, TRUE),
  (9,2,FALSE, FALSE, FALSE),
  (9,3,TRUE , FALSE, FALSE),
  (9,4,FALSE, TRUE, FALSE),
  (9,5,FALSE, FALSE, TRUE),
  (9,6,TRUE, FALSE, TRUE),
  (9,7,FALSE, TRUE, TRUE),
  (9,1,TRUE, TRUE, TRUE);