package be.kapture.quizinator.root.controller;

import be.kapture.quizinator.root.dto.QuestionDTO;
import be.kapture.quizinator.root.mapper.DozerBeanMultimapper;
import be.kapture.quizinator.root.model.Question;
import be.kapture.quizinator.root.repository.QuestionRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
        System.out.println("*******************");
        System.out.println("id: " + id);
        System.out.println("*******************");
        System.out.println("*******************");
        System.out.println(questionRepository.findOne(42L));
        System.out.println("*******************");
        return dozerBeanMapper.map(questionRepository.findOne(id), QuestionDTO.class);
    }


}
