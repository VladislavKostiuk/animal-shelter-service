INSERT INTO animal_type (id, name)
VALUES (1, 'cat'),
       (2, 'dog'),
       (3, 'parrot');

INSERT INTO breed (id, name, animal_id)
VALUES (1, 'Abyssinian', 1),
       (2, 'Burmilla', 1),
       (3, 'Korat', 1),
       (4, 'American Bulldog', 2),
       (5, 'Chow Chow', 2),
       (6, 'Indian Spitz', 2),
       (7, 'Arinae', 3),
       (8, 'Cacatuidae', 3),
       (9, 'Microglossini', 3);

INSERT INTO color(id, name)
VALUES (1, 'black'),
       (2, 'grey'),
       (3, 'white'),
       (4, 'orange'),
       (5, 'brown'),
       (6, 'two-color'),
       (7, 'three-color');

INSERT INTO cat (id, name, birthday, breed_id, color_id, gender, info)
VALUES (1, 'Emma', '2024-02-01', 3, 3, 'FEMALE', ''),
       (2, 'Tommy', '2023-12-20', 2, 6, 'MALE', ''),
       (3, 'Lola', '2024-05-10', 3, 6, 'FEMALE', ''),
       (4, 'Minnie', '2023-10-23', 1, 7, 'FEMALE', ''),
       (5, 'Chip', '2024-05-18', 1, 1, 'MALE', '');

INSERT INTO dog (id, name, birthday, breed_id, color_id, gender, info)
VALUES (1, 'Pudding', '2024-03-11', 4, 1, 'MALE', ''),
       (2, 'Lollipop', '2024-05-10', 5, 1, 'MALE', ''),
       (3, 'Twinkle', '2024-06-03', 4, 2, 'FEMALE', ''),
       (4, 'Peanut', '2023-09-24', 5, 3, 'MALE', ''),
       (5, 'Starlight', '2024-01-10', 6, 3, 'FEMALE', '');

INSERT INTO parrot (id, name, birthday, breed_id, color_id, gender, info)
VALUES (1, 'Fido', '2021-11-28', 9, 6, 'MALE', ''),
       (2, 'Fluffy', '2019-09-07', 9, 6, 'MALE', ''),
       (3, 'Lassie', '2024-06-06', 8, 7, 'FEMALE', ''),
       (4, 'Lucky', '2023-06-25', 8, 7, 'MALE', ''),
       (5, 'Kate', '2024-03-14', 7, 3, 'FEMALE', '');