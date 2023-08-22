package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.DTOs.SignUpDto;
import com.maddoxgraham.TimeToToast.DTOs.UserDto;
import com.maddoxgraham.TimeToToast.DTOs.CredentialsDto;
import com.maddoxgraham.TimeToToast.Exception.AppException;
import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Mappers.UserMapper;
import com.maddoxgraham.TimeToToast.Models.User;
import com.maddoxgraham.TimeToToast.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;

// Annoter la classe avec @Service indique que c'est une classe de service Spring.
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto login(CredentialsDto credentialsDto){
      User user = userRepository.findUserByLogin(credentialsDto.login())
                .orElseThrow(() -> new AppException("Unknown User", HttpStatus.NOT_FOUND));
      if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()),
              user.getPassword())){
          return userMapper.toUserDto(user);
      }
      throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto signUpDto){
        Optional<User> oUser = userRepository.findUserByLogin(signUpDto.login());

        if (oUser.isPresent()){
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(signUpDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));
        User savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);
    }

    public UserDto findByLogin(String login) {
        User user = userRepository.findUserByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }

    public UserDto save(UserDto userDto) {
        User existingUser = userRepository.findUserByLogin(userDto.getLogin())
                .orElseThrow(() -> new AppException("User inconnu", HttpStatus.NOT_FOUND));
        existingUser.setToken(userDto.getToken());
        existingUser.setRefreshToken(userDto.getRefreshToken());

        User updatedUser = userRepository.save(existingUser);

        return  userMapper.toUserDto(updatedUser);
    }

    public UserDto findByRefreshToken(String refreshToken) {
        User user = userRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new AppException("Admin avec ce refreshToken inconnu", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }

    public void clearTokens(UserDto userDto) {
        userDto.setToken(null);
        userDto.setRefreshToken(null);
        save(userDto);
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

   public User updateUser(User user){
        return userRepository.save(user);
    }

     public User findUserByIdUser(Long idUser){
        return userRepository.findUserByIdUser(idUser).orElseThrow(() -> new UserNotFoundException("User n° " + idUser + " was not found"));
    }

    public void deleteUser(Long idUser){
        userRepository.deleteUserByIdUser(idUser);
    }
}
