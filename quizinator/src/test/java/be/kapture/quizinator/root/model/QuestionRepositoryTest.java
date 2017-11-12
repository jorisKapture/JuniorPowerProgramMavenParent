package be.kapture.quizinator.root.model;

import be.kapture.quizinator.root.Main;
import be.kapture.quizinator.root.repository.QuestionRepository;
import be.kapture.quizinator.root.repository.TagRepository;
import be.kapture.quizinator.root.repository.ThemeRepository;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static be.kapture.quizinator.root.model.builder.QuestionBuilder.aQuestion;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class QuestionRepositoryTest {

    private static final Long ID = 123456789L;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ThemeRepository themeRepository;

    private Theme theme;
    private Tag tag, otherTag;

    @Before
    public void setUp() throws Exception {
        theme = new Theme();
        theme.setName("theme");

        tag = aTag("tag");
        otherTag = aTag("otherTag");
        tagRepository.save(tag);
        tagRepository.save(otherTag);
        themeRepository.save(theme);
    }

    @After
    public void tearDown() throws Exception {
        questionRepository.deleteAll();
        tagRepository.delete(tag);
        tagRepository.delete(otherTag);
        themeRepository.delete(theme);
    }

    @Test
    public void save() {
        Question question = aQuestion().withQuestion("question").withAnswer("42").withUrl("url").withTheme(theme).addTag(tag).build();
        questionRepository.save(question);

        Question foundQuestion = questionRepository.findOne(question.getId());

        assertThat(foundQuestion.getId(), is(question.getId()));
        assertThat(foundQuestion.getQuestion(), is(question.getQuestion()));
        assertThat(foundQuestion.getAnswer(), is(question.getAnswer()));
        assertThat(foundQuestion.getUrl(), is(question.getUrl()));
        assertThat(foundQuestion.getTheme(), is(question.getTheme()));
        assertThat(foundQuestion.getTags().toArray(), is(question.getTags().toArray()));

    }

    @Test
    public void findByTags(){
        Question question1 = aQuestion().withQuestion("question1").withAnswer("42").withUrl("url1").withTheme(theme).addTag(tag).build();
        Question question2 = aQuestion().withQuestion("question2").withAnswer("43").withUrl("url2").withTheme(theme).addTag(tag).build();
        Question questionOtherTag = aQuestion().withQuestion("questionOtherTag").withAnswer("42").withUrl("url").withTheme(theme).addTag(otherTag).build();

        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(questionOtherTag);

        List<Question> questions = questionRepository.findByTags(tag);

        assertThat(questions.stream().map(Question::getId).collect(toList()), containsInAnyOrder(question1.getId(), question2.getId()));
    }

    @Test
    public void findByTags_NoTag(){
        Question question1 = aQuestion().withQuestion("question1").withAnswer("42").withUrl("url1").withTheme(theme).addTag(tag).build();
        Question question2 = aQuestion().withQuestion("question2").withAnswer("43").withUrl("url2").withTheme(theme).addTag(tag).build();

        questionRepository.save(question1);
        questionRepository.save(question2);

        List<Question> questions = questionRepository.findByTags(null);

        assertThat(questions.stream().map(Question::getId).collect(toList()), containsInAnyOrder(question1.getId(), question2.getId()));
    }


    private Tag aTag(String tag1) {
        Tag tag = new Tag();
        tag.setName(tag1);
        return tag;
    }

}