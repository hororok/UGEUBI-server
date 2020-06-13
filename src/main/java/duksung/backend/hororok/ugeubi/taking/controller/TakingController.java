package duksung.backend.hororok.ugeubi.taking.controller;

import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfo;
import duksung.backend.hororok.ugeubi.taking.dto.TakingInfoResponseDTO;
import duksung.backend.hororok.ugeubi.taking.dto.TakingInfoSaveRequestDTO;
import duksung.backend.hororok.ugeubi.taking.service.TakingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/taking")
public class TakingController {

    private final TakingService takingService;

    //복용약 등록
    @PostMapping("/registerTakingInfo")
    public Long save(@RequestBody TakingInfoSaveRequestDTO requestDTO){
        return takingService.save(requestDTO);
    }

    //복용약 수정하기
    /*@PostMapping("/registerTakingInfo")
    public Long update(@RequestBody TakingInfoSaveRequestDTO requestDTO){
        return takingService.save(requestDTO);
    }*/

    //사용자의 복용약 정보 가져오기
    @GetMapping("/getTakingInfo/{id}")
    public List<TakingInfo> findById(@PathVariable Long id){
        return takingService.findAllByid(id);
    }
}
