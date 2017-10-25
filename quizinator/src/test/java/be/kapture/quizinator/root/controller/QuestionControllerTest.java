package be.kapture.quizinator.root.controller;

import be.kapture.quizinator.root.dto.QuestionDTO;
import be.kapture.quizinator.root.mapper.DozerBeanMultimapper;
import be.kapture.quizinator.root.model.Question;
import be.kapture.quizinator.root.repository.QuestionRepository;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class QuestionControllerTest {
    @InjectMocks
    private QuestionController questionController;

    @Mock
    private DozerBeanMultimapper dozerBeanMultimapper;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private List<Question> questionList;
    @Mock
    private List<QuestionDTO> questionDTOList;

    @Test
    public void listAllQuestions() throws Exception {
        Mockito.when(questionRepository.findAll()).thenReturn(questionList);
        Mockito.when(dozerBeanMultimapper.mapCollection(questionList, QuestionDTO.class)).thenReturn(questionDTOList);
        Assertions.assertThat(questionController.listAllQuestions()).isEqualTo(questionDTOList);
    }

}