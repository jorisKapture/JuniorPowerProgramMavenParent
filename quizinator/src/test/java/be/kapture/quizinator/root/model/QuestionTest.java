package be.kapture.quizinator.root.model;

import be.kapture.quizinator.root.model.builder.QuestionBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import be.kapture.quizinator.root.repository.QuestionRepository;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class QuestionTest {

    private final static String QUESTION = "What is The Answer to the Ultimate Question of Life, The Universe, and Everything?";
    private static final String ANSWER = "42";
    private static final String URL = "https://en.wikipedia.org/wiki/The_Hitchhiker%27s_Guide_to_the_Galaxy";
    private static final Long ID = Long.valueOf(123456789);

    @Autowired
    private QuestionRepository questionRepository;

    private Question question;

    @Before
    public void before(){
        question = QuestionBuilder.aQuestion().withId(ID).withQuestion(QUESTION).withAnswer(ANSWER).withUrl(URL).build();
    }

    @Test
    public void testRepo(){
        questionRepository.save(question);
        Question foundQuestion = questionRepository.findOne(ID);
        assertEquals(foundQuestion.getId(),ID);
        assertEquals(foundQuestion.getQuestion(),QUESTION);
        assertEquals(foundQuestion.getAnswer(),ANSWER);
        assertEquals(foundQuestion.getUrl(),URL);
    }

}