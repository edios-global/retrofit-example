package com.sonu.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private APICalls calls;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("userId", "1");
//        parameters.put("_sort", "id");
//        parameters.put("_order", "desc");
//
        calls = RetrofitClient.getInstance().getApi();
//        calls.getCoomentsofSpecificUser(parameters)
//                .enqueue(new Callback<List<Post>>() {
//                    @Override
//                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                        Log.d(TAG, "onResponse: specfic post"+response.body().get(0).toString());
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Post>> call, Throwable t) {
//                        Log.d(TAG, "onFailure: "+t.getMessage());
//                    }
//                });
        calls.createPost(45,"title" , "body")
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {

                        Log.d(TAG, "onResponse: "+response.body().toString());
                    }
                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {

                        Log.d(TAG, "onFailure: "+t.getMessage());
                    }
                });
    }

    public void getPost(View view) {


        calls.getAllPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Log.d(TAG, "onResponse:  thread"+Thread.currentThread().getName());
                Log.d(TAG, "onResponse: "+response.body().get(0).getBody());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

        Log.d(TAG, "onCreate: after calls");

    }
}