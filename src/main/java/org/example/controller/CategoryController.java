package org.example.controller;

import org.example.dto.ProductDTO;
import org.example.dto.filter.ProductFilterDTO;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{name}")
    public ResponseEntity<ProductDTO> getCategoryByName(@PathVariable String name) {
        ProductDTO name1 = categoryService.getName(name);
        return ResponseEntity.ok().body(name1);
    }

    @PostMapping("/filter")
    public ResponseEntity<PageImpl<ProductDTO>> pageableFilter(@RequestParam(value = "page", defaultValue = "1") int page,
                                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                                               @RequestBody ProductFilterDTO filter) {
        PageImpl<ProductDTO> studentDTOList = categoryService.filter(filter, page - 1, size);
        return ResponseEntity.ok().body(studentDTOList);
    }
}
