package duksung.backend.hororok.ugeubi.taking.domain.repository;

import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfoDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TakingInfoDayRepository extends JpaRepository<TakingInfoDay,Long> {
    List<TakingInfoDay> findAllByUserId(Long userId);

    @Query("select ti from TakingInfoDay ti where ti.takingDayOfWeek = ?1")
    List<TakingInfoDay> findByTaking_day(String taking_day);

    @Query("select distinct ti.userId from TakingInfoDay ti where ti.takingDayOfWeek = ?1 and ti.takingTime = ?2")
    List<Long> findUserIdByTaking_day(String day, String time);

    List<TakingInfoDay> findAllByMedicineIdAndUserId(Long medicineId, Long userId);

}
