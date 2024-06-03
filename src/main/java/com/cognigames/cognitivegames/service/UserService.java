package com.cognigames.cognitivegames.service;

import com.cognigames.cognitivegames.dto.UserDTO;
import com.cognigames.cognitivegames.model.UserEntity;
import com.cognigames.cognitivegames.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO saveUser(UserDTO userDTO) {
        UserEntity user = modelMapper.map(userDTO, UserEntity.class);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        return modelMapper.map(user, UserDTO.class);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}