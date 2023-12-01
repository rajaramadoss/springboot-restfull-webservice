package com.example.project.controller;


import com.example.project.dto.UserDto;
import com.example.project.entity.User;
import com.example.project.exception.ErrorDetails;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    //build create USER REST API
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        System.out.println(" :: create User :: ");
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);

    }
    //build GET USER BY ID REST API
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id")  Long userId){
    UserDto user=userService.getUserById(userId);
    return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> users= userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody  UserDto user){
        user.setId(userId);
    UserDto updateUser=userService.updateUser(user);
    return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
    userService.deleteUser(userId);
    return new ResponseEntity<>("User Successfully Deleted :: ",HttpStatus.OK);
    }
/*
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){

        ErrorDetails errorDetails=new ErrorDetails(
                LocalDateTime.now(), exception.getMessage(),webRequest.getDescription(false),"USER_NOT_FOUND"
        );

        return  new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }*/
}
