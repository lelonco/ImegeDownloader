import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Администратор on 05.05.18.
 */
public class Login {
    public Map<String,String> login(String eMail, String password) {
        Connection.Response mainForm = null;
        Connection.Response homePage = null;
        try {
            Connection.Response loginForm = Jsoup.connect("https://accounts.pixiv.net/login?lang=en&source=pc&view_type=page&ref=wwwtop_accounts_index").method(Connection.Method.GET).execute();
            Document loginDoc = loginForm.parse();
            HashMap<String, String> formData = new HashMap<>();
            HashMap<String, String> cookies = new HashMap<>();
            String authToken = loginDoc.select("#old-login > form > input[type=\"hidden\"]:nth-child(1)")
                    .first()
                    .attr("value");
            formData.put("pixiv_id", eMail);
            formData.put("captcha", "");
            formData.put("g_recaptcha_response", "");
            formData.put("password", password);
            formData.put("post_key", authToken);
            formData.put("source", "pc");
            formData.put("ref", "wwwtop_accounts_index");
            formData.put("return_to", "https://www.pixiv.net/");
            cookies.putAll(loginForm.cookies());
            homePage = Jsoup.connect("https://accounts.pixiv.net/api/login?lang=en")
                    .cookies(cookies)
                    .data(formData)
                    .method(Connection.Method.POST)
                    .ignoreContentType(true)
                    .execute();
            Document loginPage = homePage.parse();
            String body = loginPage.body().text();
          /*  String re1 = ".*?";    // Non-greedy match on filler
            String re2 = "(\"Please check if your pixiv ID or email address was entered correctly\\.\")";    // Double Quote String 1
            String re3 = "(\"You\\\\u0027ve entered incorrect passwords too many times\\. Your account has been temporarily locked\\. Please try again later\\. \")";    // Double Quote String 1
            String re4 = ".*?";
            String re5 = ".*?";
            String re6 = "(\"Authenticate your profile after keying in your pixiv ID, e-mail address or password\\.\")";    // Double Quote String 1
            Pattern p = Pattern.compile(re1 + re2 + re4 + re3 + re4 + re5 + re6, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher m = p.matcher(body);*/
//            System.out.println(body);
            if (body.contains("validation_errors")) {
                System.out.println("Login false");
                return null;
            }
//            System.out.println(loginPage.body().text());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return homePage.cookies();
    }
}
