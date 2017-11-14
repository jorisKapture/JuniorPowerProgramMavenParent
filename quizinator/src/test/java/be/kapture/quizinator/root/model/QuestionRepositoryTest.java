package be.kapture.quizinator.root.model;

import be.kapture.quizinator.root.Main;
import be.kapture.quizinator.root.repository.QuestionRepository;
import be.kapture.quizinator.root.repository.TagRepository;
import be.kapture.quizinator.root.repository.ThemeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static be.kapture.quizinator.root.model.builder.QuestionBuilder.aQuestion;
import static java.util.Arrays.asList;
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

    private Theme theme1, themeOther;
    private Tag tag1, tag2, otherTag;

    @Before
    public void setUp() throws Exception {
        theme1 = new Theme();
        theme1.setName("theme1");

        themeOther= new Theme();
        themeOther.setName("themeOther");

        tag1 = aTag("tag1");
        tag2 = aTag("tag2");
        otherTag = aTag("otherTag");
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        tagRepository.save(otherTag);
        themeRepository.save(theme1);
        themeRepository.save(themeOther);
    }

    @After
    public void tearDown() throws Exception {
        questionRepository.deleteAll();
        tagRepository.delete(tag1);
        tagRepository.delete(tag2);
        tagRepository.delete(otherTag);
        themeRepository.delete(theme1);
        themeRepository.delete(themeOther);
    }

    @Test
    public void save() {
        Question question = aQuestion().withQuestion("question").withAnswer("42").withUrl("url").withTheme(theme1).addTag(tag1).build();
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
        Question question1 = aQuestion().withQuestion("question1").withAnswer("42").withUrl("url1").withTheme(theme1).addTag(tag1).build();
        Question question2 = aQuestion().withQuestion("question2").withAnswer("43").withUrl("url2").withTheme(theme1).addTag(tag1).build();
        Question questionOtherTag = aQuestion().withQuestion("questionOtherTag").withAnswer("42").withUrl("url").withTheme(theme1).addTag(otherTag).build();

        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(questionOtherTag);

        List<Question> questions = questionRepository.findByTags(tag1);

        assertThat(questions.stream().map(Question::getId).collect(toList()),
                containsInAnyOrder(question1.getId(), question2.getId()));
    }

    @Test
    public void findByTags_NoTag(){
        Question question1 = aQuestion().withQuestion("question1").withAnswer("42").withUrl("url1").withTheme(theme1).addTag(tag1).build();
        Question question2 = aQuestion().withQuestion("question2").withAnswer("43").withUrl("url2").withTheme(theme1).addTag(tag1).build();

        questionRepository.save(question1);
        questionRepository.save(question2);

        List<Question> questions = questionRepository.findByTags(null);

        assertThat(questions.stream().map(Question::getId).collect(toList()), containsInAnyOrder(question1.getId(), question2.getId()));
    }

    @Test
    public void find(){
        Question question1 = aQuestion().withQuestion("question1").withAnswer("42").withUrl("url1").withTheme(theme1).addTag(tag1).build();
        Question question2 = aQuestion().withQuestion("question2").withAnswer("43").withUrl("url2").withTheme(theme1).addTag(tag2).build();
        Question questionOtherTag = aQuestion().withQuestion("questionOtherTag").withAnswer("42").withUrl("url").withTheme(theme1).addTag(otherTag).build();
        Question questionOtherTheme = aQuestion().withQuestion("questionOtherTheme").withAnswer("42").withUrl("url1").withTheme(themeOther).addTag(tag1).build();

        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(questionOtherTag);
        questionRepository.save(questionOtherTheme);

        List<Question> questions = questionRepository.find(theme1, asList(tag1, tag2));
        assertThat(questions.stream().map(Question::getId).collect(toList()),
            contains(question1.getId(), question2.getId()));
    }


    private Tag aTag(String tag1) {
        Tag tag = new Tag();
        tag.setName(tag1);
        return tag;
    }

}