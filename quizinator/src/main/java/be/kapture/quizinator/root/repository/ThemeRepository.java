package be.kapture.quizinator.root.repository;

import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long>{
    Theme findByName(String theme);

//    @Query(value="select question.tags from Question question where question=?1")
//    List<Tag> findAllByQuestions(Question question);
}
