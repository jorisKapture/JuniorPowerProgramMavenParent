package be.kapture.quizinator.root.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Question extends PersistentObject
{
    // id, link, vraag, antwoord, thema en tags
    private String question;

    private String answer;

    private String url;

    @NotNull
    @ManyToOne
    private Theme theme;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Tag> tags;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) { this.tags.add(tag);}

    public void removeTag(Tag tag) { this.tags.remove(tag);}
}
