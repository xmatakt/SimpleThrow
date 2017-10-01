package majapp.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListFragment extends Fragment{
    private View view;
    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_list_fragment, container, false);
        ThrowTrajectory trajectory = TrajectoryHolder.getInstance().getThrowTrajectory();
        if(trajectory != null)
            displayReceivedData(trajectory);
        return view;
    }

    public void displayReceivedData(ThrowTrajectory trajectory)
    {
            TextView tv = (TextView)view.findViewById(R.id.textView);
            tv.setText("");

            recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);

            customAdapter = new CustomAdapter(trajectory.Trajectory);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(customAdapter);
    }
}
