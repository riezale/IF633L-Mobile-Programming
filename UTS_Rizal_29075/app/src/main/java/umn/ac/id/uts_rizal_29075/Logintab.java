package umn.ac.id.uts_rizal_29075;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Logintab extends AppCompatActivity {
    private Button btnList;
    private EditText username;
    private EditText password;
    private Button BtnkeHome1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logintab);

        username = (EditText) findViewById(R.id.uname);
        password = (EditText) findViewById(R.id.pword);
        btnList = (Button) findViewById(R.id.kelagu);
        btnList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                validate(username.getText().toString(),password.getText().toString());
            }
        });

        BtnkeHome1 = (Button) findViewById(R.id.BtnkeHome1);
        BtnkeHome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keHome1();
            }
        });


    }

    private void validate(String nameuser, String passworduser)  {
        if((nameuser.equals("uasmobile")) && (passworduser.equals("uasmobilegenap"))) {
            Intent intent = new Intent(this,MusicPlayer.class);
            startActivity(intent);
        } else {
            Toast.makeText(Logintab.this, "Email / Password salah", Toast.LENGTH_LONG).show();


        }
    }

    private void keHome1() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}