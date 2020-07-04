package duksung.backend.hororok.ugeubi.taking.domain.repository;

import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfoDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TakingInfoDayRepository extends JpaRepository<TakingInfoDay,Long> {
    @Query("select ti from TakingInfoDay ti where ti.userId = ?1")
    List<TakingInfoDay> findAllByid(Long user_id);

    @Query("select ti from TakingInfoDay ti where ti.takingDayOfWeek = ?1")
    List<TakingInfoDay> findByTaking_day(String taking_day);

}
