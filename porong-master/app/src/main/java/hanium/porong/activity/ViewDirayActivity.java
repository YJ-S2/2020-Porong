package hanium.porong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hanium.porong.R;
import hanium.porong.data.DiaryData;
import hanium.porong.data.DiaryViewData;
import hanium.porong.data.DiaryViewResponse;
import hanium.porong.network.RetrofitClient;
import hanium.porong.network.ServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewDirayActivity extends AppCompatActivity {

    private TextView view_title;
    private TextView view_content;
    private TextView view_date;
    private TextView view_feeling;

    private TextView tv_goList;

    private ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_view);
        view_title = findViewById(R.id.view_title);
        view_content = findViewById(R.id.view_content);
        view_date = findViewById(R.id.view_date);
        view_feeling = findViewById(R.id.view_feeling);

        //service = RetrofitClient.getClient().create(ServiceApi.class);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String title = intent.getStringExtra("title");

        view_title.setText(intent.getStringExtra("title"));
        view_content.setText(intent.getStringExtra("content"));
        view_feeling.setText(intent.getStringExtra("feeling"));
        view_date.setText(intent.getStringExtra("diaryDate"));

        service.viewDiary(new DiaryViewData(email, title)).enqueue(new Callback<DiaryViewResponse>() {
            @Override
            public void onResponse(Call<DiaryViewResponse> call, Response<DiaryViewResponse> response) {
                DiaryViewResponse result = response.body();
                if(result == null){
                    //Toast.makeText(ViewDirayActivity.this, "result is null", Toast.LENGTH_SHORT).show();
                } else if(result.getCode()==204){
                    Toast.makeText(ViewDirayActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    view_title.setText(result.getTitle());
                    view_content.setText(result.getContent());
                    view_feeling.setText(result.getFeeling());
                    view_date.setText("임시날짜");
                }
            }


            @Override
            public void onFailure(Call<DiaryViewResponse> call, Throwable t) {
                Toast.makeText(ViewDirayActivity.this, "일기 조회에 실패하였습니다", Toast.LENGTH_SHORT).show();

            }
        });


        tv_goList = findViewById(R.id.tv_goList);
        tv_goList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewDirayActivity.this, SecondDiaryListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
