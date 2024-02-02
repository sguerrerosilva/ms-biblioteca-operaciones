package com.unir.loans.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateLoanRequest {

    private Integer idBook;
    private String idClient;
    private Date startDate;
    private Date dueDate;
    private String status;

}
