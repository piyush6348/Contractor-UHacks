package uhack.contractor.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


import uhack.contractor.R;
import uhack.contractor.model.Worker;


/**
 * Created by piyush on 7/10/17.
 */

public class WorkerListRecyclerAdapter extends RecyclerView.Adapter<WorkerListRecyclerAdapter.HospitalViewHolder> {

    private Context context;
    private ArrayList<Worker> workerArrayList;

    public WorkerListRecyclerAdapter(Context context, ArrayList<Worker> workerArrayList) {
        this.context = context;
        this.workerArrayList = workerArrayList;
    }

    @Override
    public HospitalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.worker_list_item,parent,false);

        final HospitalViewHolder hospitalViewHolder = new HospitalViewHolder(view);


        hospitalViewHolder.ib_call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e( "onClick: ", "call");
                Uri number = Uri.parse("tel:"+workerArrayList.get(hospitalViewHolder.getAdapterPosition()).getMobile());
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                context.startActivity(callIntent);
            }
        });

        hospitalViewHolder.ibMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return hospitalViewHolder;
    }

    @Override
    public void onBindViewHolder(HospitalViewHolder holder, int position) {

        holder.tvHospitalName.setText(workerArrayList.get(position).getName());
        holder.tvHospitalDistance.setText(workerArrayList.get(position).getMobile()+"");

    }

    @Override
    public int getItemCount() {
        return workerArrayList.size();
    }

    public class HospitalViewHolder extends RecyclerView.ViewHolder{

        private TextView tvHospitalName,tvHospitalDistance;
        private LinearLayout rowLayout;
        private ImageButton ib_call_btn, ibMessage;
        public HospitalViewHolder(View itemView) {
            super(itemView);

            tvHospitalName = (TextView) itemView.findViewById(R.id.tv_worker_name);
            tvHospitalDistance = (TextView) itemView.findViewById(R.id.tv_mobile);
            rowLayout = (LinearLayout) itemView.findViewById(R.id.row_layout);
            ib_call_btn = (ImageButton) itemView.findViewById(R.id.ib_call);
            ibMessage = itemView.findViewById(R.id.ib_msg);
        }
    }
}
