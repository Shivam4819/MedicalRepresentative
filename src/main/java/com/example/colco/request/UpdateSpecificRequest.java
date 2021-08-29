package com.example.colco.request;

import lombok.Data;

@Data
public class UpdateSpecificRequest {
    private int empid;
    private String medName;
    private String updateMedName;
    private int updatePrice;
}
