package umn.ac.id.week11_29075;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    private Object Response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();

        JsonPlaceHolder jsonPlaceHolderApi = retrofit.create(JsonPlaceHolder.class);
        
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        
        call.enqueue(new Callback<List<Post>>(){
            @Override
            public void onResponse(Call<List<Post>> call, retrofit2.Response<List<Post>> response) {
                if(!response.isSuccessful()) {
                    textViewResult.setText("Code : " + response.code());
                    return;
                }
                List<Post> posts = response.body();

                for(Post post : posts) {
                   String content="";
                   content+="ID : " + post.getId() +"\n";
                   content+="User ID : " + post.getUserId() +"\n";
                   content+="Title : " + post.getTitle() +"\n";
                   content+="Text : " + post.getText() +"\n\n";

                   textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}