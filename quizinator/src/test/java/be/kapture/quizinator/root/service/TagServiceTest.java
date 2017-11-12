package be.kapture.quizinator.root.service;

import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.repository.TagRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TagServiceTest {
    private static final Tag TAG = new Tag("ietske");

    @InjectMocks
    private TagService tagService;

    @Mock
    private TagRepository tagRepository;

    @Test
    public void findOrThrow() throws Exception {
        when(tagRepository.findOne(1L)).thenReturn(TAG);

        assertThat(tagService.findOrThrow(1L)).isEqualTo(TAG);
    }


    @Test
    public void findOrThrow_NotFound() throws Exception {
        when(tagRepository.findOne(1L)).thenReturn(null);

        assertThatCode(() -> tagService.findOrThrow(1L)).isInstanceOf(IllegalArgumentException.class);
    }

}