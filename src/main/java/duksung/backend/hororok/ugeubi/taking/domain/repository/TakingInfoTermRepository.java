package duksung.backend.hororok.ugeubi.taking.domain.repository;

import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfoTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TakingInfoTermRepository extends JpaRepository<TakingInfoTerm,Long> {
}
