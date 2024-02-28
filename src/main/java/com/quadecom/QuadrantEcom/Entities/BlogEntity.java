package com.quadecom.QuadrantEcom.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="post_blogs")
public class BlogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT",name = "blog_title")
    private String blogTitle;

    @Column(name = "post_date")
    private Date postDate;

    @Column(columnDefinition = "TEXT",name = "post_content")
    private String postContent;

    @Column(name = "post_author")
    private String postAuthor;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "post_type")
    private String postType;

    @Column(columnDefinition = "TEXT",name = "post_category")
    private String postCategory;

    @Column(columnDefinition = "TEXT",name = "post_img")
    private String postImgPath;
}
