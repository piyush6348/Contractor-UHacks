package uhack.contractor;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by piyush on 14/10/17.
 */

public class FirebaseReference extends Application {

    public static FirebaseDatabase database;
    public static DatabaseReference projectReference;
    public static DatabaseReference builderReference;
    public static DatabaseReference inventoryReference;
    public static DatabaseReference contractorReference;
    public static DatabaseReference transactionReference;

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseApp.initializeApp(getApplicationContext());


        database = FirebaseDatabase.getInstance();
        projectReference = database.getReference();
        projectReference = database.getReference().child("Projects");
        builderReference = database.getReference().child("Builder");
        inventoryReference = database.getReference().child("Inventory");
        contractorReference = database.getReference().child("Contractor");
        transactionReference = database.getReference().child("Transaction");

    }
}
