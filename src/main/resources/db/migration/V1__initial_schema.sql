CREATE TABLE users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       phone VARCHAR(50),
                       role VARCHAR(50),
                       created_at DATETIME,
                       updated_at DATETIME
);

CREATE TABLE pet (
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     name VARCHAR(255),
                     species VARCHAR(255),
                     breed VARCHAR(255),
                     age INT,
                     size VARCHAR(50),
                     gender VARCHAR(50),
                     description TEXT,
                     neutered BOOLEAN,
                     vaccinated BOOLEAN,
                     health_notes VARCHAR(500),
                     status VARCHAR(50),
                     owner_id BIGINT,
                     created_at DATETIME,
                     CONSTRAINT fk_owner FOREIGN KEY (owner_id) REFERENCES users(id)
);