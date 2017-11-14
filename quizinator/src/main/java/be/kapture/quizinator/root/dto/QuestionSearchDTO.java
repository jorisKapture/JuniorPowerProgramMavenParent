package be.kapture.quizinator.root.dto;

import java.util.List;

public class QuestionSearchDTO {
    private Long themeId;
    private List<Long> tagIds;

    public Long getThemeId() {
        return themeId;
    }

    public void setThemeId(Long themeId) {
        this.themeId = themeId;
    }

    public List<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
    }
}
