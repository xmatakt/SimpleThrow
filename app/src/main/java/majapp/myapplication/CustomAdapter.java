package majapp.myapplication;

import android.graphics.PointF;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private List<PointF> trajectory;

    public CustomAdapter(List<PointF> trajectory) {
        this.trajectory = trajectory;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PointF point = trajectory.get(position);
        holder.xAxis.setText(Float.toString(point.x));
        holder.yAxis.setText(Float.toString(point.y));
    }

    @Override
    public int getItemCount() {
        return trajectory.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView xAxis, yAxis;

        public MyViewHolder(View view) {
            super(view);
            xAxis = (TextView) view.findViewById(R.id.xAxisTextView);
            yAxis = (TextView) view.findViewById(R.id.yAxisTextView);
        }
    }
}
