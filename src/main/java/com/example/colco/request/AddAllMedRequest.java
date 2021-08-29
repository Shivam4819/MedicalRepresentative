package com.example.colco.request;

import lombok.Data;

@Data
public class AddAllMedRequest {
    private int empId;
    private String medName;
    private int price;
}
