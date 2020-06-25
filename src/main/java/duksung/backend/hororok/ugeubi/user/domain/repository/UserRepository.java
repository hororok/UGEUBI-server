package duksung.backend.hororok.ugeubi.user.domain.repository;

import duksung.backend.hororok.ugeubi.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserId(String userId); //아이디 중복 확인

    boolean existsByEmail(String email); // 회원가입 이메일 중복 확인

    boolean existsByEmailAndUserId(String email, String userId); //비밀번호 찾기

    Optional<User> findByUserId(String userId);

    Optional<User> findByEmailAndUserName(String email, String userName); //아이디 찾기
}
