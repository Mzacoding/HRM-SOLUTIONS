package org.integratedhrm.jobmodule.repository;

import org.integratedhrm.jobmodule.entity.Job;
import org.integratedhrm.jobmodule.entity.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSkillRepository extends JpaRepository<JobSkill,Long> {
}
