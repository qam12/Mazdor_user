package com.example.qamberhaider.mazdor_user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.qamberhaider.mazdor_user.RecyclerviewAdapter.KEY_RATE;
import static com.example.qamberhaider.mazdor_user.RecyclerviewAdapter.KEY_TITLE;
import static com.example.qamberhaider.mazdor_user.RecyclerviewAdapter.KEY_TYPE;

public class Job_Details extends AppCompatActivity {


    String JobTitle = "";
    String JobType = "";
    String Rate = "";
    //String Bc_no = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job__details);


//    public static final String KEY_REGNO = "RegNo";

        Intent intent = getIntent();
        if (null != intent) {
            JobTitle = intent.getStringExtra(KEY_TITLE);
            JobType = intent.getStringExtra(KEY_TYPE);
            Rate = intent.getStringExtra(KEY_RATE);
            //Bc_no = intent.getStringExtra(KEY_REGNO);
        }
        else {
            Toast.makeText(this, "Empty Fields", Toast.LENGTH_SHORT).show();
        }

        TextView Applicant = (TextView) findViewById(R.id.jobTI);
        Applicant.setText(JobTitle);

        TextView UID = (TextView) findViewById(R.id.jobTy);
        UID.setText(JobType);

        TextView Regno = (TextView) findViewById(R.id.jobRa);
        Regno.setText(Rate);

//        TextView Address = (TextView) findViewById(R.id.address);
//        Address.setText(address);

        System.out.println(JobTitle + "job_");

    }
}
