package com.hoainam10th.phanquyendatabase.services;

import com.hoainam10th.phanquyendatabase.dtos.LoginDto;
import com.hoainam10th.phanquyendatabase.dtos.UserDto;
import com.hoainam10th.phanquyendatabase.entities.User;
import com.hoainam10th.phanquyendatabase.exceptions.BadRequestException;
import com.hoainam10th.phanquyendatabase.repositories.UserRepository;
import com.hoainam10th.phanquyendatabase.security.JwtTokenProvider;
import com.hoainam10th.phanquyendatabase.utils.Utilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.*;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public  Map<String,Object> login(LoginDto data) {
        Optional<User> user = userRepository.findByUsername(data.getUsername());

        if(user.isEmpty()) throw new BadRequestException("Invalid username or email");

        //sai pass quang ra loi 500; khi handle loi 500 custom lai thanh 400
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                data.getUsername(),
                data.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication, user.get().getRoles());

        Map<String,Object> map = new HashMap<>();
        map.put("token", token);
        map.put("user", modelMapper.map(user.get(), UserDto.class));

        return map;
    }

    public UserDto getCurrentUserToDto() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = Utilities.getUsername(auth);
        var user = userRepository.findByUsername(username);
        return modelMapper.map(user.get(), UserDto.class);
    }

    public List<String> getRolesForCurrentuser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Utilities.getRoles(auth);
    }

    public Optional<User> getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = Utilities.getUsername(auth);
        return userRepository.findByUsername(username);
    }
}
