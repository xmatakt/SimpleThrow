package majapp.myapplication;

import android.graphics.PointF;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private List<PointF> trajectory;
    private float[] t;

    public CustomAdapter(List<PointF> trajectory, float[] t) {
        this.trajectory = trajectory;
        this.t = t;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.CEILING);
        PointF point = trajectory.get(position);
        holder.xAxis.setText(df.format(point.x));
        holder.yAxis.setText(df.format(point.y));
        holder.tAxis.setText(df.format(t[position]));
        holder.position.setText("-" + (position + 1) + "-");
    }

    @Override
    public int getItemCount() {
        return trajectory.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView xAxis, yAxis, tAxis, position;

        public MyViewHolder(View view) {
            super(view);
            tAxis  =(TextView) view.findViewById(R.id.tAxisTextView);
            xAxis = (TextView) view.findViewById(R.id.xAxisTextView);
            yAxis = (TextView) view.findViewById(R.id.yAxisTextView);
            position = (TextView) view.findViewById(R.id.positionTextView);
        }
    }
}
