package hci201.se1171.oceanstudy;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {
    ImageView fish1;
    ImageView fish2;
    ImageView fish3;
    ImageView fish4;

    Context context = this;

    ImageView btnPlayGame;
    TextView txtHuongDan;
    ImageView poiting;

    ImageView boat;

    private ImageView fish_home_1;
    private ImageView fish_home_2;
    private ImageView fish_home_3;
    private ImageView fish_home_4;

    private ImageView fish_home_6;
    private ImageView fish_home_7;

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



    private float fish_home_1_X;
    private float fish_home_1_Y;
    private float fish_home_2_X;
    private float fish_home_2_Y;
    private float fish_home_3_X;
    private float fish_home_3_Y;
    private float fish_home_4_X;
    private float fish_home_4_Y;

    private float fish_home_6_X;
    private float fish_home_6_Y;
    private float fish_home_7_X;
    private float fish_home_7_Y;

    private Handler handler = new Handler();
    private Timer timer = new Timer();
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.start_activity);

//        mp = MediaPlayer.create(getApplicationContext(), R.raw.funny_background);
//        mp.start();


        FishAnimation();
        BubbleAnimation();



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

//        StringBuilder stringBuilder = new StringBuilder("http://192.168.85.2:8080/getAllFish");
//        String url = stringBuilder.toString();
//
//        Object dataTransfer[] = new Object[1];
//        dataTransfer[0] = url;
//
//        GetFishesUseAPI getAllFishes = new GetFishesUseAPI(this);
//        getAllFishes.execute(dataTransfer);

    }

    public void FishAnimation() {
        fish1 =  findViewById(R.id.fish1);
        fish2 =  findViewById(R.id.fish2);
        fish3 =  findViewById(R.id.fish3);
        fish4 =  findViewById(R.id.fish4);

        btnPlayGame = findViewById(R.id.btnPlayGame);
//        txtHuongDan = findViewById(R.id.text_huongdan);
        poiting = findViewById(R.id.pointing);

        fish_home_1 = findViewById(R.id.fish_home_1);
        fish_home_2 =  findViewById(R.id.fish_home_2);
        fish_home_3 = findViewById(R.id.fish_home_3);
        fish_home_4 = findViewById(R.id.fish_home_4);

        fish_home_6 = findViewById(R.id.fish_home_6);
        fish_home_7 = findViewById(R.id.fish_home_7);

        boat = findViewById(R.id.boat);




        TranslateAnimation translateYAnimation = new TranslateAnimation(0f, 0f, 0f, -10f);
        translateYAnimation.setDuration(900);
        translateYAnimation.setRepeatCount(Animation.INFINITE);
        translateYAnimation.setRepeatMode(Animation.REVERSE);
        fish1.setAnimation(translateYAnimation);
        fish2.setAnimation(translateYAnimation);
        fish3.setAnimation(translateYAnimation);
        fish4.setAnimation(translateYAnimation);
        boat.setAnimation(translateYAnimation);



        TranslateAnimation translateYAnimation1 = new TranslateAnimation(0f, 0f, 0f, -10f);
        translateYAnimation1.setDuration(600);
        translateYAnimation1.setRepeatCount(Animation.INFINITE);
        translateYAnimation1.setRepeatMode(Animation.REVERSE);



        Animation scaleAnim = AnimationUtils.loadAnimation(this, R.anim.scale);
        btnPlayGame.setAnimation(scaleAnim);


        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setDuration(8000);


//        poiting.setAnimation(fadeOut);
////        txtHuongDan.setAnimation(fadeOut);
//        poiting.setVisibility(View.INVISIBLE);
////        txtHuongDan.setVisibility(View.INVISIBLE);



        translateYAnimation = new TranslateAnimation(0f, 0f, 0f, -10f);
        translateYAnimation.setDuration(1000);
        translateYAnimation.setRepeatCount(Animation.INFINITE);
        translateYAnimation.setRepeatMode(Animation.REVERSE);
        fish_home_1.setAnimation(translateYAnimation);
        fish_home_2.setAnimation(translateYAnimation);
        fish_home_3.setAnimation(translateYAnimation);
        fish_home_4.setAnimation(translateYAnimation);

        fish_home_6.setAnimation(translateYAnimation);


        WindowManager wm = getWindowManager();
        Display dp = wm.getDefaultDisplay();
        Point size = new Point();
        dp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        fish_home_1.setX(-80.0f);
        fish_home_1.setY(-200.0f);
        fish_home_2.setX(screenWidth + 80.0f);
        fish_home_2.setY(-80.0f);
        fish_home_3.setX(screenWidth + 80.0f);
        fish_home_3.setY(-200.0f);

        fish_home_4.setX(-80.0f);
        fish_home_4.setY(-200.0f);

        fish_home_6.setX(screenWidth + 80.0f);
        fish_home_6.setY(-80.0f);

        fish_home_7.setX(-80.0f);
        fish_home_7.setY(-200.0f);


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePostFish();
                    }
                });
            }
        },0,20);


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        poiting.setVisibility(View.INVISIBLE);
                    }
                });
            }
        },0,20);


    }


    public void BubbleAnimation(){
        bubble1 = findViewById(R.id.bubble1);
        bubble2 = findViewById(R.id.bubble2);
        bubble3 = findViewById(R.id.bubble3);

        WindowManager wm = getWindowManager();
        Display dp = wm.getDefaultDisplay();
        Point size = new Point();
        dp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        bubble1.setX(-80.0f);
        bubble1.setY(-200.0f);
        bubble2.setX(-80.0f);
        bubble2.setY(-200.0f);
        bubble3.setX(-80.0f);
        bubble3.setY(-200.0f);


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePostBubble();
                    }
                });
            }
        },0,20);

    }

    public void changePostBubble(){

        bubble1_Y -= 10;
        if (bubble1.getY() + bubble1.getHeight() < 0){
            bubble1_X = (float)Math.floor(Math.random() * (screenWidth - bubble1.getWidth()));
            bubble1_Y = screenHeight + 200.0f;
        }
        bubble1.setX(bubble1_X);
        bubble1.setY(bubble1_Y);


        bubble2_Y -= 10;
        if (bubble2.getY() + bubble2.getHeight() < 0){
            bubble2_X = (float)Math.floor(Math.random() * (screenWidth - bubble2.getWidth()));
            bubble2_Y = screenHeight + 200.0f;
        }
        bubble2.setX(bubble2_X);
        bubble2.setY(bubble2_Y);

        bubble3_Y -= 10;
        if ( bubble3.getY() +  bubble3.getHeight() < 0){
            bubble3_X = (float)Math.floor(Math.random() * (screenWidth -  bubble3.getWidth()));
            bubble3_Y = screenHeight + 200.0f;
        }
        bubble3.setX( bubble3_X);
        bubble3.setY( bubble3_Y);


    }


    public void changePostFish(){

//        fish_home_1_Y -= 7;
//        if (fish_home_1.getY() + fish_home_1.getHeight() < 0){
//            fish_home_1_X = (float)Math.floor(Math.random() * (screenWidth - fish_home_1.getWidth()));
//            fish_home_1_Y = screenHeight + 100.0f;
//        }
//        fish_home_1.setX(fish_home_1_X);
//        fish_home_1.setY(fish_home_1_Y);


        fish_home_1_X -= 3;
        if (fish_home_1.getX() + fish_home_1.getWidth() < 0){
            fish_home_1_Y = (float)Math.floor(Math.random() * (screenHeight - fish_home_1.getHeight())+ 300.0f);
            fish_home_1_X = screenWidth + 100.0f;
        }
        fish_home_1.setX(fish_home_1_X);
        fish_home_1.setY(fish_home_1_Y);

        fish_home_2_X += 2;
        if (fish_home_2.getX() > screenWidth){
            fish_home_2_X = -100.0f;
            fish_home_2_Y = (float)Math.floor(Math.random() * (screenHeight - fish_home_2.getHeight())+ 300.0f);

        }
        fish_home_2.setX(fish_home_2_X);
        fish_home_2.setY(fish_home_2_Y);


        fish_home_3_X += 5;
        if (fish_home_3.getX() > screenWidth){
            fish_home_3_X = -100.0f;
            fish_home_3_Y = (float)Math.floor(Math.random() * (screenHeight - fish_home_3.getHeight())+ 300.0f);

        }
        fish_home_3.setX(fish_home_3_X);
        fish_home_3.setY(fish_home_3_Y);



        fish_home_4_X -= 1;
        if (fish_home_4.getX() + fish_home_4.getWidth() < 0){
            fish_home_4_Y = (float)Math.floor(Math.random() * (screenHeight - fish_home_4.getHeight() )+ 300.0f);
            fish_home_4_X = screenWidth + 100.0f;
        }
        fish_home_4.setX(fish_home_4_X);
        fish_home_4.setY(fish_home_4_Y);


        fish_home_6_X += 5;
        if (fish_home_6.getX() > screenWidth){
            fish_home_6_X = -100.0f;
            fish_home_6_Y = (float)Math.floor(Math.random() * (screenHeight - fish_home_6.getHeight())  + 300.0f);

        }
        fish_home_6.setX(fish_home_6_X);
        fish_home_6.setY(fish_home_6_Y);

        fish_home_7_X -= 3;
        if (fish_home_7.getX() + fish_home_1.getWidth() < 0){
            fish_home_7_Y = (float)Math.floor(Math.random() * (screenHeight - fish_home_7.getHeight())+ 300.0f);
            fish_home_7_X = screenWidth + 100.0f;
        }
        fish_home_7.setX(fish_home_7_X);
        fish_home_7.setY(fish_home_7_Y);



    }



    public void showFishInfo1(View view) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_fish_dialog);
        dialog.setTitle("Thông tin của cá.");

        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.dialogButtonOK);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mp = MediaPlayer.create(getApplicationContext(), R.raw.button_click_1);
//                mp.start();
                dialog.dismiss();

            }
        });
//        mp = MediaPlayer.create(this, R.raw.button_click_1);
//        mp.start();
        dialog.show();

//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mp = MediaPlayer.create(getApplicationContext(), R.raw.sound_dolphin);
//                        mp.start();
//                    }
//                });
//            }
//        },4);

    }

    public void showFishInfo2(View view) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_fish_dialog1);
        dialog.setTitle("Thông tin của cá.");

        // set the custom dialog components - text, image and button
//        TextView text = (TextView) dialog.findViewById(R.id.text);
//        text.setText("Android custom dialog example!");
//        ImageView image = (ImageView) dialog.findViewById(R.id.image);
//        image.setImageResource(R.drawable.ic_launcher);

        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.dialogButtonOK);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mp = MediaPlayer.create(getApplicationContext(), R.raw.button_click_1);
//                mp.start();
                dialog.dismiss();
            }
        });
//        mp = MediaPlayer.create(this, R.raw.button_click_1);
//        mp.start();
        dialog.show();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mp = MediaPlayer.create(getApplicationContext(), R.raw.sound_shark);
//                        mp.start();
//                    }
//                });
//            }
//        },4);

    }

    public void showFishInfo3(View view) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_fish_dialog2);

        dialog.setTitle("Thông tin của cá.");

        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.dialogButtonOK);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mp = MediaPlayer.create(getApplicationContext(), R.raw.button_click_1);
//                mp.start();
                dialog.dismiss();
            }
        });
//        mp = MediaPlayer.create(this, R.raw.button_click_1);
//        mp.start();
        dialog.show();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mp = MediaPlayer.create(getApplicationContext(), R.raw.sound_seahorse);
//                        mp.start();
//                    }
//                });
//            }
//        },4);

    }

    public void showFishInfo4(View view) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_fish_dialog3);
        dialog.setTitle("Thông tin của cá.");

        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.dialogButtonOK);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mp = MediaPlayer.create(getApplicationContext(), R.raw.button_click_1);
//                mp.start();
                dialog.dismiss();
            }
        });
//        mp = MediaPlayer.create(this, R.raw.button_click_1);
//        mp.start();
        dialog.show();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mp = MediaPlayer.create(getApplicationContext(), R.raw.sound_whale);
//                        mp.start();
//                    }
//                });
//            }
//        },4);


    }

    public void showFishInfo5(View view) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_fish_dialog4);
        dialog.setTitle("Thông tin của cá.");

        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.dialogButtonOK);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mp = MediaPlayer.create(getApplicationContext(), R.raw.button_click_1);
//                mp.start();
                dialog.dismiss();

            }
        });
//        mp = MediaPlayer.create(this, R.raw.button_click_1);
//        mp.start();
        dialog.show();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mp = MediaPlayer.create(getApplicationContext(), R.raw.sound_pufferfish);
//                        mp.start();
//                    }
//                });
//            }
//        },4);

    }

    public void playMusicAgain(){
        Intent intent = new Intent(this, BackgroundMusicService.class);
        startService(intent);
    }

    public void changeToGame(View view) {
//        mp.stop();
//        mp.release();
//        MediaPlayer mp = MediaPlayer.create(this, R.raw.button_click_1);
//        mp.start();

        Intent intent = new Intent(StartActivity.this, GameActivity.class);

        startActivity(intent);
    }



}
