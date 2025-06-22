package com.awbd.musicshop.repositories.security;

import com.awbd.musicshop.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Optional<Authority> findByRole(String role);
}