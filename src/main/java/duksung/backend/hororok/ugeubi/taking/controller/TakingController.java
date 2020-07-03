package duksung.backend.hororok.ugeubi.taking.controller;

import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingHistory;
import duksung.backend.hororok.ugeubi.taking.domain.entity.TakingInfoDay;
import duksung.backend.hororok.ugeubi.taking.dto.TakingHistorySaveRequestDTO;

import duksung.backend.hororok.ugeubi.taking.dto.TakingInfoTermSaveRequestDTO;
import duksung.backend.hororok.ugeubi.taking.dto.TakingInfoSaveRequestDTO;
import duksung.backend.hororok.ugeubi.taking.service.TakingHistoryService;
import duksung.backend.hororok.ugeubi.taking.service.TakingInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
/*
    //복용약 등록 - 간격
    @PostMapping("/registerTakingInfoTerm")
    public Long saveTerm(@RequestBody TakingInfoTermSaveRequestDTO requestDTO) {
        return takingInfoService.saveTerm(requestDTO);
    }*/

    //복용약 수정하기
    /*@PostMapping("/registerTakingInfo")
    public Long update(@RequestBody TakingInfoSaveRequestDTO requestDTO){
        return takingService.save(requestDTO);
    }*/

    /*
    //사용자의 복용약 정보 가져오기
    @GetMapping("/getTakingInfo")
    public List<TakingInfoDay> findById(@RequestParam(value = "id") Long id) {
        return takingInfoService.findAllByid(id);
    }*/


    //복용 기록 등록
    @Scheduled(cron="0 0 0 * * ? ") //자정마다 실행
    @PostMapping("/registerTakingHistory")
    public void registerTakingHistory() {

        Calendar oCalendar = Calendar.getInstance( );  // 현재 날짜/시간 등의 각종 정보 얻기
        // 1     2     3     4     5     6     7
        final String[] week = { "일", "월", "화", "수", "목", "금", "토" };
        String today = week[oCalendar.get(Calendar.DAY_OF_WEEK) - 1];
        List<TakingInfoDay> takingInfoDayList = takingInfoService.findByTaking_day(week[oCalendar.get(Calendar.DAY_OF_WEEK) - 1]);

        SimpleDateFormat df = new SimpleDateFormat ( "yyyy-MM-dd");
        String current_day = df.format(oCalendar.getTime());

        for(TakingInfoDay takinginfoday : takingInfoDayList){
            TakingHistorySaveRequestDTO requestDTO =
                    new TakingHistorySaveRequestDTO(takinginfoday.getUserId(), takinginfoday.getId() , current_day, false);
            takingHistoryService.save(requestDTO);
        }

        return;
    }

    //복용 기록 등록
  /*  @PostMapping("/registerTakingHistory")
    public Long save(@RequestBody TakingHistorySaveRequestDTO requestDTO) {
        return takingHistoryService.save(requestDTO);
    }*/


    //사용자의 날짜별 복용 정보 가져오기
    @GetMapping("/getTakingHistory")
    public List<TakingHistory> findById(@RequestParam(value = "id") Long id, @RequestParam(value = "date") String date) {
        return takingHistoryService.findAllByIdAndDate(id, date);
    }

}
