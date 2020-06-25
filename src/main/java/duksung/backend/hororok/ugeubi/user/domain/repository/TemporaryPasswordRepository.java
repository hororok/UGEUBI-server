package duksung.backend.hororok.ugeubi.user.domain.repository;

import duksung.backend.hororok.ugeubi.user.domain.entity.TemporaryPassword;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TemporaryPasswordRepository extends CrudRepository<TemporaryPassword, String> {
    Optional<TemporaryPassword> findByIdAndUserId(String email, String userId);
}
