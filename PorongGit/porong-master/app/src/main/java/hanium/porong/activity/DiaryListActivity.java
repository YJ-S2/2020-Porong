

package hanium.porong.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import hanium.porong.R;
//import hanium.porong.adapter.CustomAdapter;
import hanium.porong.data.DiaryData;

public class DiaryListActivity extends AppCompatActivity {
    private TextView feeling;
    private TextView title;
    private TextView content;
    private TextView date;

    /*
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<DiaryData> arrayList;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    String userId;
    List<DiaryData> datas;
    ProgressDialog progressDialog;
*/


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diarylist);

        feeling = findViewById(R.id.tv_feeling);
        title = findViewById(R.id.tv_title);
        content = findViewById(R.id.tv_content);
        date = findViewById(R.id.time);












        /*
        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        String strId = sp.getString("checkId", "");
        userId = strId;

        //ProgressDialog 생성 및 동작
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("ProgressDialog running...");
        progressDialog.setCancelable(true);
        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Horizontal);

        //리사이클러뷰를 다루기 위한 기본 설정 (recyclerView의 레이아웃 매니저 설정)
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();

        //firebase에서 데이터 받기
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Diary");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    DiaryData diaryData = snapshot.getValue(DiaryData.class);
                        arrayList.add(diaryData);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //adapter = new CustomAdapter(arrayList, this);
        //recyclerView.setAdapter(adapter);
*/
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

