package majapp.myapplication;

public class TrajectoryHolder {
    private static TrajectoryHolder dataObject = null;

    private TrajectoryHolder() {
        // left blank intentionally
    }

    public static TrajectoryHolder getInstance() {
        if (dataObject == null)
            dataObject = new TrajectoryHolder();
        return dataObject;
    }
    private ThrowTrajectory throwTrajectory;

    public ThrowTrajectory getThrowTrajectory() {
        return throwTrajectory;
    }

    public void setThrowTrajectory(ThrowTrajectory throwTrajectory) {
        this.throwTrajectory = throwTrajectory;
    }
}
