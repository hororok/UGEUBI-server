package duksung.backend.hororok.ugeubi.user.service;

import duksung.backend.hororok.ugeubi.config.auth.JwtProvider;
import duksung.backend.hororok.ugeubi.user.domain.entity.User;
import duksung.backend.hororok.ugeubi.user.domain.repository.UserRepository;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqSignInDto;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqSignUpDto;
import duksung.backend.hororok.ugeubi.user.dto.response.ResTokenDto;
import duksung.backend.hororok.ugeubi.user.dto.response.ResUserDto;
import duksung.backend.hororok.ugeubi.user.dto.response.ResUserInfoDto;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public ResUserInfoDto signIn(ReqSignInDto reqSignInDto){
        User user = userRepository.findByUserId(reqSignInDto.getUserId())
                .orElseThrow(NoResultException::new);

        if(user.isNotEqualToPassword(BCrypt.hashpw(reqSignInDto.getPassword(), BCrypt.gensalt()))){
            throw new IllegalArgumentException("비밀번호가 일치하지 않음");
        }

        ResUserDto resUserDto = createUserInfo(user);
        ResTokenDto resTokenDto = createTokens(user);

        return ResUserInfoDto.builder()
                .tokens(resTokenDto)
                .user(resUserDto)
                .build();
    }

    public void signUp(ReqSignUpDto reqSignUpDto){

        boolean isExistUserId = userRepository.existsByUserId(reqSignUpDto.getUserId());
        if(isExistUserId){
            throw new IllegalArgumentException("중복되는 아이디");
        }

        userRepository.save(reqSignUpDto.toEntity());

    }

    private ResTokenDto createTokens(User user){

        String accessToken = jwtProvider.createAccessToken(user.getUserId());
        String refreshToken = jwtProvider.createRefreshToken(user.getUserId());

        return ResTokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private ResUserDto createUserInfo(User user){
        return ResUserDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .build();
    }
}
