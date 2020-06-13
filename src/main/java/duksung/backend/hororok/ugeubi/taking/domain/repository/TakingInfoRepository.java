package duksung.backend.hororok.ugeubi.taking.domain.repository;

import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TakingInfoRepository extends JpaRepository<TakingInfo,Long> {
    @Query("select ti from TakingInfo ti where ti.user_id = ?1")
    List<TakingInfo> findAllByid(Long user_id);
}
