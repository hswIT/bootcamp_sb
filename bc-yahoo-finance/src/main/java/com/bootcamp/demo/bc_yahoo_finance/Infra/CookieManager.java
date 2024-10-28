package com.bootcamp.demo.bc_yahoo_finance.Infra;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import org.springframework.web.client.RestTemplate;

@Component
public class CookieManager{

  //   @Autowired
  //   @Qualifier(value = "YfinanceRestTemplate")
  //   private RestTemplate restTemplate; // Tool A (Dependency of CookieManager)
    
  //   public CookieManager(RestTemplate restTemplate) { // Dependency Injection (Constructor Injection)
  //     this.restTemplate = restTemplate;
  //   }

    // Action B, require Tool A

  private static final String COOKIE_URL = "https://fc.yahoo.com";
  private RestTemplate restTemplate; // Tool A (Dependency of CookieManager)

  // Dependency Injection (Constructor Injection)
  // Optional to use @Autowired as it is implicitly included
  public CookieManager(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  // Action B, requires Tool A
  public String getCookie() {
    // try {
    //   ResponseEntity<String> responseEntity =
    //       this.restTemplate.getForEntity(COOKIE_URL, String.class); // throw
    //   List<String> cookies = responseEntity.getHeaders().get("Set-Cookie");
    //   return cookies.get(0).split(";")[0];

    // } catch (HttpClientErrorException e) {
    //   // System.out.println(e.getClass().getName()); // HttpClientErrorException
    //   HttpHeaders headers = e.getResponseHeaders();
    //   List<String> cookies = headers == null ? null : headers.get("Set-Cookie");
    //   if (cookies == null || cookies.isEmpty())
    //     return null;
    //   return cookies.get(0).split(";")[0]; // String -> String[] -> String
    // }

    try {
      ResponseEntity<String> responseEntity =
          this.restTemplate.getForEntity(COOKIE_URL, String.class); // throw
      List<String> cookies = responseEntity.getHeaders().get("Set-Cookie");
      System.out.println("Print from CookManager: " + cookies);
      return cookies.get(0).split(";")[0];
    } catch (HttpClientErrorException e) {
      // System.out.println(e.getClass().getName()); // HttpClientErrorException
      HttpHeaders headers = e.getResponseHeaders();
      List<String> cookies = headers == null ? null : headers.get("Set-Cookie");
      System.out.println(cookies);
      if (cookies == null || cookies.isEmpty())
        return null;
      return cookies.get(0).split(";")[0]; // String -> String[] -> String
    }
  }




}

