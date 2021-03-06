package hanium.porong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import hanium.porong.R;
import hanium.porong.data.JoinData;
import hanium.porong.data.JoinResponse;
import hanium.porong.network.RetrofitClient;
import hanium.porong.network.ServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivityStep1 extends AppCompatActivity {
    final static int REQ = 100;

    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText mPasswordConfirmView;
    private EditText mNameView;
    private EditText mBirthView;
    private RadioGroup mGenderView;
    private Spinner mJobView;
    private CheckBox mRoommateView;
    private CheckBox mChildView;
    private CheckBox mMarriedView;

    private String name;
    private String email;
    private String pwd;
    private String birth;
    private String job;
    private String gender;
    private String roommate;
    private String marry;
    private String child;


    private Button mCompleteButton;

    //private ImageButton mPreButton;
    //private ProgressBar mProgressView;
    private ServiceApi service;

    private JoinData joinData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        mEmailView = (EditText) findViewById(R.id.join_email);
        mPasswordView = (EditText) findViewById(R.id.join_password);
        mPasswordConfirmView = (EditText) findViewById(R.id.join_password_confirm);
        mNameView = (EditText) findViewById(R.id.join_name);


        mBirthView = (EditText) findViewById(R.id.join_birth);
        mGenderView = (RadioGroup) findViewById(R.id.join_gender);
        final String[] jobs = getResources().getStringArray(R.array.jobs);
        mJobView = (Spinner) findViewById(R.id.join_job);;
        mRoommateView = (CheckBox) findViewById(R.id.join_roommate);
        mChildView = (CheckBox) findViewById(R.id.join_child);
        mMarriedView = (CheckBox) findViewById(R.id.join_married);


        //mProgressView = (ProgressBar) findViewById(R.id.join_progress);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        //birth
        //startJoin??????


        //job
        mJobView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                job = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //gender
        mGenderView.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.join_man:
                        gender = "??????";
                        break;
                    case R.id.join_woman:
                        gender = "??????";
                        break;
                }
            }
        });

        //roommate
        roommate = "??????";
        mRoommateView.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    roommate = "??????";
                }
            }
        }) ;


        //married
        marry = "??????";
        mMarriedView.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    marry = "??????";
                }
            }
        }) ;

        //child
        child = "??????";
        mChildView.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    child = "??????";
                }
            }
        }) ;

        mCompleteButton = (Button) findViewById(R.id.join_finish);
        mCompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptJoin();
            }
        });
    }

    private void attemptJoin() {
        mNameView.setError(null);
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mPasswordConfirmView.setError(null);

        name = mNameView.getText().toString();
        email = mEmailView.getText().toString();
        pwd = mPasswordView.getText().toString();
        String confirmPwd = mPasswordConfirmView.getText().toString();

        birth = mBirthView.getText().toString(); //??? ????????? ????????? ???????????? ????????????...

        boolean cancel = false;
        View focusView = null;

        // ??????????????? ????????? ??????
        if (pwd.isEmpty()) {
            mPasswordView.setError("??????????????? ??????????????????.");
            focusView = mPasswordView;
            cancel = true;
        } else if (!isPasswordValid(pwd)) {
            mPasswordView.setError("6??? ????????? ??????????????? ??????????????????.");
            focusView = mPasswordView;
            cancel = true;
        } else if (!isEqualsPwd(pwd, confirmPwd)) {
            mPasswordConfirmView.setError("??????????????? ???????????? ????????????.");
            focusView = mPasswordConfirmView;
            cancel = true;

        }

        // ???????????? ????????? ??????
        if (email.isEmpty()) {
            mEmailView.setError("???????????? ??????????????????.");
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError("@??? ????????? ????????? ???????????? ??????????????????.");
            focusView = mEmailView;
            cancel = true;
        }

        // ????????? ????????? ??????
        if (name.isEmpty()) {
            mNameView.setError("????????? ??????????????????.");
            focusView = mNameView;
            cancel = true;
        }

        // ????????? ????????? ??????
       /* if (!birth.isEmpty() && !CheckDatePattern(birth)) {
            mBirthView.setError("????????? ???????????? ??????????????????.");
            focusView = mBirthView;
            cancel = true;
        }*/

        if (cancel) {
            focusView.requestFocus();
        } else {
           startJoin(new JoinData(name, email, pwd, birth, gender, job, roommate, marry, child));
        }
    }

    private void startJoin(JoinData data) {
        service.userJoin(data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                JoinResponse result = response.body();
                Toast.makeText(JoinActivityStep1.this, result.getMessage(), Toast.LENGTH_SHORT).show();

                if (result.getCode() == 200) {
                    Intent intent = new Intent(JoinActivityStep1.this, JoinCompleteActicity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(JoinActivityStep1.this, "???????????? ?????? ??????", Toast.LENGTH_SHORT).show();
                Log.e("???????????? ?????? ??????", t.getMessage());
            }
        });
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isEqualsPwd(String pwd, String confirmPwd){
        return pwd.equals(confirmPwd);
    }
    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    public  boolean  CheckDatePattern(String checkDate){

        try{
            SimpleDateFormat  dateFormat = new  SimpleDateFormat("yyyy-MM-dd");

            dateFormat.setLenient(false);
            dateFormat.parse(checkDate);
            return  true;

        }catch (ParseException  e){
            return  false;
        }

    }
/*
    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }*/
}