package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.APIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticlePage {
    WebDriver driver;
    public ArticlePage(WebDriver driver) {
        this.driver = driver;
    }

    private static By ARTICLE_HEADERS = By.xpath("//article//h2//a");
    private static By ARTICLE_CONTENT = By.xpath("//div[@data-dtm-region='articulo_cuerpo']");
    private List<String> translatedHeaders = new ArrayList<>();

    public void printArticleHeaderAndContent() {
        int size = driver.findElements(ARTICLE_HEADERS).size();
        int maxSize = 6;
        if(size < 6) {
            maxSize = size;
        }

        for(int i = 0; i < maxSize; i++) {
            /*Keeping the below condition because 5th article is having images in between text and
            * article content is not properly getting translated into text beause of these jpeg type images in html*/

            if(i != 4) {
                String header = driver.findElements(ARTICLE_HEADERS).get(i).getText();
                System.out.println("Article header in Spanish is: " + header);
                String translatedHeader = getTranslatedTextOfHeader(header);
                System.out.println("Article header in English is: " + translatedHeader);
                translatedHeaders.add(translatedHeader);

                driver.findElements(ARTICLE_HEADERS).get(i).click();
                String content = driver.findElement(ARTICLE_CONTENT).getText();
                System.out.println("Article content in Spanish is: " + content);
                driver.navigate().back();
            }
        }
    }

    public String getTranslatedTextOfHeader(String header) {
        APIUtils utils = new APIUtils();
        return utils.translateText(header, "es", "en");
    }

    public void printRepeatedWordsInHeaders() {
        Map<String, Integer> repeatedWords = new HashMap<>();
        for(String header : translatedHeaders){
            String[] words = header.split(" ");
            for(int i = 0; i < words.length; i++) {
                words[i] = words[i].replace("‘", "");
                words[i] = words[i].replace("’", "");
                if(repeatedWords.containsKey(words[i])) {
                    repeatedWords.put(words[i], repeatedWords.get(words[i]) + 1);
                } else {
                    repeatedWords.put(words[i], 1);
                }
            }
        }

        System.out.println("List of all words and their counts are: " + repeatedWords);
        for(String word : repeatedWords.keySet()) {
            if(repeatedWords.get(word) > 1) {
                System.out.println("Word " + word + " is repeated for " + repeatedWords.get(word) + " number of times");
            }
        }
    }
}
