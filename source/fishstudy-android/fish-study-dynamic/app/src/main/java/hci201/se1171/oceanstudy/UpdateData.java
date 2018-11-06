package hci201.se1171.oceanstudy;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import hci201.se1171.oceanstudy.model.Fish;
import pl.droidsonroids.gif.GifImageView;

public class UpdateData extends AppCompatActivity {

    private static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        Log.i("Start", "Start Update Activity");
        ArrayList<Fish> listFish = (ArrayList<Fish>) getIntent().getSerializableExtra("listFish");
        saveInternal(listFish);
        finish();
    }

    public void saveInternal(List<Fish> listFish) {
//        String name = txtName.getText().toString(); //Cập nhật nếu user thay đổi
//        Fish fish = new Fish("1", 1);
        Fish fish = new Fish(1, "Ca Heo", 350.2, 3.5, 5.3, 100, 50,
                "https://www.facebook.com/photo.php?fbid=955630151259555&set=a.103453743143871&type=3&theater",
                "https://www.facebook.com/photo.php?fbid=955630151259555&set=a.103453743143871&type=3&theater",
                "Active");
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        ObjectOutputStream oos = null;

        try {
            fos = openFileOutput("myfile.txt", MODE_PRIVATE);
            osw = new OutputStreamWriter(fos);
            for (int i = 0; i < listFish.size(); i++) {
                osw.write(listFish.get(i).toString());
                if (i != listFish.size()) {
                    osw.write(";");
                }
                osw.write("\n");
                osw.flush();
            }
//            txtName.setText("");
//            txtResult.setText("Save internal success !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Close connection file
            try {
                if (osw != null) {
                    osw.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loadInternal() {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        try {
            fis = openFileInput("myfile.txt");
            isr = new InputStreamReader(fis);
            char[] buffer = new char[READ_BLOCK_SIZE];
            String s = "Value: ";
            int charRead;
            //1,2;1,3;1,4
            List fishList = new ArrayList();
            while ((charRead = isr.read(buffer)) > 0) {
                String reading = String.copyValueOf(buffer, 0, charRead);
                String[] list = reading.split(";");
                for (int i = 0; i < list.length; i++) {
                    String[] fishSplit = list[i].split(",");
//                    fishList.add(new Fish(fishSplit[0], Integer.parseInt(fishSplit[1])));
                }
//                s += reading;
                for (int i = 0; i < fishList.size(); i++) {
                    s += fishList.get(i).toString();
                    Log.i("Fish" + i, fishList.get(i).toString());
                }
                buffer = new char[READ_BLOCK_SIZE];
            }
//            txtName.setText(s);
//            txtResult.setText("Load Internal success !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Close connection file here
            try {
                if (isr != null) {
                    isr.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
