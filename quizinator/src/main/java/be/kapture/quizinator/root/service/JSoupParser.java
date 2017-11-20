package be.kapture.quizinator.root.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by missika on 19/11/2017.
 */
@Component
public class JSoupParser {
    public Document getDefaultFileDocument1(String urlString) throws IOException {
        return Jsoup.connect(urlString).get();
    }

    public String getFirstParagraaf(Document document){
        Elements elements = document.select("p");
        return elements.get(0).text();
    }

}
