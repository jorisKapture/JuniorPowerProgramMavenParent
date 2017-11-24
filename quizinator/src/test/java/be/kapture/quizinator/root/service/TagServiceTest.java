package be.kapture.quizinator.root.service;

import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.repository.TagRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TagServiceTest {

    private static final Tag TAG = new Tag("ietske");
    private static final String NEW_TAG_NAME = "tag1";
    private static final Tag TAG0 = aTag("tag0", 42L);
    private static final Tag NEW_TAG = aTag(NEW_TAG_NAME, 43L);

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


    @Test
    public void findListCreateIfNeeded() throws Exception {
        when(tagRepository.findByName(TAG0.getName())).thenReturn(TAG0);

        when(tagRepository.findByName(NEW_TAG_NAME)).thenReturn(null);
        when(tagRepository.save(any(Tag.class))).thenReturn(NEW_TAG);

        List<Tag> result = tagService.findListCreateIfNeeded(TAG0.getName() + "," + NEW_TAG_NAME);

        assertThat(result).contains(TAG0, NEW_TAG);
    }

    @Test
    public void findListCreateIfNeeded_ListEmpty() throws Exception {
        List<Tag> result = tagService.findListCreateIfNeeded("");

        assertThat(result).isEmpty();
        verifyZeroInteractions(tagRepository);
    }


    private static Tag aTag(String tag0name, Long tag0id) {
        Tag tag0 = new Tag();
        tag0.setName(tag0name);
        tag0.setId(tag0id);
        return tag0;
    }

}