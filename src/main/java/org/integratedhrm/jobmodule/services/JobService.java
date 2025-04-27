package org.integratedhrm.jobmodule.services;

import org.integratedhrm.jobmodule.entity.Job;

import java.util.List;

public interface JobService {

    List<Job> findAll();
    Job findJobById(Long id);
    void createJob(Job job);
    boolean  deleleJobById(Long id);
    boolean updateJob(Long id, Job newJob);
}
