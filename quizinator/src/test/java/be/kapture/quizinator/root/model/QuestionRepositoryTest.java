package be.kapture.quizinator.root.model;

import be.kapture.quizinator.root.Main;
import be.kapture.quizinator.root.repository.QuestionRepository;
import be.kapture.quizinator.root.repository.TagRepository;
import be.kapture.quizinator.root.repository.ThemeRepository;
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
public class QuestionRepositoryTest {

    private static final Long ID = 123456789L;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ThemeRepository themeRepository;

    private Question question;



    @Test
    public void save() {
        Theme theme = new Theme();
        theme.setName("theme");
        Tag tag = aTag("tag");
        tagRepository.save(tag);
        themeRepository.save(theme);
        question = aQuestion().withQuestion("question").withAnswer("42").withUrl("url").withTheme(theme).addTag(tag).build();
        questionRepository.save(question);

        Question foundQuestion = questionRepository.findOne(question.getId());

        assertThat(foundQuestion.getId(), is(question.getId()));
        assertThat(foundQuestion.getQuestion(), is(question.getQuestion()));
        assertThat(foundQuestion.getAnswer(), is(question.getAnswer()));
        assertThat(foundQuestion.getUrl(), is(question.getUrl()));
        assertThat(foundQuestion.getTheme(), is(question.getTheme()));
        assertThat(foundQuestion.getTags().toArray(), is(question.getTags().toArray()));
    }

    private Tag aTag(String tag1) {
        Tag tag = new Tag();
        tag.setName(tag1);
        return tag;
    }

}