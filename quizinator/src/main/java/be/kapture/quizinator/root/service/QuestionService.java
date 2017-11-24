package be.kapture.quizinator.root.service;

import be.kapture.quizinator.root.model.Question;
import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.model.Theme;
import be.kapture.quizinator.root.repository.QuestionRepository;
import be.kapture.quizinator.root.repository.TagRepository;
import be.kapture.quizinator.root.repository.ThemeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

@Service
public class QuestionService {
    static Logger log = Logger.getLogger(QuestionService.class.getName());
    private final ThemeService themeService;
    private final TagService tagService;

    @Autowired
    private ParserService parserService;

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, ThemeService themeService, TagService tagService) {
        this.questionRepository = questionRepository;
        this.themeService = themeService;
        this.tagService = tagService;
    }

    public List<Question> findQuestions(long tagId) {
        Tag tag = tagId == 0 ? null : tagService.findOrThrow(tagId);
        return questionRepository.findByTags(tag);
    }

    public Question findQuestion(Long id) {
        return questionRepository.findOne(id);
    }

    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    public void deleteQuestion(Long id) {
        questionRepository.delete(id);
    }

//    public Question saveQuestion(Question question, String themeName, List<String> tagStrings) {
//
//        Theme theme = themeService.findByName(themeName);
//        if (theme == null){
//            throw new IllegalArgumentException("themeName does not exists");
//        }
//        question.setTheme(theme);
//        List<Tag> tags = tagStrings.stream().map(name -> getTag(name)).collect(Collectors.toList());
//        question.setTags(tags);
//        questionRepository.save(question);
//        return question;
//    }

    private Tag getTag(String name) {
        Tag existingTag = tagService.findByName(name);
        if (existingTag != null) {
            return existingTag;
        } else {
            Tag newTag = new Tag();
            newTag.setName(name);
            return tagService.save(newTag);
        }
    }

    public List<Question> find(Long themeId, List<Long> tagIds) {
        Theme theme = (themeId == null) ? null : themeService.findOrThrow(themeId);
        List<Tag> tags = tagIds.stream().map(tagService::findOrThrow).collect(toList());
        List<Question> result = new ArrayList<>();
        if(theme != null && !tags.isEmpty()){
            result = questionRepository.find(theme, tags);
        }
        else if (theme !=null){
            result = questionRepository.findByTheme(theme);
        }
        else if (!tags.isEmpty()){
            result = questionRepository.findByTags(tags.get(0));
        }
        else{
            result = questionRepository.findAll();
        }

        return result;
    }

    public List<Question> bulkCreate(List<String> urls, String tagsString, String themeName){
        List<Tag> tags = tagService.findListCreateIfNeeded(tagsString);
        List<Long> tagIds = new ArrayList<>();
        for(Tag tag : tags){
            tagIds.add(tag.getId());
        }
        Long themeId = themeService.findOrCreateId(themeName);

        List<Question> questions = new ArrayList<>();
        int failures = 0;

        for(String url : urls){
            try {
                Question question = questionRepository.save(parserService.makeFile(url, themeId, tagIds));
                questions.add(question);
            }
//            catch (IOException e){
//                log.error(e.getMessage());
//                log.error("failed saving: " + url);
//            }
            catch (Exception e){
                failures++;
                log.error("failure " + failures);
                log.error(e.getMessage());
                log.error("failed saving: " + url);
            }
        }
        return questions;
    }
}
