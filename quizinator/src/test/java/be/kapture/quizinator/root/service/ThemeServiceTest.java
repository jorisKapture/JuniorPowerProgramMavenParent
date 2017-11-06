package be.kapture.quizinator.root.service;

import be.kapture.quizinator.root.model.Theme;
import be.kapture.quizinator.root.repository.ThemeRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ThemeServiceTest {
    private static final String NAME = "ietske";
    private static final Theme THEME1 = aTheme("name", 1L);
    private static final Theme THEME2 = aTheme("name2", 2L);

    @InjectMocks
    private ThemeService themeService;
    @Mock
    private ThemeRepository themeRepository;

    @Test
    @Ignore
    public void save() throws Exception {
        Theme savedTheme = themeService.save(NAME);

        assertThat(savedTheme.getName(), is(NAME));
        verify(themeRepository).save(savedTheme);
        themeService.delete(savedTheme.getId());
    }

    @Test
    public void findAll() throws Exception {
        when(themeRepository.findAll()).thenReturn(asList(THEME1, THEME2));

        assertThat(themeService.findAll(), contains(THEME1, THEME2));
    }

    private static  Theme aTheme(String name, Long id) {
        Theme theme = new Theme();
        theme.setName(name);
        theme.setId(id);
        return theme;
    }
}