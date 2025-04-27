package org.integratedhrm.jobmodule.repository;

import org.integratedhrm.jobmodule.entity.Job;
import org.integratedhrm.jobmodule.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {
}
