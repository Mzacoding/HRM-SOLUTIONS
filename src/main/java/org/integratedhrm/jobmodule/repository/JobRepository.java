package org.integratedhrm.jobmodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.integratedhrm.jobmodule.entity.Job;
import org.springframework.stereotype.Repository;


@Repository
public interface JobRepository  extends JpaRepository<Job ,Long> {


}
