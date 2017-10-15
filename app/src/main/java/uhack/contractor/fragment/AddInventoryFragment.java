package uhack.contractor.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import uhack.contractor.FirebaseReference;
import uhack.contractor.R;
import uhack.contractor.model.Inventory;
import uhack.contractor.utils.FirebaseLinks;


/**
 * Created by piyush on 14/10/17.
 */

public class AddInventoryFragment extends DialogFragment implements FirebaseLinks {

    private EditText etInventoryName, etInventoryQty;
    private Button btnDone;
    private String projectID;

    public AddInventoryFragment(String projectID) {
        this.projectID = projectID;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle("Add New Inventory");
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MyDialogFragmentStyle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle("Add New Inventory");

        View view = inflater.inflate(R.layout.fragment_add_inventory, container);
        initializer(view);
        // lvContractors.setAdapter(new ContractorsListAdapter(getList(),getActivity()));
        return view;
    }

    private void initializer(View view) {
        etInventoryName = (EditText) view.findViewById(R.id.et_inventory_name);
        etInventoryQty = (EditText) view.findViewById(R.id.et_inventory_qty);

        btnDone = (Button) view.findViewById(R.id.btn_done);
        // lvContractors = (ListView) view.findViewById(R.id.lv_contractors);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendProjectDetailsToFirebase();
            }
        });
    }

    private void sendProjectDetailsToFirebase() {
        // SuperPrefs superPrefs = new SuperPrefs(getActivity());
        final DatabaseReference databaseReference = FirebaseReference.inventoryReference
                .push();
        databaseReference.setValue(new Inventory(
                databaseReference.getKey(),
                etInventoryName.getText().toString(),
                Integer.parseInt(etInventoryQty.getText().toString())
        ));

       /* FirebaseReference.builderReference.child(superPrefs.getString(FirebaseLinks.BUILDER_ID))
                .child(FirebaseLinks.PROJECT_IDS).push().setValue(databaseReference.getKey());*/

        FirebaseReference.projectReference.child(projectID).child("inventoryIDs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<String> stringArrayList = new ArrayList<String>();
                if(dataSnapshot!=null) {
                    for (DataSnapshot data : dataSnapshot.getChildren())
                        stringArrayList.add(data.getValue(String.class));
                }
                stringArrayList.add(databaseReference.getKey());
                FirebaseReference.projectReference.child(projectID).child("inventoryIDs").removeEventListener(this);
                FirebaseReference.projectReference.child(projectID).child("inventoryIDs").setValue(stringArrayList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        getDialog().dismiss();
    }


}
