package majapp.myapplication;

import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Collections;
import java.util.List;

public class GraphFragment extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_graph_fragment, container, false);
        displayReceivedData();
        return view;
    }

    private void displayReceivedData()
    {
        ThrowTrajectory trajectory = TrajectoryHolder.getInstance().getThrowTrajectory();
        if(trajectory != null)
        {
            TextView tv = (TextView)view.findViewById(R.id.textView);
            tv.setText("");
            CreateGraph(trajectory);
        }
    }

    private void CreateGraph(ThrowTrajectory trajectory)
    {
        GraphView graph = (GraphView)view.findViewById(R.id.graph);

        graph.getViewport().setScalable(true);
        graph.getViewport().setMinX(trajectory.MinX);
        graph.getViewport().setMinY(0f);
        graph.getViewport().setMaxX(trajectory.MaxX);
        graph.getViewport().setMaxY(trajectory.MaxY);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(getGraphData(trajectory.Trajectory));
        series.setTitle("Trajektória šikmého vrhu");
        //series.setDrawDataPoints(true);
        //series.setDataPointsRadius(10f);
        //series.setColor(Color.GREEN);
        graph.addSeries(series);
    }

    private DataPoint[] getGraphData(List<PointF> trajectory){
        DataPoint[] result = new DataPoint[trajectory.size()];
        for (int i = 0; i < trajectory.size(); i++) {
            result[i] = new DataPoint(trajectory.get(i).x, trajectory.get(i).y);
        }

        return result;
    }
}
