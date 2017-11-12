package be.kapture.quizinator.root.repository;

import be.kapture.quizinator.root.model.Question;
import be.kapture.quizinator.root.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

    @Query(value="select question from Question question join question.tags tags where ?1 = null or tags = ?1")
    List<Question> findByTags(Tag tag);
}
