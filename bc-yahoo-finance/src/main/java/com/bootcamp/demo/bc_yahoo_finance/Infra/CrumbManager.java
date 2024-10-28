package com.bootcamp.demo.bc_yahoo_finance.Infra;

import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CrumbManager {

  private final RestTemplate restTemplate;
  private final CookieManager cookieManager; // CookieManager dependency
  private String crumb;
  String cookie;

  // Constructor injection for RestTemplate and CookieManager
  public CrumbManager(RestTemplate restTemplate, CookieManager cookieManager) {
      this.restTemplate = restTemplate;
      this.cookieManager = cookieManager;
      this.cookie = cookieManager.getCookie();
  }

  // Method to retrieve the crumb using cookies from CookieManager
  public String getCrumb() {
      // If crumb is already available, return it
      if (crumb != null) {
          return crumb;
      }

      System.out.println("Print from CrumbManager: " + cookie);

      // URL to retrieve the crumb
      String crumbUrl = "https://query1.finance.yahoo.com/v1/test/getcrumb";
      System.out.println("Access to Crumb: " + crumbUrl);

      try {
          // Create HttpHeaders with the cookie
          HttpHeaders headers = new HttpHeaders();
          headers.add("Cookie", cookie);
          headers.add("User-Agent", "Mozilla/5.0");

          // Send the GET request with the cookie
          HttpEntity<String> requestEntity = new HttpEntity<>(headers);
          ResponseEntity<String> responseEntity = restTemplate.exchange(crumbUrl, HttpMethod.GET, requestEntity, String.class);

          // Extract the crumb from the response body
          crumb = responseEntity.getBody().trim();  // Assuming the response body is the crumb itself

          System.out.println("Retrieved Crumb: " + crumb);
          return crumb;
      } catch (Exception e) {
          e.printStackTrace();  // Print the detailed error
          throw new RuntimeException("Failed to retrieve the crumb", e);
      }
  }

  // Method to force refresh the crumb
  public String refreshCrumb() {
      crumb = null;  // Clear the cached crumb
      return getCrumb();  // Fetch a new crumb
  }

}
