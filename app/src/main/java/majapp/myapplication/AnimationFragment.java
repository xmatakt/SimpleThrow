package majapp.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class AnimationFragment extends Fragment {
    private View view;
    private ImageView projectileImage;
    private ImageView playButton;
    private ImageView resetButton;
    private ThrowTrajectory trajectory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_animation_fragment, container, false);
        trajectory = TrajectoryHolder.getInstance().getThrowTrajectory();
        projectileImage = (ImageView)view.findViewById(R.id.projectileImage);
        playButton = (ImageView)view.findViewById(R.id.playButton);
        resetButton = (ImageView)view.findViewById(R.id.resetButton);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projectileImage.clearAnimation();
                final int left = projectileImage.getLeft();
                final int top = projectileImage.getTop();
                final int right = projectileImage.getRight();
                final int bottom = projectileImage.getBottom();
                projectileImage.layout(left, top, right, bottom);
                playButton.setVisibility(View.VISIBLE);
                resetButton.setVisibility(View.INVISIBLE);
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playButton.setVisibility(View.INVISIBLE);
                AnimateThrow(0, SettingsHolder.getInstance().getSettings().getAnimationSpeedInt());
            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void AnimateThrow(final int positionInList, final int speed)
    {
        Log.d("speed", "speed = " + speed);

        if(view == null || trajectory == null
                || positionInList >= trajectory.Trajectory.size() - 1)
        {
            resetButton.setVisibility(View.VISIBLE);
            return;
        }

        Log.d("positionInList", "position = " + positionInList);

        float startX = trajectory.Trajectory.get(positionInList).x;
        float startY = trajectory.Trajectory.get(positionInList).y;
        float endX = trajectory.Trajectory.get(positionInList + 1).x;
        float endY = trajectory.Trajectory.get(positionInList + 1).y;

        if(positionInList == 0)
            projectileImage.clearAnimation();

        TranslateAnimation transAnim = new TranslateAnimation(startX, endX, -startY, -endY);
        transAnim.setStartOffset(0);
        transAnim.setDuration(speed);
        transAnim.setFillAfter(true);

        transAnim.setInterpolator(new LinearInterpolator());
//        transAnim.setInterpolator(new AccelerateDecelerateInterpolator());
//        transAnim.setInterpolator(new BounceInterpolator());
        transAnim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                AnimateThrow(positionInList + 1, speed);
            }
        });

        projectileImage.startAnimation(transAnim);
    }

    private int getDisplayHeight() {
        return this.getResources().getDisplayMetrics().heightPixels;
    }

    private int getDisplayWidth() {
        return this.getResources().getDisplayMetrics().widthPixels;
    }
}

