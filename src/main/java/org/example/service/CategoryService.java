package org.example.service;

import org.example.dto.ProductDTO;
import org.example.dto.filter.ProductFilterDTO;
import org.example.dto.response.FilterResponseDTO;
import org.example.entity.ProductEntity;
import org.example.exp.AppBadException;
import org.example.repository.CategoryRepository;
import org.example.repository.ProductRepository;
//import org.example.repository.customRepository.ProductCustomRepository;
import org.example.repository.customRepository.ProductCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCustomRepository productCustomRepository;
    public ProductDTO getName(String name) {
        List<ProductEntity> byName = productRepository.findByName(name);
        if (byName.isEmpty()) {
            throw new AppBadException("product not found");
        }
        ProductEntity productEntity = byName.get(0);
        return productToDTO(productEntity);
    }

    private ProductDTO productToDTO(ProductEntity productEntity) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(productEntity.getName());
        productDTO.setDescription(productEntity.getDescription());
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setOriginalPrice(productEntity.getOriginalPrice());
        productDTO.setImageUrl(productEntity.getImageUrl());
        productDTO.setCondition(productEntity.getCondition());
        productDTO.setDiscount(productEntity.getDiscount());
        productDTO.setStatistics(productEntity.getStatistics());
        return productDTO;
    }

    public PageImpl<ProductDTO> filter(ProductFilterDTO filter, int page, int size) {
        FilterResponseDTO<ProductEntity> filterResponse = productCustomRepository.filter(filter, page, size);

        List<ProductDTO> dtoList = new LinkedList<>();
        for (ProductEntity entity : filterResponse.getContent()) {
            ProductDTO dto = new ProductDTO();
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setPrice(entity.getPrice());
            dto.setOriginalPrice(entity.getOriginalPrice());
            dto.setDiscount(entity.getDiscount());
            dto.setStatistics(entity.getStatistics());
            dtoList.add(dto);
        }
        return new PageImpl<ProductDTO>( dtoList, PageRequest.of(page,size), filterResponse.getTotalCount());
    }
}
