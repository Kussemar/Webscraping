package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Collector;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class WebScrapingFirstPractice {

    private static final Logger logger = Logger.getLogger(WebScrapingFirstPractice.class.getName());

    public static void main(String[] args) {
        File input = new File("index.html");

        try {
            Document doc = Jsoup.parse(input, "UTF-8");


            // TODO: Extract and print the title of the HTML page.
            String title = doc.getElementsByTag("title").text();
            System.out.printf("Title: %s ", title);


            // TODO: Write code to find and print all the text within `<p>` tags on the page.
            Elements pTag = doc.getElementsByTag("p");
            pTag.forEach(p -> System.out.printf("P tags text: %s", p.text()));
            // TODO: Extract and print all the URLs (href attributes) of the `<a>` tags on the page.
            Elements aTag = doc.getElementsByTag("a");
            aTag.forEach(a -> System.out.println(a.attr("href")));


            // TODO: Exercise 3: Extract All Links
            Elements allLinks = doc.getElementsByAttribute("href");
            allLinks.forEach(href -> System.out.println(href.attr("href")));
            // TODO: extract and print the data from the table as a structured format (e.g., as a list of name-age pairs).
            Map<String, Integer> tableData = new HashMap<>();
            Elements tr = doc.getElementsByTag("td");

//            Map<String, String> collect = tr.stream().collect(Collectors.toMap(
//              e -> e.text(),
//                    e -> e.child(1).text()
//            ));
//            System.out.println("Map: " + collect);

            for (int i = 0; i < tr.size(); i++) {
                String name = tr.get(i).text();
                Integer age = Integer.parseInt(tr.get(i + 1).text());
                tableData.put(name, age);
                i++;
            }
            System.out.println(tableData);

            // TODO: Search for and print any paragraphs that contain the phrase "Random text" in their text content.
            Elements randomText = doc.getElementsContainingOwnText("Random text");
            randomText.forEach(text -> System.out.println(text.text()));

            // TODO: Modify your code to find and print only the URLs that contain "example" in their href attribute.
            Elements example = doc.getElementsByAttributeValueContaining("href", "example");
            example.forEach(ex -> System.out.println(ex.attr("href")));
            // TODO: Extract and print the content of the `<header>` tag.
            Elements header = doc.getElementsByTag("header");
            header.forEach(h -> System.out.println(h.text()));
            // TODO: Find and print the text content of the `<div>` with the id "content" and all its nested elements.
            Elements content = doc.getElementsByAttributeValue("id", "content");
            content.forEach(c -> System.out.println(c.text()));
            // TODO: Extract and print the text content of the `<footer>` tag.
            Elements footer = doc.getElementsByTag("footer");
            footer.forEach(f -> System.out.println(f.text()));

        } catch (IOException e) {
            logger.warning(e.getMessage());
        }

    }
}