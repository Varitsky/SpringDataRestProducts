package ru.geekbrains.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.university.model.Product;
import ru.geekbrains.university.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**     товары дороже min цены,                              +
            товары дешевле max цены,                             +
            товары, цена которых находится в пределах min-max).  +
     */


    List<Product> findAllByPriceIsGreaterThan(int price);

    List<Product> findAllByPriceIsLessThan(int price);

    List<Product> findAllByPriceBetween(int min, int max);


}
