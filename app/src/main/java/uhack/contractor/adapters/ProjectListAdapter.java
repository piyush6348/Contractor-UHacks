package uhack.contractor.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

import uhack.contractor.R;
import uhack.contractor.model.Project;

/**
 * Created by piyush on 14/10/17.
 */

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ProjectViewHolder> {
    ArrayList<Project> projectArrayList;
    Context context;

    public ProjectListAdapter(ArrayList<Project> projectArrayList, Context context) {
        this.projectArrayList = projectArrayList;
        this.context = context;
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.project_list_item,parent,false);

        final ProjectViewHolder vh = new ProjectViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(context, ProjectDetails.class);
               // intent.putExtra("project-id", projectArrayList.get(vh.getAdapterPosition()).getProjectID());
               // context.startActivity(intent);
                //vh.getAdapterPosition();
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        holder.tvProjectName.setText(projectArrayList.get(position).getName());
        holder.tvProjectLocation.setText(projectArrayList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return projectArrayList.size();
    }

    class ProjectViewHolder extends RecyclerView.ViewHolder{
        private TextView tvProjectName, tvProjectLocation;
        public ProjectViewHolder(View itemView) {
            super(itemView);
            tvProjectName = (TextView) itemView.findViewById(R.id.tv_project_name);
            tvProjectLocation = (TextView) itemView.findViewById(R.id.tv_project_location);
        }
    }
}
