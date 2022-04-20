package com.example.springsecurityexam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springsecurityexam.dto.ApiResponse;
import com.example.springsecurityexam.dto.ProductDTO;
import com.example.springsecurityexam.entity.Product;
import com.example.springsecurityexam.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;


    public ApiResponse getAll() {
        List<Product> productList = productRepository.findAll();
        if (productList.isEmpty()) {
            return new ApiResponse("Productlar listi bo'sh",false);
        }
        return new ApiResponse("Productlar Listi",true,productList);
    }


    public ApiResponse getOneId(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) {
            return new ApiResponse("Bunday id li product mavjud emas",false);
        }
        return new ApiResponse("Mana",true,productOptional.get());
    }

    public ApiResponse add(ProductDTO productDto) {
        if (productRepository.existsByName(productDto.getName())){
            return new ApiResponse("Bunday nomli product mavjud",false);
        }

        Product product=new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        Product save = productRepository.save(product);
        return new ApiResponse("Product Qo'shildi",true,save);
    }

    public ApiResponse edited(Integer id, ProductDTO productDto) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) {
            return new ApiResponse("Bunday id li product mavjud emas",false);
        }
        Product product = productOptional.get();
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        Product save = productRepository.save(product);

        return new ApiResponse("Product O'zgartirildi",true,save);
    }

    public ApiResponse delete(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) {
            return new ApiResponse("Bunday id li product mavjud emas",false);
        }

        productRepository.deleteById(id);
        return new ApiResponse("O'chirildi",true);
    }
}
