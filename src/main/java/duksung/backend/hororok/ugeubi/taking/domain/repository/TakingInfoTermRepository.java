package duksung.backend.hororok.ugeubi.taking.domain.repository;

import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfo;
import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfoTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TakingInfoTermRepository extends JpaRepository<TakingInfoTerm,Long> {
}
