package duksung.backend.hororok.ugeubi.taking.controller;

import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingHistory;
import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfo;
import duksung.backend.hororok.ugeubi.taking.dto.TakingHistorySaveRequestDTO;
import duksung.backend.hororok.ugeubi.taking.dto.TakingInfoSaveRequestDTO;
import duksung.backend.hororok.ugeubi.taking.dto.TakingInfoTermSaveRequestDTO;
import duksung.backend.hororok.ugeubi.taking.service.TakingHistoryService;
import duksung.backend.hororok.ugeubi.taking.service.TakingInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/taking")
public class TakingController {

    private final TakingInfoService takingInfoService;
    private final TakingHistoryService takingHistoryService;


    /******TakingInfo******/


    //복용약 등록 - 요일
    @PostMapping("/registerTakingInfo")
    public Long save(@RequestBody TakingInfoSaveRequestDTO requestDTO) {
        return takingInfoService.save(requestDTO);
    }

    //복용약 등록 - 간격
    @PostMapping("/registerTakingInfoTerm")
    public Long saveTerm(@RequestBody TakingInfoTermSaveRequestDTO requestDTO) {
        return takingInfoService.saveTerm(requestDTO);
    }

    //복용약 수정하기
    /*@PostMapping("/registerTakingInfo")
    public Long update(@RequestBody TakingInfoSaveRequestDTO requestDTO){
        return takingService.save(requestDTO);
    }*/

    //사용자의 복용약 정보 가져오기
    @GetMapping("/getTakingInfo")
    public List<TakingInfo> findById(@RequestParam(value = "id") Long id) {
        return takingInfoService.findAllByid(id);
    }


    /******TakingHistory******/

    //복용 기록 등록
    @PostMapping("/registerTakingHistory")
    public Long save(@RequestBody TakingHistorySaveRequestDTO requestDTO) {
        return takingHistoryService.save(requestDTO);
    }

    //사용자의 날짜별 복용 정보 가져오기
    @GetMapping("/getTakingHistory")
    public List<TakingHistory> findById(@RequestParam(value = "id") Long id, @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return takingHistoryService.findAllByIdAndDate(id, date);
    }


}
