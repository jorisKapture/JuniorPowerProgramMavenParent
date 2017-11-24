package be.kapture.quizinator.root.dto;

import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.model.Theme;

import java.util.List;

public class QuestionUrlListDTO {
    private List<String> urls;
    private String themeName;
    private String tagsString;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getTagsString() {
        return tagsString;
    }

    public void setTagsString(String tagsString) {
        this.tagsString = tagsString;
    }
}
