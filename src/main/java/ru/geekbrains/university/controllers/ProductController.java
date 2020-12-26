package ru.geekbrains.university.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.university.model.Product;
import ru.geekbrains.university.model.Student;
import ru.geekbrains.university.repositories.ProductRepository;
import ru.geekbrains.university.repositories.StudentRepository;

import java.util.List;

/**
        получение товара по id [ GET .../app/products/{id} ]            +
        получение всех товаров [ GET .../app/products ]                 +
        создание нового товара [ POST .../app/products ]                +
        удаление товара по id.[ GET .../app/products/delete/{id} ]      +
 */

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).get();
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Postman [POST] - http://localhost:8189/app/products - raw - [JSON]
    //  {
    //    "title": "Harpalyke",
    //    "price": 1
    //  }
    @PostMapping
    public Product save(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productRepository.deleteById(id);
    }


    //Как убрать ResponseBody чтобы получить редиррект используя RestController?
//    @GetMapping("/delete/{id}")
//    public String deleteProductById(@PathVariable Long id) {
//        productRepository.deleteById(id);
//        return "redirect:/products";
//    }


    /**
     * * К запросу всех товаров добавьте возможность фильтрации по минимальной и максимальной цене
     * (в трех вариантах:
     *
     * товары дороже min цены,                              +
     * товары дешевле max цены,                             +
     * товары, цена которых находится в пределах min-max).  +
     */

    //http://localhost:8189/app/products/search_above_min_price?min_price=10
    @GetMapping("/search_above_min_price")
    public List<Product> searchAboveMinPrice(@RequestParam(name = "min_price") Integer minPrice){
        return productRepository.findAllByPriceIsGreaterThan(minPrice);
    }

    //http://localhost:8189/app/products/search_below_max_price?max_price=10
    @GetMapping("/search_below_max_price")
    public List<Product> searchBelowMaxPrice(@RequestParam(name = "max_price") Integer maxPrice){
        return productRepository.findAllByPriceIsLessThan(maxPrice);
    }

    //http://localhost:8189/app/products/search_between?min_price=0&max_price=20
    @GetMapping("/search_between")
    public List<Product> searchAboveMinPrice(@RequestParam(name = "min_price") Integer minPrice, @RequestParam(name = "max_price") Integer maxPrice) {
        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }
}
