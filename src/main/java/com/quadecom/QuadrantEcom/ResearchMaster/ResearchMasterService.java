package com.quadecom.QuadrantEcom.ResearchMaster;

import com.quadecom.QuadrantEcom.Response.ApiResponse;

import java.util.List;

public interface ResearchMasterService {
    ResearchMaster createResearchMaster(ResearchMaster researchMaster);

    ApiResponse getResearchDataWithPage(int page, int size);

    ApiResponse getResearchData(int page, int size);

    ApiResponse getResearchMasterDtoById(Integer id);

    ApiResponse getResearchByName(String reportName,int page,int pageSize);

    int getTotalResearchCount();

    ApiResponse getResearchDataByReportType(List<Integer> reportTypeIds, int page, int pageSize);

    ApiResponse getResearchDataByReportCoverage(List<Integer> reportCoverageIds, int page, int pageSize);

    ApiResponse getlatestsparkmatrixreportController();
}
