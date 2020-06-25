package duksung.backend.hororok.ugeubi.user.domain.repository;

import duksung.backend.hororok.ugeubi.user.domain.entity.FindPasswordCode;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FindPasswordCodeRepository extends CrudRepository<FindPasswordCode, String> {
    Optional<FindPasswordCode> findByIdAndUserId(String email, String userId);
}
