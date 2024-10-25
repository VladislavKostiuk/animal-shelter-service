CREATE TABLE IF NOT EXISTS animal_type
(
    id   BIGSERIAL NOT NULL,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS color
(
    id        BIGSERIAL NOT NULL,
    name      VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS breed
(
    id        BIGSERIAL NOT NULL,
    name      VARCHAR(255),
    animal_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (animal_id)
        REFERENCES animal_type (id)
);

CREATE TABLE IF NOT EXISTS cat
(
    id       BIGSERIAL NOT NULL,
    name     VARCHAR(255),
    birthday DATE,
    breed_id INT,
    color_id INT,
    gender   VARCHAR(255),
    info     VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (breed_id)
        REFERENCES breed (id),
    FOREIGN KEY (color_id)
        REFERENCES color (id)
);

CREATE TABLE IF NOT EXISTS dog
(
    id       BIGSERIAL NOT NULL,
    name     VARCHAR(255),
    birthday DATE,
    breed_id INT,
    color_id INT,
    gender   VARCHAR(255),
    info     VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (breed_id)
        REFERENCES breed (id),
    FOREIGN KEY (color_id)
        REFERENCES color (id)
);

CREATE TABLE IF NOT EXISTS parrot
(
    id       BIGSERIAL NOT NULL,
    name     VARCHAR(255),
    birthday DATE,
    breed_id INT,
    color_id INT,
    gender   VARCHAR(255),
    info     VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (breed_id)
        REFERENCES breed (id),
    FOREIGN KEY (color_id)
        REFERENCES color (id)
)




