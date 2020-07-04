package duksung.backend.hororok.ugeubi.medicine.service;

import duksung.backend.hororok.ugeubi.common.auth.UserInfo;
import duksung.backend.hororok.ugeubi.common.domain.BaseTimeEntity;
import duksung.backend.hororok.ugeubi.medicine.domain.entity.Medicine;
import duksung.backend.hororok.ugeubi.medicine.domain.repository.MedicineRepository;
import duksung.backend.hororok.ugeubi.medicine.dto.request.ReqAddMedicineDto;
import duksung.backend.hororok.ugeubi.medicine.dto.TakingInfoDayDto;
import duksung.backend.hororok.ugeubi.medicine.dto.response.ResAddMedicineDto;
import duksung.backend.hororok.ugeubi.medicine.dto.response.ResMedicineListDto;
import duksung.backend.hororok.ugeubi.medicine.dto.response.ResSingleMedicineDto;
import duksung.backend.hororok.ugeubi.medicine.exception.NoExistMedicineException;
import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfoDay;
import duksung.backend.hororok.ugeubi.taking.domain.repository.TakingInfoDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FirstAidKitService {

    private final MedicineRepository medicineRepository;
    private final TakingInfoDayRepository takingInfoDayRepository;

    @Transactional
    public ResAddMedicineDto addMedicine(ReqAddMedicineDto reqAddMedicineDto, UserInfo userInfo) {

        boolean isTaken = reqAddMedicineDto.getIsTaken();
        Long userId = userInfo.getId();

        Medicine medicine = Medicine.builder()
                .userId(userId)
                .medicineName(reqAddMedicineDto.getMedicineName())
                .medicineType(reqAddMedicineDto.getMedicineType())
                .medicineValidTerm(reqAddMedicineDto.getMedicineValidTerm())
                .isTaken(isTaken)
                .memo(Optional.ofNullable(reqAddMedicineDto.getMemo()).orElse(""))
                .build();
        Medicine savedMedicine = medicineRepository.save(medicine);

        if(isTaken){ //복용약의 경우
            List<TakingInfoDay> list = reqAddMedicineDto.getTakingInfoDayDto().toEntities(userId, savedMedicine.getId());
            list.stream().forEach(takingInfoDay -> takingInfoDayRepository.save(takingInfoDay));
        }

        return ResAddMedicineDto.builder()
                .medicineId(savedMedicine.getId())
                .medicineName(savedMedicine.getMedicineName())
                .medicineType(savedMedicine.getMedicineType().getTypeDescription())
                .isTaken(savedMedicine.getIsTaken())
                .medicineValidTerm(savedMedicine.getMedicineValidTerm())
                .memo(savedMedicine.getMemo())
                .takingInfoDayDto(Optional.ofNullable(reqAddMedicineDto.getTakingInfoDayDto())
                                            .orElse(new TakingInfoDayDto()))
                .build();
    }

    public ResMedicineListDto getUsersMedicineList(UserInfo userInfo) {
        List<Medicine> list = medicineRepository.findAllByUserId(userInfo.getId());

        return ResMedicineListDto.builder()
                .medicineList(list.stream()
                        .sorted(Comparator.comparing(BaseTimeEntity::getCreatedTime).reversed())
                        .map(medicine -> medicine.toSingleMedicineDto())
                        .collect(Collectors.toList()))
                .build();
    }

    public ResSingleMedicineDto getSingleMedicine(String medicineId, UserInfo userInfo) {
        Long userId = userInfo.getId();
        List<TakingInfoDay> takingInfoDaysList;
        List<String> takingDayOfWeekList;
        List<String> takingTimeList;
        TakingInfoDayDto takingInfoDayDto = null;

        Medicine medicine = medicineRepository.findByUserIdAndId(userId, Long.parseLong(medicineId))
                .orElseThrow(()-> new NoExistMedicineException());

        if(medicine.getIsTaken()){ //복용약의 경우
            takingInfoDaysList = takingInfoDayRepository.findAllByMedicineIdAndUserId(medicine.getId(), userId);
            takingDayOfWeekList = new ArrayList<>();
            takingTimeList = new ArrayList<>();

            takingInfoDaysList.stream().forEach(takingInfoDay -> {
                takingDayOfWeekList.add(takingInfoDay.getTakingDayOfWeek());
                takingTimeList.add(takingInfoDay.getTakingTime());
            });
            takingInfoDayDto = TakingInfoDayDto.builder()
                    .takingTime(takingTimeList)
                    .takingDayOfWeek(takingDayOfWeekList)
                    .takingNumber(takingInfoDaysList.get(0).getTakingNumber())
                    .build();
        }

        return ResSingleMedicineDto.builder()
                .medicineName(medicine.getMedicineName())
                .medicineType(medicine.getMedicineType().getTypeDescription())
                .medicineValidTerm(medicine.getMedicineValidTerm())
                .memo(medicine.getMemo())
                .isTaken(medicine.getIsTaken())
                .takingInfoDayDto(Optional.ofNullable(takingInfoDayDto)
                        .orElse(new TakingInfoDayDto()))
                .build();

    }
}
