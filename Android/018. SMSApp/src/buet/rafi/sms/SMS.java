/**
 * 25 July 2012
 */

package buet.rafi.sms;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMS extends Activity {

	private EditText phoneNoField;
	private EditText messageField;
	private Button sendButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		phoneNoField = (EditText) findViewById(R.id.phone_number_field);
		messageField = (EditText) findViewById(R.id.message_field);
		sendButton = (Button) findViewById(R.id.send_button);

		sendButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String phoneNo = phoneNoField.getText().toString();
				String message = messageField.getText().toString();

				if(phoneNo.length() == 0 || message.length() == 0)
					Toast.makeText(SMS.this, "Please enter both phone number and message", Toast.LENGTH_SHORT)
						.show();
				else
					sendSMS(phoneNo, message);
			}
		});
	}

	private void sendSMS(String phoneNo, String message) {
		PendingIntent smsIntent = PendingIntent.getActivity(this, 0, new Intent(this, SMS.class), 0);
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(phoneNo, null, message, smsIntent, null);
	}
}