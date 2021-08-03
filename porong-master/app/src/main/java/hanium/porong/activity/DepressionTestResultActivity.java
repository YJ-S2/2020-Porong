package hanium.porong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import hanium.porong.MainActivity;
import hanium.porong.R;

public class DepressionTestResultActivity extends AppCompatActivity {
    private Button tButton;
    private ImageView view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        final TextView tv = (TextView)findViewById(R.id.myResult);
        Intent intent = new Intent(this.getIntent());

        int count = intent.getIntExtra("value", 0);

        if(count == 0)
            tv.setText("회원님은 자연스러운 우울감을 느끼고 있는 상태입니다.");
        if(count == 1)
            tv.setText("2주 이상 이 현상이 지속되면 초기 우울증으로 간주합니다.");
        if(count == 2)
            tv.setText("2주 이상 이 현상이 지속되면 중증도의 우울증으로 간주합니다.");
        if(count == 3)
            tv.setText("2주 이상 이 현상이 지속되면 심한 우울증으로 간주합니다.");

        view = (ImageView) findViewById(R.id.home);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}