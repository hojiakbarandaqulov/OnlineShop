package org.example.repository;

import org.example.entity.EmailHistoryEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailHistoryRepository extends JpaRepository<EmailHistoryEntity, Integer> {

    Long countByEmailAndCreatedDateBetween(String email, LocalDateTime from, LocalDateTime to);

    Optional<EmailHistoryEntity> findByEmail(String email);

    Optional<EmailHistoryEntity> findByCreatedDate(LocalDateTime createdDate);

    @NotNull
    Page<EmailHistoryEntity> findAll(@NotNull Pageable pageable);

    Optional<EmailHistoryEntity> findByEmailAndVisibleTrue(String email);

}
