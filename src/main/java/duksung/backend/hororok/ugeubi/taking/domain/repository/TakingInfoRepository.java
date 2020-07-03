package duksung.backend.hororok.ugeubi.taking.domain.repository;

import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfoDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TakingInfoRepository extends JpaRepository<TakingInfoDay,Long> {
    @Query("select ti from TakingInfoDay ti where ti.user_id = ?1")
    List<TakingInfoDay> findAllByid(Long user_id);

    @Query("select ti from TakingInfo ti where ti.taking_day = ?1")
    List<TakingInfo> findAllByTaking_day(String taking_day);

}
