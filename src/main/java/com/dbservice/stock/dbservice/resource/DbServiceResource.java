package com.dbservice.stock.dbservice.resource;

import com.dbservice.stock.dbservice.model.Quote;
import com.dbservice.stock.dbservice.model.Quotes;
import com.dbservice.stock.dbservice.repository.QuotesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

    private QuotesRepository quotesRepository;

    public DbServiceResource(QuotesRepository quotesRepository) {
        this.quotesRepository = quotesRepository;
    }

    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") final String username){
        return getQuotesByUserName(username);
    }

    private List<String> getQuotesByUserName(@PathVariable("username") final String username){
        return quotesRepository.findByUserName(username)
        .stream()
        .map(Quote::getQuote)
        .collect(Collectors.toList());
        //return null;
    }

    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes){

        quotes.getQuotes()
                .stream()
                .map(quote -> new Quote(quotes.getUserName(), quote ))
                .forEach(quote -> quotesRepository.save(quote));
        return getQuotesByUserName(quotes.getUserName());
    }

    @PostMapping("/delete/{username}")
    public List<String> delete(@PathVariable("username") final String username){

        List<Quote> quotes  = quotesRepository.findByUserName(username);
        quotesRepository.delete((Quote) quotes);

        return getQuotesByUserName(username);
    }
}
