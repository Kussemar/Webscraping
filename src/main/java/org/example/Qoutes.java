package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.logging.Logger;

public class Qoutes {
    //private static final Logger logger = Logger.getLogger(WebScrapingFirstPractice.class.getName());

    public static void main(String[] args) throws IOException {
        String url = "http://quotes.toscrape.com/";
        Document doc = Jsoup.connect(url).get();

        Element nextButton = doc.selectFirst("li.next > a");

        Elements quotes = doc.select("div.quote");
        for (Element quote : quotes) {
            String quoteText = quote.selectFirst("span.text").text();
            String author = quote.selectFirst("small.author").text();
            System.out.println(quoteText + " - " + author);
        }

        if (nextButton != null) {
            String nextPageUrl = nextButton.attr("abs:href");
            doc = Jsoup.connect(nextPageUrl).get();
        }

        while (nextButton != null) {
            // Extract data from current page
            Elements quotes2 = doc.select("div.quote");
            for (Element quote : quotes2) {
                String quoteText = quote.selectFirst("span.text").text();
                String author = quote.selectFirst("small.author").text();
                System.out.println(quoteText + " - " + author);
            }

            // Navigate to next page
            if (nextButton != null) {
                String nextPageUrl = nextButton.attr("abs:href");
                doc = Jsoup.connect(nextPageUrl).get();
                nextButton = doc.selectFirst("li.next > a");
            }
        }

    }
}
