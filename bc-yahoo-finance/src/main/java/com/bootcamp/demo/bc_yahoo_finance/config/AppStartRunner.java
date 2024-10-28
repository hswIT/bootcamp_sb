package com.bootcamp.demo.bc_yahoo_finance.config;


import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.bootcamp.demo.bc_yahoo_finance.Infra.CookieManager;
import com.bootcamp.demo.bc_yahoo_finance.Infra.CrumbManager;
import com.bootcamp.demo.bc_yahoo_finance.Infra.FetchManager;
import com.bootcamp.demo.bc_yahoo_finance.repository.QuoteRepository;

@Component
public class AppStartRunner implements CommandLineRunner {

    @Autowired
    private CrumbManager crumbManager;

    @Autowired
    private CookieManager cookieManager;

    @Autowired
    private FetchManager fetchManager;

    @Autowired
    private QuoteRepository quoteRepository;

    @Override
    public void run(String... args) {

    try {

        String quotes = fetchManager.fetchQuotes(List.of("0005.HK", "0700.HK", "0388.HK"));
        System.out.println(quotes);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace(); // Log the error
            System.out.println("Error: Unable to fetch quotes due to an issue with network or server.");
        }
        



        
        // QuoteEntity quote1 = QuoteEntity.builder()
        //         .symbol("0005.HK")
        //         .build();

        // QuoteEntity quote2 = QuoteEntity.builder()
        //         .symbol("0700.HK")
        //         .build();

        // quoteRepository.save(quote1);
        // quoteRepository.save(quote2);
        // quoteRepository.findAll().forEach(quote -> System.out.println("Symbol Added: " + quote.getSymbol()));
    }
}
