package majapp.myapplication;

import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SettingsActivity extends AppCompatActivity {
    private TextView animationSpeedTextView;
    private TextView countTextView;
    private Switch onlineSwitch;
    private SeekBar seekBar;
    private SeekBar countSeekBar;
    private Toolbar toolbar;
    private Handler seekBarHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        initializeGuiElements();
    }

    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0)
            fm.popBackStack();
        else
            super.onBackPressed();
    }

    private void initializeGuiElements()
    {
        seekBarHandler = new Handler();
        animationSpeedTextView = (TextView)findViewById(R.id.animationSpeedTextView);
        countTextView = (TextView)findViewById(R.id.countTextView);
        onlineSwitch = (Switch)findViewById(R.id.onlineSwitch);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        countSeekBar = (SeekBar)findViewById(R.id.countSeekBar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        switch (SettingsHolder.getInstance().getSettings().getAnimationSpeed())
        {
            case SLOW:
                setSeekBarValue(0);
                break;
            case MIDDLE:
                setSeekBarValue(1);
                break;
            case FAST:
                setSeekBarValue(2);
                break;
        }

        setCountSeekBarValue(SettingsHolder.getInstance().getSettings().getStepCount() - 10);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (progress){
                    case 0:
                        animationSpeedTextView.setText(R.string.slowSpeedText);
                        SettingsHolder.getInstance().getSettings().setAnimationSpeed(AnimationSpeedEnum.SLOW);
                        break;
                    case 1:
                        animationSpeedTextView.setText(R.string.middleSpeedText);
                        SettingsHolder.getInstance().getSettings().setAnimationSpeed(AnimationSpeedEnum.MIDDLE);
                        break;
                    case 2:
                        animationSpeedTextView.setText(R.string.fastSpeedText);
                        SettingsHolder.getInstance().getSettings().setAnimationSpeed(AnimationSpeedEnum.FAST);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        countSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int value = progress + 10;
                countTextView.setText(Integer.toString(value));
                SettingsHolder.getInstance().getSettings().setStepsCount(value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setSeekBarValue(final int value) {
        seekBarHandler.post(new Runnable() {
            @Override
            public void run() {
                if (seekBar != null) {
                    seekBar.setMax(0);
                    seekBar.setMax(2);
                    seekBar.setProgress(value);
                }
            }
        });
    }

    private void setCountSeekBarValue(final int value) {
        seekBarHandler.post(new Runnable() {
            @Override
            public void run() {
                if (countSeekBar != null) {
                    countSeekBar.setMax(0);
                    countSeekBar.setMax(990);
                    countSeekBar.setProgress(value);
                }
            }
        });
    }
}
