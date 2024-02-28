package com.quadecom.QuadrantEcom.Controllers;

import com.quadecom.QuadrantEcom.Entities.BlogEntity;
import com.quadecom.QuadrantEcom.Response.ApiResponse;
import com.quadecom.QuadrantEcom.ServiceImpl.ResearchDomainServiceImpl;
import com.quadecom.QuadrantEcom.Services.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    Logger logger= LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private BlogService blogService;

    @GetMapping("/get-all-blogs")
    public ResponseEntity<ApiResponse> getPressReleaseList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    ){
        logger.info("BlogController:getPressReleaseList start");

        ApiResponse blogList = blogService.getPressReleaseList(page,size);

        if(blogList!=null){
            blogList.setSuccess(true);
            blogList.setMessage("Received Press Release List !");
            logger.info("BlogController:getPressReleaseList end");

            return ResponseEntity.ok(blogList);

        }else {
            logger.info("BlogController:getPressReleaseList end with status code"+HttpStatus.NOT_FOUND);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }


    }

    @GetMapping("/get-blog-by-id")
    public ResponseEntity<ApiResponse> getBlogById(
            @RequestParam int id
    ){
        logger.info("BlogController:getBlogById start");

        ApiResponse blogList = blogService.getBlogById(id);

        if(blogList!=null){
            blogList.setSuccess(true);
            if(blogList.getSinglePressRelease()!=null) {
                blogList.setMessage(blogList.getSinglePressRelease().getPostType());
            }
            logger.info("BlogController:getBlogById end");

            return ResponseEntity.ok(blogList);
        }else {
            logger.info("BlogController:getBlogById end with status code"+HttpStatus.NOT_FOUND);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @GetMapping("/get-blogs-list")
    public ResponseEntity<ApiResponse> getCompleteBlogList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    ){
        logger.info("BlogController:getCompleteBlogList start");

        ApiResponse blogList = blogService.getCompleteBlogList(page,size);

        if(blogList!=null){
            blogList.setSuccess(true);
            blogList.setMessage("Received Blogs !");
            logger.info("BlogController:getCompleteBlogList end");

            return ResponseEntity.ok(blogList);

        }else {
            logger.info("BlogController:getCompleteBlogList end with status code"+HttpStatus.NOT_FOUND);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @GetMapping("/get-single-blog-by-id")
    public ResponseEntity<ApiResponse> getSingleBlogById(
            @RequestParam int id
    ){
        logger.info("BlogController:getSingleBlogById start");

        ApiResponse blogList = blogService.getBlogById(id);

        if(blogList!=null){
            blogList.setSuccess(true);
            blogList.setMessage("Received Single Blog !");
            logger.info("BlogController:getSingleBlogById end");

            return ResponseEntity.ok(blogList);
        }else {
            logger.info("BlogController:getSingleBlogById end with status code"+HttpStatus.NOT_FOUND);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }


}
