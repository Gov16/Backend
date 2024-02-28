package com.quadecom.QuadrantEcom.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quadecom.QuadrantEcom.DTO.SessionCreationDTO;
import com.quadecom.QuadrantEcom.DTO.SessionDto;
import com.quadecom.QuadrantEcom.DTO.SessionRequestDto;
import com.quadecom.QuadrantEcom.Order.OrderDataDto;
import com.quadecom.QuadrantEcom.Order.OrderRequestDto;
import com.quadecom.QuadrantEcom.Order.OrderService;
import com.quadecom.QuadrantEcom.ResearchMaster.ResearchMasterDto;
import com.quadecom.QuadrantEcom.ResearchMaster.ResearchMasterRepository;
import com.quadecom.QuadrantEcom.Response.ApiResponse;
import com.quadecom.QuadrantEcom.Services.HDFCServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.*;

@RestController
@RequestMapping("/session")
public class HDFCSessionController {

    @Autowired
    ResearchMasterRepository researchRepo;

    @Autowired
    private OrderService orderService;

    @Autowired
    HDFCServices hdfcServices;

    @RequestMapping(value = "/check-results", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> returnUrl(
            @RequestParam(value = "resultIndicator", required = false) String resultIndicator,
            @RequestParam(value = "sessionVersion" , required = false) String sessionVersion,
            @RequestParam(value = "checkoutVersion" , required = false) String checkoutVersion,
            RedirectAttributes redirectAttributes
    ) throws IOException {
        System.out.println(resultIndicator + " - " + sessionVersion + " - " + checkoutVersion);

        ApiResponse response = new ApiResponse();
        response = hdfcServices.findOrdersWithUserDetailsBySuccessIndicator(resultIndicator);

        Resource resource = new ClassPathResource("static/thankyou.html");
        InputStream inputStream = resource.getInputStream();

        // Set the content type to HTML
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);

        // Wrap the input stream in an InputStreamResource
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

        // Return a ResponseEntity with the HTML content and headers
        return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);

    }


    @RequestMapping(value = "/creation", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> createSession(
            @RequestBody SessionCreationDTO sessionRequestDto

    ) throws IOException {
//        System.out.println(sessionRequestDto.getOrderRequestDto());



        int page = 1;
        int pageSize = 100;
        int offset = (page - 1) * pageSize;
        Integer totalPrice = 0;
        for (int i =0 ; sessionRequestDto.getOrderRequestDto().getCart().size() > i; i++){
            String reportName = sessionRequestDto.getOrderRequestDto().getCart().get(i).getResearch().getReport();
            Page<Object[]> results = researchRepo.getResearchByName(reportName, PageRequest.of(offset, pageSize));
            for (int j =0 ; results.getTotalElements() > j; j++){
                for (Object[] result : results) {
                    totalPrice = totalPrice + (Integer.parseInt(result[8].toString()));
                }
            }
        }
        sessionRequestDto.getSessionRequestDto().getOrder().setAmount(totalPrice.toString());

        SessionDto response = this.doYourThing(sessionRequestDto);

        System.out.println(sessionRequestDto.getSessionRequestDto());

        UUID uid = UUID.randomUUID();
        OrderDataDto orderData = new OrderDataDto();
        orderData.setSessionID(response.getSession().getId());
        orderData.setOrderId(sessionRequestDto.getSessionRequestDto().getOrder().getId());
        orderData.setCurrency(sessionRequestDto.getSessionRequestDto().getOrder().getCurrency());
        orderData.setTotalAmount(totalPrice.toString());
        orderData.setSuccessIndicator(response.getSuccessIndicator());

        sessionRequestDto.getOrderRequestDto().setOrder(orderData);

        System.out.println("totalPrice: " + totalPrice);

        ApiResponse order  = orderService.createOrder(sessionRequestDto.getOrderRequestDto());
        order.setUid(sessionRequestDto.getSessionRequestDto().getOrder().getId());
        order.setSession(response);

        return ResponseEntity.ok().body(order);

    }

    private HttpHeaders createHttpHeaders(String user, String password)
    {
        String notEncoded = user + ":" + password;
        String encodedAuth = "Basic " + Base64.getEncoder().encodeToString(notEncoded.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", encodedAuth);
        return headers;
    }

    // code eligible for USD only.
    // Must make same code for other currency or make this code dynamic.
    private SessionDto doYourThing(SessionCreationDTO sessionRequestDto) {
        String theUrl = "https://hdfcbank.gateway.mastercard.com/api/rest/version/67/merchant/99020164/session";
        RestTemplate restTemplate = new RestTemplate();

        try {
            HttpHeaders headers = createHttpHeaders("merchant.99020164", "97a9e918146d91123dcb80068c114c45");

            // You can use the ResponseEntity to receive the entire response and access the body and status code.
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(theUrl, new HttpEntity<>(sessionRequestDto.getSessionRequestDto(), headers), String.class);

            // Parse the JSON response body using ObjectMapper (you need to add the Jackson dependency for this)
            ObjectMapper objectMapper = new ObjectMapper();
            SessionDto response = objectMapper.readValue(responseEntity.getBody(), SessionDto.class);

            // Print the session ID received in the response
            System.out.println(response);
            System.out.println("Session ID: " + response.getSession().getId());

            // Now you can access the session ID and any other information from the response
            // If needed, you can also build a custom response object instead of directly using ApiResponse

            // You can return the ApiResponse or your custom response object as needed
            return response;
        } catch (Exception eek) {
            System.out.println("** Exception: " + eek.getMessage());
            return null; // Return a proper error response as needed
        }
    }

}
