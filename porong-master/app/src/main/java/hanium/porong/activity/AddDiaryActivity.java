package hanium.porong.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import hanium.porong.R;
import hanium.porong.data.DiaryAddResponse;
import hanium.porong.data.DiaryData;
import hanium.porong.network.RetrofitClient;
import hanium.porong.network.ServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDiaryActivity extends AppCompatActivity {
        // private ImageButton dateSelect;
        private Button diarySave;
        private Button diaryCancle;
        private EditText diaryTitle;
        private TextView diaryDate;
        private Spinner feelSelect;
        private EditText diaryContent;

        private ServiceApi service;

        private String email;
        private String date;
        private String title;
        private String content;
        private String feeling;

        private DatePickerDialog.OnDateSetListener callbackMethod;

        @SuppressLint("WrongViewCast")
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_diary_write);

            this.InitializeListener();

            service = RetrofitClient.getClient().create(ServiceApi.class);

            diarySave = findViewById(R.id.diary_saveBtn);
            diaryCancle = findViewById(R.id.diary_cancleBtn);
            diaryTitle = findViewById(R.id.diary_title);
            diaryDate = findViewById(R.id.diary_date);
            final String[] feelings = getResources().getStringArray(R.array.feeling);
            feelSelect = findViewById(R.id.diary_feeling);
            diaryContent = findViewById(R.id.diary_content);

            feelSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    feeling = (String)parent.getItemAtPosition(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            diarySave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SharedPreferences shared = getSharedPreferences("session", MODE_PRIVATE);
                    String email = shared.getString("email", "");
                    attemptAdd();
                }
            });
            diaryCancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(hanium.porong.activity.AddDiaryActivity.this, "일기 작성이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                    finish(); //Go Home
                }
            });
        }


        private void attemptAdd() { /* 유효성 검사 */
            diaryTitle.setError(null);
            diaryDate.setError(null);
            diaryContent.setError(null);

            SharedPreferences shared = getSharedPreferences("session", MODE_PRIVATE);
            email = shared.getString("email", "");

            title = diaryTitle.getText().toString();
            content = diaryContent.getText().toString();

            boolean cancel = false;
            View focusView = null;

            /* 유효성 검사 */
            //제목
            if (title.isEmpty()) {
                diaryTitle.setError("제목을 입력해주세요.");
                focusView = diaryTitle;
                cancel = true;
            }

            //내용
            if (content.isEmpty()) {
                diaryContent.setError("내용을 입력해주세요.");
                focusView = diaryContent;
                cancel = true;
            }

            //작성일
            if (date.isEmpty()) {
                diaryDate.setError("날짜를 입력해주세요.");
                focusView = diaryDate;
                cancel = true;
            }

            if (cancel) {
                focusView.requestFocus();
            } else {
                startAdd(new DiaryData(email, title, date, feeling, content));
            }
        }

        private void startAdd(DiaryData data) {
            service.insertDiary(data).enqueue(new Callback<DiaryAddResponse>()  {
                @Override
                public void onResponse(Call<DiaryAddResponse> call, Response<DiaryAddResponse> response) {
                    DiaryAddResponse result = response.body();

                    if (result.getCode() == 200) {
                        Toast.makeText(hanium.porong.activity.AddDiaryActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(AddDiaryActivity.this, ViewDirayActivity.class);
                        intent.putExtra("email", email);
                        intent.putExtra("title", title);
                        intent.putExtra("content", content);
                        intent.putExtra("feeling", feeling);
                        intent.putExtra("diaryDate", diaryDate.getText().toString());
                        Log.d("diaryDate", diaryDate.getText().toString());
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<DiaryAddResponse> call, Throwable t) {
                    // Toast.makeText(AddDiaryActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                    Log.e("회원가입 에러 발생", t.getMessage());
                }
            });
        }

        public void InitializeListener()
        {
            callbackMethod = new DatePickerDialog.OnDateSetListener()
            {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                {
                    diaryDate.setText(year + "년 " + (monthOfYear+1) + "월 " + dayOfMonth + "일");
                    date = String.valueOf(year)+'/'+ (monthOfYear+1)+'/'+dayOfMonth;
                }
            };
        }

        public void OnClickHandler(View view)
        {
            SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
            String[] today = dfDate.format(new Date()).split("-");

            DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, Integer.parseInt(today[0]),  Integer.parseInt(today[1])-1,  Integer.parseInt(today[2]));

            dialog.show();
        }

}


