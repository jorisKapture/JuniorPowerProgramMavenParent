package be.kapture.quizinator.root.service;

import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.repository.TagRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TagServiceTest {

    private static final Tag TAG = new Tag("ietske");
    private static final String TAG0NAME = "tag0";
    private static final String TAG1NAME = "tag1";
    private static final Long TAG0ID = 42L;
    private static final Long TAG1ID = 43L;

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
        Tag tag0 = new Tag();
        tag0.setName(TAG0NAME);
        tag0.setId(TAG0ID);

        Tag tag1 = new Tag();
        tag1.setName(TAG1NAME);
        tag1.setId(TAG1ID);
        when(tagRepository.findByName(TAG0NAME)).thenReturn(tag0);
        when(tagRepository.findByName(TAG1NAME)).thenReturn(null);
        when(tagRepository.save(any(Tag.class))).thenReturn(tag1);
        final String TAG01STRING = TAG0NAME + "," + TAG1NAME;

        List<Tag> result = tagService.findListCreateIfNeeded(TAG01STRING);

        assertThat(result.size() == 2);
        assertThat(result.get(0).getId()).isEqualTo(TAG0ID);
        assertThat(result.get(0).getName()).isEqualTo(TAG0NAME);
        assertThat(result.get(1).getId()).isNotNull();
        assertThat(result.get(1).getName()).isEqualTo(TAG1NAME);
    }

}