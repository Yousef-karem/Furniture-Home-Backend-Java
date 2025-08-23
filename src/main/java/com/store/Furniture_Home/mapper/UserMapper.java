package com.store.Furniture_Home.mapper;

import com.store.Furniture_Home.dto.RegisterDto;
import com.store.Furniture_Home.entites.Cart;
import com.store.Furniture_Home.entites.Role;
import com.store.Furniture_Home.entites.User;

public class UserMapper {
    static public User toUserRegister(RegisterDto registerDto)
    {
        //String name, String email, String password, int phone, Role role, Cart cart
        User user=new User(registerDto.getName()
                ,registerDto.getEmail()
                ,registerDto.getPassword(),
                registerDto.getPhone(),
                Role.Customer,
                new Cart());
        return user;
    }
}
