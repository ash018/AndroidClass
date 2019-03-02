package buet.rafi.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		SmsMessage[] messages;
		String str = "";
		
		if(bundle != null) {
			Object[] pdus = (Object[]) bundle.get("pdus");
			messages = new SmsMessage[pdus.length];
			
			for(int i = 0; i < messages.length; i++) {
				messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
				str += "SMS from " + messages[i].getDisplayOriginatingAddress() + ": ";
				str += messages[i].getMessageBody().toString() + "\n";
			}
			Toast.makeText(context, str, Toast.LENGTH_LONG).show();
		}
		Log.d("SMS", "received");
		this.abortBroadcast();
	}

}
