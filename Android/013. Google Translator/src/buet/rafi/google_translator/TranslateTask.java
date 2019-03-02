package buet.rafi.google_translator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class TranslateTask implements Runnable{

	private static final String TAG = "TranslateTask";
	private GoogleTranslatorActivity translatorActivity;
	private final String original;
	private final String from;
	private final String to;

	public TranslateTask(GoogleTranslatorActivity translatorActivity,
			String original, String from, String to) {

		this.translatorActivity = translatorActivity;
		this.original = original;
		this.from = from;
		this.to = to;
	}

	public void run() {
		String trans = doTranslate(original, from, to);
		translatorActivity.setTranslated(trans);
		
		String reTrans = doTranslate(trans, to, from);
		translatorActivity.setReTranslated(reTrans);
	}

	private String doTranslate(String original, String from,
			String to) {
		String result = translatorActivity.getResources().getString(
				R.string.translate_error);
		HttpURLConnection con = null;
		Log.d(TAG, "doTranslate(" + original + ", " + from + ", "
				+ to + ")" );
		try {
			// Check if task has been interrupted
			if (Thread.interrupted())
				throw new InterruptedException();
			// Build RESTful query for Google API
			String q = URLEncoder.encode(original, "UTF-8" );
			URL url = new URL(
					"http://ajax.googleapis.com/ajax/services/language/translate"
							+ "?v=1.0" + "&q=" + q + "&langpair=" + from
							+ "%7C" + to);
			con = (HttpURLConnection) url.openConnection();
			con.setReadTimeout(10000);
			con.setConnectTimeout(15000);
			con.setRequestMethod("GET" );
			con.addRequestProperty("Referer" ,
					"http://www.pragprog.com/titles/eband3/hello-android" );
			con.setDoInput(true);
			// Start the query
			con.connect();
			// Check if task has been interrupted
			if (Thread.interrupted())
				throw new InterruptedException();
			// Read results from the query
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(con.getInputStream(), "UTF-8" ));
			String payload = reader.readLine();
			reader.close();
			// Parse to get translated text
			JSONObject jsonObject = new JSONObject(payload);
			result = jsonObject.getJSONObject("responseData" )
					.getString("translatedText" )
					.replace("&#39;" , "'" )
					.replace("&amp;" , "&" );
			// Check if task has been interrupted
			if (Thread.interrupted())
				throw new InterruptedException();
		} catch (IOException e) {
			Log.e(TAG, "IOException" , e);
		} catch (JSONException e) {
			Log.e(TAG, "JSONException" , e);
		} catch (InterruptedException e) {
			Log.d(TAG, "InterruptedException" , e);
			result = translatorActivity.getResources().getString(
					R.string.translate_error);
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		// All done
		Log.d(TAG, " -> returned " + result);
		return result;
	}
}
