package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Sort;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping(path = "")
    public List<Product> index(
		@RequestParam(value = "min", required = false) Integer minPrice,
        @RequestParam(value = "max", required = false) Integer maxPrice) {
        if (minPrice == null && maxPrice == null) {
            return productRepository.findAll();
        } else if (minPrice != null && maxPrice != null) {
            return productRepository.findAllByPriceBetweenOrderByPriceAsc(minPrice, maxPrice);
        } else if (minPrice != null) {
            return productRepository.findAllByPriceGreaterThanEqualOrderByPriceAsc(minPrice);
        } else {
            return productRepository.findAllByPriceLessThanEqualOrderByPriceAsc(maxPrice);
        }
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        var product =  productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }
}
