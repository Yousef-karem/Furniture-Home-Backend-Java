# JWT Authentication and Role-Based Access Control Guide

## Overview
This Spring Boot application now includes JWT (JSON Web Token) authentication and role-based access control. Users receive a JWT token upon successful login, which they must include in subsequent requests to access protected endpoints.

## How It Works

### 1. Registration
- **Endpoint**: `POST /api/register`
- **Access**: Public (no authentication required)
- **Body**: 
```json
{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "password123",
    "phone": 1234567890,
    "role": "Customer"
}
```

### 2. Login
- **Endpoint**: `POST /api/login`
- **Access**: Public (no authentication required)
- **Body**:
```json
{
    "email": "john@example.com",
    "password": "password123"
}
```
- **Response**: Returns a JWT token
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "message": "Login successful",
    "userEmail": "john@example.com",
    "userRole": "Customer"
}
```

### 3. Using the JWT Token
After receiving the token, include it in the `Authorization` header for all subsequent requests:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

## Protected Endpoints

### Admin-Only Endpoints (Requires Admin Role)
- `GET /api/admin/dashboard` - Admin dashboard
- `GET /api/admin/users` - List all users
- `POST /api/admin/users/{userId}/role` - Update user role
- `DELETE /api/admin/users/{userId}` - Delete user
- `GET /api/admin/statistics` - System statistics
- `POST /api/admin/maintenance` - Perform maintenance

### Customer-Only Endpoints (Requires Customer Role)
- `GET /api/customer/profile` - Customer profile
- `GET /api/customer/orders` - Customer orders
- `POST /api/customer/orders` - Create order
- `GET /api/customer/cart` - Customer cart
- `PUT /api/customer/cart/add` - Add item to cart

### General User Endpoints (Requires Any Authentication)
- `GET /api/user/info` - User information
- `PUT /api/user/profile` - Update profile
- `GET /api/user/settings` - User settings
- `POST /api/user/logout` - Logout

## Security Features

1. **JWT Token Expiration**: Tokens expire after 24 hours
2. **Role-Based Access**: Different endpoints require different user roles
3. **Stateless Authentication**: No server-side session storage
4. **Password Encryption**: Passwords are encrypted using BCrypt
5. **CSRF Protection**: Disabled for API endpoints

## Testing the System

### 1. Register a User
```bash
curl -X POST http://localhost:8080/api/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Admin User",
    "email": "admin@example.com",
    "password": "admin123",
    "phone": 1234567890,
    "role": "Admin"
  }'
```

### 2. Login to Get Token
```bash
curl -X POST http://localhost:8080/api/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@example.com",
    "password": "admin123"
  }'
```

### 3. Use Token to Access Admin Endpoint
```bash
curl -X GET http://localhost:8080/api/admin/dashboard \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE"
```

## Error Handling

- **401 Unauthorized**: Invalid or missing JWT token
- **403 Forbidden**: Valid token but insufficient role permissions
- **404 Not Found**: Endpoint doesn't exist or user not found

## Important Notes

1. **Token Storage**: Store the JWT token securely (e.g., in localStorage for web apps, secure storage for mobile apps)
2. **Token Refresh**: Implement token refresh logic for production applications
3. **Secret Key**: The JWT secret key in `JWTService` should be changed in production
4. **HTTPS**: Always use HTTPS in production for secure token transmission

## Database Schema
Make sure your database has the following tables:
- `user` table with fields: id, name, email, password, phone, role, cart_id
- `role` enum with values: Admin, Customer
- `cart` table for user shopping carts
