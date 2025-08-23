-- Default admin user creation
-- This will run automatically when the application starts
-- Password is BCrypt encoded "admin123"

INSERT INTO cart (total_no_of_items, total_price) VALUES (0, 0.0);

INSERT INTO user (name, email, password, phone, role, cart_id) 
VALUES (
    'System Administrator',
    'admin@furniture.com',
    '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa',
    '1234567890',
    'Admin',
    (SELECT id FROM cart WHERE total_no_of_items = 0 AND total_price = 0.0 LIMIT 1)
)
ON DUPLICATE KEY UPDATE name = VALUES(name), role = VALUES(role);
