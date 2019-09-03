package com.example.kvittering;

import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Screen_3a extends AppCompatActivity {

    private LinearLayout linearLayout;
    private configuration item;
    private TextView textViewAmount,textViewProfile,textViewPhone,textViewThumb,textViewCurrentTime,textViewCurrentDate;
    private CircleImageView circleImageView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        item = (configuration) getIntent().getExtras().getSerializable("item");



        if(item.getType().equals("Private")){
            setContentView(R.layout.activity_screen_3a);
            linearLayout = findViewById(R.id.main_ticket);
            textViewAmount = findViewById(R.id.amount);
            textViewPhone = findViewById(R.id.phone);
            textViewProfile = findViewById(R.id.name);
            circleImageView = findViewById(R.id.profile_image);
            textViewThumb = findViewById(R.id.thumb_text);
            textViewCurrentTime = findViewById(R.id.currentTime);

            if(item != null){
                textViewProfile.setText(item.getProfileName());
                textViewPhone.setText(item.getPhoneNumber());
                textViewAmount.setText(item.getAmount());
                textViewCurrentTime.setText(item.getCurrentTime());
                if(item.getImageIcon()!=null){
                    circleImageView.setImageURI(Uri.parse(item.getImageIcon()));
                    textViewThumb.setText("");
                }else{
                    String[] temp = item.getProfileName().trim().split("\\s+");
                    String thumb = "";
                    for(int i=0;i<temp.length;i++){
                        thumb += temp[i].substring(0, 1).toUpperCase();
                    }

                    textViewThumb.setText(thumb);
                }

            }
        }else if(item.getType().equals("Business")){
            setContentView(R.layout.activity_screen_3b);
            linearLayout = findViewById(R.id.main_ticket);
            textViewAmount = findViewById(R.id.amount);
            textViewPhone = findViewById(R.id.phone);
            textViewProfile = findViewById(R.id.name);
            imageView = findViewById(R.id.profile_image);
            textViewCurrentTime = findViewById(R.id.currentTime);
            textViewCurrentDate = findViewById(R.id.currentDate);

            if(item != null){
                textViewProfile.setText(item.getProfileName());
                textViewPhone.setText(item.getPhoneNumber());
                textViewAmount.setText(item.getAmount());
                String time = item.getCurrentTime();

                String[] times = time.trim().split("\\s+");
                textViewCurrentDate.setText(times[0]);
                textViewCurrentTime.setText(times[2]);



                if(item.getImageIcon()!=null){
                    imageView.setImageURI(Uri.parse(item.getImageIcon()));
                }

            }
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();




        Animation bottomUp = AnimationUtils.loadAnimation(this,
                R.anim.slide_int_top_ticket);
        ViewGroup hiddenPanel = (ViewGroup) linearLayout;
        hiddenPanel.startAnimation(bottomUp);
        hiddenPanel.setVisibility(View.VISIBLE);
    }
}
