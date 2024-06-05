package org.example.service;

import org.example.dto.ProductDTO;
import org.example.dto.filter.ProductFilterDTO;
import org.example.dto.response.FilterResponseDTO;
import org.example.entity.ProductEntity;
import org.example.exp.AppBadException;
import org.example.repository.ProductRepository;
import org.example.repository.customRepository.ProductCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    private final ProductCustomRepository productCustomRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductCustomRepository productCustomRepository) {
        this.productRepository = productRepository;
        this.productCustomRepository = productCustomRepository;
    }

    public ProductDTO create(ProductDTO dto) {
        ProductEntity productEntity = new ProductEntity();
        productEntityToDTO(dto, productEntity);
        productRepository.save(productEntity);
        return productToDTO(productEntity);
    }

    private void productEntityToDTO(ProductDTO dto, ProductEntity productEntity) {
        productEntity.setName(dto.getName());
        productEntity.setDescription(dto.getDescription());
        productEntity.setPrice(dto.getPrice());
        productEntity.setOriginalPrice(dto.getOriginalPrice());
        productEntity.setImageUrl(dto.getImageUrl());
        productEntity.setCondition(dto.getCondition());
        productEntity.setDiscount(dto.getDiscount());
        productEntity.setStatistics(dto.getStatistics());
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

    public ProductDTO update(Integer id, ProductDTO productDTO) {
        Optional<ProductEntity> byId = productRepository.findById(id);
        if (byId.isEmpty()) {
            throw new AppBadException("Product not found");
        }
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(id);
        productEntityToDTO(productDTO, productEntity);
        productRepository.save(productEntity);
        return productToDTO(productEntity);
    }

    public List<ProductDTO> getAll() {
        Iterable<ProductEntity> all = productRepository.findAll();
        List<ProductDTO> dtoList = new LinkedList<>();
        for (ProductEntity productEntity : all) {
            dtoList.add(productToDTO(productEntity));
        }
        return dtoList;
    }

    public PageImpl<ProductDTO> filter(ProductFilterDTO filter, int page, int size) {
        FilterResponseDTO<ProductEntity> filterResponse = productCustomRepository.filter(filter, page, size);

        List<ProductDTO> dtoList = new LinkedList<>();
        for (ProductEntity entity : filterResponse.getContent()) {
            dtoList.add(productToDTO(entity));
        }
        return new PageImpl<ProductDTO>(dtoList, PageRequest.of(page, size), filterResponse.getTotalCount());
    }

    public PageImpl<ProductDTO> getPagination(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Order.desc("price"));
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProductEntity> pagePbj = productRepository.findAll(pageable);
        List<ProductDTO> productList = new LinkedList<>();
        for (ProductEntity entity : pagePbj.getContent()) {
            productList.add(productToDTO(entity));
        }
        Long totalElements = pagePbj.getTotalElements();
        return new PageImpl<ProductDTO>(productList, pageable, totalElements);
    }

    public ProductDTO getName(String name) {
        List<ProductEntity> byName = productRepository.findByName(name);
        if (byName.isEmpty()) {
            throw new AppBadException("product not found");
        }
        ProductEntity productEntity = byName.get(0);
        return productToDTO(productEntity);
    }

    public void deleteId(Integer id) {
        productRepository.deleteById(id);
    }
}
