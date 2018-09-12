package com.example.qamberhaider.mazdor_user;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qamber.haider on 4/17/2018.
 */

public class Jobs_Fragment extends Fragment {

    public Jobs_Fragment(){

    }

    public final static String Database_Path = "JOBS"; // Root Database Name for Firebase Database.
    static String UID;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog ;
    List<Listdata> list;
    RecyclerView recyclerview;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.jobs, container, false);

        recyclerview = (RecyclerView) v.findViewById(R.id.rview);


        mDatabase = FirebaseDatabase.getInstance().getReference(Database_Path);
        firebaseAuth = FirebaseAuth.getInstance();


        UID = firebaseAuth.getCurrentUser().getUid();

        Log.v("Loc", UID);

        progressDialog = new ProgressDialog(getContext());

        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                list = new ArrayList<>();
                // StringBuffer stringbuffer = new StringBuffer();


                try {
                    for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                        Job_class userdetails = dataSnapshot1.getValue(Job_class.class);

                            Listdata listdata = new Listdata();
                            String name=userdetails.getJob_title();
                            String email=userdetails.getJob_location();
                            String address=userdetails.getJob_rate();
                            listdata.setName(name);
                            listdata.setEmail(email);
                            listdata.setAddress(address);
                            list.add(listdata);
                            progressDialog.dismiss();

                    }
                }
                catch (Exception e) {
                        Toast.makeText(getContext(), "Exception applied", Toast.LENGTH_LONG).show();
                        Log.e(e.getClass().getName(), e.getMessage(), e);
                        progressDialog.dismiss();
                }

                RecyclerviewAdapter recycler = new RecyclerviewAdapter(getActivity(),list);
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(getContext());
                recyclerview.setLayoutManager(layoutmanager);
                recyclerview.setItemAnimator( new DefaultItemAnimator());
                recyclerview.setAdapter(recycler);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //  Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(getContext(), "No post Uploaded Yet", Toast.LENGTH_SHORT).show();
            }
        });




        return v;
    }


}
