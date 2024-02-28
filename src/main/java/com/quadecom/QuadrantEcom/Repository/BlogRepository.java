package com.quadecom.QuadrantEcom.Repository;

import com.quadecom.QuadrantEcom.Entities.BlogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<BlogEntity, Long> {

    @Query("SELECT b.id, b.blogTitle, b.postDate, b.postContent, b.postAuthor, b.endDate, b.postType, b.postCategory, b.postImgPath FROM BlogEntity b WHERE b.postType = 'Press Release' ORDER BY b.postDate DESC")
    Page<Object[]> getAllBlogList(Pageable pageable);

    @Query("SELECT b.id, b.blogTitle, b.postDate, b.postContent, b.postAuthor, b.endDate, b.postType, b.postCategory, b.postImgPath FROM BlogEntity b WHERE b.postType = 'Press Release' ORDER BY b.postDate DESC")
    List<Object[]> getPressReleaseList(Pageable pageable);

    @Override
    List<BlogEntity> findAll(Sort sort);

    @Query("SELECT b FROM BlogEntity b WHERE b.id=:id")
    BlogEntity findByPressId(int id);

    @Query("SELECT b.id, b.blogTitle, b.postDate, b.postContent, b.postAuthor, b.endDate, b.postType, b.postCategory, b.postImgPath FROM BlogEntity b WHERE b.postType = 'Blog' ORDER BY b.postDate DESC")
    Page<Object[]> getBlogList(Pageable pageable);

    @Query("SELECT b.id, b.blogTitle, b.postDate, b.postContent, b.postAuthor, b.endDate, b.postType, b.postCategory, b.postImgPath FROM BlogEntity b WHERE b.postType = 'Blog' ORDER BY b.postDate DESC")
    List<Object[]> getCompleteBlogList(Pageable pageable);

    @Query("SELECT b FROM BlogEntity b WHERE b.postType = 'Blog' ORDER BY b.postDate DESC")
    List<BlogEntity> getFullBlogList();

    @Query("SELECT b FROM BlogEntity b WHERE LOWER(b.blogTitle) LIKE LOWER(concat('%', :blogTitle, '%'))")
    List<BlogEntity> getBlogByName(@Param("blogTitle") String blogTitle, Pageable pageable);

    @Query("SELECT COUNT(rm) FROM BlogEntity rm WHERE rm.blogTitle LIKE %:blogTitle%")
    Integer getBlogDataCount(@Param("blogTitle") String blogTitle);

}
