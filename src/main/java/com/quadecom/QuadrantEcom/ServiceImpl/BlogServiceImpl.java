package com.quadecom.QuadrantEcom.ServiceImpl;

import com.quadecom.QuadrantEcom.Config.PaginationMetadata;
import com.quadecom.QuadrantEcom.DTO.BlogListDTO;
import com.quadecom.QuadrantEcom.Entities.BlogEntity;
import com.quadecom.QuadrantEcom.Repository.BlogRepository;
import com.quadecom.QuadrantEcom.ResearchMaster.ResearchMasterDto;
import com.quadecom.QuadrantEcom.Response.ApiResponse;
import com.quadecom.QuadrantEcom.Services.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.ValidationUtils;

import java.math.BigInteger;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    Logger logger= LoggerFactory.getLogger(BlogServiceImpl.class);
    @Autowired
    BlogRepository blogRepo;

    @Override
    public ApiResponse getPressReleaseList(int page, int size) {
        logger.info("BlogServiceImpl:getPressReleaseList start");
        Pageable pageable = PageRequest.of(page, size);
        ApiResponse res = new ApiResponse();
        Page<Object[]> blogList = blogRepo.getAllBlogList(pageable);
        List<Object[]> pressReleaseList = blogRepo.getPressReleaseList(pageable);

        List<BlogListDTO> blogDtoList = new ArrayList<>();

        for (Object[] result : pressReleaseList) {
            try {
                BlogListDTO blogDto = new BlogListDTO();
                blogDto.setId((Integer) result[0]);
                blogDto.setBlogTitle((String) result[1]);
                blogDto.setPostDate((Date) result[2]);
                blogDto.setPostContent((String) result[3]);
                blogDto.setPostAuthor((String) result[4]);
                blogDto.setEndDate((Date) result[5]);
                blogDto.setPostType((String) result[6]);
                blogDto.setPostCategory((String) result[7]);
                blogDto.setPostImgPath((String) result[8]);

                blogDtoList.add(blogDto);
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        if(!blogList.isEmpty()){
            res.setBlogList(blogDtoList);
            // Create a PaginationMetadata object to hold the pagination information
            PaginationMetadata paginationMetadata = new PaginationMetadata(
                    page,
                    size,
                    (int) blogList.getTotalElements(),
                    blogList.getTotalPages()
            );

            res.setCount((int)blogList.getTotalElements());
            res.setPagination(paginationMetadata);
        }else {
            res.setSuccess(false);
            res.setStatus(HttpStatus.NOT_FOUND);
            res.setMessage("Blog List Not Found !");
        }

        logger.info("BlogServiceImpl:getPressReleaseList end");
        return res;
    }

    public ApiResponse getBlogById(int id) {
        logger.info("BlogServiceImpl:getBlogById start");
        int page = 0;
        int size = 100;

        Pageable pageable = PageRequest.of(page, size);
        ApiResponse res = new ApiResponse();
        BlogEntity blogList = blogRepo.findByPressId(id);
        BlogListDTO blogDtoList = new BlogListDTO();

        try{
            blogDtoList.setBlogTitle(blogList.getBlogTitle());
            blogDtoList.setId(blogList.getId());
            blogDtoList.setPostCategory(blogList.getPostCategory());
            blogDtoList.setEndDate(blogList.getEndDate());
            blogDtoList.setPostAuthor(blogList.getPostAuthor());
            blogDtoList.setPostCategory(blogList.getPostCategory());
            blogDtoList.setPostContent(blogList.getPostContent());
            blogDtoList.setPostDate(blogList.getPostDate());
            blogDtoList.setPostType(blogList.getPostType());
            blogDtoList.setPostImgPath(blogList.getPostImgPath());
        }catch (Exception e){
            e.printStackTrace();
        }

        res.setSinglePressRelease(blogDtoList);

        if(!res.getSinglePressRelease().getBlogTitle().isEmpty()){
            res.setSuccess(true);
            res.setMessage("found blog by id");
            res.setStatus(HttpStatus.OK);
        }else {
            res.setSuccess(false);
            res.setStatus(HttpStatus.NOT_FOUND);
            res.setMessage("Blog List Not Found !");
        }

        logger.info("BlogServiceImpl:getBlogById end");
        return res;
    }


    @Override
    public ApiResponse getCompleteBlogList(int page, int size) {
        logger.info("BlogServiceImpl:getCompleteBlogList start");
        Pageable pageable = PageRequest.of(page, size);
        ApiResponse res = new ApiResponse();
        Page<Object[]> blogList = blogRepo.getBlogList(pageable);
        List<BlogEntity> completeBlogList = blogRepo.getFullBlogList();
        List<BlogListDTO> blogDtoList = new ArrayList<>();



        for (BlogEntity result : completeBlogList) {
            try {
                BlogListDTO blogDto = new BlogListDTO();
                blogDto.setId((Integer) result.getId());
                blogDto.setBlogTitle((String) result.getBlogTitle());
                blogDto.setPostDate((Date) result.getPostDate());
                blogDto.setPostContent((String) result.getPostContent());
                blogDto.setPostAuthor((String) result.getPostAuthor());
                blogDto.setEndDate((Date) result.getEndDate());
                blogDto.setPostType((String) result.getPostType());
                blogDto.setPostCategory((String) result.getPostCategory());
                blogDto.setPostImgPath((String) result.getPostImgPath());

                blogDtoList.add(blogDto);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if(!blogList.isEmpty()){
            res.setBlogList(blogDtoList);
            // Create a PaginationMetadata object to hold the pagination information
            PaginationMetadata paginationMetadata = new PaginationMetadata(
                    page,
                    size,
                    (int) blogList.getTotalElements(),
                    blogList.getTotalPages()
            );

            res.setCount((int)blogList.getTotalElements());
            res.setPagination(paginationMetadata);
        }else {
            res.setSuccess(false);
            res.setStatus(HttpStatus.NOT_FOUND);
            res.setMessage("Blog List Not Found !");
        }

        logger.info("BlogServiceImpl:getCompleteBlogList end");
        return res;
    }

    @Override
    public ApiResponse getBlogByName(String blogTitle,int page,int pageSize){
        logger.info("BlogServiceImpl:getBlogByName start");

        ApiResponse response = new ApiResponse();

        try {

            int offset = (page - 1) * pageSize;


            List<BlogEntity> results = blogRepo.getBlogByName(blogTitle,PageRequest.of(offset, pageSize));


            List<BlogListDTO> researchDtos = new ArrayList<>();
            for (BlogEntity result : results) {

                BlogListDTO dto = new BlogListDTO();
                dto.setId((Integer) result.getId());
                dto.setBlogTitle((String) result.getBlogTitle());
                dto.setPostDate((Date) result.getPostDate());
                dto.setPostContent((String) result.getPostContent());
                dto.setPostAuthor((String) result.getPostAuthor());
                dto.setEndDate((Date) result.getEndDate());
                dto.setPostType((String) result.getPostType());
                dto.setPostCategory((String) result.getPostCategory());
                dto.setPostImgPath((String) result.getPostImgPath());

                researchDtos.add(dto);
            }

            // Retrieve the total count
            int totalCount = blogRepo.getBlogDataCount(blogTitle);

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

                response.setBlogList(researchDtos);
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

        logger.info("BlogServiceImpl:getBlogByName end");

        return response;
    }


}
