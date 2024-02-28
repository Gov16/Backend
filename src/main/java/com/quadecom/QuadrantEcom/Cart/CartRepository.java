package com.quadecom.QuadrantEcom.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<NewCartEntity, Integer> {
}
