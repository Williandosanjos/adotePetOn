package br.com.adotepeton.adotepeton.domain.repository;

import br.com.adotepeton.adotepeton.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.spi.ToolProvider;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
