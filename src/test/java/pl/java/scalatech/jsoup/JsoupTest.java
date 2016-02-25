package pl.java.scalatech.jsoup;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.google.common.base.Strings;
import com.google.common.io.Files;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class JsoupTest {

    
    @Test
    @SneakyThrows
    public void shouldHtmlParser(){
        Document doc = Jsoup.connect("http://jsoup.org").get();

        Element link = doc.select("a").first();
        String relHref = link.attr("href"); // == "/"
        String absHref = link.attr("abs:href"); // "http://jsoup.org/"
       Elements links = doc.select("a[href]");
        
        for(Element l:links){
            System.out.println(l.toString());
        }
        
        log.info("{}",absHref);
    }
    public static String getImageSrcFormHtml(String htmlString,String selector) {
        String srcString = null;
        Document document = Jsoup.parse(htmlString);
        Element element = document.select(selector).first();
        srcString=element.attr("src");
        return srcString;
    }
    @Test
    public void should_retrieve_all_src(){
        Document doc;
        try {
            doc = Jsoup.connect("https://twitter.com/przodownikR1").get();
            Elements imgs = doc.select("img[src]");
            File file = new File(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "");
            for (Element img : imgs) {
                log.info("images : {}",img);
              
               Files.append(img.toString()+"\n", file, Charset.defaultCharset());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
}
