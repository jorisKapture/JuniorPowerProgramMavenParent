package be.kapture.quizinator.root.service;

import be.kapture.quizinator.root.model.Question;
import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.model.Theme;
import be.kapture.quizinator.root.repository.QuestionRepository;
import be.kapture.quizinator.root.repository.TagRepository;
import be.kapture.quizinator.root.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private TagRepository tagRepository;

    public Question findQuestion(Long id){
        return questionRepository.findOne(id);
    }

    public void saveQuestion(Question question){
        questionRepository.save(question);
    }

    public Question saveQuestion(Question question, String themeName, List<String> tagStrings) {

        Theme theme = themeRepository.findByName(themeName);
        if (theme == null){
            throw new IllegalArgumentException("themeName does not exists");
        }
        question.setTheme(theme);
        List<Tag> tags = tagStrings.stream().map(name -> getTag(name)).collect(Collectors.toList());
        question.setTags(tags);
        questionRepository.save(question);
        return question;
    }

    private Tag getTag(String name) {
        Tag existingTag = tagRepository.findByName(name);
        if (existingTag != null) {
            return existingTag;
        } else {
            Tag newTag = new Tag();
            newTag.setName(name);
            tagRepository.save(newTag);
            return newTag;
        }
    }
}
