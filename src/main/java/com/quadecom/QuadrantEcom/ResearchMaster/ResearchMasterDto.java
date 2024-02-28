package com.quadecom.QuadrantEcom.ResearchMaster;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResearchMasterDto {
    private Integer id;
    private String categoryName;
    private String reportType;
    private String report;

    private String description;

    private String author;
    private String mAuthor;
    private Date publishDate;
    private BigInteger price;
    private BigInteger price2;

    private String tableOfContent;

    public ResearchMasterDto(String categoryName, String reportType, String report, String description, String author, String mAuthor, Date publishDate, BigInteger price, BigInteger price2, String tableOfContent) {
        this.categoryName = categoryName;
        this.reportType = reportType;
        this.report = report;
        this.description = description;
        this.author = author;
        this.mAuthor = mAuthor;
        this.publishDate = publishDate;
        this.price = price;
        this.price2 = price2;
        this.tableOfContent = tableOfContent;
    }
}
