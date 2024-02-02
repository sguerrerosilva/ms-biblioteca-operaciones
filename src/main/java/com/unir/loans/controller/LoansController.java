package com.unir.loans.controller;

import com.unir.loans.model.pojo.Loan;
import com.unir.loans.service.LoanServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Loans Controller", description = "Microservicio encargado de manejar operaciones para el prestamo de libros en la biblioteca.")
public class LoansController {

    @Autowired
    private LoanServiceImpl service;

    @Operation(
            operationId = "Obtener productos",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de todos los productos almacenados en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Loan.class)))
    @GetMapping("/loans")
    public ResponseEntity<List<Loan>> getLoans() {

        List<Loan> loans = service.getLoans();
        if (loans != null) {
            return ResponseEntity.ok(loans);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
