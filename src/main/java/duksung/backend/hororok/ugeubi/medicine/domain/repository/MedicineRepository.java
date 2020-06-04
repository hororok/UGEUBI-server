package duksung.backend.hororok.ugeubi.medicine.domain.repository;

import duksung.backend.hororok.ugeubi.medicine.domain.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Long> {
}
