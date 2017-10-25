package be.kapture.quizinator.root.controller;

import be.kapture.quizinator.root.dto.QuestionDTO;
import be.kapture.quizinator.root.mapper.DozerBeanMultimapper;
import be.kapture.quizinator.root.model.Question;
import be.kapture.quizinator.root.repository.QuestionRepository;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class QuestionController {
    private QuestionRepository questionRepository;

    private DozerBeanMultimapper dozerBeanMultimapper;

    public List<QuestionDTO> listAllQuestions(){
        return dozerBeanMultimapper.mapCollection(questionRepository.findAll(), QuestionDTO.class);
    }
}
