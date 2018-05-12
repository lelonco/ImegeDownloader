import org.jsoup.Jsoup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Администратор on 05.05.18.
 */
public class Downloader {
    public void downloadImage(String urlString,String referrerer, String imageName)
    {
        File image=new File(imageName);
        try {
            byte[] bytes= Jsoup.connect(urlString).referrer(referrerer).ignoreContentType(true).execute().bodyAsBytes();
            saveImage(bytes,image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void saveImage(byte[] imageBuffer, File destination) {
        try {
            OutputStream outputStream=new FileOutputStream(destination);
            outputStream.write(imageBuffer);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
