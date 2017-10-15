package uhack.contractor.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import uhack.contractor.FirebaseReference;
import uhack.contractor.R;
import uhack.contractor.activities.FirstViewActivity;
import uhack.contractor.adapters.ProjectListAdapter;
import uhack.contractor.adapters.WorkerListRecyclerAdapter;
import uhack.contractor.model.Project;
import uhack.contractor.model.Worker;
import uhack.contractor.utils.FirebaseLinks;
import uhack.contractor.utils.SuperPrefs;


public class WorkersFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public WorkersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WorkersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WorkersFragment newInstance(String param1, String param2) {
        WorkersFragment fragment = new WorkersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private ArrayList<Worker> workerList;
    private ArrayList<String> listOfWorkerIDs;
    WorkerListRecyclerAdapter workerListRecyclerAdapter;

    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_workers, container, false);


        workerList = new ArrayList<>();
        listOfWorkerIDs = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_worker_list);
        setUpAdapter();
        SuperPrefs superPrefs = new SuperPrefs(getActivity());
        FirebaseReference.contractorReference.child("0").child(superPrefs.getString(FirebaseLinks.CONTRACTOR_ID))
                .child("workerIds").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listOfWorkerIDs.clear();
                for (DataSnapshot itr:dataSnapshot.getChildren()) {
                    listOfWorkerIDs.add(itr.getValue(String.class));
                    Log.e("onDataChange: ", itr.getValue(String.class));
                }
                updateUI();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        /*(view.findViewById(R.id.fab_add_worker)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                AddInventoryFragment addInventoryFragment = new AddInventoryFragment(groupID);
                addInventoryFragment.show(fragmentManager, "TAG");
            }
        });*/
        return view;
    }
    private void setUpAdapter() {
        workerList = new ArrayList<Worker>();
        workerListRecyclerAdapter = new WorkerListRecyclerAdapter(
                getActivity(),
                workerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(workerListRecyclerAdapter);
    }
    private void updateUI() {

        for( int k=0;k<listOfWorkerIDs.size();k++){

            FirebaseReference.workerReference.child(listOfWorkerIDs.get(k)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    workerList.add(dataSnapshot.getValue(Worker.class));
                    //FirebaseReference.projectReference.child(listOfProjectIDs.get(i.getVal())).removeEventListener(this);
                    workerListRecyclerAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }


}
