package com.unir.loans.service;


import com.unir.loans.data.LoanRepository;
import com.unir.loans.model.pojo.Loan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LoanServiceImpl {

    @Autowired
    private LoanRepository repository;

    public List<Loan> getLoans(){
        return repository.getLoans();
    }


}
