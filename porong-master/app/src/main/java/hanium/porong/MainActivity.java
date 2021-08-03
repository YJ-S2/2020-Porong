package hanium.porong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hanium.porong.activity.AddDiaryActivity;
import hanium.porong.activity.DepressionTestActivity;
//import hanium.porong.activity.DiaryListActivity;
import hanium.porong.activity.DiaryListActivity;
import hanium.porong.activity.ViewActivity;


public class MainActivity extends AppCompatActivity {
    //final int LOGIN_CODE = 100;
    //private Button mLogoutBtn;
    private ImageView mLogoutBtn;

    private Button diaryBtn;
    private Button chatBtn;
    private Button testBtn;
    private TextView tvUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLogoutBtn = findViewById(R.id.logoutBtn);
        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceManager.clear(MainActivity.this);
                finish(); //Go LoginActivity
            }
        });

        diaryBtn = findViewById(R.id.diaryBtn);
        diaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, DiaryListActivity.class);
             startActivity(intent1);
         }
        });


        chatBtn = findViewById(R.id.chatbotBtn);
        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, ViewActivity.class);
                startActivity(intent2);
            }
        });


        testBtn = findViewById(R.id.testBtn);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(MainActivity.this, DepressionTestActivity.class);
                startActivity(intent3);

            }
        });


        tvUsername = findViewById(R.id.tvUsername);
        SharedPreferences shared = getSharedPreferences("session", MODE_PRIVATE);
        tvUsername.setText(shared.getString("username", "")+"님 환영합니다!");


      /*  mLogoutBtn = (Button)findViewById(R.id.main_logoutBtn);
        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceManager.clear(MainActivity.this);
                finish(); //Go LoginActivity
            }
        }); */


        // Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        //startActivityForResult(intent, LOGIN_CODE);

        final TextView tv = findViewById(R.id.todayText);




        Intent intent = new Intent(this.getIntent());
        int value= intent.getIntExtra("value", 0);

        if (value == 0) {
            tv.setText("멈추지 말고 한 가지 목표에 매진하라. 그것이 성공의 비결이다.\n" +
                    "To follow, without halt, one aim: There's the secret of success.");
        }
        if (value == 1) {
            tv.setText("물리학을 믿는 나와 같은 사람들은 과거, 현재, 미래의 구별이란 단지 고질적인 환상일 뿐이란 사실을 알고 있다.\n" +
                    "People like us, who believe in physics, know that the distinction between past, present, and future is only a stubbornly persistent illusion");
        }
        if (value == 2) {
            tv.setText("미래에 사로잡혀있으면 현재를 있는 그대로 볼 수 없을 뿐 아니라 과거까지 재구성하려 들게 된다.\n" +
                    "A preoccupation with the future not only prevents us from seeing the present as it is but often prompts us to rearrange the past.");

        }
        if (value == 3) {
            tv.setText("이 세상에 보장된 것은 아무것도 없으며 오직 기회만 있을 뿐이다.\n" +
                    "There is no security on this earth, there is only opportunity.");

        }

        if (value == 4) {
            tv.setText("저는 미래가 어떻게 전개될지는 모르지만, 누가 그 미래를 결정하는지는 압니다.\n" +
                    "I do not know what the future holds, but I know who holds the future.");

        }

        if (value == 5) {
            tv.setText("기회는 없어지지 않는다. 당신이 놓친 것은 다른 사람이 잡는다.\n" +
                    "Opportunities are never lost. The other fellow takes those you miss.");

        }

        if (value == 6) {
            tv.setText("명확히 설정된 목표가 없으면, 우리는 사소한 일상을 충실히 살다 결국 그 일상의 노예가 되고 만다.\n" +
                    "In the absence of clearly-defined goals, we become strangely loyal to performing daily trivia until ultimately we become enslaved by it.");

        }

        if (value == 7) {
            tv.setText("남에게 이기는 방법의 하나는 예의범절로 이기는 것이다.\n" +
                    "One of the greatest victories you can gain over someone is to beat him at politeness.");

        }

        if (value == 8) {
            tv.setText("현재뿐 아니라 미래까지 걱정한다면 인생은 살 가치가 없을 것이다.\n" +
                    "Life wouldn't be worth living if I worried over the future as well as the present.");

        }

        if (value == 9) {
            tv.setText("시간은 인간이 쓸 수 있는 가장 값진 것이다.\n" +
                    "Time is the most valuable thing a man can spend.");

        }
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // if(id == android.R.id.home) {
        //        Intent hameIntent = new Intent(this, HomeActivity.class);
        //     startActivity(homeIntent);
        //  }


        if(id == R.id.logoutBtn){
            PreferenceManager.clear(MainActivity.this);
            finish(); //Go LoginActivity
        }

        return super.onOptionsItemSelected(item);

    }
}