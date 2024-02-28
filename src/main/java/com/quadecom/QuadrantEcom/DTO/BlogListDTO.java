package com.quadecom.QuadrantEcom.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BlogListDTO {

    private Integer id;

    private String blogTitle;

    private Date postDate;

    private String postContent;

    private String postAuthor;

    private Date endDate;

    private String postType;

    private String postCategory;

    private String postImgPath;
}
