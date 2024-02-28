package com.quadecom.QuadrantEcom.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="become_client")
public class FormContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "fname")
    private String fName;

    @Column(name = "lname")
    private String lName;

    @Column(name = "office_email")
    private String officeEmail;

    @Column(name = "business_phone")
    private BigInteger businessPhone;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "form_category")
    private String formCategory;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "zip_code")
    private Integer zipCode;

//    @Column(name = "resume")
//    private String resume;

    @Column(columnDefinition = "TEXT",name = "description")
    private String description;

    @Column(name = "form_submission_date")
    private Timestamp formSubmissionDate;
}
