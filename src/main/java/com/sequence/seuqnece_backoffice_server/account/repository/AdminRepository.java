package com.sequence.seuqnece_backoffice_server.account.repository;

import com.sequence.seuqnece_backoffice_server.account.entity.Admin;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByUsername(String username);

}
