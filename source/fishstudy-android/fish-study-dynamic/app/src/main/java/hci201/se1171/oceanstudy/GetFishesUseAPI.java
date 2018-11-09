package hci201.se1171.oceanstudy;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
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

public class GetFishesUseAPI extends AsyncTask<Object, String, String>  {

    Context context;
    Activity activity;
    String url;
    InputStream is;
    BufferedReader br;
    StringBuilder sb;

    String data;

    public GetFishesUseAPI(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(Object... objects) {
        try {

            url = (String) objects[0];
            URL myUrl = new URL(url);
            HttpURLConnection httpUrlConnection = (HttpURLConnection) myUrl.openConnection();
            httpUrlConnection.setConnectTimeout(3000);
            httpUrlConnection.setReadTimeout(3000);
            Log.i("Responseeee", "Codeeeeeee: " + httpUrlConnection.getResponseCode());
            httpUrlConnection.connect();

            is = httpUrlConnection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            String line = "";
            sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
        } catch (Exception e) {
            if (e.toString().contains("failed to connect to")){
                Log.i("FAILLLLL", "FAILLLLLLL");
            }
            e.printStackTrace();
            return "Could not connect to server";
        }
        return data;
    }

    @Override
    protected void onPostExecute(String s) {
//        try {
//
//            LinearLayout linearLayout = (LinearLayout) activity.findViewById(R.id.layoutBody);
//            ArrayList<Fish> listFish = new ArrayList<Fish>();
//            JSONArray jsonArray = new JSONArray(s);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject = jsonArray.optJSONObject(i);
//                int id = Integer.parseInt(jsonObject.getString("id"));
//                final String fishName = jsonObject.getString("name");
//                final double weight = Double.parseDouble(jsonObject.getString("weight"));
//                final double lenght = Double.parseDouble(jsonObject.getString("length"));
//                final double height = Double.parseDouble(jsonObject.getString("height"));
//                final int deep = Integer.parseInt(jsonObject.getString("deep"));
//                final int age = Integer.parseInt(jsonObject.getString("age"));
//                String image = jsonObject.getString("img");
//                final String video = jsonObject.getString("video");
//                final String active = jsonObject.getString("status");
//                Fish fish = new Fish(id, fishName, weight, lenght, height, deep, age, image, video, active);
//                listFish.add(fish);
//                Log.i("Fish", fish.toString());
//                GifImageView imageView = new GifImageView(this.context);
//                imageView.setId(id);
//                GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
//                Glide.with(this.context).load(image).into(imageViewTarget);
//
//                imageView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        showFishDialog(fishName, video);
//                    }
//                });
////
////
//                linearLayout.addView(imageView, i);
//            }
////            Intent intent = new Intent(context, UpdateData.class);
////            intent.putExtra("listFish", listFish);
////            activity.startActivityForResult(intent, 1);
//
////                Toast.makeText(this.context, jsonArray + " " , Toast.LENGTH_LONG).show();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } finally {
//
//        }

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
