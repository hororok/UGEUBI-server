package duksung.backend.hororok.ugeubi.user.service;

import duksung.backend.hororok.ugeubi.user.domain.entity.User;
import duksung.backend.hororok.ugeubi.user.domain.repository.UserRepository;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqSignInDto;
import duksung.backend.hororok.ugeubi.user.dto.response.ResUserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public ResUserInfoDto signIn(ReqSignInDto reqSignInDto){
        User user = userRepository.findByEmail(reqSignInDto.getEmail())
                .orElseThrow(NoResultException::new);

        if(user.isNotEqualToPassword(reqSignInDto.getPassword())){
            throw new NoResultException();
        }

        return ResUserInfoDto.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
