import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Start {
    Get g = new Get();

    Start(String urlToRead, String login, String password) throws IOException {
//        URL url = new URL(urlToRead);
        Map<String,String> cookies = new Login().login(login, password);
        Connection.Response page;
        Document doc;
        if (cookies != null) {
            page = Jsoup.connect(urlToRead)
                    .referrer("https://www.pixiv.net/")
                    .cookies(cookies)
                    .method(Connection.Method.GET)
                    .execute();
            doc = page.parse();
            g.getRecomendation(doc,cookies);
        } else {
            page = Jsoup.connect(urlToRead).execute();
            doc = page.parse();
            g.getUrlNoLogin(doc);
        }
//        System.out.println(doc);
    }

    Start(String urlToRead) throws IOException {
        Connection.Response page;
        Document doc;
        page = Jsoup.connect(urlToRead).execute();
        doc = page.parse();
        g.getUrlNoLogin(doc);

    }
}
