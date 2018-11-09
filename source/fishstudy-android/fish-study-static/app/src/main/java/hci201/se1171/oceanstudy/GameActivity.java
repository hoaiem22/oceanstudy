package hci201.se1171.oceanstudy;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    ImageView imgGame_fish;
    Button btn_answer1,btn_answer2,btn_answer3,btn_answer4;

    List<FishItem> list;

    Random r = new Random();
    int turn = 1;

    private Handler handler = new Handler();
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        imgGame_fish = findViewById(R.id.gameImage);

        btn_answer1 = findViewById(R.id.answerA);
        btn_answer2 = findViewById(R.id.answerB);
        btn_answer3 = findViewById(R.id.answerC);
        btn_answer4 = findViewById(R.id.answerD);


        Animation scaleAnim = AnimationUtils.loadAnimation(this, R.anim.scale);
        btn_answer1.setAnimation(scaleAnim);
        btn_answer2.setAnimation(scaleAnim);
        btn_answer3.setAnimation(scaleAnim);
        btn_answer4.setAnimation(scaleAnim);

        MediaPlayer mp = MediaPlayer.create(this, R.raw.musicingame);
        mp.start();

        list = new ArrayList<>();
        //Add all fishes to list;
        for(int i = 0; i< new QuizData().answers.length ; i++){
            list.add(new FishItem(new QuizData().answers[i], new QuizData().fishes[i]) );

        }

        // Shuffle list
        Collections.shuffle(list);
        newQuestion(turn);

        btn_answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_answer1.getText().toString().equalsIgnoreCase(list.get(turn - 1).getName())){
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                    mp.start();
                    setCorrect();

//                    new Timer().schedule(new TimerTask() {
//                        @Override
//                        public void run() {
//                            // this code will be executed after 2 seconds
//
//                        }
//                    }, 2000);




                }else{
//                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
//                    mp.start();
                    setWrong(list.get(turn - 1).getName());
//                    openWrongAskingDialog(list.get(turn - 1).getName(), list.get(turn - 1).getImage());
                }
            }
        });

        btn_answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_answer2.getText().toString().equalsIgnoreCase(list.get(turn - 1).getName())){
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                    mp.start();


                    setCorrect();



                }else{
//                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
//                    mp.start();
                    setWrong(list.get(turn - 1).getName());
//                    openWrongAskingDialog(list.get(turn - 1).getName(), list.get(turn - 1).getImage());
                }
            }
        });


        btn_answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_answer3.getText().toString().equalsIgnoreCase(list.get(turn - 1).getName())){
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                    mp.start();


                    setCorrect();


                }else{
//                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
//                    mp.start();
//                    openWrongAskingDialog(list.get(turn - 1).getName(), list.get(turn - 1).getImage());
                    setWrong(list.get(turn - 1).getName());
                }
            }
        });


        btn_answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_answer4.getText().toString().equalsIgnoreCase(list.get(turn - 1).getName())){
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                    mp.start();

                   setCorrect();

                }else{
//                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
//                    mp.start();
                    setWrong(list.get(turn - 1).getName());
//                    openWrongAskingDialog(list.get(turn - 1).getName(), list.get(turn - 1).getImage());
                }
            }
        });


    }



    public void setCorrect(){
        final TextView fish_asking_text = (TextView) findViewById(R.id.fish_asking_text);
        imgGame_fish.setImageResource(R.drawable.correctdancing);

        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.hooray);


        mp.start();
        btn_answer1.setVisibility(View.GONE);
        btn_answer2.setVisibility(View.GONE);
        btn_answer3.setVisibility(View.GONE);
        btn_answer4.setVisibility(View.GONE);
        fish_asking_text.setText("Đúng rồi !!!");
        fish_asking_text.setTextSize(50);

        btn_answer1.clearAnimation();
        btn_answer2.clearAnimation();
        btn_answer3.clearAnimation();
        btn_answer4.clearAnimation();



        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(turn < list.size()){
                            turn++;
                            btn_answer1.setVisibility(View.VISIBLE);
                            btn_answer2.setVisibility(View.VISIBLE);
                            btn_answer3.setVisibility(View.VISIBLE);
                            btn_answer4.setVisibility(View.VISIBLE);
                            fish_asking_text.setText("Đây là con gì?");

                            Animation scaleAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
                            btn_answer1.setAnimation(scaleAnim);
                            btn_answer2.setAnimation(scaleAnim);
                            btn_answer3.setAnimation(scaleAnim);
                            btn_answer4.setAnimation(scaleAnim);
                            newQuestion(turn);
                        }else{
                            openDoneAskingDialog();

                        }

                    }
                });
            }
        },3000);
    }

    public void setWrong(String name){
        final TextView fish_asking_text = (TextView) findViewById(R.id.fish_asking_text);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.gameHeaderImage);
        imgGame_fish.setImageResource(R.drawable.wrong_spong);
        imgGame_fish.getLayoutParams().height = 300;
        imgGame_fish.getLayoutParams().width = 300;
        imgGame_fish.requestLayout();
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.oh);


        mp.start();
        btn_answer1.setVisibility(View.GONE);
        btn_answer2.setVisibility(View.GONE);
        btn_answer3.setVisibility(View.GONE);
        btn_answer4.setVisibility(View.GONE);
        fish_asking_text.setText("Sai mất rồi !!!\n Là: " + name.toUpperCase());
        fish_asking_text.setTextSize(50);

        btn_answer1.clearAnimation();
        btn_answer2.clearAnimation();
        btn_answer3.clearAnimation();
        btn_answer4.clearAnimation();


        linearLayout.setPadding(100,100,0,0);



        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(turn < list.size()){
                            turn++;
                            btn_answer1.setVisibility(View.VISIBLE);
                            btn_answer2.setVisibility(View.VISIBLE);
                            btn_answer3.setVisibility(View.VISIBLE);
                            btn_answer4.setVisibility(View.VISIBLE);
                            fish_asking_text.setText("Đây là con gì?");
                            imgGame_fish.getLayoutParams().height = LinearLayout.LayoutParams.MATCH_PARENT;
                            imgGame_fish.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
                            linearLayout.setPadding(0,0,0,0);

                            Animation scaleAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
                            btn_answer1.setAnimation(scaleAnim);
                            btn_answer2.setAnimation(scaleAnim);
                            btn_answer3.setAnimation(scaleAnim);
                            btn_answer4.setAnimation(scaleAnim);
                            newQuestion(turn);
                        }else{
                            openDoneAskingDialog();

                        }

                    }
                });
            }
        },3000);
    }




    public void openDoneAskingDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_ask_user);
        final TextView fish_asking_text = (TextView) findViewById(R.id.fish_asking_text);
        ImageView btn_no = (ImageView) dialog.findViewById(R.id.btnNo);
        ImageView btn_yes = (ImageView) dialog.findViewById(R.id.btnYes);
        TextView txt_right = (TextView) dialog.findViewById(R.id.txtRightAnswer);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.askingFish);
        txt_right.setText("Bạn đã trả lời hết câu hỏi!");
        imageView.setImageResource(R.drawable.correctdancing);
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent1 = new Intent(GameActivity.this, BackgroundMusicService.class);
                stopService(intent1);

                Intent intent2 = new Intent(GameActivity.this, BackgroundMusicService.class);
                startService(intent2);
                Intent intent = new Intent(GameActivity.this, StartActivity.class);

                startActivity(intent);

            }
        });

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
//                Intent intent = new Intent(GameActivity.this, GameActivity.class);
//                startActivity(intent);
                btn_answer1.setVisibility(View.VISIBLE);
                btn_answer2.setVisibility(View.VISIBLE);
                btn_answer3.setVisibility(View.VISIBLE);
                btn_answer4.setVisibility(View.VISIBLE);
                fish_asking_text.setText("Đây là cá gì?");
                turn = 1;
                newQuestion(turn);

            }
        });

        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
                Intent intent = new Intent(GameActivity.this, StartActivity.class);

                startActivity(intent);
            }
        });


        MediaPlayer mp = MediaPlayer.create(this, R.raw.button_click_1);
        mp.start();
        dialog.show();
    }


    public void openWrongAskingDialog(String fishName, int image){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_ask_user);

        Button btn_no = (Button) dialog.findViewById(R.id.btnNo);
        Button btn_yes = (Button) dialog.findViewById(R.id.btnYes);
        TextView txt_right = (TextView) dialog.findViewById(R.id.txtRightAnswer);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.askingFish);

        txt_right.setText("Đáp án đúng: " + fishName);
        imageView.setImageResource(image);
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(GameActivity.this, StartActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                newQuestion(turn);
//                Intent intent = new Intent(GameActivity.this, GameActivity.class);
//
//                startActivity(intent);

            }
        });

        MediaPlayer mp = MediaPlayer.create(this, R.raw.button_click_1);
        mp.start();
        dialog.show();
    }


    public void newQuestion(int number){
        //set flag image to screen

        imgGame_fish.setImageResource(list.get(number - 1).getImage());
        int correct_answer = r.nextInt(4) + 1;

        int firstButton = number - 1;
        int secondButton;
        int thirdButton;
        int forthButton;

        switch (correct_answer){
            case 1:
                btn_answer1.setText(list.get(firstButton).getName());

                do{
                    secondButton = r.nextInt(list.size());
                }while (secondButton == firstButton);
                do{
                    thirdButton = r.nextInt(list.size());
                }while (thirdButton == firstButton || thirdButton == secondButton);
                do{
                    forthButton = r.nextInt(list.size());
                }while (forthButton == firstButton || forthButton == secondButton || forthButton == thirdButton);

                btn_answer2.setText(list.get(secondButton).getName());
                btn_answer3.setText(list.get(thirdButton).getName());
                btn_answer4.setText(list.get(forthButton).getName());


                break;
            case 2:
                btn_answer2.setText(list.get(firstButton).getName());

                do{
                    secondButton = r.nextInt(list.size());
                }while (secondButton == firstButton);
                do{
                    thirdButton = r.nextInt(list.size());
                }while (thirdButton == firstButton || thirdButton == secondButton);
                do{
                    forthButton = r.nextInt(list.size());
                }while (forthButton == firstButton || forthButton == secondButton || forthButton == thirdButton);

                btn_answer1.setText(list.get(secondButton).getName());
                btn_answer3.setText(list.get(thirdButton).getName());
                btn_answer4.setText(list.get(forthButton).getName());
                break;
            case 3:
                btn_answer3.setText(list.get(firstButton).getName());

                do{
                    secondButton = r.nextInt(list.size());
                }while (secondButton == firstButton);
                do{
                    thirdButton = r.nextInt(list.size());
                }while (thirdButton == firstButton || thirdButton == secondButton);
                do{
                    forthButton = r.nextInt(list.size());
                }while (forthButton == firstButton || forthButton == secondButton || forthButton == thirdButton);

                btn_answer2.setText(list.get(secondButton).getName());
                btn_answer1.setText(list.get(thirdButton).getName());
                btn_answer4.setText(list.get(forthButton).getName());
                break;
            case 4:
                btn_answer4.setText(list.get(firstButton).getName());

                do{
                    secondButton = r.nextInt(list.size());
                }while (secondButton == firstButton);
                do{
                    thirdButton = r.nextInt(list.size());
                }while (thirdButton == firstButton || thirdButton == secondButton);
                do{
                    forthButton = r.nextInt(list.size());
                }while (forthButton == firstButton || forthButton == secondButton || forthButton == thirdButton);

                btn_answer2.setText(list.get(secondButton).getName());
                btn_answer3.setText(list.get(thirdButton).getName());
                btn_answer1.setText(list.get(forthButton).getName());
                break;
        }
    }

//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(GameActivity.this, StartActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//    }
}
