package buet.rafi.widget;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class Widget extends AppWidgetProvider {
	private SimpleDateFormat dateFormat = 
			new SimpleDateFormat("EEEEEEEEE\nd MMM yyyy");
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
		String currentTime = dateFormat.format(new Date());
		
		RemoteViews updateViews = new RemoteViews(context.getPackageName(), R.layout.main);
		Log.d("WIDGET", context.getPackageName());
		updateViews.setTextViewText(R.id.text, currentTime);
		appWidgetManager.updateAppWidget(appWidgetIds, updateViews);
	}

}
