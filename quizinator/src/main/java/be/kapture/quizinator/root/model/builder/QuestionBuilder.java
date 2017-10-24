package be.kapture.quizinator.root.model.builder;

import be.kapture.quizinator.root.model.Question;
import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.model.Theme;

import java.util.ArrayList;
import java.util.List;

public class QuestionBuilder
{
    private Question question;

    private QuestionBuilder(){
        question = new Question();
        question.setTags(new ArrayList<>());
    }

    public static QuestionBuilder aQuestion(){
        return new QuestionBuilder();
    }

    public Question build(){
        return this.question;
    }

    public QuestionBuilder withId(Long id){
        question.setId(id);
        return this;
    }

    public QuestionBuilder withQuestion(String questionstring){
        question.setQuestion(questionstring);
        return this;
    }

    public QuestionBuilder withAnswer(String answer){
        question.setAnswer(answer);
        return this;
    }

    public QuestionBuilder withUrl(String url){
        question.setUrl(url);
        return this;
    }

    public QuestionBuilder withTheme(Theme theme){
        question.setTheme(theme);
        return this;
    }

    public QuestionBuilder withTags(List<Tag> tags){
        question.setTags(tags);
        return this;
    }

    public QuestionBuilder addTag(Tag tag){
        question.addTag(tag);
        return this;
    }
}
