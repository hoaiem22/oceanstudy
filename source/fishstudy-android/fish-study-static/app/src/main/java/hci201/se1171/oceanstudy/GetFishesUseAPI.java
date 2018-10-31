package hci201.se1171.oceanstudy;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetFishesUseAPI extends AsyncTask<Object, String, String> {

    Context context;
    String url;
    InputStream is;
    BufferedReader br;
    StringBuilder sb;

    String data;



    public GetFishesUseAPI(Context context){
        this.context = context;
    }
    @Override
    protected String doInBackground(Object... objects) {
        try{

            url =(String) objects[0];
            URL myUrl = new URL(url);
            HttpURLConnection httpUrlConnection = (HttpURLConnection)myUrl.openConnection();
            httpUrlConnection.connect();
            is = httpUrlConnection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));

            String line = "";
            sb = new StringBuilder();
            while((line = br.readLine()) != null){
                sb.append(line);
            }

            data = sb.toString();

        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }


        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        try{
                JSONArray jsonArray = new JSONArray(s);
                for(int i = 0; i < jsonArray.length() ; i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("id");
                    String name = jsonObject.getString("name");


                }
                Toast.makeText(this.context, jsonArray + " " , Toast.LENGTH_LONG).show();
        }catch(JSONException e){
            e.printStackTrace();
        }

    }
}
