package be.kapture.quizinator.root.controller;

import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET)
    public List<Tag> listAllTags(){
        return tagService.findAll();
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Tag findTagById(@PathVariable Long id){
        return tagService.findOne(id);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTagById(@PathVariable Long id){
        tagService.delete(id);
    }


    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/findcreate", method = RequestMethod.POST)
    public ResponseEntity<List<Tag>> findCreateTags(@RequestBody String tagsString){
        return new ResponseEntity<List<Tag>>(tagService.findListCreateIfNeeded(tagsString), HttpStatus.OK);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag){
        Tag savedTag;
        try{
            savedTag = tagService.save(tag);
        } catch (Exception e){
            savedTag = tagService.findByName(tag.getName());
            return new ResponseEntity<>(savedTag, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(savedTag, HttpStatus.OK);
    }
}
