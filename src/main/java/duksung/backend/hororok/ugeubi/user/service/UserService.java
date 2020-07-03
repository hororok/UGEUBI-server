package duksung.backend.hororok.ugeubi.user.service;

import duksung.backend.hororok.ugeubi.common.auth.JwtProvider;
import duksung.backend.hororok.ugeubi.common.auth.UserInfo;
import duksung.backend.hororok.ugeubi.common.util.ReplaceString;
import duksung.backend.hororok.ugeubi.user.domain.entity.User;
import duksung.backend.hororok.ugeubi.user.domain.repository.UserRepository;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqModifyPasswordDto;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqSignInDto;
import duksung.backend.hororok.ugeubi.user.dto.request.ReqSignUpDto;
import duksung.backend.hororok.ugeubi.user.dto.response.*;
import duksung.backend.hororok.ugeubi.user.exception.ExistUserIdException;
import duksung.backend.hororok.ugeubi.user.exception.IsNotEqualToPasswordException;
import duksung.backend.hororok.ugeubi.user.exception.NoExistUserAccountException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public ResUserInfoDto signIn(ReqSignInDto reqSignInDto){
        User user = getUserByUserId(reqSignInDto.getUserId());

        if(user.isNotEqualToPassword(reqSignInDto.getPassword())){
            throw new IsNotEqualToPasswordException();
        }

        UserInfo userInfo = createUserInfo(user);
        ResTokenDto resTokenDto = createTokens(user);

        return ResUserInfoDto.builder()
                .tokens(resTokenDto)
                .user(userInfo)
                .build();
    }

    public void signUp(ReqSignUpDto reqSignUpDto){

        boolean isExistUserId = userRepository.existsByUserId(reqSignUpDto.getUserId());
        if(isExistUserId){
            throw new ExistUserIdException();
        }

        userRepository.save(reqSignUpDto.toEntity());
    }

    public ResCheckUserIdDto checkUserIdDuplication(String userId) {

        boolean isExistUserId = userRepository.existsByUserId(userId);

        return ResCheckUserIdDto.builder()
                .userId(userId)
                .available(!isExistUserId).build();
    }

    public ResFindIdDto findForgottenUserId(String userName, String email) {
        User user = userRepository.findByEmailAndUserName(email, userName)
                .orElseThrow(()-> new NoExistUserAccountException());

        String changedId = ReplaceString.changeAsterisk(user.getUserId());

        return ResFindIdDto.builder().userId(changedId).build();
    }

    @Transactional
    public void modifyUserPassword(ReqModifyPasswordDto reqModifyPasswordDto) {
        User user = userRepository.findByUserId(reqModifyPasswordDto.getUserId())
                .orElseThrow(()-> new NoExistUserAccountException());

        user.modifyPassword(reqModifyPasswordDto.getPassword());
    }

    public UserInfo createUserInfo(User user){
        return UserInfo.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .build();
    }

    public User getUserByUserId(String userId){
        return userRepository.findByUserId(userId).orElseThrow(NoResultException::new);
    }

    private ResTokenDto createTokens(User user){

        String accessToken = jwtProvider.createAccessToken(user.getUserId());

        return ResTokenDto.builder()
                .tokenType("bearer")
                .accessToken(accessToken)
                .build();
    }
}
