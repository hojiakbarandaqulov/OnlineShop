package org.example.controller;

import org.example.dto.CategoryDTO;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/adm/v1/create")
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO response = categoryService.create(categoryDTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/adm/v2/categoryAll")
    public ResponseEntity<List<CategoryDTO>> getAllProducts() {
        List<CategoryDTO> all = categoryService.getAll();
        return ResponseEntity.ok().body(all);
    }
    @PutMapping("/adm/v3/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
        categoryService.update(id, categoryDTO);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/adm/v4/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        categoryService.deleteId(id);
        return ResponseEntity.ok().body(true);
    }
}
