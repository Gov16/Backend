package com.quadecom.QuadrantEcom.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormDto {
    private Integer id;

    private String fName;

    private String lName;

    private String officeEmail;

    private BigInteger businessPhone;

    private String jobTitle;

    private String formCategory;

    private Integer countryId;

    private String companyName;

    private String cityName;

    private Integer zipCode;

    private String description;

    private Timestamp formSubmissionDate;
    //private  String resume;
}
