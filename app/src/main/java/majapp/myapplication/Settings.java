package majapp.myapplication;

import android.widget.SeekBar;

public class Settings {
    private boolean isOnline;
    private AnimationSpeedEnum animationSpeed;
    private int stepsCount;

    public Settings() {
        isOnline = false;
        animationSpeed = AnimationSpeedEnum.MIDDLE;
        stepsCount = 100;
    }

    public Settings(boolean isOnline, AnimationSpeedEnum animationSpeed, int stepsCount) {
        this.isOnline = isOnline;
        this.animationSpeed = animationSpeed;
        this.stepsCount = stepsCount;
    }

    public boolean getIsOnline(){
        return isOnline;
    }

    public void setIsOnline(boolean value){
        isOnline = value;
    }

    public AnimationSpeedEnum getAnimationSpeed(){
        return animationSpeed;
    }

    public int getAnimationSpeedInt(){
        switch(animationSpeed){
            case SLOW:
                return 100;
            case MIDDLE:
                return 50;
            case FAST:
                return 10;
            default:
                return 10;
        }
    }

    public void setAnimationSpeed(AnimationSpeedEnum value){
        animationSpeed = value;
    }

    public int getStepCount(){
        return stepsCount;
    }

    public void setStepsCount(int value){
        stepsCount = value;
    }
}
