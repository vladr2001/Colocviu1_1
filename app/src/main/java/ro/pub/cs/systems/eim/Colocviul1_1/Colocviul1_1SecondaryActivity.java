package ro.pub.cs.systems.eim.Colocviul1_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Colocviul1_1SecondaryActivity extends AppCompatActivity {

    EditText total;
    Button ok;
    Button cancel;


    ButtonListener buttonListener = new ButtonListener();
    class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.okButton) {
                setResult(RESULT_OK, null);
            } else if (v.getId() == R.id.cancelButton) {
                setResult(RESULT_CANCELED, null);
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviul1_1_secondary);

        total = (EditText) findViewById(R.id.editTextTotal);
        ok = (Button) findViewById(R.id.okButton);
        cancel = (Button) findViewById(R.id.cancelButton);
        ok.setOnClickListener(buttonListener);
        cancel.setOnClickListener(buttonListener);

        Intent intent = getIntent();
        if (intent != null) {
            String totalReceived = intent.getStringExtra("TOTAL_BUTTON_PRESSED");
            if (totalReceived != null) {
                total.setText(totalReceived);
            } else {
                Toast.makeText(this, "Number is empty!", Toast.LENGTH_LONG).show();
            }
        }
    }
}