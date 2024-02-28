package com.quadecom.QuadrantEcom.ServiceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quadecom.QuadrantEcom.DTO.FormDto;
import com.quadecom.QuadrantEcom.DTO.SessionDto;
import com.quadecom.QuadrantEcom.Entities.FormContentEntity;
import com.quadecom.QuadrantEcom.Repository.FormContentRepository;
import com.quadecom.QuadrantEcom.Response.ApiResponse;
import com.quadecom.QuadrantEcom.Services.FormContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FormContentServiceImpl implements FormContentService {

    Logger logger= LoggerFactory.getLogger(FormContentServiceImpl.class);
    @Autowired
    FormContentRepository formRepo;

    private final ObjectMapper objectMapper;

    @Autowired
    public FormContentServiceImpl(FormContentRepository formRepo, ObjectMapper objectMapper) {
        this.formRepo = formRepo;
        this.objectMapper = objectMapper;
    }

    @Override
    public ApiResponse saveFormData(FormDto formData) {
        logger.info("FormContentServiceImpl:saveFormData start");

        FormContentEntity formEntityData = new FormContentEntity();
        // Map fields from FormDto to FormContentEntity
        formEntityData.setFName(formData.getFName());
        formEntityData.setLName(formData.getLName());
        formEntityData.setOfficeEmail(formData.getOfficeEmail());
        formEntityData.setBusinessPhone(formData.getBusinessPhone());
        formEntityData.setJobTitle(formData.getJobTitle());
        formEntityData.setFormCategory(formData.getFormCategory());
        formEntityData.setCountryId(formData.getCountryId());
        formEntityData.setCompanyName(formData.getCompanyName());
        formEntityData.setCityName(formData.getCityName());
        formEntityData.setZipCode(formData.getZipCode());
        formEntityData.setDescription(formData.getDescription());

        formEntityData.setFormSubmissionDate(formData.getFormSubmissionDate());
        FormContentEntity savedEntity = formRepo.save(formEntityData);

        ApiResponse response = new ApiResponse();
        // Set any necessary response data here
        response.setMessage("Form data saved successfully");

        if(savedEntity!=null) {
            response.setFormDto(formData);
        }

        logger.info("FormContentServiceImpl:saveFormData end");

        return response;
    }
}
