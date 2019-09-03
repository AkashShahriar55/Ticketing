package com.example.kvittering;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class Screen_2 extends AppCompatActivity {

    private SwipeButton swipeButton;
    private configuration item;
    private TextView textViewAmount,textViewProfile,textViewPhone,textViewThumb;
    private CircleImageView circleImageView;
    private LinearLayout linearLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_2);


        item = (configuration) getIntent().getExtras().getSerializable("item");
        swipeButton = findViewById(R.id.Swipe_button);
        textViewAmount = findViewById(R.id.amount);
        textViewPhone = findViewById(R.id.phone);
        textViewProfile = findViewById(R.id.name);
        circleImageView = findViewById(R.id.profile_image);
        linearLayout = findViewById(R.id.top_layout);
        textViewThumb = findViewById(R.id.thumb_text);


        Animation bottomUp = AnimationUtils.loadAnimation(this,
                R.anim.slide_in_top);
        ViewGroup hiddenPanel = (ViewGroup) linearLayout;
        hiddenPanel.startAnimation(bottomUp);
        hiddenPanel.setVisibility(View.VISIBLE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        if(item != null){
            textViewProfile.setText(item.getProfileName());
            textViewPhone.setText(item.getPhoneNumber());
            textViewAmount.setText(item.getAmount());
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

        swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                gointent();
            }
        });
    }

    private void gointent() {
        Intent intent = new Intent(this,Screen_3a.class);
        intent.putExtra("item",item);
        this.startActivity(intent);
        this.overridePendingTransition(0, 0);
    }
}
