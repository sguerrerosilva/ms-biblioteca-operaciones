package com.unir.products.controller;

import com.unir.products.model.pojo.Book;
import com.unir.products.service.ProductsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Products Controller", description = "Microservicio encargado de exponer operaciones CRUD sobre productos alojados en la base de datos en memoria de la biblioteca.")
public class ProductsController {


    private final ProductsServiceImpl service;

    @GetMapping("/books")
    @Operation(
            operationId = "Obtener libros",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de todos los libros almacenados en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el producto con el identificador indicado.")
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Error interno del servidor interno.")
    public ResponseEntity<List<Book>> getProducts(
            @RequestHeader Map<String, String> headers,
            @Parameter(name = "author", description = "Nombre del autor del libro. No tiene por que ser exacto", example = "Gabriel", required = false)
            @RequestParam(required = false) String author,
            @Parameter(name = "title", description = "Nombre del libro. No tiene por que ser exacto", example = "Cien años de soledad", required = false)
            @RequestParam(required = false) String title,
            @Parameter(name = "isbn", description = "Código isbn del del libro. Debe ser exacto", example = "ISBN 0-7645-2641-3", required = false)
            @RequestParam(required = false) String isbn,
            @Parameter(name = "age", description = "Año de publicación del libro. Debe ser exacto", example = "2023", required = false)
            @RequestParam(required = false) Short age,
            @Parameter(name = "synapsis", description = "Sinopsis del libro. No tiene que ser exacto, por ejemplo una palabra clave", example = "Soledad", required = false)
            @RequestParam(required = false) String synapsis,
            @Parameter(name = "stock", description = "Stock del libro. Debe ser exacto", example = "5", required = false)
            @RequestParam(required = false) Short stock) {

        log.info("Headers: {}", headers);
        List<Book> books = service.getProducts(author,title,isbn,age,synapsis,stock);
        if(books != null){
            return ResponseEntity.ok(books);
        }else{
            return ResponseEntity.ok(Collections.emptyList());
        }
    }
}
