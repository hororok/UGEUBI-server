package duksung.backend.hororok.ugeubi.taking.domain.repository;

import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingHistory;
import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfoDay;
import duksung.backend.hororok.ugeubi.taking.dto.TakingInfoHistoryResponseDTO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface TakingHistoryRepository extends JpaRepository<TakingHistory,Long> {
    @Query("SELECT new duksung.backend.hororok.ugeubi.taking.dto.TakingInfoHistoryResponseDTO(ti.id, ti.userId, ti.medicineId, th.taking_history_id, th.taking_history_date, ti.medicineName, ti.takingTime, ti.takingNumber, th.taking_history_is_taken)" +
            "FROM TakingHistory th JOIN TakingInfoDay ti " +
            "ON ti.id = th.taking_info_id WHERE th.user_id =:user_id and th.taking_history_date =:date")
    List<TakingInfoHistoryResponseDTO> findAllByIdAndDate(@Param("user_id") Long user_id, @Param("date") String date);

    @Modifying
    @Transactional
    @Query("update TakingHistory th set th.taking_history_is_taken =:taking_history_is_taken WHERE th.taking_history_id =:taking_history_id")
    void updateIsTaken(@Param("taking_history_id") Long taking_history_id, @Param("taking_history_is_taken") Boolean taking_history_is_taken);
}
