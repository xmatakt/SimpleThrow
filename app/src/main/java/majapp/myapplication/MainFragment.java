package majapp.myapplication;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainFragment extends Fragment implements View.OnClickListener {
    View view;
    private Button simulateButton;
    private EditText angleEditText;
    private EditText speedEditText;
    private DataSender dataSender;
    private boolean isAngleValid = false;

    interface DataSender{
        void sendData(ThrowTrajectory trajectory);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_main_fragment, container, false);
        InitializeGuiItems();
        simulateButton.setOnClickListener(this);

        angleEditText.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                boolean isEmpty = speedEditText.getText().toString().length() == 0;
                if(s.toString().length() != 0 && !isEmpty && isAngleValid) {
                    simulateButton.setEnabled(true);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                simulateButton.setEnabled(false);
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String textValue = angleEditText.getText().toString();
                if(textValue.length() == 0)
                    return;

                final float value = Float.parseFloat(textValue);

                if(value <= 0 || value >= 90)
                {
                    isAngleValid = false;
                    Snackbar.make(view, R.string.badAngleValueText, Snackbar.LENGTH_INDEFINITE)
                            .setAction("CLOSE", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    isAngleValid = true;
                                    if(value <= 0)
                                        angleEditText.setText("1");
                                    else
                                        angleEditText.setText("89");
                                }
                            })
                            .setActionTextColor(Color.RED)
                            .show();
                }
                else
                    isAngleValid = true;
            }
        });

        speedEditText.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                boolean isEmpty = angleEditText.getText().toString().length() == 0;
                if(s.toString().length() != 0 && !isEmpty && isAngleValid)
                    simulateButton.setEnabled(true);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                simulateButton.setEnabled(false);
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String textValue = speedEditText.getText().toString();
                if(textValue.length() == 0)
                    return;

                final float value = Float.parseFloat(textValue);

                if(value <= 0)
                {
                    isAngleValid = false;
                    Snackbar.make(view, R.string.badSpeedValueText, Snackbar.LENGTH_INDEFINITE)
                            .setAction("CLOSE", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            })
                            .setActionTextColor(Color.RED)
                            .show();
                }
                else
                    isAngleValid = true;
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            dataSender = (DataSender) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }

    @Override
    public void onClick(View v) {
        float speed = Float.valueOf(speedEditText.getText().toString());
        float angle = Float.valueOf(angleEditText.getText().toString());
        SimulateThrow(speed, angle);

        Vibrator vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(350);
        Toast.makeText(getActivity(), R.string.computationSuccessfulText,
                Toast.LENGTH_LONG).show();
    }

    private void InitializeGuiItems()
    {
        simulateButton = (Button)view.findViewById(R.id.simulateButton);
        angleEditText = (EditText)view.findViewById(R.id.degreeEditText);
        speedEditText = (EditText)view.findViewById(R.id.speedEditText);
        simulateButton.setEnabled(false);
    }

    private void SimulateThrow(float speed, float angle)
    {
        int nSteps  = SettingsHolder.getInstance().getSettings().getStepCount();
        ThrowTrajectory trajectory = new ThrowTrajectory(speed, angle, nSteps);
        TrajectoryHolder.getInstance().setThrowTrajectory(trajectory);
        dataSender.sendData(trajectory);
    }
}
