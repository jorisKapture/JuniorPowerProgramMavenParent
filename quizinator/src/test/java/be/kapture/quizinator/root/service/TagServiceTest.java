package be.kapture.quizinator.root.service;

import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.model.Theme;
import be.kapture.quizinator.root.repository.TagRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TagServiceTest {
    private static final String NAME = "ietske";

    @InjectMocks
    private TagService tagService;

    @Mock
    private TagRepository tagRepository;

    @Test
    @Ignore
    public void save() throws Exception {
        Tag savedTag = tagService.save(NAME);

        assertThat(savedTag.getName(), is(NAME));
        verify(tagRepository).save(savedTag);
        tagService.delete(savedTag.getId());
    }

}