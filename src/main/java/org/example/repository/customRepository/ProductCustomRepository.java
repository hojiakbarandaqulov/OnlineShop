package org.example.repository.customRepository;

import org.example.dto.filter.ProductFilterDTO;
import org.example.dto.response.FilterResponseDTO;
import org.example.entity.ProductEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductCustomRepository {
    @Autowired
    private EntityManager entityManager;

    public FilterResponseDTO<ProductEntity> filter(ProductFilterDTO filter, int page, int size) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder query = new StringBuilder();
        if (filter.getName() != null) {
            query.append(" and s.name=:name ");
            params.put("name", filter.getName());
        }
        if (filter.getDescription() != null) {
            query.append(" and s.description=:description ");
            params.put("description", filter.getDescription());
        }
        if (filter.getCondition() != null) {
            query.append(" and s.condition=:condition ");
            params.put("condition", filter.getCondition());
        }
       /* if (filter.getEmail() != null) {

            query.append(" and s.email=:email ");
            params.put("email", filter.getEmail());
        }*/
        if (filter.getDiscount() != null) {
            query.append(" and s.discount=:discount ");
            params.put("discount", filter.getDiscount());
        }
        if (filter.getStatistics() != null) {
            query.append(" and s.statistics=:statistics ");
            params.put("statistics", filter.getStatistics());
        }
        if (filter.getPriceFrom() != null) {
            query.append(" and s.price between :priceFrom and :priceTo");
            params.put("priceFrom", filter.getPriceFrom());
            params.put("priceTo", filter.getPriceTo());
        }


        String countSql = "select count(s) From ProductEntity s where s.visible = true " + query;

        // select
        Query selectQuery = entityManager.createQuery("From ProductEntity s where s.visible = true " + query
                // select
        );
        Query countQuery = entityManager.createQuery(countSql);

        for (Map.Entry<String, Object> entity : params.entrySet()) {
            selectQuery.setParameter(entity.getKey(), entity.getValue());
            countQuery.setParameter(entity.getKey(), entity.getValue());
        }
        selectQuery.setFirstResult(page * size); // offset
        selectQuery.setMaxResults(size); // limit
        List<ProductEntity> studentEntityList = selectQuery.getResultList();
        // count
        Long totalCount = (Long) countQuery.getSingleResult();

        return new FilterResponseDTO<ProductEntity>(studentEntityList, totalCount);
    }
}
