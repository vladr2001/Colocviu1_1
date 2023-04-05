package ro.pub.cs.systems.eim.Colocviul1_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Colocviul1_1MainActivity extends AppCompatActivity {

    TextView pressed;
    Button north;
    Button south;
    Button east;
    Button west;
    Button navigate;

    Integer number = 0;

    StringBuffer text = new StringBuffer();

    ButtonListener buttonListener = new ButtonListener();
    class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (text.length() != 0 && v.getId() != R.id.button_navigate) {
                text.append(",");
                number++;
            }
            if (v.getId() == R.id.button_north) {
                text.append("North");
            }
            if (v.getId() == R.id.button_south) {
                text.append("South");
            }
            if (v.getId() == R.id.button_west) {
                text.append("West");
            }
            if (v.getId() == R.id.button_east) {
                text.append("East");
            }

            pressed.setText(text.toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviul1_1_main);

        pressed = (TextView) findViewById(R.id.textAlreadyPressed);
        north = (Button) findViewById(R.id.button_north);
        south = (Button) findViewById(R.id.button_south);
        east = (Button) findViewById(R.id.button_east);
        west = (Button) findViewById(R.id.button_west);
        navigate = (Button) findViewById(R.id.button_navigate);

        north.setOnClickListener(buttonListener);
        west.setOnClickListener(buttonListener);
        east.setOnClickListener(buttonListener);
        south.setOnClickListener(buttonListener);
        navigate.setOnClickListener(buttonListener);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("no_of_pressed", String.valueOf(number));
        Log.d("DEBUG", "Save instance state " + savedInstanceState.getString("number"));
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("DEBUG", "Restore instance state");
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("no_of_pressed")) {
            Log.d("DEBUG", "Restore instance state for left " + savedInstanceState.getString("no_of_pressed"));
            number = Integer.valueOf(savedInstanceState.getString("no_of_pressed"));
            Log.d("DEBUG", "Restore instance state with number " + number);
        }
    }

}