package com.example.qamberhaider.mazdor_user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Console;


public class Signup extends AppCompatActivity implements View.OnClickListener {


    public final static String Database_Path = "USERS"; // Root Database Name for Firebase Database.
    static String UID;

    ProgressDialog progressDialog ;
    private EditText Eemail;
    private EditText Epass;
    private Button Reg_coch;
    EditText fname,phone;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    private StorageReference mStorage;

    String naMe,paS,phonE,emaIl;
    TextView LoginRoot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference(Database_Path);
        firebaseAuth = FirebaseAuth.getInstance();

//        if (firebaseAuth.getCurrentUser() != null) {
//            //profile ativity
//            finish();
//            startActivity(new Intent(getApplicationContext(), Home.class));
//        }


        progressDialog = new ProgressDialog(this);


        Eemail = (EditText) findViewById(R.id.userEmail);
        Epass = (EditText) findViewById(R.id.userPass);
        Reg_coch = (Button) findViewById(R.id.reg);
        fname = (EditText) findViewById(R.id.username);
        phone = (EditText) findViewById(R.id.userPhon);

        LoginRoot = (TextView) findViewById(R.id.loginRoot);


        LoginRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this,Login.class);
                startActivity(intent);
            }
        });

        Reg_coch.setOnClickListener(this);



    }


    private void Authemployee(){

        //progressDialog.setCancelable(false);


        naMe = fname.getText().toString().trim();
        phonE = phone.getText().toString().trim();
        emaIl = Eemail.getText().toString().trim();
        paS = Epass.getText().toString().trim();

        if (TextUtils.isEmpty(naMe)){
            fname.setError("Required");
            return;
        }
        if (TextUtils.isEmpty(phonE)){
            phone.setError("Required");
            return;

        }
        if (TextUtils.isEmpty(emaIl)){
            Eemail.setError("Required");
            return;

        }
        if (TextUtils.isEmpty(paS)){
            Epass.setError("Required");
            return;

        }


        progressDialog.setMessage("Please Wait");
        progressDialog.show();





        firebaseAuth.createUserWithEmailAndPassword(emaIl, paS)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            try {
                                UID = firebaseAuth.getCurrentUser().getUid();
                                Log.v("yes",UID);
                                SaveData();
                                Toast.makeText(getApplicationContext(), "Successfull", Toast.LENGTH_LONG).show();

                            }
                            catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Exception applied", Toast.LENGTH_LONG).show();
                                Log.e(e.getClass().getName(), e.getMessage(), e);
                                progressDialog.dismiss();
                            }
                        }
                        else{

                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();

                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Signup.this, "Error in Auth", Toast.LENGTH_SHORT).show();
            }
        });







    }

    public void SaveData(){


        User_Details  user_details  = new User_Details(naMe , phonE , emaIl , paS ,UID);
        mDatabase.child(UID).setValue(user_details, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseReference.equals(databaseError)){
                    progressDialog.dismiss();
                    Toast.makeText(Signup.this , "Error in Saving" , Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Intent i = new Intent(Signup.this , Home.class);
                    startActivity(i);
                }

            }
        });

    }


    @Override
    public void onClick(View view) {
        if(view == Reg_coch){

            Authemployee();

        }

    }

}
