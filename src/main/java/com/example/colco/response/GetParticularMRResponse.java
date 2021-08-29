package com.example.colco.response;

import lombok.Data;

import java.util.List;

@Data
public class GetParticularMRResponse {
    private int empId;
    private String empName;
    private String address;

    private List<ParticularMRMedResponse> record;
}
