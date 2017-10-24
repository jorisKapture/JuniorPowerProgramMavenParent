package be.kapture.quizinator.root.model.mapper;

import be.kapture.quizinator.root.dto.QuestionDTO;
import be.kapture.quizinator.root.mapper.DozerBeanMultimapper;
import be.kapture.quizinator.root.model.Question;
import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.model.Theme;
import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static be.kapture.quizinator.root.model.builder.QuestionBuilder.aQuestion;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by deroejo on 10/10/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DozerBeanMultiMapperTest {

    private static final Question QUESTION1 = aQuestion().withAnswer("answer1").withId(1L).build();
    private static final Question QUESTION2 = aQuestion().withAnswer("answer2").withId(2L).build();


    @InjectMocks
    private DozerBeanMultimapper dozerBeanMultiMapper;
    @Mock
    private DozerBeanMapper dozerBeanMapper;

    private QuestionDTO questionDTO1= new QuestionDTO();
    private QuestionDTO questionDTO2= new QuestionDTO();

    @Test
    public void mapCollection(){
        when(dozerBeanMapper.map(QUESTION1, QuestionDTO.class)).thenReturn(questionDTO1);
        when(dozerBeanMapper.map(QUESTION2, QuestionDTO.class)).thenReturn(questionDTO2);

        assertThat(dozerBeanMultiMapper.mapCollection(asList(QUESTION1, QUESTION2), QuestionDTO.class))
            .containsExactly(questionDTO1, questionDTO2);
    }

}