package com.berraz.crm.core.lead.repository;

import com.berraz.crm.core.lead.model.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Lead, Long> {
    
}
