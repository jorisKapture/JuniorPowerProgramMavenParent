package be.kapture.quizinator.root.controller;

import be.kapture.quizinator.root.dto.QuestionDTO;
import be.kapture.quizinator.root.mapper.DozerBeanMultimapper;
import be.kapture.quizinator.root.model.Question;
import be.kapture.quizinator.root.repository.QuestionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestionControllerTest {
    private List<Question> QUESTIONS = asList(aQuestion(1L, "question1"), aQuestion(2L, "question2"));
    private List<QuestionDTO> QUESTION_DTOS = asList(aQuestionDTO(3L, "quest3"), aQuestionDTO(4L, "quest4"));

    @InjectMocks
    private QuestionController questionController;
    @Mock
    private DozerBeanMultimapper dozerBeanMultimapper;
    @Mock
    private QuestionRepository questionRepository;

    @Test
    public void listAllQuestions() throws Exception {
        when(questionRepository.findAll()).thenReturn(QUESTIONS);
        when(dozerBeanMultimapper.mapCollection(QUESTIONS, QuestionDTO.class)).thenReturn(QUESTION_DTOS);

        assertThat(questionController.listAllQuestions()).isEqualTo(QUESTION_DTOS);
    }

    private static QuestionDTO aQuestionDTO(long id, String quest1) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(id);
        dto.setQuestion(quest1);
        return dto;
    }

    private static Question aQuestion(long id, String question1) {
        Question question = new Question();
        question.setId(id);
        question.setQuestion(question1);
        return question;
    }

}