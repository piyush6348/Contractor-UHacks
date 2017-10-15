package uhack.contractor.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.ArrayList;

import uhack.contractor.R;
import uhack.contractor.model.Inventory;

/**
 * Created by nimit on 14/10/17.
 */

public class InventoryListAdapter extends RecyclerView.Adapter<InventoryListAdapter.InventoryListViewHolder> {

    Context context;
    ArrayList<Inventory> inventoryArrayList;

    public InventoryListAdapter(Context context, ArrayList<Inventory> inventoryArrayList) {
        this.context = context;
        this.inventoryArrayList = inventoryArrayList;
    }

    @Override
    public InventoryListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.inventory_list_item, parent, false);

        final InventoryListViewHolder vh = new InventoryListViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                AddTransactionDialogFragment addTransactionDialogFragment = new AddTransactionDialogFragment(
                        inventoryArrayList.get(vh.getAdapterPosition()).getInventoryQty(),
                        inventoryArrayList.get(vh.getAdapterPosition()).getInventoryId()
                );
                addTransactionDialogFragment.show(fragmentManager, "TAG");
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(InventoryListViewHolder holder, int position) {
        holder.tvInventoryName.setText(inventoryArrayList.get(position).getInventoryName());
        holder.tvQty.setText(String.valueOf(inventoryArrayList.get(position).getInventoryQty()));
    }

    @Override
    public int getItemCount() {
        return inventoryArrayList.size();
    }

    class InventoryListViewHolder extends RecyclerView.ViewHolder{

        private TextView tvInventoryName, tvQty;
        public InventoryListViewHolder(View itemView) {
            super(itemView);
            tvInventoryName = (TextView) itemView.findViewById(R.id.tv_inventory_name);
            tvQty = (TextView) itemView.findViewById(R.id.tv_inventory_qty);
        }
    }
}
