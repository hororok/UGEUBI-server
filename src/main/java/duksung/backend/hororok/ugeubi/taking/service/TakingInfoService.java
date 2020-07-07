package duksung.backend.hororok.ugeubi.taking.service;

import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfoDay;
import duksung.backend.hororok.ugeubi.taking.domain.repository.TakingInfoDayRepository;
import duksung.backend.hororok.ugeubi.taking.domain.repository.TakingInfoTermRepository;

import duksung.backend.hororok.ugeubi.taking.dto.TakingInfoSaveRequestDTO;
import duksung.backend.hororok.ugeubi.taking.dto.TakingInfoTermSaveRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TakingInfoService {

    private final TakingInfoDayRepository takingInfoDayRepositoryRepository;
    private final TakingInfoTermRepository takingInfoTermRepository;

    @Transactional
    public Long save(TakingInfoSaveRequestDTO requestDTO){
        return takingInfoDayRepositoryRepository.save(requestDTO.toEntity()).getId();
    }

    public List<TakingInfoDay> findAllByid(Long id) {
        List<TakingInfoDay> entity = takingInfoDayRepositoryRepository.findAllByUserId(id);
        //   .orElseThrow(() -> new IllegalArgumentException("복용약이 없습니다. id="+id));
        // return new TakingInfoResponseDTO(entity);
        return entity;
    }

    public List<TakingInfoDay> findByTaking_day(String day) {
        List<TakingInfoDay> entity = takingInfoDayRepositoryRepository.findByTaking_day(day);
        return entity;
    }

    public List<Long> findUserIdByTaking_day(String day, String time) {
        List<Long> entity = takingInfoDayRepositoryRepository.findUserIdByTaking_day(day, time);
        return entity;
    }


}
