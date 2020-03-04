package connection;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIconnection {

    protected transient JSONObject file;
    protected transient final String basePath = "https://www.dnd5eapi.co";

    public APIconnection(){

    }

    public APIconnection(String path) throws IOException, JSONException {

        URL urlForRequest = new URL(this.basePath + path);

        HttpURLConnection connection = (HttpURLConnection) urlForRequest.openConnection();
        connection.setRequestMethod("GET");

        int validation = connection.getResponseCode();

        if(validation == HttpURLConnection.HTTP_OK){
            InputStream in = new BufferedInputStream(connection.getInputStream());

            String s = readStream(in);

            file = new JSONObject(s);

        }else{
            file = new JSONObject("{\n" +
                    "    \"error\": \"Not found\"\n" +
                    "}");
        }
    }

    private String readStream(InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(in),100000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        in.close();
        return sb.toString();
    }

    public JSONObject getFile() {
        return file;
    }
}
