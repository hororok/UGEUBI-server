package duksung.backend.hororok.ugeubi.taking.domain.repository;

import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TakingHistoryRepository extends JpaRepository<TakingHistory,Long> {
    @Query("select th from TakingHistory th where th.user_id = ?1 and taking_history_date = ?2")
    List<TakingHistory> findAllByIdAndDate(Long user_id, Date date);
}
