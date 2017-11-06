package be.kapture.quizinator.root.service;

import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.model.Theme;
import be.kapture.quizinator.root.repository.TagRepository;
import be.kapture.quizinator.root.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public Tag save(Tag tag){
        return tagRepository.save(tag);
    }


    public Tag save(String name){
        Tag tag = new Tag();
        tag.setName(name);
        Tag testTag = tagRepository.save(tag);
        return save(tag);
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public void delete(Long id){
        tagRepository.delete(id);
    }

    public Tag findOne(Long id){
        return tagRepository.findOne(id);
    }

    public Tag findByName(String name){
        return tagRepository.findByName(name);
    }

}
