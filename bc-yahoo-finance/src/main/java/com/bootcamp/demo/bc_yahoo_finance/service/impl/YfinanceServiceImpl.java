package com.bootcamp.demo.bc_yahoo_finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo.bc_yahoo_finance.model.dto.QuoteResponseDTO;
import com.bootcamp.demo.bc_yahoo_finance.model.dto.SymbolDTO;
import com.bootcamp.demo.bc_yahoo_finance.service.YfinanceService;
import com.bootcamp.demo.bc_yahoo_finance.util.Scheme;
import com.bootcamp.demo.bc_yahoo_finance.util.Url;

@Service
public class YfinanceServiceImpl implements YfinanceService {

    @Autowired
    @Qualifier(value = "YfinanceRestTemplate")
    private RestTemplate restTemplate;

    @Value("${api.yf.domain}")
    private String yfDomain;
  
    @Value("${api.yf.endpoints}")
    private String endpoint;

    @Override
    public SymbolDTO getSymbol() {
        String url = Url.builder()
                .scheme(Scheme.HTTPS)
                .domain(this.yfDomain)
                .endpoint(this.endpoint)
                .build()
                .toUriString();

        QuoteResponseDTO response = this.restTemplate.getForObject(url, QuoteResponseDTO.class);
        if (response != null && response.getResult() != null) {
            return response.getResult().get(0);
        }
        return null;
    }
}
