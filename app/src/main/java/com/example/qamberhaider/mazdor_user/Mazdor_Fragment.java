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

public class Mazdor_Fragment extends Fragment {


    public final static String Database_Path = "Employee"; // Root Database Name for Firebase Database.
    static String UID;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog ;
    List<Listdata_Pro> pro_List;
    RecyclerView RV;
    Pro_RecycleviewAdapter Pro_mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.mazdor, container, false);

        RV = (RecyclerView) v.findViewById(R.id.Pr_list);


        mDatabase = FirebaseDatabase.getInstance().getReference(Database_Path);
        firebaseAuth = FirebaseAuth.getInstance();


        UID = firebaseAuth.getCurrentUser().getUid();

        Log.v("Loc", UID);

        progressDialog = new ProgressDialog(getContext());

        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        mDatabase.child("PROFILE").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                pro_List = new ArrayList<>();

                try {
                    for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                        Employee_details employee_details = dataSnapshot1.getValue(Employee_details.class);

                        Listdata_Pro dataE = new Listdata_Pro();
                        String Name=employee_details.getEmployee_FirstName();
                        String Type=employee_details.getEmployee_Designation();
                        dataE.setPRFirstName(Name);
                        dataE.setPRDesignation(Type);
                        pro_List.add(dataE);
                        progressDialog.dismiss();

                    }
                }
                catch (Exception e) {
                    Toast.makeText(getContext(), "Exception applied", Toast.LENGTH_LONG).show();
                    Log.e(e.getClass().getName(), e.getMessage(), e);
                    progressDialog.dismiss();
                }

                Pro_mAdapter= new Pro_RecycleviewAdapter(getActivity(),pro_List);
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(getContext());
                RV.setLayoutManager(layoutmanager);
                RV.setItemAnimator( new DefaultItemAnimator());
                RV.setAdapter(Pro_mAdapter);

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
