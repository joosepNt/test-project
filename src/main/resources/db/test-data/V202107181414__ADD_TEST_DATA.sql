-- TestuserX password is passwordX
INSERT INTO User (username, password)
VALUES ('TestUser1', '$2y$12$fsEotlsDsC3JipWIHGsSA.OI.HNPNour4ghuDOEaZviUtYs2tuuKq'),
       ('TestUser2', '$2y$12$t93lRNuYC.Wo27JeJ3tCueNkNxp981MEiGbCdL6Iifeft4EPScgKi'),
       ('TestUser3', '$2y$12$CamlsDaVzXAEWDqCEMXiveiS9AFJxHY5pKabzWS0hjeCyxVVEsnGO'),
       ('TestUser4', '$2y$12$t6jLnojZ4vWFsadGF2j0ZunajjzpKbshS2vZckhbbZD5x.lqm.aPO');

INSERT INTO Country (name)
VALUES ('Estonia'),
       ('Latvia'),
       ('Lithuania'),
       ('Finland'),
       ('Sweden');

INSERT INTO Client (first_name, last_name, username, email, address, country, managed_by)
VALUES ('John', 'Smith', 'User1', 'john.smith@pseudomail.com', '3051 Wildwood Street', 1, 2),
       ('Bob', 'Smith', 'User2', 'bob.smith@pseudomail.com', '4575 Upland Avenue', 2, 2),
       ('Billy', 'Smith', 'User3', 'billy.smith@pseudomail.com', '3388 Camel Back Road', 4, 2),
       ('John', 'Doe', 'User4', 'john.doe@pseudomail.com', '3388 Camel Back Road', 4, 1),
       ('Billy', 'Buffalo', 'User5', 'billy.buffalo@pseudomail.com', '3222 Buffalo Street', 4, 1);
