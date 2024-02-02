package com.unir.loans.service;


import com.google.gson.Gson;
import com.unir.loans.clients.MsLibrary;
import com.unir.loans.data.LoanRepository;
import com.unir.loans.fecade.LoansFecade;
import com.unir.loans.model.BookDto;
import com.unir.loans.model.pojo.Loan;
import com.unir.loans.model.request.CreateLoanRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class LoanServiceImpl {

    @Autowired
    private LoanRepository repository;

    @Autowired
    private LoansFecade loansFecade;

    @Autowired
    private MsLibrary  msLibrary;

    public List<Loan> getLoans(Integer idBook, String idClient, String status){

        if( idBook != null || StringUtils.hasLength(idClient) ||  StringUtils.hasLength(status)){
            return repository.search(idBook,idClient,status);
        }
        return repository.getLoans();
    }

    public Loan addLoan(CreateLoanRequest request){
        BookDto book = new BookDto();
        try{

            book = loansFecade.getBook(request.getIdBook());
            //book = msLibrary.getBook(request.getIdBook());
        }catch (Exception e){
            log.info(e.getMessage());
        }
        if(book.getStock() > 0){
            Loan loan = Loan.builder().idBook(request.getIdBook()).idClient(request.getIdClient()).
                    startDate(Date.valueOf(LocalDate.now())).dueDate(Date.valueOf(LocalDate.now().plusDays(5))).status("borrowed").build();
            repository.save(loan);
            book.setStock((short) (book.getStock()-1));
            String body = new Gson().toJson(book);
            log.info(body);
            loansFecade.patchBook(request.getIdBook(), body);
            BookDto patchBook = msLibrary.patchBook(request.getIdBook(), book);
            return loan;
        }
        return null;
    }


}
