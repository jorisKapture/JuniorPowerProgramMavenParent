package be.kapture.quizinator.root.controller;

import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.model.Theme;
import be.kapture.quizinator.root.service.TagService;
import be.kapture.quizinator.root.service.ThemeService;
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
    private ThemeService themeService;

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/theme", method = RequestMethod.GET)
    public List<Theme> listAllThemes(){
        return themeService.findAll();
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/theme/{id}", method = RequestMethod.GET)
    public Theme findThemeById(@PathVariable Long id){
        return themeService.findOne(id);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/theme/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteThemeById(@PathVariable Long id){
        themeService.delete(id);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/theme/create", method = RequestMethod.POST)
    public ResponseEntity<Theme> createTheme(@RequestBody Theme theme){
        Theme savedTheme;
        try{
            savedTheme = themeService.save(theme);
        } catch (Exception e){
            savedTheme = themeService.findByName(theme.getName());
            return new ResponseEntity<>(savedTheme, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(savedTheme, HttpStatus.OK);
    }


    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/theme/findorcreate", method = RequestMethod.POST)
    public ResponseEntity<Theme> findOrCreateTheme(@RequestBody String themename){
        Theme theme = themeService.findOrCreate(themename);
        return new ResponseEntity<>(theme, HttpStatus.OK);
    }
}
