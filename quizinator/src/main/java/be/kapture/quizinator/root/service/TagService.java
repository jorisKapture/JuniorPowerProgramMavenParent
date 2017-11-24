package be.kapture.quizinator.root.service;

import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Service
public class TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag save(Tag tag){
        return tagRepository.save(tag);
    }


    public Tag findOrThrow(long id){
        Tag tag = tagRepository.findOne(id);
        if (tag == null){
            throw new IllegalArgumentException("no tag found");
        }
        return tag;
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

    public List<Tag> findListCreateIfNeeded(String tagsString){
        if (isEmpty(tagsString)){
            return Collections.emptyList();
        }
        String[] tagArray = tagsString.split(",");
        List<Tag> tags = new ArrayList<>();
        for(int i=0;i<tagArray.length;i++){
            Tag tag = tagRepository.findByName(tagArray[i]);
            if(tag == null){
                tag = new Tag();
                tag.setName(tagArray[i]);
                tag = tagRepository.save(tag);
            }
            tags.add(tag);
        }
        return tags;
    }

    public List<Long> findTagIdsFromString(String tagsString){
        List<Tag> tags = findListCreateIfNeeded(tagsString);
        List<Long> result = new ArrayList<>();
        for(Tag tag : tags){
            result.add(tag.getId());
        }
        return result;
    }

}
