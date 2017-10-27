package be.kapture.quizinator.root.controller;

import be.kapture.quizinator.root.dto.QuestionDTO;
import be.kapture.quizinator.root.mapper.DozerBeanMultimapper;
import be.kapture.quizinator.root.model.Question;
import be.kapture.quizinator.root.repository.QuestionRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping()
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private DozerBeanMultimapper dozerBeanMultimapper;


    private DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    @ResponseBody
    @RequestMapping(value = "/question", method = RequestMethod.GET)
    public List<QuestionDTO> listAllQuestions(){
        return dozerBeanMultimapper.mapCollection(questionRepository.findAll(), QuestionDTO.class);
    }

    @ResponseBody
    @RequestMapping(value = "/question/{id}", method = RequestMethod.GET)
    public QuestionDTO findQuestionById(@PathVariable Long id){
        return dozerBeanMapper.map(questionRepository.findOne(id), QuestionDTO.class);
    }

    @ResponseBody
    @RequestMapping(value = "/question/create", method = RequestMethod.POST)
    public QuestionDTO createQuestion(QuestionDTO questionDTO){
        Question question = dozerBeanMapper.map(questionDTO, Question.class);
        return dozerBeanMapper.map(questionRepository.save(question), QuestionDTO.class);
    }

    /*
    @RequestMapping(value = "add/{id}", method = RequestMethod.POST)
public String addPerson(@RequestParam("name") String name, @PathVariable("id") String id)
     */


}
