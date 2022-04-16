package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.example.Retrofit.NetworkService;
import com.example.example.Retrofit.User;
import com.example.example.Retrofit.UserRes;
import com.example.example.users.UsersActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    TextInputLayout textFieldEmail;
    TextInputEditText txtEmail;
    TextInputLayout textFieldFName;
    TextInputEditText txtFName;
    TextInputLayout textFieldSName;
    TextInputEditText txtSName;
    TextInputLayout textFieldPhoto;
    TextInputEditText txtPhoto;
    TextInputLayout textFieldPhone;
    TextInputEditText txtPhone;
    TextInputLayout textFieldPassword;
    TextInputEditText txtPassword;
    //TextView txtReg;
    TextView txtLog;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = new User();
        textFieldEmail = findViewById(R.id.textFieldEmail);
        txtEmail = findViewById(R.id.txtEmail);
        textFieldFName = findViewById(R.id.textFieldFName);
        txtFName = findViewById(R.id.txtFName);
        textFieldSName = findViewById(R.id.textFieldSName);
        txtSName = findViewById(R.id.txtSName);
//        textFieldPhoto = findViewById(R.id.textFieldPhoto);
//        txtPhoto = findViewById(R.id.txtPhoto);
        textFieldPhone = findViewById(R.id.textFieldPhone);
        txtPhone = findViewById(R.id.txtPhone);
        textFieldPassword = findViewById(R.id.textFieldPassword);
        txtPassword = findViewById(R.id.txtPassword);
        //txtReg = findViewById(R.id.textReg);
        txtLog = findViewById(R.id.textLog);
    }

    public void handleClick(View view) {
        textFieldEmail.setError("");
        textFieldFName.setError("");
        textFieldSName.setError("");
        //textFieldPhoto.setError("");
        textFieldPhone.setError("");
        textFieldPassword.setError("");
        if( txtEmail.getText().toString().equals(""))
            textFieldEmail.setError("Вкажіть пошту");
        else
            user.setEmail(txtEmail.getText().toString());
        if( txtFName.getText().toString().equals(""))
            textFieldFName.setError("Вкажіть ім'я");
        else
            user.setFirstName(txtFName.getText().toString());
        if( txtSName.getText().toString().equals(""))
            textFieldSName.setError("Вкажіть прізіище");
        else
            user.setSecondName(txtSName.getText().toString());
//        if( txtPhoto.getText().toString().equals(""))
//            textFieldPhoto.setError("Вкажіть фото");
//        else
//            user.setPhoto(txtPhoto.getText().toString());
        if( txtPhone.getText().toString().equals(""))
            textFieldPhone.setError("Вкажіть номер телефону");
        else
            user.setPhone(txtPhone.getText().toString());
        if( txtPassword.getText().toString().equals(""))
            textFieldPassword.setError("Вкажіть пароль");
        else
            user.setPhone(txtPhone.getText().toString());
        if( txtPassword.getText().toString().length() != 5)
            textFieldPassword.setError("Некоректний пароль");
        else {
            user.setPassword(txtPassword.getText().toString());
            user.setConfirmPassword(txtPassword.getText().toString());
        }
        user.setPhoto(sImage);
        NetworkService.getInstance()
                .getJSONApi()
                .addUser(user)
                .enqueue(new Callback<UserRes>() {
                    @Override
                    public void onResponse( Call<UserRes> call, Response<UserRes> response) {
                        if(response.isSuccessful()) {
                            UserRes data = response.body();
                        }
                        else {
                            try {
                                String json = response.errorBody().string();
                                int a=12;
                            }
                            catch(Exception ex) {

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserRes> call, Throwable t) {
                    }
                });

    }

    int SELECT_PICTURE = 200;
    String sImage="";

    public void ImgSelect(View view) {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
        view.setVisibility(View.GONE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri uri = data.getData();
                // update the preview image in the layout

                Bitmap bitmap= null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // initialize byte stream
                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                // compress Bitmap
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                // Initialize byte array
                byte[] bytes=stream.toByteArray();
                // get base64 encoded string
                sImage= Base64.encodeToString(bytes,Base64.DEFAULT);
            }
        }
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PICK_PHOTO && resultCode == Activity.RESULT_OK) {
//            if (data == null) {
//                return;
//            }
//            Uri uri = data.getData();
//            File f = new File(uri.getPath());
//            String encodstring = encodeFileToBase64Binary(f);
//            user.setPhoto(encodstring);
//
//        }
//    }

//    private static String encodeFileToBase64Binary(File file){
//        String encodedfile = null;
//        try {
//            FileInputStream fileInputStreamReader = new FileInputStream(file);
//            byte[] bytes = new byte[(int)file.length()];
//            fileInputStreamReader.read(bytes);
//            encodedfile = Base64.encodeToString(bytes, Base64.URL_SAFE | Base64.NO_WRAP);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return encodedfile;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.m_users:
                intent = new Intent(this, UsersActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}