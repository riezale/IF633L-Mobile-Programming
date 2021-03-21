package umn.ac.id.uts_rizal_29075;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button BtnkeHome;
    private Button BtnLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button =(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });

        BtnLogin = (Button) findViewById(R.id.login);
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openLogin();
            }
        });

    }

    private void openLogin() {
        Intent intent = new Intent(this,Logintab.class);
        startActivity(intent);
    }

    public void openProfile(){
        Intent intent = new Intent(this, MainProfile.class);
        startActivity(intent);
    }


}