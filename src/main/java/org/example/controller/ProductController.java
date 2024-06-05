package org.example.controller;

import org.example.dto.ProductDTO;
import org.example.dto.filter.ProductFilterDTO;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/adm/v1/create")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
        ProductDTO response = productService.create(productDTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/adm/v2/productAll")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> all = productService.getAll();
        return ResponseEntity.ok().body(all);
    }

    @PutMapping("/adm/v3/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        productService.update(id, productDTO);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/adm/v4/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        productService.deleteId(id);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProductDTO> getCategoryByName(@PathVariable String name) {
        ProductDTO name1 = productService.getName(name);
        return ResponseEntity.ok().body(name1);
    }

    @GetMapping("/pagination")
    public ResponseEntity<PageImpl<ProductDTO>> pagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                           @RequestParam(value = "size", defaultValue = "10") Integer size) {
        PageImpl<ProductDTO> pagination = productService.getPagination(page, size);
        return ResponseEntity.ok().body(pagination);
    }

    @PostMapping("/filter")
    public ResponseEntity<PageImpl<ProductDTO>> pageableFilter(@RequestParam(value = "page", defaultValue = "1") int page,
                                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                                               @RequestBody ProductFilterDTO filter) {
        PageImpl<ProductDTO> studentDTOList = productService.filter(filter, page - 1, size);
        return ResponseEntity.ok().body(studentDTOList);
    }
}
