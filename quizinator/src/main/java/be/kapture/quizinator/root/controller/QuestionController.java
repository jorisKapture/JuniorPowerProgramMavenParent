package be.kapture.quizinator.root.controller;

import be.kapture.quizinator.root.dto.QuestionDTO;
import be.kapture.quizinator.root.dto.QuestionSearchDTO;
import be.kapture.quizinator.root.mapper.DozerBeanMultimapper;
import be.kapture.quizinator.root.model.Question;
import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.repository.QuestionRepository;
import be.kapture.quizinator.root.repository.TagRepository;
import be.kapture.quizinator.root.service.QuestionService;
import be.kapture.quizinator.root.service.TagService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/question")
public class QuestionController {
    private final QuestionService questionService;

    private final QuestionRepository questionRepository;

    private final DozerBeanMultimapper dozerBeanMultimapper;


    private DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    @Autowired
    public QuestionController(QuestionRepository questionRepository, DozerBeanMultimapper dozerBeanMultimapper, QuestionService questionService) {
        this.questionRepository = questionRepository;
        this.dozerBeanMultimapper = dozerBeanMultimapper;
        this.questionService = questionService;
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping( method = RequestMethod.GET)
    public List<QuestionDTO> listAllQuestions(){
        return dozerBeanMultimapper.mapCollection(questionRepository.findAll(), QuestionDTO.class);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public QuestionDTO findQuestionById(@PathVariable Long id){
        return dozerBeanMapper.map(questionRepository.findOne(id), QuestionDTO.class);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/tag/{tagId}", method = RequestMethod.GET)
    public List<QuestionDTO> findQuestionByTag(@PathVariable Long tagId){
        return dozerBeanMultimapper.mapCollection(questionService.findQuestions(tagId), QuestionDTO.class);
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteQuestionById(@PathVariable Long id){
        questionRepository.delete(id);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<QuestionDTO> createQuestion(@RequestBody QuestionDTO questionDTO){
        Question question = dozerBeanMapper.map(questionDTO, Question.class);
        return new ResponseEntity<>(dozerBeanMapper.map(questionRepository.save(question), QuestionDTO.class), HttpStatus.OK);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ResponseEntity<List<QuestionDTO>> findQuestions(@RequestBody QuestionSearchDTO questionSearchDTO){
        return new ResponseEntity<List<QuestionDTO>>(dozerBeanMultimapper.mapCollection(questionService.find(questionSearchDTO.getThemeId(), questionSearchDTO.getTagIds()), QuestionDTO.class), HttpStatus.OK);
    }

}
