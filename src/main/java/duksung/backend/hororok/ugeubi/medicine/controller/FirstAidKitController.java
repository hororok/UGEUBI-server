package duksung.backend.hororok.ugeubi.medicine.controller;

import duksung.backend.hororok.ugeubi.common.auth.LoginUserInfo;
import duksung.backend.hororok.ugeubi.common.auth.UserInfo;
import duksung.backend.hororok.ugeubi.medicine.dto.request.ReqAddMedicineDto;
import duksung.backend.hororok.ugeubi.medicine.dto.response.ResAddMedicineDto;
import duksung.backend.hororok.ugeubi.medicine.dto.response.ResMedicineListDto;
import duksung.backend.hororok.ugeubi.medicine.dto.response.ResSingleMedicineDto;
import duksung.backend.hororok.ugeubi.medicine.service.FirstAidKitService;
import duksung.backend.hororok.ugeubi.user.domain.entity.User;
import duksung.backend.hororok.ugeubi.user.exception.RequestWrongFieldException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class FirstAidKitController {

    private final FirstAidKitService firstAidKitService;

    @GetMapping("/first-aid-kit/medicines")
    public ResponseEntity<ResMedicineListDto> getUsersMedicineList(@LoginUserInfo UserInfo userInfo){
        return ResponseEntity.status(HttpStatus.OK).body(firstAidKitService.getUsersMedicineList(userInfo));
    }

    @PostMapping("/first-aid-kit/medicines")
    public ResponseEntity<ResAddMedicineDto> addMedicine(@RequestBody @Valid ReqAddMedicineDto reqAddMedicineDto,
                                                         @LoginUserInfo UserInfo userInfo,
                                                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RequestWrongFieldException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(firstAidKitService.addMedicine(reqAddMedicineDto,userInfo));
    }

    @GetMapping("/first-aid-kit/medicines/{medicine-id:^[0-9]+$}")
    public ResponseEntity<ResSingleMedicineDto> getSingleMedicine(@PathVariable("medicine-id") String medicineId,
                                                                  @LoginUserInfo UserInfo userInfo){
        return ResponseEntity.status(HttpStatus.OK).body(firstAidKitService.getSingleMedicine(medicineId, userInfo));
    }
    /*
    @PatchMapping("/first-aid-kit/medicines/{medicine-id:^[0-9]+$}")
    public ResponseEntity<ResMedicine>
    */

    @DeleteMapping("/first-aid-kit/medicines/{medicine-id:^[0-9]+$}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable("medicine-id") Long medicineId,
                                               @LoginUserInfo UserInfo userInfo){

        firstAidKitService.deleteMedicine(medicineId, userInfo);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
