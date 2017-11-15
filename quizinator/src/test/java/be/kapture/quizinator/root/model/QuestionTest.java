package be.kapture.quizinator.root.model;

import be.kapture.quizinator.root.Main;
import be.kapture.quizinator.root.model.builder.QuestionBuilder;
import be.kapture.quizinator.root.service.QuestionService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static be.kapture.quizinator.root.model.builder.QuestionBuilder.aQuestion;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class QuestionTest {

    private static final Long ID = 123456789L;

    @Autowired
    private QuestionService questionService;

    private Question question;

    @Before
    public void before() {
        question = aQuestion().withQuestion("question").withAnswer("42").withUrl("url").build();
    }

    @Test
    @Ignore
    public void testService() {
        questionService.saveQuestion(question);

        Question foundQuestion = questionService.findQuestion(question.getId());

        assertThat(foundQuestion.getId(), is(question.getId()));
        assertThat(foundQuestion.getQuestion(), is(question.getQuestion()));
        assertThat(foundQuestion.getAnswer(), is(question.getAnswer()));
        assertThat(foundQuestion.getUrl(), is(question.getUrl()));

        questionService.deleteQuestion(question.getId());
    }

}