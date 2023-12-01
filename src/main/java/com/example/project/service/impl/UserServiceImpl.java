package com.example.project.service.impl;

import com.example.project.dto.UserDto;
import com.example.project.entity.User;
import com.example.project.exception.EmailAlreadyExistsException;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.mapper.AutoUserMapper;
import com.example.project.mapper.UserMapper;
import com.example.project.repository.UserRepository;
import com.example.project.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
    // convert UserDto into User JPA Entity
        //User user= UserMapper.mapToUser(userDto);
                /*new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()

        );*/
        Optional<User> optionalUser=userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exixts in the User");
        }

       // User user= modelMapper.map(userDto,User.class);// Model Mapper Logic
        User user= AutoUserMapper.MAPPER.mapToUser(userDto); // Mapstruct Logic


            User savedUser =userRepository.save(user);
            // Convert User JPA entity to UserDto
        //UserDto savedUserDto=UserMapper.mapToUserDto(savedUser);
                /*new UserDto(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail()
        );*/
        //UserDto savedUserDto=modelMapper.map(savedUser,UserDto.class); //Model Mapper Logic
        UserDto savedUserDto=AutoUserMapper.MAPPER.mapToUserDto(savedUser); // Map struct Logic
        return  savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user= userRepository.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("User","id",userId)
        );


        //return UserMapper.mapToUserDto(user);
        //return modelMapper.map(user,UserDto.class); // Model Mapper Logic
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
      //return   users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
        //return   users.stream().map((user)->modelMapper.map(user,UserDto.class)).collect(Collectors.toList());// ModelMapper Logic
        return   users.stream().map((user)->AutoUserMapper.MAPPER.mapToUserDto(user)).collect(Collectors.toList());//Map struct LOgic
    }

    @Override
    public UserDto updateUser(UserDto user) {
         User existingUser=userRepository.findById(user.getId()).orElseThrow(
                 ()->new ResourceNotFoundException("user","id", user.getId())

         );

        existingUser.setEmail(user.getEmail());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
       User updatedUser= userRepository.save(existingUser);
        //return UserMapper.mapToUserDto(updatedUser);
        //return modelMapper.map(updatedUser,UserDto.class);//Model Mapper Logic
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);//Map struct LOgic
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser=userRepository.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("user","id",userId)

        );

        userRepository.deleteById(userId);
    }
}
