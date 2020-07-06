package duksung.backend.hororok.ugeubi.medicine.domain.repository;

import duksung.backend.hororok.ugeubi.medicine.domain.entity.Medicine;
import duksung.backend.hororok.ugeubi.taking.dto.TakingInfoHistoryResponseDTO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Long> {
    List<Medicine> findAllByUserId(Long userId);
    Optional<Medicine> findByUserIdAndId(Long userId, Long medicineId);

    @Query("select distinct m.userId from Medicine m where m.medicineValidTerm = ?1")
    List<Long> findUserIdValidTerm(Date medicineValidTerm);

    @Query("select m from Medicine m where m.medicineValidTerm = ?1")
    List<Medicine> findAllByValidTerm(Date medicineValidTerm);
}
