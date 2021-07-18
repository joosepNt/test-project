CREATE TABLE IF NOT EXISTS Client
(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(320),
    address VARCHAR(500) NOT NULL,
    country BIGINT NOT NULL,
    managed_by BIGINT NOT NULL,
    FOREIGN KEY (country) REFERENCES Country (id),
    FOREIGN KEY (managed_by) REFERENCES User (id)
);
