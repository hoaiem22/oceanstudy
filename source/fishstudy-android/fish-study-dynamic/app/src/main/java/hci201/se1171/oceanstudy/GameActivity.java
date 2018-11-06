package hci201.se1171.oceanstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private List fishList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        fishList = (List) getIntent().getSerializableExtra("listFish");

    }
}
