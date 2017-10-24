package be.kapture.quizinator.root.repository;

import be.kapture.quizinator.root.Main;
import be.kapture.quizinator.root.model.Question;
import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.model.builder.QuestionBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static be.kapture.quizinator.root.model.builder.QuestionBuilder.aQuestion;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;

@SpringBootTest(classes = Main.class)
@RunWith(SpringRunner.class)
public class TagRepositoryTest {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private QuestionRepository questionRepository;
    private Tag questionTag1;
    private Tag questionTag2;
    private Tag otherTag;
    public static final String NAME = "name";

//    @Before
//    public void setUp() throws Exception {
//        questionTag1 = aTag("tag");
//        questionTag2 = aTag("tag2");
//
//        otherTag = aTag("tag3");
//        tagRepository.save(otherTag);
//        tagRepository.save(questionTag1);
//        tagRepository.save(questionTag2);
//
//    }

    @Test(expected = RuntimeException.class)
    public void save_DuplicateNames() {
        Tag tag = aTag(NAME);
        Tag duplicateTag = aTag(NAME);

        tagRepository.save(tag);
        tagRepository.save(duplicateTag);
    }

    @Test(expected = RuntimeException.class)
    public void save_NameMandatory_Empty() {
        Tag tag = aTag("");

        tagRepository.save(tag);
    }

    @Test(expected = RuntimeException.class)
    public void save_NameMandatory_Null() {
        Tag tag = aTag(null);

        tagRepository.save(tag);
    }


//    @Test
//    public void findAllByQuestions() throws Exception {
//        Question question = aQuestion().withQuestion("question")
//                .addTag(questionTag1).addTag(questionTag2).build();
//        questionRepository.save(question);
//        questionRepository.save(aQuestion().withQuestion("question2").addTag(otherTag).build());
//
//        assertThat(tagRepository.findAllByQuestions(question),
//                contains(questionTag1, questionTag2));
//
//    }

    private Tag aTag(String tagName) {
        Tag tag = new Tag();
        tag.setName(tagName);
        return tag;
    }

}