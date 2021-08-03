//package hanium.porong.adapter;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.graphics.Color;
//import android.os.Build;
//import android.util.Log;
//import android.view.ActionMode;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.firebase.database.ChildEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.gson.Gson;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import hanium.porong.R;
//import hanium.porong.data.DiaryData;
//
//import static android.content.Context.MODE_PRIVATE;
//
//public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {
//
//    private ArrayList<DiaryData> arrayList;
//    private boolean multiSelect = false;
//    private ArrayList<String> selectedItems = new ArrayList<>();
//    private Context mContext;
//
//    private Gson gson;
//    List<DiaryData> datas;
//    List<DiaryData> datasUser = new ArrayList<>();
//    String strId;
//    String id_real;
//
//    private ValueEventListener postListener;
//    private ChildEventListener childEventListener;
//
//    //생성자
////Alt+insert로 만듬
//    public RecyclerViewAdapter(ArrayList<DiaryData> arrayList) {      //String[] nameSet, String[] contentSet, int[] imgSet
//        this.arrayList = arrayList;
//    }
//
//    //액션모드
//    private ActionMode.Callback actionModeCallbacks = new ActionMode.Callback() {
//
//        @Override
//        //Action mode가 생성됐을때 콜백 함수. 여기서 menu를 추가할 수 있어.(ex. delete)
//        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//            multiSelect = true;
//            menu.add("Delete");
//            return true;
//        }
//
//        @Override
//        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//            return false;
//        }
//        @Override
//        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//
//            for (String intItem : selectedItems) {
//                remove(intItem);
//            }
//
//            mode.finish();
//            return true;
//        }
//
//        @Override
//        public void onDestroyActionMode(ActionMode mode) {
//
//        }
//    };
//
//    //recyclerView에 들어갈 뷰 홀더, 그리고 그 뷰 홀더에 들아갈 아이템들을 지정 / '이게 맵핑 전에 필요한 과정이 아닐까?'
//    public class CustomViewHolder extends RecyclerView.ViewHolder {
//
//        protected TextView tv_name;
//        protected TextView time;
//        protected LinearLayout linearLayout;
//        protected TextView tv_emotion;
//
//        public CustomViewHolder(@NonNull View itemView) {
//            super(itemView);
//            this.tv_name = itemView.findViewById(R.id.tv_name);
//            this.time = itemView.findViewById(R.id.time);
//            this.linearLayout = itemView.findViewById(R.id.linearLayoutItem);
//            this.tv_emotion = itemView.findViewById(R.id.tv_emotion);
//
//        }
//
//        void selectItem(String item) {
//            if (multiSelect) {
//                if (selectedItems.contains(item)) {
//                    selectedItems.remove(item);
//                    linearLayout.setBackgroundColor(Color.WHITE);
//                } else {
//                    selectedItems.add(item);
//                    linearLayout.setBackgroundColor(Color.LTGRAY);
//                }
//            }
//        }
//
//        void update(final String value) {
//            if (selectedItems.contains(value)) {
//                linearLayout.setBackgroundColor(Color.LTGRAY);
//            } else {
//                linearLayout.setBackgroundColor(Color.WHITE);
//            }
//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    ((AppCompatActivity) v.getContext()).startActionMode(actionModeCallbacks);
//                    selectItem(value);
//                    return true;
//                }
//            });
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (multiSelect == true) {
//                        selectItem(value);
//                    } else {
//                        //다이얼이 아닌 activity를 띄울거에요.
//                        final int position = getAdapterPosition();
//                        final Context vContext = v.getContext();
//
//                        id_real = arrayList.get(position).getId();
//
//                        Log.d("데이터", "넘어간다");
//                       // vContext.startActivity(intent);
//                    }
//                }
//            });
//        }
//    }
//
//    @NonNull
//    //액티비티의 onCreate와 비슷
//    //뷰 홀더를 생성 (이게 item.xml을 listview에 넣어주는 것.) *여기서 큰 틀 잡고
//    //RecyclerView에 들어갈 뷰 홀더를 할당(create)하는 함수 & extend의 <>안에 들어가는 타입을 따른다.  RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder>
//    @Override
//    public RecyclerViewAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        mContext = parent.getContext();
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
//        CustomViewHolder holder = new CustomViewHolder(view);
//
//        return holder;
//    }
//
//    //실제로 추가될때 생명주기
//    //실제 각 뷰 홀더에 데이터를 연결해주는 함수 (이게 data를 넣어주는 것) *여기서 세부 내용 넣어주는 것.
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    public void onBindViewHolder(@NonNull final RecyclerViewAdapter.CustomViewHolder holder, final int position) {
//
//        //홍드로이드
//        holder.tv_emotion.setText(arrayList.get(position).getTv_emotion());
//        holder.tv_name.setText(arrayList.get(position).getTv_name());
//        holder.time.setText(arrayList.get(position).getTime());
//
//        holder.update(arrayList.get(position).getId());
//
//        //쉐어드에서 데이터 받아오기
//        SharedPreferences sp = mContext.getSharedPreferences("shared", MODE_PRIVATE);
//        strId = sp.getString("checkId", "");
//
//
//    }
//
//    //RecyclerView 안에 들어갈 뷰 홀더의 갯수
//    @Override
//    public int getItemCount() {
//        //return nameSet.length > imgSet.length ? nameSet.length : imgSet.length;
//        return (null != arrayList ? arrayList.size() : 0);  //왜 해주는거지?  null체크라는 것인데 List는 null로 체크하면 안 된단다. 왜?
//    }
//
//
//    public void remove(String id) {
//
//        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
//
//        db.child("diarydata").orderByChild("id").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
//                    childSnapshot.getRef().removeValue();       //여기서 getRef()가 해당 자료의 폴더 이름인듯.
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//
//    }
//
//}
