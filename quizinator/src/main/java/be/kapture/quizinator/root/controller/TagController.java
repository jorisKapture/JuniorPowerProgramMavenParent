package be.kapture.quizinator.root.controller;

import be.kapture.quizinator.root.dto.QuestionDTO;
import be.kapture.quizinator.root.mapper.DozerBeanMultimapper;
import be.kapture.quizinator.root.model.Question;
import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.repository.QuestionRepository;
import be.kapture.quizinator.root.repository.TagRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping()
public class TagController {
    @Autowired
    private TagRepository tagRepository;

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/tag", method = RequestMethod.GET)
    public List<Tag> listAllTags(){
        return tagRepository.findAll();
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/tag/{id}", method = RequestMethod.GET)
    public Tag findTagById(@PathVariable Long id){
        return tagRepository.findOne(id);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/tag/{id}", method = RequestMethod.DELETE)
    public void deleteTagById(@PathVariable Long id){
        tagRepository.delete(id);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/tag/create", method = RequestMethod.POST)
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag){
        return new ResponseEntity<Tag>(tagRepository.save(tag), HttpStatus.OK);
    }
}
