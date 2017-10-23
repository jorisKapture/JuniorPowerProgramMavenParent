package be.kapture.quizinator.root.model.mapper;

import be.kapture.quizinator.root.dto.QuestionDTO;
import be.kapture.quizinator.root.model.Question;
import org.assertj.core.api.Assertions;
import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import be.kapture.quizinator.root.mapper.DozerBeanMultimapper;

import java.util.Arrays;

/**
 * Created by deroejo on 10/10/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DozerBeanMultiMapperTest {

    @InjectMocks
    private DozerBeanMultimapper dozerBeanMultiMapper;

    @Mock
    private DozerBeanMapper dozerBeanMapper;

    @Mock
    private Question question1;
    @Mock
    private Question question2;

    @Mock
    private QuestionDTO questionDTO1;
    @Mock
    private QuestionDTO questionDTO2;

    @Test
    public void mapCollection(){
        Mockito.when(dozerBeanMapper.map(question1, QuestionDTO.class)).thenReturn(questionDTO1);
        Mockito.when(dozerBeanMapper.map(question2, QuestionDTO.class)).thenReturn(questionDTO2);

        Assertions.assertThat(dozerBeanMultiMapper.mapCollection(Arrays.asList(question1, question2), QuestionDTO.class))
            .containsExactly(questionDTO1, questionDTO2);
    }

}