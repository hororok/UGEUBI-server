package duksung.backend.hororok.ugeubi.medicine.domain.repository;

import duksung.backend.hororok.ugeubi.medicine.domain.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Long> {
    List<Medicine> findAllByUserId(Long userId);
    Optional<Medicine> findByUserIdAndId(Long userId, Long medicineId);
}
