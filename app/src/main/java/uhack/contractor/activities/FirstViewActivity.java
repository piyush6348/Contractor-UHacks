package uhack.contractor.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import uhack.contractor.FirebaseReference;
import uhack.contractor.R;
import uhack.contractor.adapters.ProjectListAdapter;
import uhack.contractor.model.Project;
import uhack.contractor.utils.FirebaseLinks;
import uhack.contractor.utils.SuperPrefs;

public class FirstViewActivity extends AppCompatActivity {

    private SuperPrefs superPrefs;
    private RecyclerView recyclerView;

    private ArrayList<Project> projectList;
    private ArrayList<String> listOfProjectIDs;
    private ProjectListAdapter projectListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_view);

        superPrefs = new SuperPrefs(this);

        listOfProjectIDs = new ArrayList<>();
        setUpAdapter();

        FirebaseReference.contractorReference.child(superPrefs.getString(FirebaseLinks.CONTRACTOR_ID))
                .child("projectIDs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listOfProjectIDs.clear();
                for (DataSnapshot itr:dataSnapshot.getChildren()) {
                    listOfProjectIDs.add(itr.getValue(String.class));
                    Log.e("onDataChange: ", itr.getValue(String.class));
                }
                updateUI();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setUpAdapter() {
        projectList = new ArrayList<Project>();
        projectListAdapter = new ProjectListAdapter(
                projectList,
                FirstViewActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(projectListAdapter);
    }

    private void updateUI() {

        for( int k=0;k<listOfProjectIDs.size();k++){

            FirebaseReference.projectReference.child(listOfProjectIDs.get(k)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    projectList.add(dataSnapshot.getValue(Project.class));
                    //FirebaseReference.projectReference.child(listOfProjectIDs.get(i.getVal())).removeEventListener(this);
                    projectListAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }


}
