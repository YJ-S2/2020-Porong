package hanium.porong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hanium.porong.R;

public class DepressionTestActivity extends AppCompatActivity {
    private Button tButton;
    private int count;
    private int count1 = 0;
    private int count2 = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        tButton= (Button) findViewById(R.id.resultBtn);
        tButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CheckBox cb1 = (CheckBox)findViewById(R.id.checkList1);
                final CheckBox cb2 = (CheckBox)findViewById(R.id.checkList2);
                final CheckBox cb3 = (CheckBox)findViewById(R.id.checkList3);
                final CheckBox cb4 = (CheckBox)findViewById(R.id.checkList4);
                final CheckBox cb5 = (CheckBox)findViewById(R.id.checkList5);
                final CheckBox cb6 = (CheckBox)findViewById(R.id.checkList6);
                final CheckBox cb7 = (CheckBox)findViewById(R.id.checkList7);
                final CheckBox cb8 = (CheckBox)findViewById(R.id.checkList8);
                final CheckBox cb9 = (CheckBox)findViewById(R.id.checkList9);
                final CheckBox cb10 = (CheckBox)findViewById(R.id.checkList10);







                if(cb1.isChecked() == true)
                    count1++;
                if(cb2.isChecked() == true)
                    count1++;
                if(cb3.isChecked() == true)
                    count1++;

                if(cb4.isChecked() == true)
                    count2++;
                if(cb5.isChecked() == true)
                    count2++;
                if(cb6.isChecked() == true)
                    count2++;
                if(cb7.isChecked() == true)
                    count2++;
                if(cb8.isChecked() == true)
                    count2++;
                if(cb9.isChecked() == true)
                    count2++;
                if(cb10.isChecked() == true)
                    count2++;



                if(count1 >= 2 && count2 == 2)
                    count = 1;
                else if(count1 >=2 && (3 <= count2 && count2 <= 4))
                    count = 2;
                else if(count1 >= 2 && 4 < count2)
                    count = 3;


                Intent intent = new Intent(DepressionTestActivity.this, DepressionTestResultActivity.class);
                intent.putExtra("value", count);
                startActivity(intent);
                finish();
            }
        });


    }
}