package com.quadecom.QuadrantEcom.ResearchMaster;

import com.quadecom.QuadrantEcom.Config.PaginationMetadata;
import com.quadecom.QuadrantEcom.Response.ApiResponse;
import org.hibernate.sql.ast.tree.expression.Over;
import org.slf4j.ILoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ResearchMasterServiceImpl implements ResearchMasterService {

    private final ResearchMasterRepository researchMasterRepository;

    public ResearchMasterServiceImpl(ResearchMasterRepository researchMasterRepository) {
        this.researchMasterRepository = researchMasterRepository;
    }

    @Override
    public ResearchMaster createResearchMaster(ResearchMaster researchMaster) {
        return researchMasterRepository.save(researchMaster);
    }

    @Override
    public ApiResponse getResearchDataWithPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Object[]> researchDataPage = researchMasterRepository.getPaginatedEntities(pageable);

        List<ResearchMasterDto> researchData = new ArrayList<>();
        for (Object[] result : researchDataPage.getContent()) {
            ResearchMasterDto researchMaster = new ResearchMasterDto();
            researchMaster.setId((Integer) result[0]);
            researchMaster.setCategoryName((String) result[1]);
            researchMaster.setReportType((String) result[2]);
            researchMaster.setReport((String) result[3]);
            researchMaster.setDescription((String) result[4]);
            researchMaster.setAuthor((String) result[5]);
            researchMaster.setMAuthor((String) result[6]);
            researchMaster.setPublishDate((Date) result[7]);
            researchMaster.setPrice((BigInteger) result[8]);
            researchMaster.setPrice2((BigInteger) result[9]);
            researchMaster.setTableOfContent((String) result[10]);

            researchData.add(researchMaster);
        }

        ApiResponse response = new ApiResponse();

        response.setResearchMasterList(researchData);

        return response;
    }


    @Override
    public ApiResponse getResearchData(int page, int size) {
        ApiResponse response = new ApiResponse();

        try {
            // Fetch the paginated data from the repository
            Pageable pageable = PageRequest.of(page, size);
            Page<Object[]> pageResults = researchMasterRepository.getResearchData(pageable);

            // Extract the results from the page
            List<Object[]> results = pageResults.getContent();

            // Process the results and map them to your desired DTO or entity objects
            List<ResearchMasterDto> researchDtos = new ArrayList<>();
            for (Object[] result : results) {
                // Map each result to your DTO object
                ResearchMasterDto dto = new ResearchMasterDto();
                dto.setId((Integer) result[0]);
                dto.setCategoryName((String) result[1]);
                dto.setReportType((String) result[2]);
                dto.setReport((String) result[3]);
                dto.setDescription((String) result[4]);
                dto.setAuthor((String) result[5]);
                dto.setMAuthor((String) result[6]);
                dto.setPublishDate((Date) result[7]);
                dto.setPrice((BigInteger) result[8]);
                dto.setPrice2((BigInteger) result[9]);
                dto.setTableOfContent((String) result[10]);
                // Set other properties...
                researchDtos.add(dto);
            }

            if (researchDtos.isEmpty()) {
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setSuccess(false);
                response.setMessage("Cannot fetch the result");
            } else {
                response.setResearchMasterList(researchDtos);
                response.setStatus(HttpStatus.OK);
                response.setSuccess(true);
                response.setMessage("Fetched the results of size " + size);

                // Create a PaginationMetadata object to hold the pagination information
                PaginationMetadata paginationMetadata = new PaginationMetadata(
                        page,
                        size,
                        (int) pageResults.getTotalElements(),
                        pageResults.getTotalPages()
                );

                // Set the pagination metadata in the ApiResponse
                response.setPagination(paginationMetadata);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @Override
    public ApiResponse getResearchDataByReportType(List<Integer> reportTypeIds, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Object[]> researchDataPage = researchMasterRepository.getResearchDataByReportTypeWithPagination(reportTypeIds,pageable);

        List<ResearchMasterDto> researchData = new ArrayList<>();
        for (Object[] result : researchDataPage.getContent()) {
            ResearchMasterDto researchMaster = new ResearchMasterDto();
            researchMaster.setId((Integer) result[0]);
            researchMaster.setCategoryName((String) result[1]);
            researchMaster.setReportType((String) result[2]);
            researchMaster.setReport((String) result[3]);
            researchMaster.setDescription((String) result[4]);
            researchMaster.setAuthor((String) result[5]);
            researchMaster.setMAuthor((String) result[6]);
            researchMaster.setPublishDate((Date) result[7]);
            researchMaster.setPrice((BigInteger) result[8]);
            researchMaster.setPrice2((BigInteger) result[9]);
            researchMaster.setTableOfContent((String) result[10]);

            researchData.add(researchMaster);
        }

        ApiResponse response = new ApiResponse();

        response.setResearchMasterList(researchData);

        return response;
    }


    @Override
    public ApiResponse getResearchDataByReportCoverage(List<Integer> reportCoverageIds, int page, int pageSize) {
        ApiResponse response = new ApiResponse();

        try {
            // Calculate the offset for pagination
            int offset = (page - 1) * pageSize;

            // Fetch the data from the repository based on the reportCoverageIds parameter and pagination
            Page<Object[]> results = researchMasterRepository.getResearchDataByCategoryWithPagination(reportCoverageIds, PageRequest.of(offset, pageSize));

            // Process the results and map them to your desired DTO or entity objects
            List<ResearchMasterDto> researchDtos = new ArrayList<>();
            for (Object[] result : results) {
                // Map each result to your DTO object
                ResearchMasterDto dto = new ResearchMasterDto();
                dto.setId((Integer) result[0]);
                dto.setCategoryName((String) result[1]);
                dto.setReportType((String) result[2]);
                dto.setReport((String) result[3]);
                dto.setDescription((String) result[4]);
                dto.setAuthor((String) result[5]);
                dto.setMAuthor((String) result[6]);
                dto.setPublishDate((Date) result[7]);
                dto.setPrice((BigInteger) result[8]);
                dto.setPrice2((BigInteger) result[9]);
                dto.setTableOfContent((String) result[10]);
                // Set other properties...
                researchDtos.add(dto);
            }

            // Retrieve the total count
            int totalCount = researchMasterRepository.getResearchDataCountByReportType(reportCoverageIds.get(0));

            if (researchDtos.isEmpty()) {
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setSuccess(false);
                response.setMessage("Cannot fetch the result");
            } else {
                PaginationMetadata paginationMetadata = new PaginationMetadata();
                paginationMetadata.setCurrentPage(page);
                paginationMetadata.setPageSize(pageSize);
                paginationMetadata.setTotalItems(totalCount);
                int totalPages = (int) Math.ceil((double) totalCount / pageSize);
                paginationMetadata.setTotalPages(totalPages);

                System.out.println(paginationMetadata);

                response.setResearchMasterList(researchDtos);
                response.setPagination(paginationMetadata);
                response.setStatus(HttpStatus.OK);
                response.setSuccess(true);
                response.setMessage("Fetched the results of size " + researchDtos.size());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }

        return response;
    }


    @Override
    public ApiResponse getResearchMasterDtoById(Integer id) {
        ApiResponse response = new ApiResponse();

        ResearchMasterDto singleResearch = researchMasterRepository.findResearchMasterDtoById(id);

        if(singleResearch==null){
            response.setSuccess(false);
            response.setMessage("No Research With That Id Found");
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setResearchMaster(null);
        }else {
            response.setSuccess(true);
            response.setMessage("Research with id " + id + " found !");
            response.setStatus(HttpStatus.OK);
            response.setResearchMaster(singleResearch);
        }

        return response;
    }

    public List<ResearchMaster> getResearchByName(String reportName) {
        List<ResearchMaster> allResearch = researchMasterRepository.findAll();

        List<ResearchMaster> matchingResearch = new ArrayList<>();

        for (ResearchMaster research : allResearch) {
            if (matchesReportName(research.getReport(), reportName)) {
                matchingResearch.add(research);
            }
        }

        return matchingResearch;
    }

    private boolean matchesReportName(String dbReportName, String searchReportName) {
        String normalizedDbReport = normalizeReportName(dbReportName);
        String normalizedSearchReport = normalizeReportName(searchReportName);

        return normalizedDbReport.contains(normalizedSearchReport);
    }

    private String normalizeReportName(String reportName) {
        // Remove punctuation and convert to lowercase
        String normalized = reportName.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();

        // Remove extra spaces
        normalized = normalized.replaceAll("\\s+", " ").trim();

        return normalized;
    }

    public List<ResearchMaster> searchResearchByNormalizedReportName(String normalizedReportName) {
        List<ResearchMaster> allResearch = researchMasterRepository.findAll();

        List<ResearchMaster> matchingResearch = new ArrayList<>();

        for (ResearchMaster research : allResearch) {
            if (matchesReportName(research.getReport(), normalizedReportName)) {
                matchingResearch.add(research);
            }
        }

        return matchingResearch;
    }

    @Override
    public ApiResponse getResearchByName(String reportName,int page,int pageSize){
        ApiResponse response = new ApiResponse();
        String modifiedReportName = "";
        if (reportName.contains("-")) {
            // Replace hyphens with spaces
            modifiedReportName = reportName.replace("-", " ");
            reportName = modifiedReportName;
        }

        try {
            // Calculate the offset for pagination
            int offset = (page - 1) * pageSize;

            // Fetch the data from the repository based on the reportCoverageIds parameter and pagination
            Page<Object[]> results = researchMasterRepository.getResearchByName(reportName,PageRequest.of(offset, pageSize));

            // Process the results and map them to your desired DTO or entity objects
            List<ResearchMasterDto> researchDtos = new ArrayList<>();
            for (Object[] result : results) {
                // Map each result to your DTO object
                ResearchMasterDto dto = new ResearchMasterDto();
                dto.setId((Integer) result[0]);
                dto.setCategoryName((String) result[1]);
                dto.setReportType((String) result[2]);
                dto.setReport((String) result[3]);
                dto.setDescription((String) result[4]);
                dto.setAuthor((String) result[5]);
                dto.setMAuthor((String) result[6]);
                dto.setPublishDate((Date) result[7]);
                dto.setPrice((BigInteger) result[8]);
                dto.setPrice2((BigInteger) result[9]);
                dto.setTableOfContent((String) result[10]);
                // Set other properties...
                researchDtos.add(dto);
            }

            // Retrieve the total count
            int totalCount = researchMasterRepository.getResearchDataCount(reportName);

            if (researchDtos.isEmpty()) {
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setSuccess(false);
                response.setMessage("Cannot fetch the result");
            } else {
                PaginationMetadata paginationMetadata = new PaginationMetadata();
                paginationMetadata.setCurrentPage(page);
                paginationMetadata.setPageSize(pageSize);
                paginationMetadata.setTotalItems(totalCount);
                int totalPages = (int) Math.ceil((double) totalCount / pageSize);
                paginationMetadata.setTotalPages(totalPages);

                System.out.println(paginationMetadata);

                response.setResearchMasterList(researchDtos);
                response.setPagination(paginationMetadata);
                response.setStatus(HttpStatus.OK);
                response.setSuccess(true);
                response.setMessage("Fetched the results of size " + researchDtos.size());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @Override
    public int getTotalResearchCount() {
        return researchMasterRepository.getTotalResearchCount();
    }


    @Override
    public ApiResponse getlatestsparkmatrixreportController() {

        ApiResponse response = new ApiResponse();

        //get the latest  Cloud Management report from database.
        List<ResearchMaster> latestreportData = researchMasterRepository.getlatestreport();

        if(!latestreportData.isEmpty()){
            response.setDomainResponse(latestreportData);
            response.setSuccess(true);
            response.setStatus(HttpStatus.OK);
            response.setStatusCode(HttpStatus.OK);
            response.setMessage("Latest 6 report  Data !");
        }else {
            response.setSuccess(false);
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setStatusCode(HttpStatus.NOT_FOUND);
            response.setMessage(" latest 6 report  data not found !");
        }

        return response;


    }


}
