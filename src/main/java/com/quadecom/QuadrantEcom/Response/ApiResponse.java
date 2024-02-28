package com.quadecom.QuadrantEcom.Response;

import com.quadecom.QuadrantEcom.Cart.CartItem;
import com.quadecom.QuadrantEcom.Config.PaginationMetadata;
import com.quadecom.QuadrantEcom.DTO.BlogListDTO;
import com.quadecom.QuadrantEcom.DTO.FormDto;
import com.quadecom.QuadrantEcom.DTO.SessionDto;
import com.quadecom.QuadrantEcom.Order.NewOrderEntity;
import com.quadecom.QuadrantEcom.Order.OrderRequestDto;
import com.quadecom.QuadrantEcom.Product.Product;
import com.quadecom.QuadrantEcom.ResearchMaster.ResearchMaster;
import com.quadecom.QuadrantEcom.ResearchMaster.ResearchMasterDto;
import com.quadecom.QuadrantEcom.ResearchMaster.ResearchMasterService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private boolean success;
    private String message;
    private HttpStatus status;
    private HttpStatusCode statusCode;
    private List<Product> product;
    private ResearchMasterDto ResearchMaster;
    private List<BlogListDTO> blogList;
    private BlogListDTO singlePressRelease;
    private List<ResearchMasterDto> ResearchMasterList;
    private PaginationMetadata pagination;
    private int count;
    private OrderRequestDto orderData;
    private NewOrderEntity order;
    private List<CartItem> cartItems;
    private Boolean paymentStatus;
    private SessionDto session;
    private String uid;
    private FormDto formDto;
    private List<Object[]> returnOrderData;
    private List<ResearchMaster> domainResponse;
    private List<ResearchMasterService> latestreport;

}
