/**
 * 22 July 2012
 */

package buet.rafi.google_translator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class GoogleTranslatorActivity extends Activity {
	
	private EditText oringinText;
	private Spinner fromLang;
	private Spinner toLang;
	
	private TextView transText;
	private TextView reTransText;
	
	private TextWatcher textWatcher;
	private OnItemSelectedListener itemSelectedListener;
	
	private Handler guiThread;
	private ExecutorService transThread;
	private Runnable updateTask;
	private Future transPending;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        initThreading();
        findViews();
        findAdaptars();
        setListeners();
    }


	private void initThreading() {
		guiThread = new Handler();
		transThread = Executors.newSingleThreadExecutor();
		
		updateTask = new Runnable() {
			
			public void run() {
				String original = oringinText.getText().toString().trim();
				
				if(transPending != null)
					transPending.cancel(true);
				
				if(original.length() == 0) {
					transText.setText(R.string.empty);
					reTransText.setText(R.string.empty);
				}
				else {
					transText.setText(R.string.translating);
					reTransText.setText(R.string.translating);
				}
				
				try {
					TranslateTask translateTask = new TranslateTask(
							GoogleTranslatorActivity.this, original, getLang(fromLang), getLang(toLang));
					transPending = transThread.submit(translateTask);
				}
				catch(RejectedExecutionException executionException) {
					
					transText.setText(R.string.translate_error);
					reTransText.setText(R.string.translate_error);
				}
			}

			private String getLang(Spinner spinner) {
				String result = spinner.getSelectedItem().toString();
				int leftParen = result.indexOf('(');
				int rightParen = result.indexOf(')');
				
				return result.substring(leftParen + 1, rightParen);
			}
		};
	}


	private void findViews() {
		oringinText = (EditText) findViewById(R.id.original_text);
		fromLang = (Spinner) findViewById(R.id.from_language);
		toLang = (Spinner) findViewById(R.id.to_language);
		transText = (TextView) findViewById(R.id.translated_text);
		reTransText = (TextView) findViewById(R.id.retranslated_text);
	}


	private void findAdaptars() {
		
		ArrayAdapter<CharSequence> adapter = 
				ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		fromLang.setAdapter(adapter);
		toLang.setAdapter(adapter);
	}


	private void setListeners() {
		textWatcher = new TextWatcher() {
			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				queueUpdate(1000);
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		};
		
		itemSelectedListener = new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				queueUpdate(200);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		
		oringinText.addTextChangedListener(textWatcher);
		fromLang.setOnItemSelectedListener(itemSelectedListener);
		toLang.setOnItemSelectedListener(itemSelectedListener);
	}

	private void queueUpdate(int delayMillis) {
		guiThread.removeCallbacks(updateTask);
		guiThread.postDelayed(updateTask, delayMillis);
	}
	
	void setTranslated(String text) {
		guiSetText(transText, text);
	}

	void setReTranslated(String text) {
		guiSetText(reTransText, text);
	}
	
	private void guiSetText(final TextView textView, final String text) {
		guiThread.post(new Runnable() {
			
			public void run() {
				textView.setText(text);
			}
		});
	}
}