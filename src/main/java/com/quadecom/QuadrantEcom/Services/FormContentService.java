package com.quadecom.QuadrantEcom.Services;

import com.quadecom.QuadrantEcom.DTO.FormDto;
import com.quadecom.QuadrantEcom.Entities.FormContentEntity;
import com.quadecom.QuadrantEcom.Response.ApiResponse;

public interface FormContentService {

    ApiResponse saveFormData(FormDto formData);

}
