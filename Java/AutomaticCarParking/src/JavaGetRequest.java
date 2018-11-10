import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
public class JavaGetRequest {
    private static HttpURLConnection con;

    public static boolean func() throws MalformedURLException,
            ProtocolException, IOException {

        String url = "http://automaticcarparking.000webhostapp.com/getunsed.php";

        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("GET");

            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            System.out.println(content.toString());
            if(content.toString().contains("true"))
            return true;
            else return false;

        } finally {
            
            con.disconnect();
        }
    }
}
