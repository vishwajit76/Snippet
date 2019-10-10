package com.cyberparkitsolutions.jbcc.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cyberparkitsolutions.jbcc.AppUtils;
import com.cyberparkitsolutions.jbcc.R;
import com.cyberparkitsolutions.jbcc.listeners.RemoveItemListener;
import com.cyberparkitsolutions.jbcc.models.LastActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;

import cn.gavinliu.android.lib.scale.ScaleLinearLayout;


public class ActivityAdapter extends FirebaseRecyclerAdapter<LastActivity, ActivityAdapter.GalleryHolder> {
    Context context;
    String p_id;

    public ActivityAdapter(FirebaseRecyclerOptions<LastActivity> options, String p_id, Context context) {
        super(options);
        this.context = context;
        this.p_id = p_id;
    }


    @Override
    public GalleryHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_timeline, parent, false);
        return new GalleryHolder(view);
    }

    @Override
    protected void onBindViewHolder(GalleryHolder holder, int position, LastActivity model) {
        holder.bind(model, context, position);
    }


    public class GalleryHolder extends RecyclerView.ViewHolder {

        public View view_line;
        public CircularImageView iv_user;
        public ScaleLinearLayout ll_time;

        public TextView tv_month, tv_day, tv_day_text, tv_username, tv_message, tv_time_ago;

        View itemView;


        public GalleryHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            view_line = (View) itemView.findViewById(R.id.view_line);
            iv_user = (CircularImageView) itemView.findViewById(R.id.iv_user);
            ll_time = (ScaleLinearLayout) itemView.findViewById(R.id.ll_time);

            tv_month = (TextView) itemView.findViewById(R.id.tv_month);
            tv_day = (TextView) itemView.findViewById(R.id.tv_day);
            tv_day_text = (TextView) itemView.findViewById(R.id.tv_day_text);
            tv_username = (TextView) itemView.findViewById(R.id.tv_username);
            tv_message = (TextView) itemView.findViewById(R.id.tv_message);
            tv_time_ago = (TextView) itemView.findViewById(R.id.tv_time_ago);


        }

        public void bind(final LastActivity model, final Context context, int position) {


            Log.w("vishwa", "position - " + position + " id - " + model.getActivity_id() + "  ts - " + model.getTimestampLong() + " msg - " + model.getMessage());


            if (position == (getItemCount() - 1)) {
                ll_time.setVisibility(View.VISIBLE);
            } else {

                if (!AppUtils.isSameDay(getItem(position + 1).getTimestampLong(), model.getTimestampLong())) {

                    ll_time.setVisibility(View.VISIBLE);
                }
            }


            tv_month.setText(AppUtils.getDate(model.getTimestampLong(), "MMM").toUpperCase());
            tv_day.setText(AppUtils.getDate(model.getTimestampLong(), "dd"));
            tv_day_text.setText(AppUtils.getDate(model.getTimestampLong(), "EEE").toUpperCase());
            tv_time_ago.setText(AppUtils.getTimeAgo(model.getTimestampLong()));

            tv_message.setText(model.getMessage());

            AppUtils.mUser.child(model.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String name = (String) dataSnapshot.child("name").getValue();
                        String profile_photo = (String) dataSnapshot.child("profile_photo").getValue();
                        tv_username.setText(AppUtils.getSpannedText(name));

                        Glide.with(context).load(profile_photo).into(iv_user);


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
    }


}
