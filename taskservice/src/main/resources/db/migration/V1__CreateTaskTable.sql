CREATE TABLE IF NOT EXISTS task (
    id VARCHAR(255) PRIMARY KEY,
    status VARCHAR(255) NOT NULL,
    summary VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    creator VARCHAR(255) NOT NULL,
    assignee VARCHAR(255) NULL,
    creation_date TIMESTAMP NOT NULL,
    deadline TIMESTAMP NULL,
    priority varchar(255) null
);