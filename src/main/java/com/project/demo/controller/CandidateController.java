package com.project.demo.controller;

import com.project.demo.dto.CandidateDTO;
import com.project.demo.dto.SkillDTO;
import com.project.demo.model.Candidate;
import com.project.demo.model.Skill;
import com.project.demo.repository.CandidateRepository;
import com.project.demo.repository.SkillRepository;
import com.project.demo.service.SkillService;
import com.project.demo.service.implementation.CandidateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@RestController
@Controller
@RequestMapping(value = "/api/candidates", produces = MediaType.APPLICATION_JSON_VALUE)
public class CandidateController {

    @Autowired
    private CandidateServiceImpl candidateService;
    @Autowired
    private SkillService skillService;

    @RequestMapping(value = "/allSorted", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Candidate> getAllCandidates() {

        List<Candidate> sortedCandidates = this.candidateService.findAllCandidates();
        sortedCandidates.sort(Comparator.comparing(Candidate::getName));
        return sortedCandidates;
    }
    @RequestMapping(value = "/addNewCandidate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newCandidate(@RequestBody CandidateDTO candidateDTO) {
        Candidate c = new Candidate();

        c.setName(candidateDTO.getName());
        c.setEmail(candidateDTO.getEmail());
        c.setDateOfBirth(candidateDTO.getDateOfBirth());
        c.setSurname(candidateDTO.getSurname());
        c.setPhoneNumber(candidateDTO.getPhoneNumber());

        Candidate exist = this.candidateService.findByEmail(candidateDTO.getEmail());
        if (exist != null) {

            return new ResponseEntity<Candidate>(c, HttpStatus.CONFLICT);
        }
        this.candidateService.save(c);
        return new ResponseEntity<Candidate>(c, HttpStatus.OK);
    }
    @RequestMapping(value = "/getCandidateSkills", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Skill> getCandidateSkills(Long id) {

        Candidate c =  this.candidateService.findById(id);
        if(c != null){
            return c.getSkills();
        }
        return null;
    }
    @RequestMapping(value = "/addSkillToCandidate/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addSkillToCandidate(@PathVariable("id") Long candidateId, @RequestBody SkillDTO skillDTO) {

        Skill skill = this.skillService.findByName(skillDTO.getName());
        Candidate c =  this.candidateService.findById(candidateId);
        if(skill != null && c != null){
            c.getSkills().add(skill);
            this.candidateService.save(c);
            return new ResponseEntity<Candidate>(c, HttpStatus.OK);
        }

        return new ResponseEntity<>(c, HttpStatus.NO_CONTENT);
    }
    @RequestMapping(value = "/deleteCandidate/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCandidate(@PathVariable("id") Long candidateId) {
        Candidate c =  this.candidateService.findById(candidateId);

        this.candidateService.delete(c);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
    @RequestMapping(value = "/removeSkillFromCandidate/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeSkillFromCandidate(@PathVariable("id") Long candidateId, @RequestBody SkillDTO skillDTO) {

        Skill skill = this.skillService.findByName(skillDTO.getName());
        Candidate c =  this.candidateService.findById(candidateId);
        if(skill != null && c != null){
            c.getSkills().remove(skill);
            this.candidateService.save(c);
            return new ResponseEntity<Candidate>(c, HttpStatus.OK);
        }
        return new ResponseEntity<>(c, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/findCandidatesByName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>  findCandidatesByName(@RequestBody CandidateDTO candidateDTO) {

        return new ResponseEntity<List<Candidate>>(this.candidateService.findCandidatesByName(candidateDTO.getName()), HttpStatus.OK);

    }
    @RequestMapping(value = "/findCandidatesBySkills", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>  findCandidatesBySkills(@RequestBody SkillDTO skillDTO) {

       String name = skillDTO.getName();
        return new ResponseEntity<List<Candidate>>(this.candidateService.findCandidatesBySkills(name), HttpStatus.OK);
    }

}
