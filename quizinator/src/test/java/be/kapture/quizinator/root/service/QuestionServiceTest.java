package be.kapture.quizinator.root.service;

import be.kapture.quizinator.root.model.Question;
import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.model.Theme;
import be.kapture.quizinator.root.repository.QuestionRepository;
import be.kapture.quizinator.root.repository.TagRepository;
import be.kapture.quizinator.root.repository.ThemeRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {
    private static final String THEME_STRING = "theme";
    private static final Tag TAG1 = aTag(1L, "name1");
    private static final Tag TAG2 = aTag(2L, "name2");
    private static final Tag TAG_NEW = aTag(3L, "newTag");
    private static final Theme THEME = aTheme(1L);

    @InjectMocks
    private QuestionService questionService;

    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private ThemeRepository themeRepository;
    @Mock
    private TagRepository tagRepository;
    @Captor
    private ArgumentCaptor<Tag> newTagCaptor;

    @Before
    public void setUp() throws Exception {
        when(themeRepository.findByName(THEME.getName())).thenReturn(THEME);
        when(tagRepository.findByName(TAG1.getName())).thenReturn(TAG1);
        when(tagRepository.findByName(TAG2.getName())).thenReturn(TAG2);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void saveQuestion_ThemeDoesNotExists() throws Exception {
        when(themeRepository.findByName(THEME_STRING)).thenReturn(null);

        questionService.saveQuestion(new Question(), THEME_STRING, asList("tag1","tag2"));
    }

    @Test
    @Ignore
    public void saveQuestion() throws Exception {
        Question question= mock(Question.class);

        questionService.saveQuestion(question, THEME.getName(), asList(TAG1.getName(), TAG2.getName()));

        InOrder inOrder = inOrder(question, questionRepository);
        inOrder.verify(question).setTheme(THEME);
        inOrder.verify(question).setTags(asList(TAG1, TAG2));
        inOrder.verify(questionRepository).save(question);
    }

    @Ignore
    @Test
    public void saveQuestion_NewTag() throws Exception {
        String newTagName = "newTag";

        when(tagRepository.save(newTagCaptor.capture())).thenReturn(TAG_NEW);
        Question question= mock(Question.class);

        questionService.saveQuestion(question, THEME.getName(), asList(TAG1.getName(), newTagName));

        InOrder inOrder = inOrder(question, questionRepository);
        inOrder.verify(question).setTheme(THEME);
        inOrder.verify(question).setTags(asList(TAG1, TAG_NEW));
        inOrder.verify(questionRepository).save(question);
    }


    private static Tag aTag(Long id, String name) {
        Tag tag = new Tag();
        tag.setId(id);
        tag.setName(name);
        return tag;
    }

    private static Theme aTheme(long id) {
        Theme theme = new Theme();
        theme.setId(id);
        theme.setName(THEME_STRING);
        return theme;
    }


}