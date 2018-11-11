package hci201.se1171.oceanstudy;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

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

//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
//import com.bumptech.glide.signature.StringSignature;

public class StartActivity extends AppCompatActivity {

    private static String url = "http://192.168.1.75:8090/fish/getListActiveAsc";
    private static final int READ_BLOCK_SIZE = 5000;
    Context context = this;
    ImageView poiting;
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
    private List<Fish> fishShow; //List of Fish to show on layout
    private MediaPlayer mp;
    private MediaPlayer mpButtonClick;
    private boolean is3g = false;
    private boolean isWifi = false;
    private LinearLayout layoutBody;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        poiting = findViewById(R.id.pointing);
        createSound();
        mp.start();

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


        AnimationSet set = new AnimationSet(true);
        Animation trAnimation = new TranslateAnimation(0, 10, 0, 0);
        trAnimation.setDuration(400);

        trAnimation.setRepeatMode(Animation.REVERSE); /*---------> This will make the view translate in the reverse direction*/
        trAnimation.setRepeatCount(20);
        set.addAnimation(trAnimation);
        Animation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(10000);
        set.addAnimation(anim);

        poiting.startAnimation(set);


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        changePostBubble();
//
//                    }
//                });
            }
        }, 0, 20);

        //Check if your device have 3g or wifi,
        //Then update data from server and save to internal
        if (isInternet()) {
            saveInternal(fetchAPI());
        } else {
            Toast.makeText(getApplicationContext(), "Please make sure your Network Connection is ON to update more Fish", Toast.LENGTH_LONG).show();
        }
        fishShow = loadInternal();
        layoutBody = (LinearLayout) findViewById(R.id.layoutBody);
        //If list fish is empty, then add static data and reload layout by resource
        // Else load data from text file with img, video is loaded from internet
        if (fishShow.isEmpty()) {
            saveInternalStatic();
            fishShow = loadInternal();
            //Load data to layout
        }
        loadToLayout(layoutBody, fishShow);
    }

    @Override
    protected void onResume() {
        super.onResume();
        createSound();
        mp.start();
    }

    public boolean isInternet() {
        //Return true if have 3G or wifi
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        //For 3G check
        is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .isConnectedOrConnecting();
        //For WiFi Check
        isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnectedOrConnecting();
        if (is3g || isWifi)
            return true;
        return false;
    }

    public void loadToLayout(LinearLayout linearLayout, final List<Fish> fishShow) {


        for (int i = 0; i < fishShow.size(); i++) {
            GifImageView imageView = new GifImageView(StartActivity.this);
            imageView.setId(fishShow.get(i).getId());
            final Fish fish = fishShow.get(i);
            final String fishName = fishShow.get(i).getName();
            final String img = fishShow.get(i).getImg();
            final String video = fishShow.get(i).getVideo();
            try {
                int posImg = Integer.parseInt(img);
                imageView.setBackgroundResource(posImg);
            } catch (Exception e) {
//                GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
                Glide.with(this).load(img)
                        .into(imageView).clearOnDetach();

            }
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mpButtonClick.start();
                    showFishDialog(fish);
                }
            });
            linearLayout.addView(imageView, i);

        }
    }

    public List<Fish> fetchAPI() {
        StringBuilder stringBuilder = new StringBuilder(url);
        String url = stringBuilder.toString();
        Object dataTransfer[] = new Object[1];
        dataTransfer[0] = url;
        ArrayList<Fish> listFish = new ArrayList<Fish>();
        GetFishesUseAPI getAllFishes = new GetFishesUseAPI(this, this);
        try {
            String s = getAllFishes.execute(dataTransfer).get();
            if (!s.equals("Could not connect to server")) {
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
        //If list fish have more than 4 fish, then change to Game Activity

        //If list fish have less than 4 fish then check 3G or Wifi
        //If your device has 3G or wifi, then update from server
        //else make Toast
        mpButtonClick.start();
        if (fishShow.size() > 3) {
            if (mp != null) {
                stopPlaying(mp);
            }
            Intent intent = new Intent(StartActivity.this, GameActivity.class);
            intent.putExtra("listFish", (Serializable) fishShow);
            startActivity(intent);
        } else {
            if (isInternet()) {
                saveInternal(fetchAPI());

                fishShow = loadInternal();
                layoutBody.removeAllViews();
                loadToLayout(layoutBody, fishShow);
                //Notify fish update
                Toast.makeText(getApplicationContext(), "Fish has been update", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Oops, you don't have enough fish to start game." +
                        "Please open 3G or Wifi to update fishes.", Toast.LENGTH_LONG).show();
            }
        }
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

    public void showFishDialog(Fish fish) {
        final Dialog dialog = new Dialog(this.context);
        dialog.setContentView(R.layout.activity_fish_dialog);
        dialog.setTitle("Thông tin của cá");
        ImageView imageView = dialog.findViewById(R.id.image_fish_info);
        try {
            int posVideo = Integer.parseInt(fish.getVideo());
            imageView.setBackgroundResource(posVideo);
        } catch (Exception e) {
//            GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
            Glide.with(this).load(fish.getVideo()).into(imageView);
        }

        // set the custom dialog components - text, image and buttonImageView
        TextView fishName = (TextView) dialog.findViewById(R.id.txt_fish_name);
        TextView fishWeight = (TextView) dialog.findViewById(R.id.txtWeight);
        TextView fishLength = (TextView) dialog.findViewById(R.id.txtLength);
//        TextView fishHeight = (TextView) dialog.findViewById(R.id.txtHeight);
        TextView fishDeep = (TextView) dialog.findViewById(R.id.txtDeep);
        TextView fishAge = (TextView) dialog.findViewById(R.id.txtAge);

        fishName.setText("Tên tiếng anh: " + fish.getName());
        fishName.setTextSize(30);
        fishWeight.setText("Cân nặng: " + String.valueOf(fish.getWeight()) + "(kg)");
        fishWeight.setTextSize(20);
        fishLength.setText("Chiều dài: " + String.valueOf(fish.getLenght()) + "(m)");
        fishLength.setTextSize(20);
//        fishHeight.setText("Height:" + String.valueOf(fish.getHeight()));
//        fishHeight.setTextSize(30);
        fishDeep.setText("Độ sâu: " + String.valueOf(fish.getDeep()) + "(m)");
        fishDeep.setTextSize(20);
        fishAge.setText("Tuổi thọ: " + String.valueOf(fish.getAge()) + "(năm)");
        fishAge.setTextSize(20);


        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpButtonClick.start();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void stopPlaying(MediaPlayer mp) {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    private void saveInternalStatic() {
        List<Fish> fishList = new ArrayList<>();
        fishList.add(new Fish(1, "Dolphin", 1, 1, 1, 1, 1, String.valueOf(R.drawable.dolphin), String.valueOf(R.drawable.dolphin_info), "Enable"));
        fishList.add(new Fish(1, "Shark", 1, 1, 1, 2, 1, String.valueOf(R.drawable.shark), String.valueOf(R.drawable.shark_info), "Enable"));

        saveInternal(fishList);
    }

    public void createSound() {
        mp = MediaPlayer.create(this, R.raw.musicingame);
        mpButtonClick = MediaPlayer.create(this, R.raw.button_click_1);
    }
}
