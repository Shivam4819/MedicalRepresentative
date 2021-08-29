package com.example.colco.service;

import com.example.colco.dao.MedicalRecordDao;
import com.example.colco.entity.MedicalRecordEntity;
import com.example.colco.request.*;
import com.example.colco.dao.MedicalDao;
import com.example.colco.entity.MedicalEntity;
import com.example.colco.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static com.example.colco.utils.Variables.*;

@Service
public class MedicalService {

    @Autowired
    private MedicalDao medicalDao;


    @Autowired
    private MedicalRecordDao medicalRecordDao;

    // add MR data to DB
    public MRInsertResponse insertMRToDb(MRInsertRequest mrInsertRequest){
        try {
            MedicalEntity medicalEntity = new MedicalEntity();
            medicalEntity.setEmpName(mrInsertRequest.getEmpName());
            medicalEntity.setAddress(mrInsertRequest.getAddress());
            medicalDao.save(medicalEntity);

            MRInsertResponse mrInsertResponse=new MRInsertResponse();
            mrInsertResponse.setCode(SUCCESS_CODE);
            mrInsertResponse.setMsg(INSERT_MR_TO_DB_SUCCESS);
            return mrInsertResponse;
        }catch (Exception exception){
            MRInsertResponse mrInsertResponse=new MRInsertResponse();
            mrInsertResponse.setCode(ERROR_CODE);
            mrInsertResponse.setMsg(INSERT_MR_TO_DB_ERROR +exception);
            return mrInsertResponse;
        }

    }
    // get details of all MR
    public List<GetAllMRResponse> fetchAllMR(){
        List<MedicalEntity> list=medicalDao.findAll();
        List<GetAllMRResponse> getAllMRResponses = new ArrayList<>();
        for( MedicalEntity medicalEntity: list) {

            GetAllMRResponse getAllMRResponse = new GetAllMRResponse();
            getAllMRResponse.setEmpid(medicalEntity.getEmpId());
            getAllMRResponse.setAddress(medicalEntity.getAddress());
            getAllMRResponse.setEmpName(medicalEntity.getEmpName());
            getAllMRResponses.add(getAllMRResponse);
        }

        return getAllMRResponses;
    }

    // delete specific MR
    public DeleteMRResponse deleteParticularMR(DeleteMrRequest deleteMrRequest){
        try {
            medicalRecordDao.deleteALLByEmpId(deleteMrRequest.getEmpId());
            medicalDao.deleteById(deleteMrRequest.getEmpId());
            DeleteMRResponse deleteMRResponse=new DeleteMRResponse();
            deleteMRResponse.setCode(SUCCESS_CODE);
            deleteMRResponse.setMsg(DELETE_PARTICULAR_MR_SUCCESS);
            return deleteMRResponse;
        }catch (Exception exception){
            DeleteMRResponse deleteMRResponse=new DeleteMRResponse();
            deleteMRResponse.setCode(ERROR_CODE);
            deleteMRResponse.setMsg(DELETE_PARTICULAR_MR_ERROR+exception);
            return deleteMRResponse;
        }

    }
    //Add med for specific MR
    public AddAllMedResponse addAllData(AddAllMedRequest addAllMedRequest){
        try {
            Optional<MedicalEntity> record= medicalDao.findById(addAllMedRequest.getEmpId());
            AddAllMedResponse addAllMedResponse=new AddAllMedResponse();
            if (record.isPresent()) {
                MedicalRecordEntity medicalRecordEntity = new MedicalRecordEntity();
                medicalRecordEntity.setEmpId(addAllMedRequest.getEmpId());
                medicalRecordEntity.setMedName(addAllMedRequest.getMedName());
                medicalRecordEntity.setPrice(addAllMedRequest.getPrice());
                medicalRecordDao.save(medicalRecordEntity);

                addAllMedResponse.setCode(SUCCESS_CODE);
                addAllMedResponse.setMsg(ADD_ALL_DATA_SUCCESS);
            }
            else {
                addAllMedResponse.setCode(ERROR_CODE);
                addAllMedResponse.setMsg(ADD_ALL_DATA_NOT_FOUND);
            }
            return addAllMedResponse;
        }catch (Exception exception){
            AddAllMedResponse addAllMedResponse=new AddAllMedResponse();
            addAllMedResponse.setCode(ERROR_CODE);
            addAllMedResponse.setMsg(ADD_ALL_DATA_ERROR+exception);
            return addAllMedResponse;
        }

    }
 // get all data for specific MR
    public GetParticularMRResponse getSpecificMR(GetParticularMRRequest getParticularMRRequest){

      MedicalEntity medicalEntity= medicalDao.findByEmpId(getParticularMRRequest.getEmpID());

        GetParticularMRResponse getParticularMRResponse= new GetParticularMRResponse();
        getParticularMRResponse.setEmpId(medicalEntity.getEmpId());
        getParticularMRResponse.setEmpName(medicalEntity.getEmpName());
        getParticularMRResponse.setAddress(medicalEntity.getAddress());

        List<MedicalRecordEntity> list= medicalRecordDao.findAllByEmpId(getParticularMRRequest.getEmpID());

        List<ParticularMRMedResponse> particularMRMedResponses = new ArrayList<>();
        for( MedicalRecordEntity medicalRecordEntity: list) {

            ParticularMRMedResponse particularMRMedResponse = new ParticularMRMedResponse();
            particularMRMedResponse.setMedName(medicalRecordEntity.getMedName());
            particularMRMedResponse.setPrice(medicalRecordEntity.getPrice());

            particularMRMedResponses.add(particularMRMedResponse);
        }
        getParticularMRResponse.setRecord(particularMRMedResponses);

        return getParticularMRResponse;
    }

    //update med record for specific mr
    public UpdateSpecificResponse updateSpecific(UpdateSpecificRequest updateSpecificRequest){
        try {
            MedicalRecordEntity medicalRecordEntity = medicalRecordDao.findByEmpIdAndAndMedName(updateSpecificRequest.getEmpid(), updateSpecificRequest.getMedName());
            UpdateSpecificResponse updateSpecificResponse=new UpdateSpecificResponse();
            if (medicalRecordEntity != null) {
                MedicalRecordEntity medicalRecordEntity1 = new MedicalRecordEntity();
                medicalRecordEntity1.setId(medicalRecordEntity.getId());
                medicalRecordEntity1.setEmpId(medicalRecordEntity.getEmpId());
                medicalRecordEntity1.setMedName(updateSpecificRequest.getUpdateMedName());
                medicalRecordEntity1.setPrice(updateSpecificRequest.getUpdatePrice());
                medicalRecordDao.save(medicalRecordEntity1);

                updateSpecificResponse.setCode(SUCCESS_CODE);
                updateSpecificResponse.setMsg(UPDATE_SPECIFIC_SUCCESS);
                return updateSpecificResponse;
            }
            else{
                updateSpecificResponse.setCode(500);
                updateSpecificResponse.setMsg(UPDATE_SPECIFIC_DATA_NOT_FOUND);
                return updateSpecificResponse;
            }
        }catch (Exception exception){
            UpdateSpecificResponse updateSpecificResponse=new UpdateSpecificResponse();
            updateSpecificResponse.setCode(ERROR_CODE);
            updateSpecificResponse.setMsg(UPDATE_SPECIFIC_ERROR+exception);
            return updateSpecificResponse;
        }

    }

    //delete med record for specific mr
    public DeleteSpecificDataResponse deleteSpecific(DeleteSpecificDataRequest deleteSpecificDataRequest) {
        try {
            medicalRecordDao.deleteByEmpIdAndMedName(deleteSpecificDataRequest.getEmpid(), deleteSpecificDataRequest.getMed());
            DeleteSpecificDataResponse deleteSpecificDataResponse=new DeleteSpecificDataResponse();
            deleteSpecificDataResponse.setCode(SUCCESS_CODE);
            deleteSpecificDataResponse.setMsg(DELETE_SPECIFIC_SUCCESS);
            return deleteSpecificDataResponse;

        }catch (Exception exception){
            DeleteSpecificDataResponse deleteSpecificDataResponse=new DeleteSpecificDataResponse();
            deleteSpecificDataResponse.setCode(500);
            deleteSpecificDataResponse.setMsg(DELETE_SPECIFIC_ERROR+exception);
            return deleteSpecificDataResponse;
        }
    }
}
