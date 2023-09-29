package com.example.demo.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Patient {

    @Id
    @GeneratedValue

    public int tokenId;

    public String nameOfPatient;

    public String natureOPatient;

    public int ageOfPatient;

    public String placeOfPatient;

    public int weightOfPatient;

    public Long phoneNumber;

    public String bloodPressure;


}
