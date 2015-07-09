package technology.xor.bomby;

import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Handler;

public class BombyMain extends ActionBarActivity {

    private Button sendBtn;
    private EditText toPhone;
    private EditText message;
    private EditText frequency;
    private TextView numberSent;
    private int counter;
    private int ix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bomby_main);

        sendBtn = (Button) findViewById(R.id.sendBtn);
        toPhone = (EditText) findViewById(R.id.toPhone);
        message = (EditText) findViewById(R.id.message);
        frequency = (EditText) findViewById(R.id.frequency);
        numberSent = (TextView) findViewById(R.id.numberSent);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberSent.setText("Sent: ");
                counter = 0;
                sendSMS();
            }
        });


    }

    protected void sendSMS() {
        final String toPhoneNumber = toPhone.getText().toString().trim();
        final String smsMessage = message.getText().toString();
        String freq = frequency.getText().toString().trim();

        message.setText("");
        frequency.setText("");

        Handler handler = new Handler();

        try {
            final SmsManager smsManager = SmsManager.getDefault();

            for (int ix = 0; ix < Integer.parseInt(freq); ix++) {
                handler.postDelayed(new Runnable() {
                    public void run() {
                        smsManager.sendTextMessage(toPhoneNumber, null, smsMessage, null, null);
                        counter+=1;
                        numberSent.setText("Sent: " + Integer.toString(counter));
                    }
                }, ix * 5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bomby_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            this.finish();
            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
