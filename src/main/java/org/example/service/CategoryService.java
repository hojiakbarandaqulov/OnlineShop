package org.example.service;

import org.example.dto.CategoryDTO;
import org.example.dto.ProductDTO;
import org.example.entity.CategoryEntity;
import org.example.entity.ProductEntity;
import org.example.exp.AppBadException;
import org.example.repository.CategoryRepository;
//import org.example.repository.customRepository.ProductCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO create(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setOrderNumber(categoryDTO.getOrderNumber());
        categoryEntity.setName(categoryDTO.getName());
        categoryEntity.setVisible(categoryDTO.getVisible());
        categoryRepository.save(categoryEntity);
        return categoryToDTO(categoryEntity);
    }

    public CategoryDTO categoryToDTO(CategoryEntity categoryEntity) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryEntity.getId());
        categoryDTO.setName(categoryEntity.getName());
        categoryDTO.setOrderNumber(categoryEntity.getOrderNumber());
        categoryDTO.setVisible(categoryEntity.getVisible());
        categoryDTO.setCreatedDate(categoryEntity.getCreatedDate());
        return categoryDTO;
    }

    public CategoryEntity categoryToEntity(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setOrderNumber(categoryDTO.getOrderNumber());
        categoryEntity.setName(categoryDTO.getName());
        categoryEntity.setVisible(categoryDTO.getVisible());
        categoryEntity.setCreatedDate(categoryDTO.getCreatedDate());
        return categoryEntity;
    }

    public List<CategoryDTO> getAll() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<CategoryDTO> categoryList = new LinkedList<>();
        for (CategoryEntity categoryEntity : categoryEntities) {
            categoryList.add(categoryToDTO(categoryEntity));
        }
        return categoryList;
    }

    public void update(Integer id, CategoryDTO categoryDTO) {
        Optional<CategoryEntity> byId = categoryRepository.findById(id);
        if (byId.isEmpty()) {
            throw new AppBadException("category not found");
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(id);
        categoryEntityToDto(categoryDTO, categoryEntity);
    }

    public void deleteId(Integer id) {
        categoryRepository.deleteById(id);
    }

    private void categoryEntityToDto(CategoryDTO dto, CategoryEntity categoryEntity) {
        categoryEntity.setName(dto.getName());
        categoryEntity.setOrderNumber(dto.getOrderNumber());
        categoryEntity.setVisible(dto.getVisible());
        categoryEntity.setCreatedDate(LocalDate.now());
    }
}
