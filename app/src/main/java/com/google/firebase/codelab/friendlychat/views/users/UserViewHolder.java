package com.google.firebase.codelab.friendlychat.views.users;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.codelab.friendlychat.R;

public class UserViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivUserImage;
    public TextView tvUserName;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        ivUserImage = itemView.findViewById(R.id.imageView);
        tvUserName = itemView.findViewById(R.id.textView);
    }

}
