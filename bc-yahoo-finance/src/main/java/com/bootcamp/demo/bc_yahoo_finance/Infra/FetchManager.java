package com.bootcamp.demo.bc_yahoo_finance.Infra;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FetchManager {
    private static final String BASE_URL = "https://query1.finance.yahoo.com/v7/finance/quote?symbols=%s&crumb=%s";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper(); // Jackson core library

    @Autowired
    CrumbManager crumbManager;
    

    public String fetchQuotes(List<String> symbols) throws IOException, InterruptedException {
        String crumb = crumbManager.getCrumb();
        String symbolParam = String.join(",", symbols);
        String url = String.format(BASE_URL, symbolParam, crumb);
        System.out.println("Print from FetchManager: " + crumb);
        System.out.println("Print from FetchManager: " + crumbManager.cookie);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Cookie", crumbManager.cookie)
                    .GET()
                    .build();
            System.out.println("Request: " + request);
    
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); // synchronous request
    
            if (response.statusCode() == 200) {
                return response.body(); // Parse or map the response as needed
            } else {
                throw new IOException("Failed to fetch data, status code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace(); // Log the error for debugging
            return "Error: Unable to fetch quotes due to a network or server issue"; // Provide a meaningful error message
        }
    }
}
