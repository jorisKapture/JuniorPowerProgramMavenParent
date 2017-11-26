package be.kapture.quizinator.root.service;

import be.kapture.quizinator.root.model.Question;
import be.kapture.quizinator.root.model.Tag;
import be.kapture.quizinator.root.model.Theme;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.substringBetween;

/**
 * Created by missika on 19/11/2017.
 */
@Service
public class ParserService {

    private JSoupParser parser;
    private final ThemeService themeService;

    @Autowired
    public ParserService(JSoupParser parser, ThemeService themeService) {
        this.parser = parser;
        this.themeService = themeService;
    }

    public Question makeFile(String url, Long themeId, List<Tag> tags) throws IOException {
        Theme theme = themeService.findOrThrow(themeId);

        Question quizItem = new Question();
        quizItem.setUrl(url);
        Document documentItem = parser.getDefaultFileDocument1(url);
        String title = StringUtils.removeEnd(documentItem.title(), " - Wikipedia");
        quizItem.setAnswer(title);

        Elements imageElements = documentItem.getElementsByTag("img");
        getMainImage(imageElements).ifPresent(u-> quizItem.setPicture("http:"+u));
        String firstParagraaf = parser.getFirstParagraaf(documentItem);
        String explanation = nameIgnoreCaseWithoutParentheses(title);
        String extra = substringBetween(firstParagraaf, "(", ")");
        if (extra != null) {
            //   quizItem.setExtra(extra);
            firstParagraaf = firstParagraaf.replace("(" + extra + ")", "");
        }
        quizItem.setQuestion(firstParagraaf.replaceAll(explanation, "X"));
        //quizItem.setExplanation(StringUtils.substring(firstParagraaf.replaceAll(explanation, "X"),0,255));
        quizItem.setTheme(theme);
        quizItem.setTags(tags);

        return quizItem;

    }

    private Optional<String> getMainImage(Elements imageElements) {
        return imageElements.stream()
                .filter(this::imageWideEnough)
                .findFirst()
                .map(imageElement -> imageElement.attr("src"));
    }

    private boolean imageWideEnough(Element imageElement) {
        return imageElement.attr("width") != null && Integer.parseInt(imageElement.attr("width"))>100;
    }

    private String nameIgnoreCaseWithoutParentheses(String name) {
        //String name = quizItem.getName();
        if (name.contains("(")) {
            name = name.substring(0, name.indexOf("(") - 1);
        }
        return "(?i)" + name;
    }

}
