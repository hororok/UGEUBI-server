package duksung.backend.hororok.ugeubi.user.domain.repository;

import duksung.backend.hororok.ugeubi.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email); //중복 확인

    Optional<User> findByUserId(String userId);

    Optional<User> findByEmail(String email); //아이디 찾기

    Optional<User> findByEmailAndUserId(String email, String userId); //비밀번호 찾기
}
