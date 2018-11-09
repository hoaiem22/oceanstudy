package hci201.se1171.oceanstudy;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import hci201.se1171.oceanstudy.model.Fish;

public class GameActivity extends AppCompatActivity {

    List<Fish> listFish = new ArrayList<>();
    ImageView imgGame_fish;
    Button btn_answer1, btn_answer2, btn_answer3, btn_answer4;
    Random r = new Random();
    int turn = 1;
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private MediaPlayer mp;
    private MediaPlayer mpButtonClick;
    private MediaPlayer mpSoundCorrect;
    private MediaPlayer mpSoundHooray;
    private MediaPlayer mpSoundOh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        setContentView(R.layout.activity_game);
        //Set up sound in Game
        createSound();
        mp.start();
        listFish = (List) getIntent().getSerializableExtra("listFish");
        if (listFish.size() < 3) {
            Toast.makeText(getApplicationContext(), "Sorry Opp, you have not enough fish to start game." +
                    "Please open 3G or Wifi to update fish.", Toast.LENGTH_LONG).show();
        } else {
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


            // Shuffle list
            Collections.shuffle(listFish);
            newQuestion(turn);

            btn_answer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mpButtonClick.start();
                    if (btn_answer1.getText().toString().equalsIgnoreCase(listFish.get(turn - 1).getName())) {
                        mpSoundHooray.start();
                        mpSoundCorrect.start();
                        setCorrect();
                    } else {
                        mpSoundOh.start();
                        setWrong(listFish.get(turn - 1).getName());
                    }
                }
            });
            btn_answer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mpButtonClick.start();
                    if (btn_answer2.getText().toString().equalsIgnoreCase(listFish.get(turn - 1).getName())) {
                        mpSoundHooray.start();
                        mpSoundCorrect.start();
                        setCorrect();
                    } else {
                        mpSoundOh.start();
                        setWrong(listFish.get(turn - 1).getName());
                    }
                }
            });


            btn_answer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mpButtonClick.start();
                    if (btn_answer3.getText().toString().equalsIgnoreCase(listFish.get(turn - 1).getName())) {
                        mpSoundHooray.start();
                        mpSoundCorrect.start();
                        setCorrect();
                    } else {
                        mpSoundOh.start();
                        setWrong(listFish.get(turn - 1).getName());
                    }
                }
            });


            btn_answer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mpButtonClick.start();
                    if (btn_answer4.getText().toString().equalsIgnoreCase(listFish.get(turn - 1).getName())) {
                        mpSoundHooray.start();
                        mpSoundCorrect.start();
                        setCorrect();
                    } else {
                        mpSoundOh.start();
                        setWrong(listFish.get(turn - 1).getName());
                    }
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopPlaying(mp);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mpButtonClick.start();
        
    }

    public void createSound() {
        mp = MediaPlayer.create(this, R.raw.musicingame);
        mpButtonClick = MediaPlayer.create(this, R.raw.button_click_1);
        mpSoundCorrect = MediaPlayer.create(getApplicationContext(), R.raw.correct);
        mpSoundHooray = MediaPlayer.create(getApplicationContext(), R.raw.hooray);
        mpSoundOh = MediaPlayer.create(getApplicationContext(), R.raw.oh);
    }

    public void setCorrect() {
        final TextView fish_asking_text = (TextView) findViewById(R.id.fish_asking_text);
        imgGame_fish.setImageResource(R.drawable.correctdancing);
//        mpSoundHooray.start();
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
                        if (turn < listFish.size()) {
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
                        } else {
                            openDoneAskingDialog();
                        }
                    }
                });
            }
        }, 3000);
    }

    public void setWrong(String name) {
        final TextView fish_asking_text = (TextView) findViewById(R.id.fish_asking_text);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.gameHeaderImage);
        imgGame_fish.setImageResource(R.drawable.wrong_spong);
        imgGame_fish.getLayoutParams().height = 300;
        imgGame_fish.getLayoutParams().width = 300;
        imgGame_fish.requestLayout();
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
        linearLayout.setPadding(100, 100, 0, 0);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (turn < listFish.size()) {
                            turn++;
                            btn_answer1.setVisibility(View.VISIBLE);
                            btn_answer2.setVisibility(View.VISIBLE);
                            btn_answer3.setVisibility(View.VISIBLE);
                            btn_answer4.setVisibility(View.VISIBLE);
                            fish_asking_text.setText("Đây là con gì?");
                            imgGame_fish.getLayoutParams().height = LinearLayout.LayoutParams.MATCH_PARENT;
                            imgGame_fish.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
                            linearLayout.setPadding(0, 0, 0, 0);
                            Animation scaleAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
                            btn_answer1.setAnimation(scaleAnim);
                            btn_answer2.setAnimation(scaleAnim);
                            btn_answer3.setAnimation(scaleAnim);
                            btn_answer4.setAnimation(scaleAnim);
                            newQuestion(turn);
                        } else {
                            openDoneAskingDialog();
                        }
                    }
                });
            }
        }, 3000);
    }

    public void openDoneAskingDialog() {
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
                mpButtonClick.start();
                Intent intent = new Intent(GameActivity.this, StartActivity.class);
                startActivity(intent);
                stopPlaying(mp);
            }
        });

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                mpButtonClick.start();
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
        mpButtonClick.start();
        dialog.show();
    }

    public void openWrongAskingDialog(String fishName, int image) {
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


    public void newQuestion(int number) {
        //set flag image to screen
//        imgGame_fish.setImageURI(Uri.parse(listFish.get(number - 1).getImg()));
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imgGame_fish);
        Glide.with(getApplicationContext()).load(listFish.get(number - 1).getImg()).into(imageViewTarget);
        int correct_answer = r.nextInt(4) + 1;
        int firstButton = number - 1;
        int secondButton;
        int thirdButton;
        int forthButton;
        switch (correct_answer) {
            case 1:
                btn_answer1.setText(listFish.get(firstButton).getName());

                do {
                    secondButton = r.nextInt(listFish.size());
                } while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(listFish.size());
                } while (thirdButton == firstButton || thirdButton == secondButton);
                do {
                    forthButton = r.nextInt(listFish.size());
                }
                while (forthButton == firstButton || forthButton == secondButton || forthButton == thirdButton);

                btn_answer2.setText(listFish.get(secondButton).getName());
                btn_answer3.setText(listFish.get(thirdButton).getName());
                btn_answer4.setText(listFish.get(forthButton).getName());


                break;
            case 2:
                btn_answer2.setText(listFish.get(firstButton).getName());

                do {
                    secondButton = r.nextInt(listFish.size());
                } while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(listFish.size());
                } while (thirdButton == firstButton || thirdButton == secondButton);
                do {
                    forthButton = r.nextInt(listFish.size());
                }
                while (forthButton == firstButton || forthButton == secondButton || forthButton == thirdButton);

                btn_answer1.setText(listFish.get(secondButton).getName());
                btn_answer3.setText(listFish.get(thirdButton).getName());
                btn_answer4.setText(listFish.get(forthButton).getName());
                break;
            case 3:
                btn_answer3.setText(listFish.get(firstButton).getName());

                do {
                    secondButton = r.nextInt(listFish.size());
                } while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(listFish.size());
                } while (thirdButton == firstButton || thirdButton == secondButton);
                do {
                    forthButton = r.nextInt(listFish.size());
                }
                while (forthButton == firstButton || forthButton == secondButton || forthButton == thirdButton);

                btn_answer2.setText(listFish.get(secondButton).getName());
                btn_answer1.setText(listFish.get(thirdButton).getName());
                btn_answer4.setText(listFish.get(forthButton).getName());
                break;
            case 4:
                btn_answer4.setText(listFish.get(firstButton).getName());

                do {
                    secondButton = r.nextInt(listFish.size());
                } while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(listFish.size());
                } while (thirdButton == firstButton || thirdButton == secondButton);
                do {
                    forthButton = r.nextInt(listFish.size());
                }
                while (forthButton == firstButton || forthButton == secondButton || forthButton == thirdButton);

                btn_answer2.setText(listFish.get(secondButton).getName());
                btn_answer3.setText(listFish.get(thirdButton).getName());
                btn_answer1.setText(listFish.get(forthButton).getName());
                break;
        }
    }

    private void stopPlaying(MediaPlayer mp) {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
