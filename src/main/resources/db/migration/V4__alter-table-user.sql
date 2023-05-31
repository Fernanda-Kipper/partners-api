ALTER TABLE users ADD role TEXT;
UPDATE users SET role = 'user';