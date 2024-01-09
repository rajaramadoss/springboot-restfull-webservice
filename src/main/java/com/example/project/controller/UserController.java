package com.example.project.controller;


import com.example.project.dto.UserDto;
import com.example.project.entity.User;
import com.example.project.exception.ErrorDetails;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;


@Tag(
        name = "CRUD REST API for User Resource",
        description = "CRUD REST APIs - Create User,Update User,Get User,Get All Users,Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    @Operation(summary = "Create User Rest API" , description = "Create User REST API is used to save user in a database")
    @ApiResponse(responseCode = "201",description = "Http Status 201 CREATED")
    //build create USER REST API
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user){//
        System.out.println(" :: create User :: ");
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);

    }
    @Operation(summary = "Get User by ID Rest API" , description = "Get User by ID Rest API is used to get a single user from the database")
    @ApiResponse(responseCode = "200",description = "Http Status 200 SUCCESS")
    //build GET USER BY ID REST API
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id")  Long userId){
    UserDto user=userService.getUserById(userId);
    return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @Operation(summary = "Get All User Rest API" , description = "Get All Users Rest API is used to get a all the users from the database")
    @ApiResponse(responseCode = "200",description = "Http Status 200 SUCCESS")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> users= userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @Operation(summary = "Get Update User Rest API" , description = "update Users Rest API is used to update a perticular users in the database")
    @ApiResponse(responseCode = "200",description = "Http Status 200 SUCCESS")
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody @Valid  UserDto user){
        user.setId(userId);
    UserDto updateUser=userService.updateUser(user);
    return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }

    @Operation(summary = "Delete User Rest API" , description = "Delete Users Rest API is used to delete a perticular users from the database")
    @ApiResponse(responseCode = "200",description = "Http Status 200 SUCCESS")
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
