package com.quadecom.QuadrantEcom.Controllers;

import com.quadecom.QuadrantEcom.ResearchMaster.ResearchMasterService;
import com.quadecom.QuadrantEcom.Response.ApiResponse;
import com.quadecom.QuadrantEcom.ServiceImpl.ResearchDomainServiceImpl;
import com.quadecom.QuadrantEcom.Services.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/quadrant-solutions")
public class GlobalController {

    Logger logger= LoggerFactory.getLogger(GlobalController.class);

    @Autowired
    ResearchMasterService researchMasterService;

    @Autowired
    BlogService blogService;

    @GetMapping("/get-result-with-name")
    public ResponseEntity<ApiResponse> getResearchByName(
            @RequestParam(required = true) String reportName,
            @RequestParam(defaultValue = "1",required = false) int page,
            @RequestParam(defaultValue = "100",required = false) int pageSize) {

        logger.info("GlobalController:getResearchByName start");


        ApiResponse researchResponse = researchMasterService.getResearchByName(reportName,page,pageSize);

        ApiResponse blogResponse = blogService.getBlogByName(reportName,page,pageSize);

        if(!isNull(researchResponse.getResearchMasterList())){

            logger.info("GlobalController:getResearchByName research end");

            return ResponseEntity.ok(researchResponse);

        } else if (!isNull(blogResponse.getBlogList())) {

            logger.info("GlobalController:getResearchByName blog end");

            return ResponseEntity.ok(blogResponse);

        }else{

            logger.info("GlobalController:getResearchByName not found end");

            return ResponseEntity.notFound().build();

        }

    }
}
