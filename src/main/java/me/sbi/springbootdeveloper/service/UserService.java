package me.sbi.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.sbi.springbootdeveloper.domain.User;
import me.sbi.springbootdeveloper.dto.AddUserRequest;
import me.sbi.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto){
        System.out.println("값 확인 "+dto.getEmail());
        System.out.println("값 확인 "+dto.getPassword());
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                //패스워드 암호화
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }

}
