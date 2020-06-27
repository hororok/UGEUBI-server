package duksung.backend.hororok.ugeubi.taking.service;

import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingHistory;
import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfo;
import duksung.backend.hororok.ugeubi.taking.domain.repository.TakingHistoryRepository;
import duksung.backend.hororok.ugeubi.taking.domain.repository.TakingInfoRepository;
import duksung.backend.hororok.ugeubi.taking.dto.TakingHistorySaveRequestDTO;
//import duksung.backend.hororok.ugeubi.taking.dto.TakingInfoSaveRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TakingHistoryService {

    private final TakingHistoryRepository takingHistoryRepository;

    //복용약 기록 저장
    @Transactional
    public Long save(TakingHistorySaveRequestDTO requestDTO){
        return takingHistoryRepository.save(requestDTO.toEntity()).getTaking_history_id();
    }

    //사용자의 날짜별 복용약 정보 가져오기
    public List<TakingHistory> findAllByIdAndDate(Long id, Date date) {
        List<TakingHistory> entity = takingHistoryRepository.findAllByIdAndDate(id, date);
        return entity;
    }
}
