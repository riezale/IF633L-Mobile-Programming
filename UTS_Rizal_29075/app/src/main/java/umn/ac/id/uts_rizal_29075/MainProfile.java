package umn.ac.id.uts_rizal_29075;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainProfile extends AppCompatActivity {

    private Button BtnkeHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);

        TextView sumberyutup1 = (TextView) findViewById(R.id.sumberyutup1);
        sumberyutup1.setMovementMethod(LinkMovementMethod.getInstance());
        TextView sumberyutup2= (TextView) findViewById(R.id.sumberyutup2);
        sumberyutup2.setMovementMethod(LinkMovementMethod.getInstance());
        TextView sumberyutup3= (TextView) findViewById(R.id.sumberyutup3);
        sumberyutup3.setMovementMethod(LinkMovementMethod.getInstance());
        TextView sumberyutup4= (TextView) findViewById(R.id.sumberyutup4);
        sumberyutup4.setMovementMethod(LinkMovementMethod.getInstance());

        BtnkeHome = (Button) findViewById(R.id.BtnkeHome);
        BtnkeHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keHome();
            }
        });
    }

    private void keHome() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}