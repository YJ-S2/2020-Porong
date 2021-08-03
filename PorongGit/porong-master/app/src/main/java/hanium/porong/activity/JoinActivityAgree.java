package hanium.porong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hanium.porong.R;

public class JoinActivityAgree extends AppCompatActivity {
    final static int RES = 3;

    private CheckBox agree;

    private Button mNextButton;
    private Button mPreButton;

    private Boolean check;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join3);

        agree = (CheckBox)findViewById(R.id.agree);
        mNextButton = (Button)findViewById(R.id.join_nextBtn);
        mPreButton = (Button)findViewById(R.id.join_preBtn);


        check = false;
        agree.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    check = true;
                } else {
                   check = false;
                }
            }
        }) ;

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check==false){
                    Toast.makeText(view.getContext(), "약관에 동의는 필수입니다.", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(JoinActivityAgree.this, JoinActivityStep1.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        mPreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
