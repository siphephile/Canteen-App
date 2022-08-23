package com.sarzar.canteenapp;

import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

public class TestActivity extends AppCompatActivity {

    // creating variables for
    // EditText and buttons.
    private EditText empCodeedt, empFoodCatEdt, empFoodItEdt, empPlateLeftEdt, empPlatesPendingEdt, empLunchTimeEdt;
    private Button sendDatabtn;

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    Order Order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // initializing our edittext and button
        empCodeedt = findViewById(R.id.empCode);
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

        sendDatabtn = findViewById(R.id.idBtnSendData);

        // adding on click listener for our button.
        sendDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.
                String nameCode = empCodeedt.getText().toString();
                String FoodCat = empFoodCatEdt.getText().toString();
                String FoodIt = empFoodItEdt.getText().toString();
                String PlateLeft = empPlateLeftEdt.getText().toString();
                String PlatesPending = empPlatesPendingEdt.getText().toString();
                String LunchTime = empLunchTimeEdt.getText().toString();

                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(nameCode) && TextUtils.isEmpty(FoodCat) && TextUtils.isEmpty(FoodIt)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(TestActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(TestActivity.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(TestActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
