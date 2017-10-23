package be.kapture.quizinator.root.model.mapper;

import be.kapture.quizinator.root.dto.QuestionDTO;
import be.kapture.quizinator.root.model.Question;
import be.kapture.quizinator.root.model.QuestionTest;
import be.kapture.quizinator.root.model.builder.QuestionBuilder;
import org.assertj.core.api.Assertions;
import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

/**
 * Created by deroejo on 10/10/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DozerBeanMapperTest {


    private final static String QUESTION = "What is The Answer to the Ultimate Question of Life, The Universe, and Everything?";
    private static final String ANSWER = "42";
    private static final String URL = "https://en.wikipedia.org/wiki/The_Hitchhiker%27s_Guide_to_the_Galaxy";
    private static final Long ID = Long.valueOf(123456789);
    private DozerBeanMapper dozerBeanMapper;

    private Question question;

    private QuestionDTO questionDTO;

    @Before
    public void before(){
        question = QuestionBuilder.aQuestion().withId(ID).withQuestion(QUESTION).withAnswer(ANSWER).withUrl(URL).build();
        dozerBeanMapper = new DozerBeanMapper();
    }

    @Test
    public void map(){

        QuestionDTO testDTO = dozerBeanMapper.map(question, QuestionDTO.class);

        Assertions.assertThat(testDTO.getId()).isEqualTo(ID);
        Assertions.assertThat(testDTO.getQuestion()).isEqualTo(QUESTION);
        Assertions.assertThat(testDTO.getAnswer()).isEqualTo(ANSWER);
        Assertions.assertThat(testDTO.getUrl()).isEqualTo(URL);

    }

}