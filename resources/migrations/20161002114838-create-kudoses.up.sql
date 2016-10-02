CREATE TABLE kudoses (
  username VARCHAR(255),
  kudosed_by VARCHAR(255),
  reason VARCHAR(1000),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
