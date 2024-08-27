package com.SpringProject.myJobApp.Job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/jobs")

public class JobController {
    public JobController(JobService jobService) {

        this.jobService = jobService;
    }
    private JobService jobService;



    //    This is a GET Request Type handler method which is calling jobService's find-all method.
    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
        return  ResponseEntity.ok(jobService.findAll());
    }


    //    This is a POST Request Type handler method which is calling jobService's create-job method and also passing a
    //    request body 'job' which is 'Job' type
    @PostMapping
    public ResponseEntity<String> createJobs(@RequestBody Job job){
        jobService.createJob(job);
//        Company c = job.getComapany();
        return new ResponseEntity<>("job added Successfully",HttpStatus.CREATED);
    }

    //    This is a GET Request Type handler method which is calling jobService's getJobById method  and also passing a
    //    Path variable 'id'
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job = jobService.getJobById(id);
        if (job != null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean delete = jobService.deleteJobById(id);
      if (delete){
          return new ResponseEntity<>("job deleted successfully", HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated = jobService.updateJob(id, updatedJob);
        if (updated){
            return new ResponseEntity<>("Job has been updated", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
