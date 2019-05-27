package com.google.firebase.codelab.friendlychat.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.codelab.friendlychat.common.FirebaseQueryLiveData;
import com.google.firebase.codelab.friendlychat.data.FriendlyMessage;
import com.google.firebase.codelab.friendlychat.views.chat.ChatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




public class MessagesListViewModel extends ViewModel {
    private static String TAG = "ListViewModel";
    private String otherUserName;
    private String nodeId;
    private  final DatabaseReference dataRef =
            FirebaseDatabase.getInstance().getReference().child("messages");

    private List<FriendlyMessage> mList = new ArrayList<>();




    public void setConversationParts(String otherUserName,String nodeId){
        this.nodeId=nodeId;
        this.otherUserName=otherUserName;
    }


    @NonNull
    public LiveData<List<FriendlyMessage>> getMessageListLiveData(){
        FirebaseQueryLiveData mLiveData = new FirebaseQueryLiveData(dataRef.child(nodeId));

        LiveData<List<FriendlyMessage>> mMessageLiveData =
                Transformations.map(mLiveData, new Deserializer());

        return mMessageLiveData;
    }

    private class Deserializer implements Function<DataSnapshot, List<FriendlyMessage>>{

        @Override
        public List<FriendlyMessage> apply(DataSnapshot dataSnapshot) {
            mList.clear();
            for(DataSnapshot snap : dataSnapshot.getChildren()){
                FriendlyMessage msg = snap.getValue(FriendlyMessage.class);
                mList.add(msg);
            }
            return mList;
        }
    }


    private String mPhoto = "";

    private final MutableLiveData<Boolean> pictureUploadIsSuccessful = new MutableLiveData<>();
    private final MutableLiveData<Boolean> messageUploadIsSuccessful = new MutableLiveData<>();







    public MutableLiveData<Boolean> getPictureUploadIsSuccessful(){
        return pictureUploadIsSuccessful;
    }

    public MutableLiveData<Boolean> getMessageUploadIsSuccessful(){
        return messageUploadIsSuccessful;
    }



    public void createAndSendToDataBase(String text, String name, String photoUrl, String imageUrl){
        FriendlyMessage entity = new FriendlyMessage(text,name,photoUrl,imageUrl);

        // push the new message to Firebase
        Task uploadTask = FirebaseDatabase.getInstance()
                .getReference()
                .child("messages")
                .child(nodeId)
                .push()
                .setValue(entity);
        uploadTask.addOnSuccessListener(o -> messageUploadIsSuccessful.setValue(true));
    }



}


