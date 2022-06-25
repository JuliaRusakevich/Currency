CREATE TABLE IF NOT EXISTS currency_guide.currencies
(
    id               SERIAL PRIMARY KEY,
    title            VARCHAR(155) NOT NULL,
    description      VARCHAR(155) NOT NULL,
    code             VARCHAR(10)  NOT NULL,
    creation_version TIMESTAMP,
    update_version   TIMESTAMP
);