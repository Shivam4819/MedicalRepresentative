package com.example.colco.controller;


import com.example.colco.request.*;
import com.example.colco.response.*;
import com.example.colco.service.MedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static com.example.colco.utils.Urls.*;

import java.util.List;

@Transactional
@RestController
public class MedicalRepresentative {

    @Autowired
    private MedicalService medicalService;

    //add new MR
    @PostMapping(INSERT_MR)
    public MRInsertResponse insertMR(@RequestBody MRInsertRequest mrInsertRequest){
       return medicalService.insertMRToDb(mrInsertRequest);
    }

    // get all MR
    @GetMapping(GET_ALL_MR)
    public List<GetAllMRResponse> getAllMR(){
       return medicalService.fetchAllMR();
    }

    //get data of paricular MR
    @PostMapping(GET_PARTICULAR)
    public GetParticularMRResponse getParticular(@RequestBody GetParticularMRRequest getParticularMRRequest){
       return medicalService.getSpecificMR(getParticularMRRequest);
    }

    //Delete Particular MR
    @PostMapping(DELETE_MR)
    public DeleteMRResponse deleteMR(@RequestBody DeleteMrRequest deleteMrRequest){

       return medicalService.deleteParticularMR(deleteMrRequest);
    }

    //add medicine for particular MR
    @PostMapping(ADD_ALL_MED)
    public AddAllMedResponse addAllMed(@RequestBody AddAllMedRequest addAllMedRequest){
       return medicalService.addAllData(addAllMedRequest);
    }

    //update data for particular MR
    @PostMapping(UPDATE_DATA)
    public UpdateSpecificResponse updateData(@RequestBody UpdateSpecificRequest updateSpecificRequest){
       return medicalService.updateSpecific(updateSpecificRequest);
    }

    //delete specific entry for an MR
    @PostMapping(DELETE_SPECIFIC_DATA)
    public DeleteSpecificDataResponse deleteSpecificData(@RequestBody DeleteSpecificDataRequest deleteSpecificDataRequest){
         return medicalService.deleteSpecific(deleteSpecificDataRequest);
    }
}
