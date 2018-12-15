package app.task.mediaapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<MainActivity.DataMedia> data= Collections.emptyList();

    // create constructor to innitilize context and data sent from MainActivity
    public Adapter(Context context, List<MainActivity.DataMedia> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        final MainActivity.DataMedia current=data.get(position);
        myHolder.textdesp.setText(current.desp);
        myHolder.texttitle.setText(current.title);

        Glide.with(context).load(current.thumb)
                .placeholder(R.mipmap.ic_error)
                .error(R.mipmap.ic_error)
                .into(myHolder.ivthumb);

        myHolder.ivthumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ExoPlayer.class);
                intent.putExtra("vedioUrl", current.Url);
                context.startActivity(intent);
            }
        });
    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView textdesp,texttitle;
        ImageView ivthumb;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textdesp= (TextView) itemView.findViewById(R.id.textdesp);
            ivthumb= (ImageView) itemView.findViewById(R.id.ivthumb);
            texttitle = (TextView) itemView.findViewById(R.id.texttitle);
        }
    }
}
