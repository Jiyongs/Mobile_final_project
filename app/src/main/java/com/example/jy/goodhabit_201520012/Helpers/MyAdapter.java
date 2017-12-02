package com.example.jy.goodhabit_201520012.Helpers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jy.goodhabit_201520012.ActivitySetHabit;
import com.example.jy.goodhabit_201520012.HabitInfo;
import com.example.jy.goodhabit_201520012.R;

import java.util.ArrayList;

/**
 * Created by JY on 2017-12-02.
 */

//RecyclerView를 관리하는 어뎁터

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.RecyclerViewHolders> {

    private Context context;
    private ArrayList<HabitInfo> habitInfo;

    public MyAdapter(Context context, ArrayList<HabitInfo> habitInfo){
        this.context = context;
        this.habitInfo = habitInfo;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout,null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.tvHname.setText(habitInfo.get(position).getHName());
        holder.tvHtarget.setText(habitInfo.get(position).getHCount()+"/"+habitInfo.get(position).getHTarget());
        holder.tvDoday.setText(habitInfo.get(position).getDoDay());
    }

    @Override
    public int getItemCount(){
        return habitInfo.size();
    }

    //카드뷰를 짧게 클릭했을 때의 이벤트를 수행하게 하려면, 맨뒤에 implements View.OnClickListener로 변경해야 함!
    public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnLongClickListener{

        public TextView tvHname, tvHtarget, tvDoday;

        public RecyclerViewHolders(View itemView){
            super(itemView);

            tvHname = (TextView)itemView.findViewById(R.id.habit_name);
            tvHtarget = (TextView)itemView.findViewById(R.id.tv_count);
            tvDoday = (TextView)itemView.findViewById(R.id.tv_date);

            //itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        //*******************************생성되어있는 카드뷰를 길게 클릭하면 ActivitySetHabit이 나오면서 수정 가능하도록 고치기!(수정필요)
        //*******************************지금은 그냥 ActivitySetHabit이 나오면서 새로 db 데이터를 추가하게 되는 기능임
        @Override
        public boolean onLongClick(View view){
            Intent reintent = new Intent(context,ActivitySetHabit.class);
            context.startActivity(reintent);
            return false;
        }

        /* 이건 그냥 카드뷰를 짧게 클릭했을 때의 이벤트
        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Clicked Card Position = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
        }
        */
    }

}
