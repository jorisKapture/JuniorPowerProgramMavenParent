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
    @RequestMapping(value = "/tag", method = RequestMethod.GET)
    public List<Tag> listAlltags(){
        return tagRepository.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/tag/{id}", method = RequestMethod.GET)
    public Tag findTagById(@PathVariable Long id){
        return tagRepository.findOne(id);
    }

    @ResponseBody
    @RequestMapping(value = "/tag/create", method = RequestMethod.POST)
    public Tag createTag(HttpServletRequest httpServletRequest){
        Tag tag = new Tag();
        tag.setName(httpServletRequest.getParameter("name"));
        System.out.println("******************");
        System.out.println(httpServletRequest.getParameter("name"));
        System.out.println("******************");
        System.out.println(tag);
        System.out.println(tag.getName());
        System.out.println(tag.getId());
        System.out.println("******************");
        return tagRepository.save(tag);
    }

    /*
    @RequestMapping(value = "add/{id}", method = RequestMethod.POST)
public String addPerson(@RequestParam("name") String name, @PathVariable("id") String id)
     */


}
