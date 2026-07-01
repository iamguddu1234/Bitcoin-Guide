package com.iam.bitcoin.MyNewApp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iam.bitcoin.databinding.MyAppViewBinding;
import com.bumptech.glide.Glide;

import java.util.List;

public class AppModelAdapter extends RecyclerView.Adapter<AppModelAdapter.ViewHolder> {

    Context context;
    private List<AppModel> modelList;

    public AppModelAdapter(Context context, List<AppModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyAppViewBinding binding = MyAppViewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        context = holder.itemView.getContext();
        Glide.with(context).load(modelList.get(position).getAppIcon()).into(holder.appIcon);
        holder.appTitle.setText(modelList.get(position).getAppTitle());
        holder.appDeveloper.setText(modelList.get(position).getAppDeveloper());
        holder.download.setText(modelList.get(position).getDownload());
        holder.downloadTitle.setText(modelList.get(position).getDownloadTitle());
        holder.rate.setText(modelList.get(position).getRate());
        holder.ratingTitle.setText(modelList.get(position).getRatingTitle());
        holder.size.setText(modelList.get(position).getSize());
        holder.sizeTitle.setText(modelList.get(position).getSizeTitle());
        holder.appInstallButton.setText(modelList.get(position).getAppInstallButton());
        holder.appDetailsPoint1.setText(modelList.get(position).getAppDetailsPoint1());
        holder.appDetailsPoint2.setText(modelList.get(position).getAppDetailsPoint2());
        holder.appDetailsPoint3.setText(modelList.get(position).getAppDetailsPoint3());
        holder.appDetailsPoint4.setText(modelList.get(position).getAppDetailsPoint4());
        holder.appTag1.setText(modelList.get(position).getAppTag1());
        holder.appTag2.setText(modelList.get(position).getAppTag2());
        holder.appTag3.setText(modelList.get(position).getAppTag3());
        holder.appTag4.setText(modelList.get(position).getAppTag4());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                        Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.googledork&hl=en-IN"));
                        context.startActivity(intent3);
                        break;

                    case 3:
                        Intent intent4 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.deepwebtutorial&hl=en-IN"));
                        context.startActivity(intent4);
                        break;
                    case 4:
                        Intent intent5 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.wiresharktutorial&hl=en-IN"));
                        context.startActivity(intent5);
                        break;
                    case 5:
                        Intent intent6 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.higuddu.metasploit&hl=en-IN"));
                        context.startActivity(intent6);
                        break;

                    case 6:
                        Intent intent7 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=learn.example.learnlinuxatoz&hl=en_IN"));
                        context.startActivity(intent7);
                        break;

                    case 7:
                        Intent intent8 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.nmap_advance&hl=en-IN"));
                        context.startActivity(intent8);
                        break;

                    case 8:
                        Intent intent9 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.my.AdvanceInformation&hl=en-IN"));
                        context.startActivity(intent9);
                        break;

                    case 9:
                        Intent intent10 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.guddu.burpsuite&hl=en_IN"));
                        context.startActivity(intent10);
                        break;

                    case 10:
                        Intent intent11 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.finaljavaexample&hl=en-IN"));
                        context.startActivity(intent11);
                        break;

                    case 11:
                        Intent intent12 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.finalpythonexample&hl=en-IN"));
                        context.startActivity(intent12);
                        break;

                    case 12:
                        Intent intent13 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.programmingexamples&hl=en-IN"));
                        context.startActivity(intent13);
                        break;

                    case 13:
                        Intent intent14 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.swift.learnswift&hl=en_IN"));
                        context.startActivity(intent14);
                        break;

                    case 14:
                        Intent intent15 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.kotlinexamples&hl=en-IN"));
                        context.startActivity(intent15);
                        break;

                    case 15:
                        Intent intent16 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.androidxmltutorial&hl=en-IN"));
                        context.startActivity(intent16);
                        break;

                    case 16:
                        Intent intent17 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.masterinandroid&hl=en-IN"));
                        context.startActivity(intent17);
                        break;

                    case 17:
                        Intent intent18 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.finalarchlinux&hl=en-IN"));
                        context.startActivity(intent18);
                        break;

                    case 18:
                        Intent intent19 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.bitcoin&hl=en-IN"));
                        context.startActivity(intent19);
                        break;

                    case 19:
                        Intent intent20 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.masterinshortcut_key&hl=en-IN"));
                        context.startActivity(intent20);
                        break;
                    case 20:
                        Intent intent21 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.finalinterviewqueans&hl=en-IN"));
                        context.startActivity(intent21);
                        break;

                    case 21:
                        Intent intent22 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.learn100000englishsentence&hl=en-IN"));
                        context.startActivity(intent22);
                        break;

                    case 22:
                        Intent intent23 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.iam.storyappforkids&hl=en-IN"));
                        context.startActivity(intent23);
                        break;



                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView appIcon;
        TextView appTitle;
        TextView appDeveloper;
        TextView downloadTitle;
        TextView download;
        TextView ratingTitle;
        TextView rate;
        TextView sizeTitle;
        TextView size;
        TextView appInstallButton;
        TextView appDetailsPoint1;
        TextView appDetailsPoint2;
        TextView appDetailsPoint3;
        TextView appDetailsPoint4;
        TextView appTag1;
        TextView appTag2;
        TextView appTag3;
        TextView appTag4;

        public ViewHolder(@NonNull MyAppViewBinding binding) {
            super(binding.getRoot());
            appIcon = binding.imgIcon;
            appTitle = binding.tvAppName;
            appDeveloper = binding.tvDeveloper;
            downloadTitle = binding.tvDownloadsTitle;
            download = binding.tvDownloads;
            ratingTitle = binding.tvRatingTitle;
            rate = binding.tvRating;
            sizeTitle = binding.tvSizeTitle;
            size = binding.tvSize;
            appInstallButton = binding.btnInstall;
            appDetailsPoint1 = binding.tvPoint1;
            appDetailsPoint2 = binding.tvPoint2;
            appDetailsPoint3 = binding.tvPoint3;
            appDetailsPoint4 = binding.tvPoint4;
            appTag1 = binding.tvTag1;
            appTag2 = binding.tvTag2;
            appTag3 = binding.tvTag3;
            appTag4 = binding.tvTag4;
        }
    }
}
