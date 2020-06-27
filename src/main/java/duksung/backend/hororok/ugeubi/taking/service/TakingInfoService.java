package duksung.backend.hororok.ugeubi.taking.service;

import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfo;
import duksung.backend.hororok.ugeubi.taking.domain.repository.TakingInfoRepository;
import duksung.backend.hororok.ugeubi.taking.dto.TakingInfoResponseDTO;
//import duksung.backend.hororok.ugeubi.taking.dto.TakingInfoSaveRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TakingInfoService {

    private final TakingInfoRepository takingInfoRepository;

    @Transactional
    //public Long save(TakingInfoSaveRequestDTO requestDTO){
    //     return takingInfoRepository.save(requestDTO.toEntity()).getTaking_info_id();
    //}

    public List<TakingInfo> findAllByid(Long id) {
        List<TakingInfo> entity = takingInfoRepository.findAllByid(id);
             //   .orElseThrow(() -> new IllegalArgumentException("복용약이 없습니다. id="+id));
       // return new TakingInfoResponseDTO(entity);
        return entity;
    }
}
