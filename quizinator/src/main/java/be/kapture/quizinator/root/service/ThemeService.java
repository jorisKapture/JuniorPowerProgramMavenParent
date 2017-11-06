package be.kapture.quizinator.root.service;

import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.model.Theme;
import be.kapture.quizinator.root.repository.TagRepository;
import be.kapture.quizinator.root.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService {
    @Autowired
    private ThemeRepository themeRepository;

    public Theme save(Theme theme){
        return themeRepository.save(theme);
    }

    public Theme save(String name){
        Theme theme = new Theme();
        theme.setName(name);
        return save(theme);
    }

    public List<Theme> findAll() {
        return themeRepository.findAll();
    }

    public void delete(Long id){
        themeRepository.delete(id);
    }

    public Theme findOne(Long id){
        return themeRepository.findOne(id);
    }

    public Theme findByName(String name){
        return themeRepository.findByName(name);
    }

}
