package hanium.porong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hanium.porong.R;

public class SecondDiaryListActivity extends AppCompatActivity {

    private TextView feeling;
    private TextView title;
   // private TextView content;
    private TextView date;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diarylist2);

        /*feeling = findViewById(R.id.feeling2); feeling.setText("좋음");
        title = findViewById(R.id.title2); title.setText("오늘의 일기");
        date = findViewById(R.id.date2); date.setText("2020년 11월 14일");
*/
        Intent intent = getIntent();

        title.setText(intent.getStringExtra("title"));
        feeling.setText(intent.getStringExtra("feeling"));
        date.setText(intent.getStringExtra("diaryDate"));

        Button btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddDiaryActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
