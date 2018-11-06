package hci201.se1171.oceanstudy;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import hci201.se1171.oceanstudy.model.Fish;
import pl.droidsonroids.gif.GifImageView;

public class StartActivity extends AppCompatActivity {

    private static final int READ_BLOCK_SIZE = 5000;

    Button fish1;
    Button fish2;
    Button fish3;
    Button fish4;

    Context context = this;

    private int screenHeight;
    private int screenWidth;

    private ImageView bubble1;
    private ImageView bubble2;
    private ImageView bubble3;

    private float bubble1_X;
    private float bubble1_Y;
    private float bubble2_X;
    private float bubble2_Y;
    private float bubble3_X;
    private float bubble3_Y;


    private Handler handler = new Handler();
    private Timer timer = new Timer();

    private List<Fish> fishShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        //For 3G check
        boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .isConnectedOrConnecting();
        //For WiFi Check
        boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnectedOrConnecting();

        Log.i("3GGGGGG", is3g + " net " + isWifi);
//        fish1 =  findViewById(R.id.fish1);
//        fish2 =  findViewById(R.id.fish2);
//        fish3 =  findViewById(R.id.fish3);
//        fish4 =  findViewById(R.id.fish4);


        bubble1 = findViewById(R.id.bubble1);
        bubble2 = findViewById(R.id.bubble2);
        bubble3 = findViewById(R.id.bubble3);

//        TranslateAnimation translateYAnimation = new TranslateAnimation(0f, 0f, 0f, -15f);
//        translateYAnimation.setDuration(900);
//        translateYAnimation.setRepeatCount(Animation.INFINITE);
//        translateYAnimation.setRepeatMode(Animation.REVERSE);
//        fish1.setAnimation(translateYAnimation);
//        fish2.setAnimation(translateYAnimation);
//        fish3.setAnimation(translateYAnimation);
//        fish4.setAnimation(translateYAnimation);


        WindowManager wm = getWindowManager();
        Display dp = wm.getDefaultDisplay();
        Point size = new Point();
        dp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        bubble1.setX(-80.0f);
        bubble1.setY(-80.0f);
        bubble2.setX(-80.0f);
        bubble2.setY(-80.0f);
        bubble3.setX(-80.0f);
        bubble3.setY(-80.0f);


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
//                        changePostBubble();

                    }
                });
            }
        }, 0, 20);

        if(is3g || isWifi) {
            saveInternal(fetchAPI());
        } else {
            Toast.makeText(getApplicationContext(),"Please make sure your Network Connection is ON to update more Fish",Toast.LENGTH_LONG).show();
        }
        fishShow = loadInternal();
        if (!fishShow.isEmpty()) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutBody);
            for (int i = 0; i < fishShow.size(); i++) {
//                if(!fishShow.get(i).getStatus().equalsIgnoreCase("Disable")){
//                }
                GifImageView imageView = new GifImageView(this.context);
                imageView.setId(fishShow.get(i).getId());
                final String fishName = fishShow.get(i).getName();
                final String video = fishShow.get(i).getVideo();
                GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
                Glide.with(this.context).load(fishShow.get(i).getImg()).into(imageViewTarget);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showFishDialog(fishName, video);
                    }
                });
                linearLayout.addView(imageView, i);
            }
        }
    }

    public List<Fish> fetchAPI() {
        StringBuilder stringBuilder = new StringBuilder("http://192.168.1.44:8090/fish/getListActiveAsc");
        String url = stringBuilder.toString();

        Object dataTransfer[] = new Object[1];
        dataTransfer[0] = url;
        ArrayList<Fish> listFish = new ArrayList<Fish>();
        GetFishesUseAPI getAllFishes = new GetFishesUseAPI(this, this);
        try {
            String s = getAllFishes.execute(dataTransfer).get();

            JSONArray jsonArray = new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.optJSONObject(i);
                int id = Integer.parseInt(jsonObject.getString("id"));
                final String fishName = jsonObject.getString("name");
                final double weight = Double.parseDouble(jsonObject.getString("weight"));
                final double lenght = Double.parseDouble(jsonObject.getString("length"));
                final double height = Double.parseDouble(jsonObject.getString("height"));
                final int deep = Integer.parseInt(jsonObject.getString("deep"));
                final int age = Integer.parseInt(jsonObject.getString("age"));
                String image = jsonObject.getString("img");
                final String video = jsonObject.getString("video");
                final String active = jsonObject.getString("status");
                Fish fish = new Fish(id, fishName, weight, lenght, height, deep, age, image, video, active);
                listFish.add(fish);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listFish;
    }

    public void showFishInfo(View view) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_fish_dialog);
        dialog.setTitle("Thông tin của cá.");

        // set the custom dialog components - text, image and button
//        TextView text = (TextView) dialog.findViewById(R.id.text);
//        text.setText("Android custom dialog example!");
//        ImageView image = (ImageView) dialog.findViewById(R.id.image);
//        image.setImageResource(R.drawable.ic_launcher);

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    public void changePostBubble() {

        bubble1_Y -= 10;
        if (bubble1.getY() + bubble1.getHeight() < 0) {
            bubble1_X = (float) Math.floor(Math.random() * (screenWidth - bubble1.getWidth()));
            bubble1_Y = screenHeight + 100.0f;
        }
        bubble1.setX(bubble1_X);
        bubble1.setY(bubble1_Y);


        bubble2_Y -= 10;
        if (bubble2.getY() + bubble2.getHeight() < 0) {
            bubble2_X = (float) Math.floor(Math.random() * (screenWidth - bubble2.getWidth()));
            bubble2_Y = screenHeight + 100.0f;
        }
        bubble2.setX(bubble2_X);
        bubble2.setY(bubble2_Y);

        bubble3_Y -= 10;
        if (bubble3.getY() + bubble3.getHeight() < 0) {
            bubble3_X = (float) Math.floor(Math.random() * (screenWidth - bubble3.getWidth()));
            bubble3_Y = screenHeight + 100.0f;
        }
        bubble3.setX(bubble3_X);
        bubble3.setY(bubble3_Y);


    }

    public void changeToGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("listFish", (Serializable) fishShow);
        startActivity(intent);
    }

    public void saveInternal(List<Fish> listFish) {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        ObjectOutputStream oos = null;
        try {
            fos = openFileOutput("myfile.txt", MODE_PRIVATE);
            osw = new OutputStreamWriter(fos);
            for (int i = 0; i < listFish.size(); i++) {
                if (listFish.get(i).getStatus().equalsIgnoreCase("Enable")) {
                    osw.write(listFish.get(i).toString());
                    if (i != listFish.size()) {
                        osw.write("\n");
                    }
                    osw.flush();
                }
            }
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

    public List loadInternal() {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        List fishList = new ArrayList();
        try {
            fis = openFileInput("myfile.txt");
            isr = new InputStreamReader(fis);
            char[] buffer = new char[READ_BLOCK_SIZE];
            String s = "Value: ";
            int charRead = 1000000;
            //1,2;1,3;1,4
            int id;
            String name;
            double weight;
            double lenght;
            double height;
            int deep;
            int age;
            String img;
            String video;
            String status;
            while ((charRead = isr.read(buffer)) > 0) {
                String reading = String.copyValueOf(buffer, 0, charRead);
                String[] list = reading.split("\\r?\\n");
                for (int i = 0; i < list.length; i++) {
                    String[] fishSplit = list[i].split(",");
//                    fishList.add(new Fish(fishSplit[0], Integer.parseInt(fishSplit[1])));
                    id = Integer.parseInt(fishSplit[0]);
                    name = fishSplit[1];
                    weight = Double.parseDouble(fishSplit[2]);
                    lenght = Double.parseDouble(fishSplit[3]);
                    height = Double.parseDouble(fishSplit[4]);
                    deep = Integer.parseInt(fishSplit[5]);
                    age = Integer.parseInt(fishSplit[6]);
                    img = fishSplit[7];
                    video = fishSplit[8];
                    status = fishSplit[9];
                    fishList.add(new Fish(id, name, weight, lenght, height, deep, age, img, video, status));
                }
                for (int i = 0; i < fishList.size(); i++) {
                    s += fishList.get(i).toString();
                    Log.i("Fishhhhhhhh" + i, fishList.get(i).toString());
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
        return fishList;
    }

    public void showFishDialog(String name, String video) {
        final Dialog dialog = new Dialog(this.context);
        dialog.setContentView(R.layout.activity_fish_dialog);
        dialog.setTitle("Thông tin của cá.");

        ImageView imageView = dialog.findViewById(R.id.image_fish_info);

        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
        Glide.with(context).load(video).into(imageViewTarget);

        // set the custom dialog components - text, image and button
        TextView fishName = (TextView) dialog.findViewById(R.id.txt_fish_name);
        fishName.setText(name);
        fishName.setTextSize(30);


        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
