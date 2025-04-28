package org.integratedhrm.jobmodule.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.integratedhrm.jobmodule.entity.*;
import org.integratedhrm.jobmodule.exception.JobNotFoundException;
import org.integratedhrm.jobmodule.repository.*;
import  org.integratedhrm.jobmodule.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
@Transactional
public class JobServiceManager implements JobService {

    @Autowired
    JobRepository jobRepository;
    @Autowired
    SkillRepository skillRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    JobSkillRepository jobSkillRepository;
    @Autowired
     CompanyRepository companyRepository;
    @Override

    public List<Job> findAll() {

        return jobRepository.findAll();

    }
    @Override
    public Job findJobById(Long id) {

        return jobRepository.findById(id).orElseThrow(()->
        new JobNotFoundException ("Job not found with the Job Id " + id));


    }


    @Override
    @Transactional
    public void createJob(Job job) {
        // Handle Location
        Location location = job.getLocation();
        if (location != null) {
            if (location.getId() == null) {
                location = locationRepository.save(location);
            } else {
                location = locationRepository.findById(location.getId())
                        .orElseThrow(() -> new RuntimeException("Location not found for ID: " ));
            }
            job.setLocation(location);
        }

        // Set the job in each job skill
        List<JobSkill> jobSkills = job.getJobSkills();
        if (jobSkills != null && !jobSkills.isEmpty()) {
            for (JobSkill jobSkill : jobSkills) {
                jobSkill.setJob(job);
                // Handle Skill for JobSkill
                Skill skill = jobSkill.getSkill();

                if (skill == null) {
                    throw new RuntimeException("Skill is required for JobSkill");
                }

                if (skill.getId() == null) {
                    skill = skillRepository.save(skill);
                } else {
                    skill = skillRepository.findById(skill.getId())
                            .orElseThrow(() -> new RuntimeException("Skill not found for ID: "  ));
                }
                jobSkill.setSkill(skill);
            }
        }


        Company company=job.getCompany();

        if(company!=null){
            if(company.getId()==null){

                companyRepository.save(company);
            }else{
                company=  companyRepository.findById(company.getId()).orElseThrow( ()  ->
                        new RuntimeException("No Company Found")
                );
                company=  companyRepository.save(company);
            }
            job.setCompany(company);
        }

        jobRepository.save(job);
    }

    @Override
    public boolean  deleleJobById(Long id) {

       if((jobRepository.findById(id)).isPresent()) {

           jobRepository.deleteById(id);

           return true;

       }

        return false;

    }

     @Override
    public boolean  updateJob(Long id, Job newJob) {

       Optional<Job> job = jobRepository.findById(id);

         if(job.isPresent()) {
             Job oldJob = job.get();

             oldJob.setLocation(newJob.getLocation());
             oldJob.setDescription(newJob.getDescription());
             oldJob.setSalaryRangeMax(newJob.getSalaryRangeMax());
             oldJob.setSalaryRangeMin(newJob.getSalaryRangeMin());
             oldJob.setStatus(newJob.getStatus());
             oldJob.setTitle(newJob.getTitle());
             oldJob.setDepartment(newJob.getDepartment());
             jobRepository.save(oldJob);
             return true;
         }
        return false;
    }

}
