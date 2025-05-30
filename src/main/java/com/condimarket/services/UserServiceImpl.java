package com.condimarket.services;

import com.condimarket.dto.UserDto;
import com.condimarket.persistence.entities.User;
import com.condimarket.persistence.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
                this.userRepository = userRepository;
                this.passwordEncoder = passwordEncoder;
        }

        @Override
        public List<UserDto> getAllUsers() {
                return userRepository.findAll().stream()
                        .map(user -> UserDto.builder()
                                .id(user.getId())
                                .name(user.getName())
                                .email(user.getEmail())
                                .phone(user.getPhone())
                                .address(user.getAddress())
                                .build())
                        .collect(Collectors.toList());
        }

        @Override
        public UserDto createUser(UserDto dto) {
                String encodedPassword = passwordEncoder.encode(dto.getPassword());

                User user = User.builder()
                        .name(dto.getName())
                        .email(dto.getEmail())
                        .password(encodedPassword)
                        .phone(dto.getPhone())
                        .address(dto.getAddress())
                        .build();

                User saved = userRepository.save(user);

                return UserDto.builder()
                        .id(saved.getId())
                        .name(saved.getName())
                        .email(saved.getEmail())
                        .phone(saved.getPhone())
                        .address(saved.getAddress())
                        .build();
        }


        @Override
        public UserDto getUserById(Long id) {
                return userRepository.findById(id)
                        .map(user -> UserDto.builder()
                                .id(user.getId())
                                .name(user.getName())
                                .email(user.getEmail())
                                .phone(user.getPhone())
                                .address(user.getAddress())
                                .build())
                        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        }

        @Override
        public UserDto updateUser(Long id, UserDto dto) {
                return userRepository.findById(id)
                        .map(user -> {
                        user.setName(dto.getName());
                        user.setEmail(dto.getEmail());
                        User updated = userRepository.save(user);
                        return UserDto.builder()
                                .id(updated.getId())
                                .name(updated.getName())
                                .email(updated.getEmail())
                                .phone(updated.getPhone())
                                .address(updated.getAddress())
                                .build();
                        })
                        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        }

}
