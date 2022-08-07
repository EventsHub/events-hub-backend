package com.saxakiil.eventshubbackend.repository;

import com.saxakiil.eventshubbackend.model.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findById(final Long id);

    Page<Card> findByPublished(final boolean published, final Pageable pageable);

    Page<Card> findByTitleContaining(final String title, final Pageable pageable);
}
