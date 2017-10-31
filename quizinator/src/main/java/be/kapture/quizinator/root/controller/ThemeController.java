package be.kapture.quizinator.root.controller;

import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.model.Theme;
import be.kapture.quizinator.root.repository.TagRepository;
import be.kapture.quizinator.root.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping()
public class ThemeController {
    @Autowired
    private ThemeRepository themeRepository;

    @ResponseBody
    @RequestMapping(value = "/theme", method = RequestMethod.GET)
    public List<Theme> listAllThemes(){
        return themeRepository.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/theme/{id}", method = RequestMethod.GET)
    public Theme findThemeById(@PathVariable Long id){
        return themeRepository.findOne(id);
    }

    @ResponseBody
    @RequestMapping(value = "/theme/create", method = RequestMethod.POST)
    public ResponseEntity<Theme> createTheme(@RequestBody Theme theme){
        return new ResponseEntity<Theme>(themeRepository.save(theme), HttpStatus.OK);
    }
}
