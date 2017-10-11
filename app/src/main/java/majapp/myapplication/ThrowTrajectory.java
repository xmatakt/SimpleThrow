package majapp.myapplication;

import android.graphics.Path;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public class ThrowTrajectory
{
    public float[] T;
    public List<PointF> Trajectory;
    public String[] TrajectoryStringArray;
    public float MinX, MaxX, MaxY;

    private float x0 = 0;
    private float y0 = 0;
    private float g = 9.81f;
    private float speed;
    private float angle;
    private int nSteps;

    public ThrowTrajectory(float speed, float angle, int nSteps)
    {
        this.speed = speed;
        this.angle = angle;
        this.nSteps = nSteps;
        Trajectory = new ArrayList<>();

        CalculateTrajectory();
        WriteTrajectoryToStringArray();
    }

    public ThrowTrajectory(String trajectoryData)
    {
        Trajectory = new ArrayList<>();

        CalculateTrajectory(trajectoryData);
        WriteTrajectoryToStringArray();
    }

    private void CalculateTrajectory(String data)
        {
        data = data.replace(',', '.');
        String[] splittedData = data.split("&");
        nSteps = splittedData.length;

        Trajectory = new ArrayList<>();
        MinX = Float.MAX_VALUE;
        MaxX = MaxY = Float.MIN_VALUE;
        T = new float[nSteps];

        int index = 0;
        for (String line: splittedData) {
            String[] splittedLine = line.split(";");
            float t = Float.parseFloat(splittedLine[0]);
            float x = Float.parseFloat(splittedLine[1]);
            float y = Float.parseFloat(splittedLine[2]);

            if(MaxY < y) MaxY = y;
            if(MinX > x) MinX = x;
            if(MaxX < x) MaxX = x;

            T[index++] = t;
            Trajectory.add(new PointF(x, y));
        }
    }

    private void CalculateTrajectory()
    {
        Trajectory = new ArrayList<>();
        MinX = Float.MAX_VALUE;
        MaxX = MaxY = Float.MIN_VALUE;

        float radians = (float)Math.toRadians(angle);
        float cos = (float)Math.cos(radians);
        float sin = (float)Math.sin(radians);

        float tStart = 0;
        float tEnd = (speed*sin + (float)(Math.sqrt(Math.pow(speed*sin, 2)) - 2 * g * y0)) / g;
        float tStep = (tEnd - tStart)/(nSteps - 1);

        T = new float[nSteps];

        int index = 0;
        for(float t = 0; t <= tEnd; t += tStep)
        {
            T[index++] = t;
            float x = x0 + speed * t * cos;
            float y = y0 + speed * t * sin - 0.5f * g * t * t;

            if(MaxY < y) MaxY = y;
            if(MinX > x) MinX = x;
            if(MaxX < x) MaxX = x;

            Trajectory.add(new PointF(x, y));
        }
    }
    
    private void WriteTrajectoryToStringArray()
    {
        TrajectoryStringArray = new String[Trajectory.size()];
        for (int i = 0; i < TrajectoryStringArray.length; i++)
        {
            PointF point = Trajectory.get(i);
            TrajectoryStringArray[i] = "x = " + point.x + "; y = " + point.y;
        }
    }

    public String toString()
    {
        return "Rýchlosť: " + speed + "\nUhol: " + angle;
    }
}
