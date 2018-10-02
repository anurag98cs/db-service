package com.dbservice.stock.dbservice.repository;

import com.dbservice.stock.dbservice.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Size;
import java.util.List;

public interface QuotesRepository extends JpaRepository<Quote, Integer> {
    List<Quote> findByUserName(String username);

    //List<Quote> delete(List<Quote> quotes);
}
