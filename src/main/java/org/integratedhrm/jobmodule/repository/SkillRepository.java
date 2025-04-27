package org.integratedhrm.jobmodule.repository;

import org.integratedhrm.jobmodule.entity.Job;
import org.integratedhrm.jobmodule.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill,Long> {
    Skill findBySkillName(String skillName);

}
