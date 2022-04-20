package com.example.springsecurityexam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.springsecurityexam.dto.ApiResponse;
import com.example.springsecurityexam.dto.ProductDTO;
import com.example.springsecurityexam.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    ProductService productService;


    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @GetMapping
    public HttpEntity<?> getAll(){
       ApiResponse getAll= productService.getAll();
       return ResponseEntity.status(getAll.isSuccess()? HttpStatus.OK:HttpStatus.NO_CONTENT).body(getAll);
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','ADMIN1')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOneId(@PathVariable Integer id){
        ApiResponse getOneId=productService.getOneId(id);
        return  ResponseEntity.status(getOneId.isSuccess()?HttpStatus.OK:HttpStatus.NO_CONTENT).body(getOneId);
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','ADMIN1')")
    @PostMapping
    public HttpEntity<?> add(@RequestBody ProductDTO productDto){
        ApiResponse add=productService.add(productDto);
        return ResponseEntity.status(add.isSuccess()?HttpStatus.OK:HttpStatus.NO_CONTENT).body(add);
    }


    @PreAuthorize(value = "hasAnyAuthority('ADMIN','ADMIN1')")
    @PutMapping("/{id}")
    public  HttpEntity<?> edit(@PathVariable Integer id , @RequestBody ProductDTO productDto){
        ApiResponse edit=productService.edited(id,productDto);
        return ResponseEntity.status(edit.isSuccess()?HttpStatus.OK:HttpStatus.NO_CONTENT).body(edit);
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse delete=productService.delete(id);
        return ResponseEntity.status(delete.isSuccess()?HttpStatus.OK:HttpStatus.NO_CONTENT).body(delete);
    }
}
