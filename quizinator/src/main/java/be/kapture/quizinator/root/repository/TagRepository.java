package be.kapture.quizinator.root.repository;

import be.kapture.quizinator.root.model.Question;
import be.kapture.quizinator.root.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{
    Tag findByName(String tag1);


//    @Query(value="select question.tags from Question question where question=?1")
//    List<Tag> findAllByQuestions(Question question);

//    void saveOrUpdate(Tag tag);
}
