
CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    created TIMESTAMP NOT NULL,
    modified TIMESTAMP,
    last_login TIMESTAMP NOT NULL,
    inactive BOOLEAN NOT NULL
);

CREATE INDEX idx_users_email ON users (email);

CREATE TABLE phones (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    number VARCHAR(15) NOT NULL,
    citycode VARCHAR(5) NOT NULL,
    contrycode VARCHAR(5) NOT NULL,
    usuario_id UUID NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES users(id) ON DELETE CASCADE
);