CREATE TABLE IF NOT EXISTS api_token (
    id VARCHAR(255) PRIMARY KEY,
    user_id VARCHAR(255),
    expiery_date TIMESTAMP NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    is_active VARCHAR(255) NOT NULL,
    token VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);