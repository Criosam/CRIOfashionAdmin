package com.sn77.crio.criofashionadmin.date20200627921am;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText product_name;
    EditText product_description;
    EditText product_price;

    private DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference=FirebaseDatabase.getInstance().getReference().child("products");
        product_name=findViewById(R.id.productName);
        product_price=findViewById(R.id.productPrice);
        product_description=findViewById(R.id.productDescription);
    }

    public void prodectAdd(View view) {
        String nameProduct=product_name.getText().toString();
        final String priceProduct=product_price.getText().toString();
        String descriptionProduct=product_description.getText().toString();

        DatabaseReference productPushKey=databaseReference.push();
        String pushKey=productPushKey.getKey();
//onk gulo item aksathe database a push korar jonne Hashmap use kora hoi
        HashMap<String,String> productHashMap=new HashMap<>();
        productHashMap.put("name",nameProduct);
        productHashMap.put("price",priceProduct);
        productHashMap.put("description",descriptionProduct);

        databaseReference.child(pushKey).setValue(productHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "product added Successfully!", Toast.LENGTH_SHORT).show();
                    product_name.setText("");
                    product_price.setText("");
                    product_description.setText("");
                }

                else{

                    Toast.makeText(MainActivity.this, "Try again!", Toast.LENGTH_SHORT).show();
                }

            }
        });





    }
}