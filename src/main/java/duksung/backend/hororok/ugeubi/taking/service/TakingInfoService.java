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

    private final TakingInfoDayRepository takingInfoRepository;
    private final TakingInfoTermRepository takingInfoTermRepository;
    /*
    @Transactional
    public Long save(TakingInfoSaveRequestDTO requestDTO){
         return takingInfoRepository.save(requestDTO.toEntity()).getTaking_info_id();
    }

    @Transactional
    public Long saveTerm(TakingInfoTermSaveRequestDTO requestDTO){
        return takingInfoTermRepository.save(requestDTO.toEntity()).getTaking_info_term_id();
    }

    public List<TakingInfoDay> findAllByid(Long id) {
        List<TakingInfoDay> entity = takingInfoRepository.findAllByid(id);
             //   .orElseThrow(() -> new IllegalArgumentException("복용약이 없습니다. id="+id));
       // return new TakingInfoResponseDTO(entity);
        return entity;
    }*/
}
