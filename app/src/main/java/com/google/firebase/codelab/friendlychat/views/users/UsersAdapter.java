package com.google.firebase.codelab.friendlychat.views.users;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.firebase.codelab.friendlychat.R;
import com.google.firebase.codelab.friendlychat.common.ItemClickListener;
import com.google.firebase.codelab.friendlychat.data.User;

import java.util.ArrayList;
import java.util.List;


public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {
    List<User> users = new ArrayList();
     ItemClickListener onItemClickListener;
    public UsersAdapter(List<User> users) {
        this.users = users;
        Log.e("UsersAdapter",users.size()+"");
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_user, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder userViewHolder, final int i) {
        User user = users.get(i);
//        Log.e("onBindViewHolder",user.getEmail());
        userViewHolder.tvUserName.setText(user.getName());
        Glide.with(userViewHolder.itemView.getContext())
                .load(user.getPhotoUrl())
                .into(userViewHolder.ivUserImage);
        userViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsersAdapter.this.onItemClickListener.onItemClick(userViewHolder.itemView,i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setOnItemClickListener(ItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
