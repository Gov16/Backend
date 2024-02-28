package com.quadecom.QuadrantEcom.ResearchMaster;

import com.quadecom.QuadrantEcom.Config.PaginationMetadata;
import com.quadecom.QuadrantEcom.Exception.ApiException;
import com.quadecom.QuadrantEcom.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/research-masters")
public class ResearchMasterController {

    private final ResearchMasterService researchMasterService;

    public ResearchMasterController(ResearchMasterService researchMasterService) {
        this.researchMasterService = researchMasterService;
    }

    @PostMapping
    public ResearchMaster createResearchMaster(@RequestBody ResearchMaster researchMaster) {
        return researchMasterService.createResearchMaster(researchMaster);
    }

    @GetMapping("/get-total-research-number")
    public ResponseEntity<ApiResponse> getTotalCount(){
        int totalItems = researchMasterService.getTotalResearchCount();
        System.out.println("Found Total of "+ totalItems + " items.");
        ApiResponse res = new ApiResponse();
        res.setStatus(HttpStatus.OK);
        res.setMessage("Found Total of "+ totalItems + " items.");
        res.setCount(totalItems);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/research-list")
    public ResponseEntity<ApiResponse> getAllResearchWithPage(
            @RequestHeader Map<String,String> headers,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    ) {

        headers.forEach((key,value) ->{
            System.out.println("Header Name: "+key+" Header Value: "+value);
        });

        try {
            ApiResponse researchData = researchMasterService.getResearchDataWithPage(page, size);
            int totalItems = researchMasterService.getTotalResearchCount();
            int totalPages = (int) Math.ceil((double) totalItems / size);

            PaginationMetadata paginationMetadata = new PaginationMetadata(page, size, totalItems, totalPages);
            researchData.setPagination(paginationMetadata);
            researchData.setStatus(HttpStatus.OK);
            researchData.setSuccess(true);
            researchData.setMessage("fetched the total result size of = " + size);
            return ResponseEntity.ok(researchData);
        } catch (ApiException ex) {
            ApiResponse response = ex.getResponse();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/research")
    public ResponseEntity<ApiResponse> getAllResearch(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    ) {
        try {
            // Calculate the offset based on the page and size parameters
            int offset = page * size;

            // Call the researchMasterService to fetch the paginated data
            ApiResponse researchData = researchMasterService.getResearchData(offset, size);

            // Calculate the total number of research items
            int totalItems = researchMasterService.getTotalResearchCount();

            // Calculate the total number of pages based on the size parameter
            int totalPages = (int) Math.ceil((double) totalItems / size);

            // Create a PaginationMetadata object to hold the pagination information
            PaginationMetadata paginationMetadata = new PaginationMetadata(page, size, totalItems, totalPages);

            // Set the pagination metadata in the ApiResponse
            researchData.setPagination(paginationMetadata);

            return ResponseEntity.ok(researchData);
        } catch (ApiException ex) {
            ApiResponse response = ex.getResponse();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    @GetMapping("/research-by-report-type")
    public ResponseEntity<ApiResponse> getResearchByReportType(
            @RequestParam List<Object> reportTypeIds,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "100") int pageSize
    ) {
        try {
            List<Integer> intList = new ArrayList<>();
            for (Object value : reportTypeIds) {
                if (value instanceof Integer) {

                    int intValue = (Integer) value;
                    System.out.println("int " +intValue);
                    intList.add(intValue);
                    // Handle integer value
                } else if (value instanceof String) {

                    String stringValue = (String) value;
                    System.out.println("String "+stringValue);
                    intList.add(Integer.parseInt(stringValue));
                    // Handle string value
                }
            }


            ApiResponse researchData = researchMasterService.getResearchDataByReportType(intList, page, pageSize);
            int totalItems = researchMasterService.getTotalResearchCount();
            int totalPages = (int) Math.ceil((double) totalItems / pageSize);

            PaginationMetadata paginationMetadata = new PaginationMetadata(page, pageSize, totalItems, totalPages);
            researchData.setPagination(paginationMetadata);
            researchData.setStatus(HttpStatus.OK);
            researchData.setSuccess(true);
            researchData.setMessage("fetched the total result size of = " + pageSize);
            return ResponseEntity.ok(researchData);
        } catch (ApiException ex) {
            ApiResponse response = ex.getResponse();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/research-by-report-coverage")
    public ResponseEntity<ApiResponse> getResearchByReportCoverage(
            @RequestParam List<Object> reportCoverageIds,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "100") int pageSize
    ) {
        try {
            System.out.println(reportCoverageIds +" page : "+ page +" page size : "+ pageSize);
            List<Integer> intList = new ArrayList<>();
            for (Object value : reportCoverageIds) {
                if (value instanceof Integer) {

                    int intValue = (Integer) value;
                    System.out.println("int " +intValue);
                    intList.add(intValue);
                    // Handle integer value
                } else if (value instanceof String) {

                    String stringValue = (String) value;
                    System.out.println("String "+stringValue);
                    intList.add(Integer.parseInt(stringValue));
                    // Handle string value
                }
            }


            ApiResponse researchData = researchMasterService.getResearchDataByReportCoverage(intList, page, pageSize);
            int totalItems = researchMasterService.getTotalResearchCount();
            int totalPages = (int) Math.ceil((double) totalItems / pageSize);

            PaginationMetadata paginationMetadata = new PaginationMetadata(page, pageSize, totalItems, totalPages);
            researchData.setPagination(paginationMetadata);
            researchData.setStatus(HttpStatus.OK);
            researchData.setSuccess(true);
            researchData.setMessage("fetched the total result size of = " + pageSize);
            return ResponseEntity.ok(researchData);
        } catch (ApiException ex) {
            ApiResponse response = ex.getResponse();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    @GetMapping("/research/count")
    public ResponseEntity<Integer> getTotalResearchCount() {
        try {
            int totalItems = researchMasterService.getTotalResearchCount();
            return ResponseEntity.ok(totalItems);
        } catch (ApiException ex) {
            ApiResponse response = ex.getResponse();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/fetch-research-with-id")
    @ResponseBody
    public ResponseEntity<ApiResponse> getResearchMasterDtoById(@RequestParam(required = true) int id) {
        ApiResponse apiResponse = researchMasterService.getResearchMasterDtoById(id);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/fetch-research-with-name")
    public ResponseEntity<ApiResponse> getResearchByName(
            @RequestParam(required = true) String reportName,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "100") int pageSize) {
        ApiResponse apiResponse = researchMasterService.getResearchByName(reportName,page,pageSize);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse);
        } else {
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }


    @GetMapping("/latest-reports")
    public ResponseEntity<ApiResponse> getLatestsparkmatrix(){

        ApiResponse response = researchMasterService.getlatestsparkmatrixreportController();

        if(response.getStatusCode() == HttpStatus.OK){
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.notFound().build();
        }

    }

}
