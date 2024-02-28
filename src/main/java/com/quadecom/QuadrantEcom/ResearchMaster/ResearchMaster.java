package com.quadecom.QuadrantEcom.ResearchMaster;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.w3c.dom.Text;

import java.math.BigInteger;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="research_master_table")
public class ResearchMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT",name = "report")
    private String report;

    @Column(columnDefinition = "TEXT",name = "Description")
    private String description;

    @Column(name = "author")
    private String author;

    @Column(name = "m_author")
    private String mAuthor;

    @Column(name = "publish_date")
    private Date publishDate;

    @Column(columnDefinition = "TEXT",name = "img_path")
    private String imgPath;

    @Column(name = "price")
    private BigInteger price;

    @Column(name = "price2")
    private BigInteger price2;

    @Column(columnDefinition = "TEXT",name = "table_of_content")
    private String tableOfContent;

    @Column(name = "executive_overview")
    private String executiveOverview;

    @Column(name = "market_overview")
    private String marketOverView;

    @Column(name = "competition_landscape_and_analysis")
    private String competitionLandScapeAndAnalysis;

    @Column(name = "study_name")
    private String studyName;

    @Column(columnDefinition = "TEXT",name = "research_methodologis")
    private String researchMethodologies;

    @Column(name = "vendor_profiles")
    private String vendorProfiles;

    @Column(name = "custom_research_service")
    private String customResearchService;

    @Column(columnDefinition = "TEXT",name = "contents")
    private String contents;

    @Column(name = "report_type_fk")
    private Integer reportTypeFk;

    @Column(name = "category_id_fk")
    private Integer categoryIdFk;

    @Column(name = "research_leader")
    private String researchLeader;

    @Column(name = "leads")
    private String leads;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "status")
    private Integer status;

}
