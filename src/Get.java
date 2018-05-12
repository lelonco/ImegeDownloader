import org.eclipse.jetty.server.Response;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.management.StringValueExp;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Администратор on 04.05.18.
 */
public class Get {
    public void getUrlNoLogin(Document doc) throws IOException {
        File output = new File("output.txt");
        FileWriter writer = new FileWriter(output, false);
        Elements content = doc.getElementsByClass("img-container");
        String imageUrl= "";
        String nameImage= "";
//        System.out.println(content);
        writer.write(String.valueOf(doc.getAllElements()));
        for (Element link : content) {
            nameImage=makeNameNologin(link);
            System.out.println("NameImage:"+nameImage);
            imageUrl=String.valueOf(link.getElementsByAttribute("src")).split("\"")[1];
//            System.out.println(imageUrl);
        }
//        new Downloader().downloadImage(imageUrl,urlToRead,nameImage );
        writer.flush();
        writer.close();
    }
    public void getUrlLogin(Document doc) throws IOException {
        File output = new File("output.txt");
        FileWriter writer = new FileWriter(output, false);
        Elements content = doc.getElementsByClass("_illust_modal _hidden ui-modal-close-box");
        String imageUrl= "";
        String nameImage= "";
//        System.out.println(content);
        writer.write(String.valueOf(doc.getAllElements()));
        for (Element link : content) {
            nameImage=makeNameLogin(link);
            System.out.println("NameImage:"+nameImage);
            imageUrl=String.valueOf(link.getElementsByAttribute("data-src").attr("data-src"));
//            System.out.println(imageUrl);
        }
//        new Downloader().downloadImage(imageUrl,urlToRead,nameImage );
        writer.flush();
        writer.close();
    }
    public void getRecomendation(Document doc,Map<String,String> cookies)
    {
        String clearUrl="https://www.pixiv.net";
        Elements recomendation=doc.getElementsByClass("illust-recommend");
        for(Element el:recomendation) {
            System.out.println(el);
        }

    }
    private String makeNameNologin(Element source)
    {
        String name;
        name=source.getElementsByAttribute("src").attr("title");
        name=name.replaceAll("[\\*?<>|+:@%]","");
        return name+".jpg";
    }
    private String makeNameLogin(Element source)
    {
        String name;
        name= source.getElementsByAttribute("alt").attr("alt");
        name=name.replaceAll("[\\*?<>|+:@%]","");
        return name+".jpg";
    }
}


