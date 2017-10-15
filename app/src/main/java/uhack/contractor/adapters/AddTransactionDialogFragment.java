package uhack.contractor.adapters;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;


import uhack.contractor.FirebaseReference;
import uhack.contractor.R;
import uhack.contractor.model.Transaction;
import uhack.contractor.utils.FirebaseLinks;
import uhack.contractor.utils.SuperPrefs;

/**
 * Created by piyush on 14/10/17.
 */

public class AddTransactionDialogFragment extends DialogFragment implements FirebaseLinks {

    private EditText etInventoryDelta, etComments;
    private Button btnDone;
    private RadioGroup rgIncDec;
    private RadioButton selected;

    private TextView tvCurrQty;
    private int currAmt;
    private String inventoryID;


    public AddTransactionDialogFragment(int currAmt, String inventoryID) {
        this.currAmt = currAmt;
        this.inventoryID = inventoryID;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle("Transaction");
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
        getDialog().setTitle("Transaction");

        View view = inflater.inflate(R.layout.fragment_add_transaction_dialog, container);
        initializer(view);
        // lvContractors.setAdapter(new ContractorsListAdapter(getList(),getActivity()));
        return view;
    }

    private void initializer(final View view) {
        etInventoryDelta = (EditText) view.findViewById(R.id.et_inventory_delta);
        etComments = view.findViewById(R.id.et_inventory_comments);
        rgIncDec = view.findViewById(R.id.radioGroup);

        tvCurrQty = view.findViewById(R.id.tv_cur_qty);

        tvCurrQty.setText(tvCurrQty.getText().toString()+ " - " + currAmt);
        btnDone = (Button) view.findViewById(R.id.btn_done);
        // lvContractors = (ListView) view.findViewById(R.id.lv_contractors);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId=rgIncDec.getCheckedRadioButtonId();
                selected=(RadioButton)view.findViewById(selectedId);
                sendProjectDetailsToFirebase((String)selected.getTag());
            }
        });
    }

    private void sendProjectDetailsToFirebase(String selectedid) {
         SuperPrefs superPrefs = new SuperPrefs(getActivity());



        if(Integer.parseInt(selectedid)==0) {

            //increase amt
            currAmt = currAmt + Integer.parseInt(etInventoryDelta.getText().toString());
            FirebaseReference.inventoryReference.child(inventoryID).child("inventoryQty").setValue(currAmt);
            DatabaseReference databaseReference = FirebaseReference.transactionReference.push();
            databaseReference.setValue(new Transaction(
                    databaseReference.getKey(), inventoryID, etComments.getText().toString(),
                    Integer.parseInt(selectedid), superPrefs.getString(FirebaseLinks.BUILDER_ID),
                    Integer.parseInt(etInventoryDelta.getText().toString())
            ));

        }
        else {
            // decrease amt
            currAmt = currAmt - Integer.parseInt(etInventoryDelta.getText().toString());
            FirebaseReference.inventoryReference.child(inventoryID).child("inventoryQty").setValue(currAmt);
            DatabaseReference databaseReference = FirebaseReference.transactionReference.push();
            databaseReference.setValue(new Transaction(
                    databaseReference.getKey(), inventoryID, etComments.getText().toString(),
                    Integer.parseInt(selectedid), superPrefs.getString(FirebaseLinks.CONTRACTOR_ID),
                    Integer.parseInt(etInventoryDelta.getText().toString())
            ));

        }






        getDialog().dismiss();
    }




}
