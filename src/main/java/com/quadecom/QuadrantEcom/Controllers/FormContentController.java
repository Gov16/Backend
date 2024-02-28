package com.quadecom.QuadrantEcom.Controllers;

import com.quadecom.QuadrantEcom.DTO.FormDto;
import com.quadecom.QuadrantEcom.Response.ApiResponse;
import com.quadecom.QuadrantEcom.Services.FormContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/form-data")
public class FormContentController {
    Logger logger= LoggerFactory.getLogger(BlogController.class);


    @Autowired
    FormContentService formContentService;

    @PostMapping("/save-form-details")
    public ResponseEntity<ApiResponse> getAllBlog(
            @RequestParam String fName,
            @RequestParam(required = false) String lName,
            @RequestParam(required = false) String officeEmail,
            @RequestParam(required = false) BigInteger businessPhone,
            @RequestParam(required = false) String jobTitle,
            @RequestParam(required = false) String  formCategory,
            @RequestParam(required = false) Integer countryId,
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String cityName,
            @RequestParam(required = false) Integer zipCode,
            @RequestParam(required = false) String description

    ){
        logger.info("FormContentController:getAllBlog start");


        Date date = new Date();
        Timestamp timestampFormSubmissionDate = new Timestamp(date.getTime());

        System.out.println(timestampFormSubmissionDate);

        FormDto formDto = new FormDto();
        formDto.setFName(fName);
        formDto.setLName(lName);
        formDto.setOfficeEmail(officeEmail);
        formDto.setBusinessPhone(businessPhone);
        formDto.setJobTitle(jobTitle);
        formDto.setFormCategory(formCategory);
        formDto.setCountryId(countryId);
        formDto.setCompanyName(companyName);
        formDto.setCityName(cityName);
        formDto.setZipCode(zipCode);
        formDto.setDescription(description);
        formDto.setFormSubmissionDate(timestampFormSubmissionDate);


        ApiResponse formData = formContentService.saveFormData(formDto);

        if(formData!=null){
            formData.setSuccess(true);
            formData.setMessage("saved form Data !");
            logger.info("FormContentController:getAllBlog end");

            return ResponseEntity.ok(formData);
        }else {
            logger.info("FormContentController:getAllBlog end with status code"+HttpStatus.NOT_FOUND);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

}
