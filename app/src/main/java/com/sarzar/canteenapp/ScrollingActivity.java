package com.sarzar.canteenapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.sarzar.canteenapp.databinding.ActivityScrollingBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ScrollingActivity extends AppCompatActivity {

    // creating variables for
    // EditText and buttons.
    private TextView empFoodCatEdt, empFoodItEdt, empPlateLeftEdt, empPlatesPendingEdt, empLunchTimeEdt;
    private String empCodeedt;

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    Order Order;

    private ActivityScrollingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initializing our edittext and button
        empCodeedt = getTitle().toString();
        empFoodCatEdt = findViewById(R.id.empFoodCat);
        empFoodItEdt = findViewById(R.id.empFoodIt);
        empPlateLeftEdt = findViewById(R.id.empPlateLeft);
        empPlatesPendingEdt = findViewById(R.id.empPlatesPending);
        empLunchTimeEdt = findViewById(R.id.empLunchTime);

        // below line is used to get the
        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("Order");

        // initializing our object
        // class variable.
        Order = new Order();


        binding = ActivityScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Get User Name from first page
        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("MyData");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("MyData");
        }

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        //toolBarLayout.setTitle(getTitle());
        toolBarLayout.setTitle(newString);

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Voice activated", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        CardView cardView=findViewById(R.id.cvEatHearty);
        cardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getApplicationContext(),TestActivity.class);
                startActivity(intent);
            }
        });

        CardView cardView1=findViewById(R.id.cvEatLight);
        cardView1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getApplicationContext(),TestActivity.class);
                startActivity(intent);
            }
        });

        CardView cardView2=findViewById(R.id.cvEatGreen);
        cardView2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getApplicationContext(),TestActivity.class);
                startActivity(intent);
            }
        });

        CardView cardView3=findViewById(R.id.cvTraditional);
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.
                String nameCode = newString;
                //String FoodCat = empFoodCatEdt.getText().toString();
                //String FoodIt = empFoodItEdt.getText().toString();
                //String PlateLeft = empPlateLeftEdt.getText().toString();
                //String PlatesPending = empPlatesPendingEdt.getText().toString();
                //String LunchTime = empLunchTimeEdt.getText().toString();
                String FoodCat = "FoodTest2";
                String FoodIt = "FoodNameTest";
                String PlateLeft = "PlatesLeftTest";
                String PlatesPending = "PlatesPendingTest";
                String LunchTime = "LunchTimeTest";

                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(nameCode) && TextUtils.isEmpty(FoodCat) && TextUtils.isEmpty(FoodIt)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(ScrollingActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(nameCode, FoodCat, FoodIt, PlateLeft, PlatesPending, LunchTime);
                }
            }
        });


    }

    private void addDatatoFirebase(String nameCode, String FoodCat, String FoodIt, String PlateLeft, String PlatesPending, String LunchTime) {
        // below 3 lines of code is used to set
        // data in our object class.
        Order.setNameCode(nameCode);
        Order.setFoodCat(FoodCat);
        Order.setFoodIt(FoodIt);
        Order.setPlateLeft(PlateLeft);
        Order.setPlatesPending(PlatesPending);
        Order.setLunchTime(LunchTime);

        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(Order);

                // after adding this data we are showing toast message.
                Toast.makeText(ScrollingActivity.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(ScrollingActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}