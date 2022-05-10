package us.zoom.sdksample;

import android.os.StrictMode;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Constants {

    // TODO Change it to your web domain
    public final static String WEB_DOMAIN = "zoom.us";


    public	static String TOKEN(String sessionName ){
        // Create URL
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try{
            String url = "https://yoururldomain.com/video/";
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            conn.connect();

            String _sessionName=sessionName;
            if (_sessionName==null) _sessionName="herochun6871";





            JSONObject jsonParam = new JSONObject();
            jsonParam.put("sessionName", _sessionName);
            jsonParam.put("role", 1);
            jsonParam.put("user_identity", "user_identity6871");
            jsonParam.put("session_key", "session_key6871");



            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
            wr.writeBytes(jsonParam.toString());

            wr.flush();
            wr.close();

            try {
                InputStream in = new BufferedInputStream(conn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                JSONObject obj = new JSONObject(result.toString());
                return obj.getString("signature");

            } catch (IOException e) {
                e.printStackTrace();
                return "meow";
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            return "meow";
        }



    }
}
