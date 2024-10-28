package com.bootcamp.demo.bc_yahoo_finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo.bc_yahoo_finance.entity.QuoteEntity;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {
  
}
