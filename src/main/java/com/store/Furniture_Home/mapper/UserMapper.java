package com.store.Furniture_Home.mapper;

import com.store.Furniture_Home.dto.RegisterDto;
import com.store.Furniture_Home.entites.Cart;
import com.store.Furniture_Home.entites.Role;
import com.store.Furniture_Home.entites.User;

public class UserMapper {
    static public User toUserRegister(RegisterDto registerDto)
    {
        // Create user with the new constructor (5 parameters)
        User user = new User(
            registerDto.getName(),
            registerDto.getEmail(),
            registerDto.getPassword(),
            registerDto.getPhone(),
            Role.Customer
        );
        
        // Create and set cart separately
        Cart cart = new Cart();
        user.setCart(cart);
        
        return user;
    }
}
