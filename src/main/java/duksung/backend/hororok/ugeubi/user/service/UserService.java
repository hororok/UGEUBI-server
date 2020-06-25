package duksung.backend.hororok.ugeubi.user.service;

import duksung.backend.hororok.ugeubi.common.util.MailForm;
import duksung.backend.hororok.ugeubi.common.util.RandomString;
import duksung.backend.hororok.ugeubi.common.util.ReplaceString;
import duksung.backend.hororok.ugeubi.config.auth.JwtProvider;
import duksung.backend.hororok.ugeubi.user.domain.entity.User;
import duksung.backend.hororok.ugeubi.user.domain.repository.UserRepository;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqFindIdDto;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqModifyPasswordDto;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqSignInDto;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqSignUpDto;
import duksung.backend.hororok.ugeubi.user.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public ResCheckUserIdDto checkUserIdDuplication(String userId) {

        boolean isExistUserId = userRepository.existsByUserId(userId);

        return ResCheckUserIdDto.builder()
                .userId(userId)
                .available(!isExistUserId).build();
    }

    public ResFindIdDto findForgottenUserId(ReqFindIdDto reqFindIdDto) {
        User user = userRepository.findByEmailAndUserName(reqFindIdDto.getEmail(), reqFindIdDto.getUserName())
                .orElseThrow(()-> new IllegalArgumentException("해당 이메일과 유저 이름에 해당하는 계정이 존재하지 않습니다."));

        String changedId = ReplaceString.changeAsterisk(user.getUserId());

        return ResFindIdDto.builder().userId(changedId).build();
    }

    @Transactional
    public void modifyUserPassword(ReqModifyPasswordDto reqModifyPasswordDto) {
        User user = userRepository.findByUserId(reqModifyPasswordDto.getUserId())
                .orElseThrow(()-> new IllegalArgumentException("아이디에 해당하는 사용자가 없습니다."));

        user.modifyPassword(reqModifyPasswordDto.getPassword());
    }

    private ResTokenDto createTokens(User user){

        String accessToken = jwtProvider.createAccessToken(user.getUserId());
        //String refreshToken = jwtProvider.createRefreshToken(user.getUserId());

        return ResTokenDto.builder()
                .accessToken(accessToken)
                //.refreshToken(refreshToken)
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
