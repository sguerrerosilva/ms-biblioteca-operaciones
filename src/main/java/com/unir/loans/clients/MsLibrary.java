package com.unir.loans.clients;

import com.unir.loans.model.BookDto;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(url = "${spring.MsLibrary.url}" ,name = "${spring.MsLibrary.name}")
public interface MsLibrary {

    @GetMapping("/books/{idBook}")
    public BookDto getBook(@PathVariable Long idBook);

    @PatchMapping("/books/{idBook}")
    public BookDto patchBook(@PathVariable Long idBook, @RequestBody BookDto book);
}
