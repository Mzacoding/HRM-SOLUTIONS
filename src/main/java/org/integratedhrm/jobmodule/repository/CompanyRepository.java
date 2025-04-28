package org.integratedhrm.jobmodule.repository;

import org.integratedhrm.jobmodule.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {


}
