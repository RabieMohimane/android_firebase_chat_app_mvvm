package com.google.firebase.codelab.friendlychat.views.chat;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.codelab.friendlychat.R;
import com.google.firebase.codelab.friendlychat.data.FriendlyMessage;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessageViewHolder> {
    private final  String TAG="MessagesAdapter";
    List<FriendlyMessage> messages=new ArrayList<>();


        void setMessageList(final List<FriendlyMessage> messageList){
            this.messages = messageList;
            notifyDataSetChanged();
        }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new MessageViewHolder(inflater.inflate(R.layout.item_message, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final MessageViewHolder viewHolder,
                                 int position) {
       FriendlyMessage friendlyMessage=messages.get(position);
       Log.e("friendly msg adaper",friendlyMessage.toString());
        if (friendlyMessage.getText() != null) {
            viewHolder.messageTextView.setText(friendlyMessage.getText());
            viewHolder.messageTextView.setVisibility(TextView.VISIBLE);
            viewHolder.messageImageView.setVisibility(ImageView.GONE);
        } else if (friendlyMessage.getUserImageUrl() != null) {
            String imageUrl = friendlyMessage.getUserImageUrl();
            if (imageUrl.startsWith("gs://")) {
                StorageReference storageReference = FirebaseStorage.getInstance()
                        .getReferenceFromUrl(imageUrl);
                storageReference.getDownloadUrl().addOnCompleteListener(
                        new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    String downloadUrl = task.getResult().toString();
                                    Glide.with(viewHolder.messageImageView.getContext())
                                            .load(downloadUrl)
                                            .into(viewHolder.messageImageView);
                                } else {
                                    Log.w(TAG, "Getting download url was not successful.",
                                            task.getException());
                                }
                            }
                        });
            } else {
                Glide.with(viewHolder.messageImageView.getContext())
                        .load(friendlyMessage.getUserImageUrl())
                        .into(viewHolder.messageImageView);
            }
            viewHolder.messageImageView.setVisibility(ImageView.VISIBLE);
            viewHolder.messageTextView.setVisibility(TextView.GONE);
        }


        viewHolder.messengerTextView.setText(friendlyMessage.getName());
        if (friendlyMessage.getPhotoUrl() == null) {
            viewHolder.messengerImageView.setImageDrawable(ContextCompat.getDrawable(viewHolder.itemView.getContext(),
                    R.drawable.ic_account_circle_black_36dp));
        } else {
            Glide.with(viewHolder.itemView.getContext())
                    .load(friendlyMessage.getPhotoUrl())
                    .into(viewHolder.messengerImageView);
        }

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}