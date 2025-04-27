package org.integratedhrm.jobmodule.controller;

import org.integratedhrm.jobmodule.exception.JobNotFoundException;
import org.integratedhrm.jobmodule.services.JobServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.integratedhrm.jobmodule.entity.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    @Autowired
    JobServiceManager jobServiceManager;

    @GetMapping("/jobs")
    public List<Job> findAll() {

        return jobServiceManager.findAll();
    }
     @GetMapping("/jobs/{id}")
    public ResponseEntity<?> getJob(@PathVariable  Long  id) {


         try {

             return  new ResponseEntity<>(jobServiceManager.findJobById(id), HttpStatus.OK);
         } catch (JobNotFoundException e) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
         }

    }
    @PostMapping("/jobs")
    public  ResponseEntity<String> createJob(@RequestBody Job job) {

        jobServiceManager.createJob(job);
        return ResponseEntity.status(HttpStatus.CREATED).body("Job created successfully");

    }

    @PutMapping("/jobs/{id}")
   public  ResponseEntity<String> updateJob(@PathVariable  Long id , @RequestBody Job job) {

        if (jobServiceManager.updateJob(id, job)) {
            return ResponseEntity.status(HttpStatus.OK).body("Job updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
        }

    }

     @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable  Long id) {

        if(jobServiceManager.deleleJobById(id)){
           return ResponseEntity.status(HttpStatus.OK).body("Job deleted successfully");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job with this  Id :" + id + " was not found ");
        }

    }
}
