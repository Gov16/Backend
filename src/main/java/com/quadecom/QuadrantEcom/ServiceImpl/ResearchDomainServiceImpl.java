package com.quadecom.QuadrantEcom.ServiceImpl;

import com.quadecom.QuadrantEcom.ResearchMaster.ResearchMaster;
import com.quadecom.QuadrantEcom.ResearchMaster.ResearchMasterRepository;
import com.quadecom.QuadrantEcom.ResearchMaster.ResearchMasterService;
import com.quadecom.QuadrantEcom.Response.ApiResponse;
import com.quadecom.QuadrantEcom.Services.ResearchDomainsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResearchDomainServiceImpl implements ResearchDomainsService {

    Logger logger= LoggerFactory.getLogger(ResearchDomainServiceImpl.class);

    @Autowired
    ResearchMasterRepository researchMasterRepository;

    @Override
    public ApiResponse getLatestApplicationController() {
        logger.info("ResearchDomainServiceImpl:getLatestApplicationController start");

        ApiResponse response = new ApiResponse();

        //get the latest application developmnent report from database.
        List<ResearchMaster> applicationData = researchMasterRepository.getApplicationDevelopment();

        if(!applicationData.isEmpty()){
            response.setDomainResponse(applicationData);
            response.setSuccess(true);
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(HttpStatusCode.valueOf(HttpStatus.OK.value()));
            response.setMessage("Latest 3 updated Application Development Data !");
        }else {
            response.setSuccess(false);
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setStatusCode(HttpStatus.NOT_FOUND);
            response.setMessage("Application data not found !");
        }

        logger.info("ResearchDomainServiceImpl:getLatestApplicationController end");

        return response;


    }


    @Override
    public ApiResponse getLatestCloudManagementController() {
        logger.info("ResearchDomainServiceImpl:getLatestCloudManagementController start");


        ApiResponse response = new ApiResponse();

        //get the latest  Cloud Management report from database.
        List<ResearchMaster> CloudManagementData = researchMasterRepository.getCloudManagement();

        if(!CloudManagementData.isEmpty()){
            response.setDomainResponse(CloudManagementData);
            response.setSuccess(true);
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(HttpStatus.OK);
            response.setMessage("Latest 3 updated Cloud Management  Data !");
        }else {
            response.setSuccess(false);
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setStatusCode(HttpStatus.NOT_FOUND);
            response.setMessage("Cloud Management data not found !");
        }

        logger.info("ResearchDomainServiceImpl:getLatestCloudManagementController end");

        return response;


    }



    @Override
    public ApiResponse getLatestbankingfinancialController() {
        logger.info("ResearchDomainServiceImpl:getLatestbankingfinancialController start");


        ApiResponse response = new ApiResponse();

        //get the latest   Banking & Financial Services report from database.
        List<ResearchMaster> bankingfinancialData = researchMasterRepository.getBankingFinancial();

        if(!bankingfinancialData.isEmpty()){
            response.setDomainResponse(bankingfinancialData);

            response.setSuccess(true);
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(HttpStatus.OK);
            response.setMessage("Latest 3 updated  Banking & Financial Services  Data !");
        }else {
            response.setSuccess(false);
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setStatusCode(HttpStatus.NOT_FOUND);
            response.setMessage(" Banking & Financial Services data not found !");
        }

        logger.info("ResearchDomainServiceImpl:getLatestbankingfinancialController end");

        return response;


    }


    @Override
    public ApiResponse getLatestBpmProcessController() {
        logger.info("ResearchDomainServiceImpl:getLatestBpmProcessController start");


        ApiResponse response = new ApiResponse();

        //get the latest   Bpm & Process Automation Services report from database.
        List<ResearchMaster> BpmProcessData = researchMasterRepository.getBpmProcessAutomation();

        if(!BpmProcessData.isEmpty()){
            response.setDomainResponse(BpmProcessData);

            response.setSuccess(true);
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(HttpStatus.OK);
            response.setMessage("Latest 3 updated  Bpm & Process Automation Data !");
        }else {
            response.setSuccess(false);
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setStatusCode(HttpStatus.NOT_FOUND);
            response.setMessage(" Bpm & Process Automation data not found !");
        }

        logger.info("ResearchDomainServiceImpl:getLatestBpmProcessController end");

        return response;


    }
}
