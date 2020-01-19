package com.project.demo.controller;

import com.project.demo.dto.SkillDTO;
import com.project.demo.model.Candidate;
import com.project.demo.model.Skill;
import com.project.demo.repository.CandidateRepository;
import com.project.demo.repository.SkillRepository;
import com.project.demo.service.implementation.CandidateServiceImpl;
import com.project.demo.service.implementation.SkillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@Controller
@RequestMapping(value = "/api/skills", produces = MediaType.APPLICATION_JSON_VALUE)
public class SkillController {
    @Autowired
    private SkillServiceImpl skillService;

    @Autowired
    private CandidateServiceImpl candidateService;

    @RequestMapping(value = "/addNewSkill", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newCandidate(@RequestBody SkillDTO skillDTO) {
        Skill s = new Skill();
        s.setName(skillDTO.getName());
        Skill exist = this.skillService.findByName(skillDTO.getName());
        if (exist != null) {
            return new ResponseEntity<Skill>(s, HttpStatus.CONFLICT);
        }else{
            this.skillService.save(s);
        }
        return new ResponseEntity<Skill>(s, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getAllSkills", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Skill> getAllSkills() {
        return   this.skillService.findAllSkills();
    }

    @RequestMapping(value = "/getLeftSkills/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Skill> getLeftSkills(@PathVariable("id") Long id) {
        Candidate c =  this.candidateService.findById(id);
        List<Skill> candidateSkills = c.getSkills();
        List<Skill> allSkills = this.skillService.findAllSkills();
        List<Skill> leftSkills = new ArrayList<>();
        for(Skill skill : allSkills){
            boolean add = true;
            for(Skill candiSkill : candidateSkills){
                if(skill.getName().equals(candiSkill.getName())){
                    add = false;
                }
            }
            if(add){
                leftSkills.add(skill);
            }
        }
        return  leftSkills;
    }


}
