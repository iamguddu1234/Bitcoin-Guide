package com.iam.bitcoin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAppAdapter extends RecyclerView.Adapter<MyAppAdapter.Viewholder> {


    Context context;
    private List<MyAppModel> models;


    public MyAppAdapter(Context context, List<MyAppModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_app, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        context = holder.itemView.getContext();
        Glide.with(context).load(models.get(position).getImage()).into(holder.imageView);
        holder.textView.setText(models.get(position).getAppName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();


                switch (clickedPosition) {
                    case 0:
                        Intent intent1 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.Inew.ikali&hl=en-IN"));
                        context.startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.bugbonty_roadmap&hl=en-IN"));
                        context.startActivity(intent2);
                        break;

                    case 2:
                        Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.programmingexamples&hl=en-IN"));
                        context.startActivity(intent3);
                        break;

                    case 3:
                        Intent intent4 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.nmap_advance&hl=en-IN"));
                        context.startActivity(intent4);
                        break;
                    case 4:
                        Intent intent5 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.finalpythonexample&hl=en-IN"));
                        context.startActivity(intent5);
                        break;
                    case 5:
                        Intent intent6 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.kotlinexamples&hl=en-IN"));
                        context.startActivity(intent6);
                        break;

                    case 6:
                        Intent intent7 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.wiresharktutorial&hl=en-IN"));
                        context.startActivity(intent7);
                        break;

                    case 7:
                        Intent intent8 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.deepwebtutorial&hl=en-IN"));
                        context.startActivity(intent8);
                        break;

                    case 8:
                        Intent intent9 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.finaljavaexample&hl=en-IN"));
                        context.startActivity(intent9);
                        break;

                    case 9:
                        Intent intent10 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.my.AdvanceInformation&hl=en-IN"));
                        context.startActivity(intent10);
                        break;

                    case 10:
                        Intent intent11 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.googledork&hl=en-IN"));
                        context.startActivity(intent11);
                        break;

                    case 11:
                        Intent intent12 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.higuddu.metasploit&hl=en-IN"));
                        context.startActivity(intent12);
                        break;

                    case 12:
                        Intent intent13 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.finalinterviewqueans&hl=en-IN"));
                        context.startActivity(intent13);
                        break;

                    case 13:
                        Intent intent14 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.bitcoin&hl=en-IN"));
                        context.startActivity(intent14);
                        break;

                    case 14:
                        Intent intent15 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.finalarchlinux&hl=en-IN"));
                        context.startActivity(intent15);
                        break;

                    case 15:
                        Intent intent16 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.masterinandroid&hl=en-IN"));
                        context.startActivity(intent16);
                        break;

                    case 16:
                        Intent intent17 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.masterinshortcut_key&hl=en-IN"));
                        context.startActivity(intent17);
                        break;

                    case 17:
                        Intent intent18 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.storyappforkids&hl=en-IN"));
                        context.startActivity(intent18);
                        break;

                    case 18:
                        Intent intent19 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.androidxmltutorial&hl=en-IN"));
                        context.startActivity(intent19);
                        break;

                    case 19:
                        Intent intent20 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.learn100000englishsentence&hl=en-IN"));
                        context.startActivity(intent20);
                        break;


                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        private TextView textView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);


            imageView = itemView.findViewById(R.id.imgPic);
            textView = itemView.findViewById(R.id.AppTitle);
        }
    }
}
