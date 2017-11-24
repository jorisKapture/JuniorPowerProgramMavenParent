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
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static be.kapture.quizinator.root.model.builder.QuestionBuilder.aQuestion;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {
    private static final String THEME_STRING = "theme";

    private static final Tag TAG1 = aTag(11L, "name1");
    private static final Question QUESTION1 = aQuestion().withTags(singletonList(TAG1)).build();
    private static final Question QUESTION2 = aQuestion().withTags(singletonList(TAG1)).build();
    private static final Tag TAG2 = aTag(12L, "name2");
    //private static final Tag TAG_NEW = aTag(3L, "newTag");
    private static final Theme THEME = aTheme(1L);
    private static final IllegalArgumentException TAG_NOT_FOUND_EXCEPTION = new IllegalArgumentException("not allowed");
    @InjectMocks
    private QuestionService questionService;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private ThemeRepository themeRepository;
    @Mock
    private TagRepository tagRepository;
    @Mock
    private TagService tagService;
    @Mock
    private ThemeService themeService;
//    @Captor
    @Before
    public void setUp() throws Exception {
        when(themeRepository.findByName(THEME.getName())).thenReturn(THEME);
        when(tagRepository.findByName(TAG1.getName())).thenReturn(TAG1);
        when(tagRepository.findByName(TAG2.getName())).thenReturn(TAG2);
    }

    //    private ArgumentCaptor<Tag> newTagCaptor;
    @Test
    public void findQuestions(){
        when(tagService.findOrThrow(1L)).thenReturn(TAG1);
        when(questionRepository.findByTags(TAG1)).thenReturn(asList(QUESTION1, QUESTION2));

        assertThat(questionService.findQuestions(1L)).contains(QUESTION1, QUESTION2);
    }

    @Test
    public void findQuestions_NoTagId(){
        when(questionRepository.findByTags(null)).thenReturn(asList(QUESTION1, QUESTION2));

        assertThat(questionService.findQuestions(0L)).contains(QUESTION1, QUESTION2);

        verifyZeroInteractions(tagService);
    }

    @Test
    public void findQuestions_IllegalTagId(){
        when(tagService.findOrThrow(1L)).thenThrow(TAG_NOT_FOUND_EXCEPTION);
        when(questionRepository.findByTags(TAG1)).thenReturn(asList(QUESTION1, QUESTION2));

        assertThatCode(() -> questionService.findQuestions(1L)).isEqualTo(TAG_NOT_FOUND_EXCEPTION);
    }

    @Test
    public void find() throws Exception {
        when(tagService.findOrThrow(TAG1.getId())).thenReturn(TAG1);
        when(tagService.findOrThrow(TAG2.getId())).thenReturn(TAG2);
        when(themeService.findOrThrow(THEME.getId())).thenReturn(THEME);

        when(questionRepository.find(THEME, asList(TAG1,TAG2))).thenReturn(asList(QUESTION1, QUESTION2));

        assertThat(questionService.find(THEME.getId(), asList(TAG1.getId(), TAG2.getId()))).contains(QUESTION1, QUESTION2);
    }

    @Test
    @Ignore
    public void find_EmptyValues() throws Exception {
        when(questionRepository.find(null, emptyList())).thenReturn(asList(QUESTION1, QUESTION2));

        assertThat(questionService.find(null, emptyList())).contains(QUESTION1, QUESTION2);
        verifyZeroInteractions(tagService, themeService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void find_TagDoesNotExists() throws Exception {
        when(tagService.findOrThrow(0L)).thenThrow(new IllegalArgumentException("absent"));
        when(themeService.findOrThrow(THEME.getId())).thenReturn(THEME);

        questionService.find(THEME.getId(), asList(0L, TAG2.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void find_ThemeDoesNotExists() throws Exception {
        when(tagService.findOrThrow(TAG1.getId())).thenReturn(TAG1);
        when(tagService.findOrThrow(TAG2.getId())).thenReturn(TAG2);
        when(themeService.findOrThrow(0L)).thenThrow(new IllegalArgumentException());


        questionService.find(0L, asList(TAG1.getId(), TAG2.getId()));
    }


    //    @Ignore
    //    public void saveQuestion_ThemeDoesNotExists() throws Exception {
    //        when(themeRepository.findByName(THEME_STRING)).thenReturn(null);
    //
    //        questionService.saveQuestion(new Question(), THEME_STRING, asList("tag1","tag2"));
    //    }
    //
    //    @Test
    //    public void saveQuestion() throws Exception {
    //        Question question= mock(Question.class);
    //
    //        questionService.saveQuestion(question, THEME.getName(), asList(TAG0.getName(), TAG2.getName()));
    //
    //        InOrder inOrder = inOrder(question, questionRepository);
    //        inOrder.verify(question).setTheme(THEME);
    //        inOrder.verify(question).setTags(asList(TAG0, TAG2));
    //        inOrder.verify(questionRepository).save(question);
    //
    //        questionService.deleteQuestion(question.getId());
    //    }
    //
    //    @Ignore
    //    @Test
    //    public void saveQuestion_NewTag() throws Exception {
    //        String newTagName = "newTag";
//    @Test(expected = IllegalArgumentException.class)
//
//        when(tagRepository.save(newTagCaptor.capture())).thenReturn(TAG_NEW);
//        Question question= mock(Question.class);
//
//        questionService.saveQuestion(question, THEME.getName(), asList(TAG0.getName(), newTagName));
//
//        InOrder inOrder = inOrder(question, questionRepository);
//        inOrder.verify(question).setTheme(THEME);
//        inOrder.verify(question).setTags(asList(TAG0, TAG_NEW));
//        inOrder.verify(questionRepository).save(question);
//        questionService.deleteQuestion(question.getId());
//    }


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