package com.quadecom.QuadrantEcom.Services;

import com.quadecom.QuadrantEcom.Entities.BlogEntity;
import com.quadecom.QuadrantEcom.Response.ApiResponse;

public interface BlogService {

    ApiResponse getPressReleaseList(int page, int size);

    ApiResponse getBlogById(int id);

    ApiResponse getCompleteBlogList(int page, int size);

    ApiResponse getBlogByName(String blogTitle,int page,int pageSize);

}
