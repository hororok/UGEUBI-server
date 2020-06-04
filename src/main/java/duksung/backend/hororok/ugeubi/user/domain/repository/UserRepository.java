package duksung.backend.hororok.ugeubi.user.domain.repository;

import duksung.backend.hororok.ugeubi.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
