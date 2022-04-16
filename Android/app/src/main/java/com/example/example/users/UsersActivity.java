package com.example.example.users;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.example.R;
import com.example.example.Retrofit.NetworkService;
import com.example.example.Retrofit.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends AppCompatActivity {

    private UserAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar processBar;
    //private  List<UserDTO> userDTOS = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        processBar = findViewById(R.id.progressBar_User);

        recyclerView = findViewById(R.id.rcv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2,
                LinearLayoutManager.VERTICAL, false));

        NetworkService.getInstance()
                .getJSONApi()
                .getUsers()
                .enqueue(new Callback<List<UserDTO>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<UserDTO>> call, @NonNull Response<List<UserDTO>> response) {
                        adapter=new UserAdapter(response.body());
                        recyclerView.setAdapter(adapter);
                        processBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<UserDTO>> call, @NonNull Throwable t) {
                        int i = 0;

                    }
                });



//        UserDTO userDTO = new UserDTO();
//        userDTO.setEmail("ss@gg.dd");
//        userDTO.setImage("/images/5xfqagws.mgd.jpeg");
//        userDTOS.add(userDTO);
//
//        UserDTO userDTO2 = new UserDTO();
//        userDTO2.setEmail("dd@vv.dd");
//        userDTO2.setImage("/images/bzj0kmx0.rig.jpeg");
//        userDTOS.add(userDTO2);
//        adapter=new UserAdapter(userDTOS);
//        recyclerView.setAdapter(adapter);
    }
}