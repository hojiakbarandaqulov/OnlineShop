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
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCustomRepository productCustomRepository;

    public ProductDTO create(ProductDTO dto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(dto.getName());
        productEntity.setDescription(dto.getDescription());
        productEntity.setPrice(dto.getPrice());
        productEntity.setOriginalPrice(dto.getOriginalPrice());
        productEntity.setImageUrl(dto.getImageUrl());
        productEntity.setCondition(dto.getCondition());
        productEntity.setDiscount(dto.getDiscount());
        productEntity.setStatistics(dto.getStatistics());
        productRepository.save(productEntity);
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

    public ProductDTO update(Integer id, ProductDTO productDTO) {
        Optional<ProductEntity> byId = productRepository.findById(id);
        if (byId.isEmpty()) {
            throw new AppBadException("Product not found");
        }
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(id);
        productEntity.setName(productDTO.getName());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setOriginalPrice(productDTO.getOriginalPrice());
        productEntity.setImageUrl(productDTO.getImageUrl());
        productEntity.setCondition(productDTO.getCondition());
        productEntity.setDiscount(productDTO.getDiscount());
        productEntity.setStatistics(productDTO.getStatistics());
        productRepository.save(productEntity);
        return productToDTO(productEntity);
    }

    public List<ProductDTO> getAll() {
        Iterable<ProductEntity> all = productRepository.findAll();
        List<ProductDTO>dtoList=new LinkedList<>();
        for (ProductEntity productEntity : all) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setName(productEntity.getName());
            productDTO.setDescription(productEntity.getDescription());
            productDTO.setPrice(productEntity.getPrice());
            productDTO.setOriginalPrice(productEntity.getOriginalPrice());
            productDTO.setImageUrl(productEntity.getImageUrl());
            productDTO.setCondition(productEntity.getCondition());
            productDTO.setDiscount(productEntity.getDiscount());
            productDTO.setStatistics(productEntity.getStatistics());
            dtoList.add(productDTO);
        }
        return dtoList;
    }

    public Boolean deleteId(Integer id) {
        productRepository.deleteById(id);
        return true;
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
            dto.setImageUrl(entity.getImageUrl());
            dto.setCondition(entity.getCondition());
            dto.setDiscount(entity.getDiscount());
            dto.setStatistics(entity.getStatistics());
            dtoList.add(dto);
        }
        return new PageImpl<ProductDTO>( dtoList, PageRequest.of(page,size), filterResponse.getTotalCount());
    }

    public PageImpl<ProductDTO> getPagination(Integer page,Integer size) {
        Sort sort=Sort.by(Sort.Order.desc("price"));
        Pageable pageable=PageRequest.of(page,size,sort);
        Page<ProductEntity> pagePbj = productRepository.findAll(pageable);
        List<ProductDTO> productList=new LinkedList<>();
        for (ProductEntity entity : pagePbj.getContent()) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setName(entity.getName());
            productDTO.setDescription(entity.getDescription());
            productDTO.setPrice(entity.getPrice());
            productDTO.setOriginalPrice(entity.getOriginalPrice());
            productDTO.setImageUrl(entity.getImageUrl());
            productDTO.setCondition(entity.getCondition());
            productDTO.setDiscount(entity.getDiscount());
            productDTO.setStatistics(entity.getStatistics());
            productList.add(productDTO);
        }
        Long totalElements = pagePbj.getTotalElements();
        return new PageImpl<ProductDTO>(productList, pageable, totalElements);
    }
}
