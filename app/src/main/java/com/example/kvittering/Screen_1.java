package com.example.kvittering;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Screen_1 extends AppCompatActivity {


    private String mPhoneNumber,mProfileName,mAmount,mType,mCurrentTime;
    private EditText edtPhoneNumber,edtProfileName,edtAmount;
    private TextView txtCurrentTime;
    private Switch swchType;
    private String mImageByte;
    private Button mNextButton;
    private ImageButton mUploadImage;
    private static final int PICK_IMAGE_REQUEST = 1;
    private configuration mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        edtAmount = findViewById(R.id.edt_amount);
        txtCurrentTime = findViewById(R.id.currentTime);
        edtPhoneNumber = findViewById(R.id.edt_phoneNumber);
        edtProfileName = findViewById(R.id.edt_profileName);
        edtAmount = findViewById(R.id.edt_amount);
        swchType = findViewById(R.id.Switch);

        mNextButton = findViewById(R.id.Next_btn);
        mUploadImage = findViewById(R.id.Upload_btn);
        mType = "Private";


        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCurrentTime =  new SimpleDateFormat("dd.MM.yyyy - HH:mm", Locale.US).format(new Date());
                txtCurrentTime.setText(mCurrentTime);
                someHandler.postDelayed(this, 1000);
            }
        }, 10);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

        mUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        swchType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Screen_1.this,  (isChecked ? "Business" : "Private"),
                        Toast.LENGTH_SHORT).show();
                if(isChecked) {
                    mType = "Business";
                } else {
                    mType = "Private";

                }
            }
        });

    }

    private void validateData() {


        boolean error = false;

        mPhoneNumber = edtPhoneNumber.getText().toString();
        if (TextUtils.isEmpty(mPhoneNumber.trim())) {
            edtPhoneNumber.setError("Field mustn't be empty");
            edtPhoneNumber.requestFocus();
            error = true;
            return;
        }else{
            mPhoneNumber.replaceAll("([0-9][0-9])", "$0 ");
        }

        mAmount = edtAmount.getText().toString();
        if (TextUtils.isEmpty(mAmount.trim())) {
            edtAmount.setError("Field mustn't be empty");
            edtAmount.requestFocus();
            error = true;
            return;
        }


        mProfileName = edtProfileName.getText().toString();
        if (TextUtils.isEmpty(mProfileName.trim())) {
            edtProfileName.setError("Field mustn't be empty");
            edtProfileName.requestFocus();
            error = true;
            return;
        }


        mPhoneNumber = mPhoneNumber.replaceAll("(/+?)..", "$0 ");

        if(!error){
            mItem = new configuration(mPhoneNumber,mProfileName,mAmount,mCurrentTime,mImageByte,mType);
            Intent intent = new Intent(this,Screen_2.class);
            intent.putExtra("item",mItem);
            this.startActivity(intent);
        }



    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            Uri imgData = data.getData();

            mImageByte = imgData.toString();
        }else{
            Toast.makeText(this, "Problem", Toast.LENGTH_SHORT).show();
        }
    }
}
