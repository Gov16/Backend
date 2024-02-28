package com.quadecom.QuadrantEcom.ResearchMaster;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResearchMasterRepository extends JpaRepository<ResearchMaster, Integer> {

    @Query("SELECT r FROM ResearchMaster r")
    List<ResearchMaster> getAllResearchMasters();

    @Query("SELECT rm.id, ct.categoryName, rt.reportType, rm.report, rm.description, rm.author, rm.mAuthor, rm.publishDate, rm.price, rm.price2, rm.tableOfContent " +
            "FROM ResearchMaster rm " +
            "LEFT JOIN ReportTableEntity rt ON rt.id = rm.reportTypeFk " +
            "LEFT JOIN CategoryEntity ct ON ct.id = rm.categoryIdFk " +
            "WHERE rm.status = '1' ORDER BY rm.publishDate DESC")
    Page<Object[]> getPaginatedEntities(Pageable pageable);

    @Query("SELECT rm.id, ct.categoryName, rt.reportType, rm.report, rm.description, rm.author, rm.mAuthor, rm.publishDate, rm.price, rm.price2, rm.tableOfContent " +
            "FROM ResearchMaster rm " +
            "LEFT JOIN ReportTableEntity rt ON rt.id = rm.reportTypeFk " +
            "LEFT JOIN CategoryEntity ct ON ct.id = rm.categoryIdFk " +
            "WHERE rm.status = '1' ORDER BY rm.publishDate DESC")
    Page<Object[]> getResearchData(Pageable pageable);

    @Query("SELECT new com.quadecom.QuadrantEcom.ResearchMaster.ResearchMasterDto(rm.id, ct.categoryName, rt.reportType, rm.report, rm.description, rm.author, rm.mAuthor, rm.publishDate, rm.price, rm.price2, rm.tableOfContent) " +
            "FROM ResearchMaster rm " +
            "LEFT JOIN ReportTableEntity rt ON rt.id = rm.reportTypeFk " +
            "LEFT JOIN CategoryEntity ct ON ct.id = rm.categoryIdFk " +
            "WHERE rm.id = :id AND rm.status = '1' ORDER BY rm.publishDate DESC")
    ResearchMasterDto findResearchMasterDtoById(@Param("id") Integer id);

    @Query("SELECT rm.id, ct.categoryName, rt.reportType, rm.report, rm.description, rm.author, rm.mAuthor, rm.publishDate, rm.price, rm.price2, rm.tableOfContent " +
            "FROM ResearchMaster rm " +
            "LEFT JOIN ReportTableEntity rt ON rt.id = rm.reportTypeFk " +
            "LEFT JOIN CategoryEntity ct ON ct.id = rm.categoryIdFk " +
            "WHERE rm.reportTypeFk IN :reportTypeIds AND rm.status = '1' ORDER BY rm.publishDate DESC")
    Page<Object[]> getResearchDataByReportTypeWithPagination(@Param("reportTypeIds") List<Integer> reportTypeIds, Pageable pageable);

    @Query("SELECT rm.id, ct.categoryName, rt.reportType, rm.report, rm.description, rm.author, rm.mAuthor, rm.publishDate, rm.price, rm.price2, rm.tableOfContent " +
            "FROM ResearchMaster rm " +
            "LEFT JOIN ReportTableEntity rt ON rt.id = rm.reportTypeFk " +
            "LEFT JOIN CategoryEntity ct ON ct.id = rm.categoryIdFk " +
            "WHERE rm.categoryIdFk IN :categoryIds AND rm.status = '1' ORDER BY rm.publishDate DESC")
    Page<Object[]> getResearchDataByCategoryWithPagination(@Param("categoryIds") List<Integer> categoryIds, Pageable pageable);

    @Query("SELECT COUNT(rm) FROM ResearchMaster rm WHERE rm.reportTypeFk = :reportTypeFk AND rm.status = '1'")
    Integer getResearchDataCountByReportType(@Param("reportTypeFk") Integer reportTypeFk);

    @Query("SELECT COUNT(rm) FROM ResearchMaster rm WHERE rm.status = '1' AND rm.report LIKE %:reportName%")
    Integer getResearchDataCount(@Param("reportName") String reportName);

    @Query("SELECT COUNT(rm) FROM ResearchMaster rm WHERE rm.status = '1'")
    int getTotalResearchCount();

    @Query("SELECT rm.id, ct.categoryName, rt.reportType, rm.report, rm.description, rm.author, rm.mAuthor, rm.publishDate, rm.price, rm.price2, rm.tableOfContent " +
            "FROM ResearchMaster rm " +
            "LEFT JOIN ReportTableEntity rt ON rt.id = rm.reportTypeFk " +
            "LEFT JOIN CategoryEntity ct ON ct.id = rm.categoryIdFk " +
            "WHERE rm.status = '1' " +
            "AND LOWER(rm.report) LIKE LOWER(concat('%', :reportName, '%')) ORDER BY rm.publishDate DESC")
    Page<Object[]> getResearchByName(@Param("reportName") String reportName, Pageable pageable);

    @Query("SELECT rm FROM ResearchMaster rm WHERE rm.categoryIdFk='1' ORDER BY rm.publishDate DESC LIMIT 3")
    List<ResearchMaster> getApplicationDevelopment();

    @Query("SELECT rm FROM ResearchMaster rm WHERE rm.categoryIdFk='3' ORDER BY rm.publishDate DESC LIMIT 3")
    List<ResearchMaster> getCloudManagement();

    @Query("select rm FROM ResearchMaster rm ORDER BY rm.publishDate DESC LIMIT 6")
    List<ResearchMaster> getlatestreport();

    @Query("SELECT rm FROM ResearchMaster rm WHERE  rm.reportTypeFk='5' AND   rm.categoryIdFk='2' ORDER BY rm.publishDate DESC LIMIT 3")
    List<ResearchMaster> getBankingFinancial();


    @Query("SELECT rm FROM ResearchMaster rm WHERE  rm.reportTypeFk='5' AND   rm.categoryIdFk='3' ORDER BY rm.publishDate DESC LIMIT 3")
    List<ResearchMaster> getBpmProcessAutomation();

}
