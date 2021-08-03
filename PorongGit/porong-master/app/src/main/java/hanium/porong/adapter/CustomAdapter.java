/*
package hanium.porong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hanium.porong.R;
import hanium.porong.data.DiaryData;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<DiaryData> arrayList;
    private Context context;

    public CustomAdapter(ArrayList<DiaryData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
    //실제로 매칭
        holder.content.setText(arrayList.get(position).getContent());
        holder.title.setText(arrayList.get(position).getTitle());
        holder.emotion.setText(arrayList.get(position).getEmotion());
        holder.time.setText(arrayList.get(position).getTime());
    }

    @Override
    public int getItemCount() {

        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView emotion;
        TextView title;
        TextView content;
        TextView time;
       // ImageView imag_attach;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            //this.imag_attach = itemView.findViewById(R.id.img_attach);
            this.title = itemView.findViewById(R.id.tv_name);
            this.content = itemView.findViewById(R.id.tv_content);
            this.emotion = itemView.findViewById(R.id.tv_emotion);
            this.time = itemView.findViewById(R.id.time);

        }
    }
}
*/
