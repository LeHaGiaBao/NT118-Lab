package com.example.lab2_6;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public  class EmployeeAdapter extends  RecyclerView.Adapter<EmployeeAdapter.EmployeeHolder>{
    private Activity context=null;
    private int layoutID;
    private List<Employee> mListEmployee=null;

    public EmployeeAdapter(Activity context, int layoutID, List<Employee> mListEmployee) {
        this.context = context;
        this.layoutID = layoutID;
        this.mListEmployee = mListEmployee;
    }

    public List<Employee> getmListEmployee() {
        return mListEmployee;
    }

    public void setmListEmployee(List<Employee> mListEmployee) {
        this.mListEmployee = mListEmployee;
    }
    class EmployeeHolder extends RecyclerView.ViewHolder {
        TextView tvFullName, tvPosition;
        ImageView ivManager;
        LinearLayout llParent;
        public EmployeeHolder(@NonNull View itemView) {
            super(itemView);
            tvFullName = (TextView)
                    itemView.findViewById(R.id.item_employee_tv_fullname);
            tvPosition = (TextView)
                    itemView.findViewById(R.id.item_employee_tv_position);
            ivManager = (ImageView)
                    itemView.findViewById(R.id.item_employee_iv_manager);
            llParent = (LinearLayout)
                    itemView.findViewById(R.id.item_employee_ll_parent);
        }

    }
    @NonNull
    @Override
    public EmployeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_employee, null,
                false);
        return new EmployeeHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull EmployeeHolder holder, int position) {
        Employee employee = mListEmployee.get(position);
        if (employee == null ) {
            return;
        }
        if (employee.getFullName()!=null) {
            holder.tvFullName.setText(employee.getFullName());
        }
        else holder.tvFullName.setText("");
        // If this is a manager -> show icon manager. Otherwise, show Staff in tvPosition
        if (employee.isManager())
        {
            holder.ivManager.setVisibility(View.VISIBLE);
            holder.tvPosition.setVisibility(View.GONE);
        }
        else
        {
            holder.ivManager.setVisibility(View.GONE);
            holder.tvPosition.setVisibility(View.VISIBLE);
            holder.tvPosition.setText(context.getString(R.string.staff));
        }
        // Show different color backgrounds for 2 continuous employees
        if (position%2==0)
        {
            holder.llParent.setBackgroundResource(R.color.white);
        }
        else
        {

            holder.llParent.setBackgroundResource(R.color.light_blue);
        }
    }

    @Override
    public int getItemCount() {
        return mListEmployee.size();
    }
}



