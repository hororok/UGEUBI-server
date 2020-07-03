package duksung.backend.hororok.ugeubi.medicine.controller;

import duksung.backend.hororok.ugeubi.medicine.service.FirstAidKitService;
import duksung.backend.hororok.ugeubi.user.exception.RequestWrongFieldException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class FirstAidKitController {

    private final FirstAidKitService firstAidKitService;

    /*@PostMapping("/first-aid-kit/medicines")
    public ResponseEntity<ResMedicineListDto> addMedicine(@RequestBody @Valid ReqAddMedicineDto reqAddMedicineDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RequestWrongFieldException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(firstAidKitService.addMedicine(reqAddMedicineDto));
    }*/
}
