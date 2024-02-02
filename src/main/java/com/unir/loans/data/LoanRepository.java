package com.unir.loans.data;

import com.unir.loans.model.pojo.Loan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LoanRepository {

    private final LoanJpaRepository reposository;

    public List<Loan> getLoans(){ return reposository.findAll(); }

}
