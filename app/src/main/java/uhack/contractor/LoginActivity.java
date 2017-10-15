package uhack.contractor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uhack.contractor.activities.FirstViewActivity;
import uhack.contractor.utils.FirebaseLinks;
import uhack.contractor.utils.SuperPrefs;

import java.util.ArrayList;
import java.util.HashMap;



public class LoginActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        checkLoginStatus();

        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_pass);

        ((Button)findViewById(R.id.btn_login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(etEmail.getText().toString(), etPassword.getText().toString());
            }
        });
    }

    private void login(final String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                        else {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user!=null) {
                                SuperPrefs.newInstance(LoginActivity.this).setString(FirebaseLinks.CONTRACTOR_ID,
                                        user.getUid());
                                Log.d(TAG, "onComplete: " + user.getUid());

                               // HashMap<String, Builder> map = new HashMap<>();
                                /*FirebaseReference.builderReference.child(user.getUid()).setValue(
                                    new Builder("Siddharth","9873142234" , user.getUid(),
                                           email , new ArrayList<String>())
                                );*/

                                updateUI();

                            }
                        }

                        // ...
                    }
                });
    }

    private void updateUI() {
        // startActivity(new Intent(LoginActivity.this, ));
        startActivity(new Intent(this,FirstViewActivity.class));
        finish();
    }

    private void checkLoginStatus() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    updateUI();
                    /*FirebaseReference.builderReference.child(user.getUid()).setValue(
                            new Builder("Siddharth","9873142234" , user.getUid(),
                                    "sid5869@gmail.com", new ArrayList<String>()));
*/

                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out

                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
