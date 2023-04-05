package ro.pub.cs.systems.eim.Colocviul1_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Colocviul1_1MainActivity extends AppCompatActivity {

    TextView pressed;
    Button north;
    Button south;
    Button east;
    Button west;
    private Integer serviceStatus = Constants.SERVICE_STOPPED;
    Button navigate;

    Integer number = 0;

    StringBuffer text = new StringBuffer();
    private IntentFilter intentFilter = new IntentFilter();


    ButtonListener buttonListener = new ButtonListener();
    class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (text.length() != 0 && v.getId() != R.id.button_navigate) {
                text.append(",");
                number++;
            } else if (v.getId() != R.id.button_navigate) {
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

            if (v.getId() == R.id.button_navigate) {
                Intent intent = new Intent(getApplicationContext(), Colocviul1_1SecondaryActivity.class);
                intent.putExtra("TOTAL_BUTTON_PRESSED",text.toString());
                startActivityForResult(intent, 1);
                text = new StringBuffer();
                number = 0;
            }

            pressed.setText(text.toString());

            System.out.println("number este " + String.valueOf(number));

            if (number == 4) {
                System.out.println("intra in if");
                Intent intent = new Intent(getApplicationContext(), Colocviul1_1Service.class);
                intent.putExtra("first", text.toString());
                getApplicationContext().startService(intent);
                serviceStatus = Constants.SERVICE_STARTED;
            }
        }
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("DEBUG", intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA));
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

        intentFilter.addAction("actiuneaSalbatica");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
}