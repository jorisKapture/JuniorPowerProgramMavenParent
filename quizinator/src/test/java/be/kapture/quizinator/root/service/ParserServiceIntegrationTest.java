package be.kapture.quizinator.root.service;

import be.kapture.quizinator.root.Main;
import be.kapture.quizinator.root.model.Question;
import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.model.Theme;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by missika on 19/11/2017.
 */
@SpringBootTest(classes = Main.class)
@RunWith(SpringRunner.class)
public class ParserServiceIntegrationTest {

    private static final String URL1 = "https://nl.wikipedia.org/wiki/Een_ander_zijn_geluk";
    private static final String FIRST_PARAGRAPH1 = "X is een Belgische film uit 2005, geregisseerd door Fien Troch over een Vlaams dorp waarin een kind wordt doodgereden en de dader doorrijdt.";
    private static final String URL2 = "https://nl.wikipedia.org/wiki/Gent";

    @Autowired
    private ParserService parserService;

    @Autowired
    private ThemeService themeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private QuestionService questionService;

    private Theme theme;
    private Tag tag1;
    private Tag tag2;
    private Question question;

    @Before
    public void setUp() throws Exception {
        theme = themeService.save("theme");
        tag1 = tagService.save(new Tag("tag1"));
        tag2 = tagService.save(new Tag("tag2"));
    }

    @After
    public void tearDown() throws Exception {
        themeService.delete(theme.getId());
        tagService.delete(tag1.getId());
        tagService.delete(tag2.getId());


    }

    @Test
    public void makeFile() throws Exception {
        Question question = parserService.makeFile(URL1, theme.getId(), Arrays.asList(tag1.getId(), tag2.getId()));

        Question loadedQuestion= questionService.findQuestion(question.getId());

        assertThat(loadedQuestion.getAnswer(), is("Een ander zijn geluk"));
        assertThat(loadedQuestion.getUrl(), is(URL1));
        assertThat(loadedQuestion.getQuestion(), is(FIRST_PARAGRAPH1));
        assertThat(loadedQuestion.getTheme().getId(), is(theme.getId()));
        assertThat(loadedQuestion.getTags().stream().map(Tag::getId).collect(toList()), contains(tag1.getId(), tag2.getId()));

        questionService.deleteQuestion(question.getId());
    }

    @Test
    public void makeFile_WithExtra() throws Exception {
        question = parserService.makeFile(URL2, theme.getId(), Arrays.asList(tag1.getId(), tag2.getId()));

        Question loadedQuestion= questionService.findQuestion(question.getId());

        assertThat(loadedQuestion.getAnswer(), is("Gent"));
        assertThat(loadedQuestion.getUrl(), is(URL2));
        assertThat(loadedQuestion.getQuestion(), is("X  is de hoofdstad van de Belgische provincie Oost-Vlaanderen en van het arrondissement X. X heeft een oppervlakte van 156,18 km² en telt circa 259.000 inwoners (2017), waarmee het naar inwonertal de op één na grootste gemeente van België is, na Antwerpen. De stad is tevens de hoofdplaats van het kieskanton X en telt vijf gerechtelijke kantons."));

        questionService.deleteQuestion(question.getId());

    }


}