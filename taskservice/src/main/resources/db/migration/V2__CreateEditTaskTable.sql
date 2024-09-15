CREATE TABLE IF NOT EXISTS edit_task(
    id SERIAL PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    task_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (task_id) REFERENCES task(id) ON DELETE CASCADE,
    edit_time TIMESTAMP NOT NULL
);