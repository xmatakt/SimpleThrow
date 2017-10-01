package majapp.myapplication;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public class ThrowTrajectory
{
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
        float tStep = (tEnd - tStart)/nSteps;

        for(float t = 0; t <= tEnd; t += tStep)
        {
            float x = x0 + speed * t * cos;
            float y = y0 + speed * t * sin - 0.5f * g * t * t;

            if(MaxY < y) MaxY = y;
            if(MinX > x) MinX = x;
            if(MaxX < x) MaxX = x;

            Trajectory.add(new PointF(x, y));

            if(t > 0 && y <= 0)
                break;
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
