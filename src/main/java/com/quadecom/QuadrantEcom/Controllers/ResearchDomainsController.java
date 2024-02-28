package com.quadecom.QuadrantEcom.Controllers;

import com.quadecom.QuadrantEcom.Response.ApiResponse;
import com.quadecom.QuadrantEcom.Services.ResearchDomainsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domains")
public class ResearchDomainsController {

    Logger logger= LoggerFactory.getLogger(ResearchDomainsController.class);

    @Autowired
    ResearchDomainsService researchDomainsService;

    @GetMapping("/application-development")
    public ResponseEntity<ApiResponse> getLatestApplicationDevelopment(){

        logger.info("ResearchDomainsController:getLatestApplicationDevelopment start");


        ApiResponse response = researchDomainsService.getLatestApplicationController();

        if(response.getStatusCode() == HttpStatus.OK){
            logger.info("ResearchDomainsController:getLatestApplicationDevelopment end");

            return ResponseEntity.ok(response);

        }else {
            logger.info("ResearchDomainsController:getLatestApplicationDevelopment not found end");

            return ResponseEntity.notFound().build();
        }

    }



    @GetMapping("/cloud-management")
    public ResponseEntity<ApiResponse> getLatestCloudManagement(){

        logger.info("ResearchDomainsController:getLatestCloudManagement start");


        ApiResponse response = researchDomainsService.getLatestCloudManagementController();

        if(response.getStatusCode() == HttpStatus.OK){
            logger.info("ResearchDomainsController:getLatestCloudManagement end");

            return ResponseEntity.ok(response);

        }else {
            logger.info("ResearchDomainsController:getLatestCloudManagement  not found end");

            return ResponseEntity.notFound().build();
        }

    }


    @GetMapping("/Banking-Financial-Services")
    public ResponseEntity<ApiResponse> getLatestBankingFinancialServices(){

        logger.info("ResearchDomainsController:getLatestBankingFinancialServices start");


        ApiResponse response = researchDomainsService.getLatestbankingfinancialController();

        if(response.getStatusCode() == HttpStatus.OK){
            logger.info("ResearchDomainsController:getLatestBankingFinancialServices end");

            return ResponseEntity.ok(response);
        }else {
            logger.info("ResearchDomainsController:getLatestBankingFinancialServices not found end");

            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/BPM-Process-Automation")
    public ResponseEntity<ApiResponse> getLatestBpmProcessAutomation(){

        logger.info("ResearchDomainsController:getLatestBpmProcessAutomation start");


        ApiResponse response = researchDomainsService.getLatestBpmProcessController();

        if(response.getStatusCode() == HttpStatus.OK){
            logger.info("ResearchDomainsController:getLatestBpmProcessAutomation end");

            return ResponseEntity.ok(response);

        }else {
            logger.info("ResearchDomainsController:getLatestBpmProcessAutomation not found end");

            return ResponseEntity.notFound().build();
        }

    }

}
