package com.sequence.seuqnece_backoffice_server.system_link.repository;

import com.sequence.seuqnece_backoffice_server.system_link.entity.SystemLink;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemLinkJpaRepository extends JpaRepository<SystemLink, Long> {

    List<SystemLink> findAllByIsDeletedFalse();
}