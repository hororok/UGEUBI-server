package duksung.backend.hororok.ugeubi.taking.service;

import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingHistory;
import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfoDay;
import duksung.backend.hororok.ugeubi.taking.domain.repository.TakingHistoryRepository;
import duksung.backend.hororok.ugeubi.taking.dto.TakingHistorySaveRequestDTO;
//import duksung.backend.hororok.ugeubi.taking.dto.TakingInfoSaveRequestDTO;
import duksung.backend.hororok.ugeubi.taking.dto.TakingHistoryUpdateIstakenRequestDTO;
import duksung.backend.hororok.ugeubi.taking.dto.TakingInfoHistoryResponseDTO;
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
    @Transactional
    public List<TakingInfoHistoryResponseDTO> findAllByIdAndDate(Long id, String date) {
        List<TakingInfoHistoryResponseDTO> entity = takingHistoryRepository.findAllByIdAndDate(id, date);
        return entity;
    }

    public void updateIsTaken(TakingHistoryUpdateIstakenRequestDTO requestDTO) {
        takingHistoryRepository.updateIsTaken(requestDTO.getTaking_history_id(), requestDTO.isTaking_history_is_taken());
    }

}
