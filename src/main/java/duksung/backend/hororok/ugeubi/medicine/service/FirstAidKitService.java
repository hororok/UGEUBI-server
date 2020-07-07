package duksung.backend.hororok.ugeubi.medicine.service;

import duksung.backend.hororok.ugeubi.common.auth.UserInfo;
import duksung.backend.hororok.ugeubi.common.domain.BaseTimeEntity;
import duksung.backend.hororok.ugeubi.common.util.ParseString;
import duksung.backend.hororok.ugeubi.medicine.domain.entity.Medicine;
import duksung.backend.hororok.ugeubi.medicine.domain.repository.MedicineRepository;
import duksung.backend.hororok.ugeubi.medicine.dto.request.ReqMedicineDto;
import duksung.backend.hororok.ugeubi.medicine.dto.TakingInfoDayDto;
import duksung.backend.hororok.ugeubi.medicine.dto.response.ResMedicineDto;
import duksung.backend.hororok.ugeubi.medicine.dto.response.ResMedicineListDto;
import duksung.backend.hororok.ugeubi.medicine.dto.response.ResSingleMedicineDto;
import duksung.backend.hororok.ugeubi.medicine.exception.NoExistMedicineException;
import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfoDay;
import duksung.backend.hororok.ugeubi.taking.domain.repository.TakingHistoryRepository;
import duksung.backend.hororok.ugeubi.taking.domain.repository.TakingInfoDayRepository;
import duksung.backend.hororok.ugeubi.taking.dto.TakingHistorySaveRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FirstAidKitService {

    private final MedicineRepository medicineRepository;
    private final TakingInfoDayRepository takingInfoDayRepository;
    private final TakingHistoryRepository takingHistoryRepository;

    @Transactional
    public ResMedicineDto addMedicine(ReqMedicineDto reqMedicineDto, UserInfo userInfo) {

        boolean isTaken = reqMedicineDto.getIsTaken();

        Long userId = userInfo.getId();

        Medicine medicine = Medicine.builder()
                .userId(userId)
                .medicineName(reqMedicineDto.getMedicineName())
                .medicineType(reqMedicineDto.getMedicineType())
                .medicineValidTerm(reqMedicineDto.getMedicineValidTerm())
                .isTaken(isTaken)
                .memo(Optional.ofNullable(reqMedicineDto.getMemo()).orElse(""))
                .build();
        Medicine savedMedicine = medicineRepository.save(medicine);

        if(isTaken){ //복용약의 경우
            saveTakingInfoAndTakingHistory(reqMedicineDto, userInfo, savedMedicine);
        }

        return makeResMedicineDto(savedMedicine, reqMedicineDto);
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

    @SneakyThrows
    public ResSingleMedicineDto getSingleMedicine(Long medicineId, UserInfo userInfo) {
        Long userId = userInfo.getId();
        List<TakingInfoDay> takingInfoDaysList;
        Set<String> takingDayOfWeekSet;
        Set<String> takingTimeSet;
        TakingInfoDayDto takingInfoDayDto = null;

        Medicine medicine = medicineRepository.findByUserIdAndId(userId, medicineId)
                .orElseThrow(()-> new NoExistMedicineException());

        if(medicine.getIsTaken()){ //복용약의 경우
            takingInfoDaysList = takingInfoDayRepository.findAllByMedicineIdAndUserId(medicine.getId(), userId);
            takingDayOfWeekSet = new HashSet<>();
            takingTimeSet = new HashSet<>();

            takingInfoDaysList.stream().forEach(takingInfoDay -> {
                takingDayOfWeekSet.add(takingInfoDay.getTakingDayOfWeek());
                takingTimeSet.add(takingInfoDay.getTakingTime());
            });
            takingInfoDayDto = TakingInfoDayDto.builder()
                    .takingTime(new ArrayList<>(takingTimeSet))
                    .takingDayOfWeek(new ArrayList<>(takingDayOfWeekSet))
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

    @Transactional
    public void deleteMedicine(Long medicineId, UserInfo userInfo) {
        Long userId = userInfo.getId();

        Medicine medicine = medicineRepository.findByUserIdAndId(userId,medicineId)
                .orElseThrow(() -> new NoExistMedicineException());

        deleteTakingInfoDay(medicine, userInfo);

        medicineRepository.delete(medicine);
    }

    @Transactional
    public ResMedicineDto modifyMedicine(Long medicineId, UserInfo userInfo, ReqMedicineDto reqMedicineDto) {

        Long userId = userInfo.getId();

        Medicine medicine = medicineRepository.findByUserIdAndId(userId, medicineId)
                .orElseThrow(()-> new NoExistMedicineException());

        medicine.modify(reqMedicineDto.getMedicineName(),reqMedicineDto.getMedicineType(),reqMedicineDto.getMedicineValidTerm(),
                reqMedicineDto.getIsTaken(), reqMedicineDto.getMemo());

        deleteTakingInfoDay(medicine, userInfo);

        if(reqMedicineDto.getIsTaken()){//복용약의 경우
            saveTakingInfoAndTakingHistory(reqMedicineDto, userInfo, medicine);
        }
        return makeResMedicineDto(medicine, reqMedicineDto);
    }

    private void deleteTakingInfoDay(Medicine medicine, UserInfo userInfo){
        if(medicine.getIsTaken()){//복용약의 경우
            List<TakingInfoDay> list = takingInfoDayRepository.findAllByMedicineIdAndUserId(medicine.getId(),userInfo.getId());
            list.stream().forEach(takingInfoDay -> takingInfoDayRepository.delete(takingInfoDay));
        }
    }

    private void saveTakingInfoAndTakingHistory(ReqMedicineDto reqMedicineDto, UserInfo userInfo, Medicine medicine) {
        Calendar oCalendar = Calendar.getInstance( );  // 현재 날짜/시간 등의 각종 정보 얻기
        // 1     2     3     4     5     6     7
        final String[] week = { "일", "월", "화", "수", "목", "금", "토" };
        String today = week[oCalendar.get(Calendar.DAY_OF_WEEK) - 1]; //요일

        SimpleDateFormat df = new SimpleDateFormat ( "yyyy-MM-dd");
        String current_day = df.format(oCalendar.getTime()); //오늘 날짜

        List<TakingInfoDay> list = reqMedicineDto.getTakingInfoDayDto().toEntities(userInfo.getId(), medicine);
        list.stream().forEach(takingInfoDay -> {
            //takingInfo 에 저장
            Long takingInfoId = (takingInfoDayRepository.save(takingInfoDay)).getId();

            //복용약의 등록 요일이 오늘이면 TakingHistory 에 등록
            if((takingInfoDay.getTakingDayOfWeek()).equals(today)){
                TakingHistorySaveRequestDTO takingHistorySaveRequestDTO = new TakingHistorySaveRequestDTO(userInfo.getId(),takingInfoId, current_day,false);
                takingHistoryRepository.save(takingHistorySaveRequestDTO.toEntity());
            }
        });
    }

    private ResMedicineDto makeResMedicineDto(Medicine medicine, ReqMedicineDto reqMedicineDto) {
        return ResMedicineDto.builder()
                .medicineId(medicine.getId())
                .medicineName(medicine.getMedicineName())
                .medicineType(medicine.getMedicineType().getTypeDescription())
                .isTaken(medicine.getIsTaken())
                .medicineValidTerm(medicine.getMedicineValidTerm())
                .memo(medicine.getMemo())
                .takingInfoDayDto(Optional.ofNullable(reqMedicineDto.getTakingInfoDayDto())
                        .orElse(new TakingInfoDayDto()))
                .build();
    }
}
