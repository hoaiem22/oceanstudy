package hci201.se1171.oceanstudy;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {


    ImageView imgGame_fish;
    Button btn_answer1,btn_answer2,btn_answer3,btn_answer4;

    List<FishItem> list;

    Random r = new Random();
    int turn = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        imgGame_fish = findViewById(R.id.gameImage);

        btn_answer1 = findViewById(R.id.answerA);
        btn_answer2 = findViewById(R.id.answerB);
        btn_answer3 = findViewById(R.id.answerC);
        btn_answer4 = findViewById(R.id.answerD);


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


                    if(turn < list.size()){
                        turn++;
                        newQuestion(turn);
                    }else{
                        openDoneAskingDialog();

                    }

                }else{
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
                    mp.start();
                    openWrongAskingDialog(list.get(turn - 1).getName(), list.get(turn - 1).getImage());
                }
            }
        });

        btn_answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_answer2.getText().toString().equalsIgnoreCase(list.get(turn - 1).getName())){
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                    mp.start();


                    if(turn < list.size()){
                        turn++;
                        newQuestion(turn);
                    }else{
                        openDoneAskingDialog();

                    }

                }else{
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
                    mp.start();
                    openWrongAskingDialog(list.get(turn - 1).getName(), list.get(turn - 1).getImage());
                }
            }
        });


        btn_answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_answer3.getText().toString().equalsIgnoreCase(list.get(turn - 1).getName())){
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                    mp.start();


                    if(turn < list.size()){
                        turn++;
                        newQuestion(turn);
                    }else{
                        openDoneAskingDialog();

                    }

                }else{
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
                    mp.start();
                    openWrongAskingDialog(list.get(turn - 1).getName(), list.get(turn - 1).getImage());
                }
            }
        });


        btn_answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_answer4.getText().toString().equalsIgnoreCase(list.get(turn - 1).getName())){
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                    mp.start();

                    if(turn < list.size()){
                        turn++;
                        newQuestion(turn);
                    }else{
                        openDoneAskingDialog();

                    }

                }else{
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
                    mp.start();
                    openWrongAskingDialog(list.get(turn - 1).getName(), list.get(turn - 1).getImage());
                }
            }
        });


    }

    public void openDoneAskingDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_ask_user);

        Button btn_no = (Button) dialog.findViewById(R.id.btnNo);
        Button btn_yes = (Button) dialog.findViewById(R.id.btnYes);
        TextView txt_right = (TextView) dialog.findViewById(R.id.txtRightAnswer);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.askingFish);
        txt_right.setText("Bạn đã trả lời hết câu hỏi!");
        imageView.setImageResource(R.drawable.cartoon2);
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
//                Intent intent = new Intent(GameActivity.this, GameActivity.class);
//                startActivity(intent);
                turn = 1;
                newQuestion(turn);

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


}
