INSERT INTO
    App_user (firstName, lastName, email, password)
VALUES
    -- s28113@pjwstk.edu.pl/ Pjatk123 / ADMIN
    ('Piotr', 'Dancewicz', 's28113@pjwstk.edu.pl', '{bcrypt}$2a$10$cLj7OzLGBlQpF7bw4bMtIOVugCy95v2VrgKMe5k3UrGO5OVG01vo.'),
    -- alex@pja.edu.pl / TPOtask12 / LIBRARIAN
    ('Maciej', 'Kot', 'maci3j@gmail.com', '{MD5}{CzzFeDBLGEXsC5xOsQun7fM1LmFuX/oHiLfAlBFBylk=}308a9c767fd80e3ec182eb8ff1a5797f'),
    -- sb@wp.pl / Pjwstk0112? / PUBLISHER
    ('Szymon', 'Kowalski', 'sb@wp.pl', '{noop}Pjwstk0112?'),
    -- wkpl@gmail.com / - / READER
    ('Wojciech', 'Krawczyk', 'wkpl@gmail.com', '{SHA-256}{ga0mJM2HB2OUlBMbaUVsfL6YYyXLDuKOcTP4QdjUVaw=}89bc22c7dfeeccb5873c72ad8f5b74324f8c7448285ffe5fafa0115d045ced23'),
    -- guest@gmail.com / HelloGuest! / GUEST
    ('Dummy', 'Dummy', 'guest@gmail.com', '{noop}HelloGuest!');

INSERT INTO
    UserRole (name, description)
VALUES
    ('ADMIN', 'Administrative privileges'),
    ('LIBRARIAN', 'Administrative privileges regarding books'),
    ('PUBLISHER', 'Regular user privileges with possibility to add a book'),
    ('READER', 'Regular user privileges'),
    ('GUEST', 'No privileges');

INSERT INTO
    App_user_roles (User_id, roles_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5);