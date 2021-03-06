package com.anna.sent.soft.simplewidget;

import java.util.Calendar;

import com.anna.sent.soft.simplewidget.R;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

public class SimpleWidgetConfigure extends Activity {
	int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setResult(RESULT_CANCELED);
		setContentView(R.layout.simple_widget_configure);

		// First, get the App Widget ID from the Intent that launched the
		// Activity:
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null) {
			mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);

			// Perform App Widget configuration.
		}

		if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
			Toast.makeText(this, R.string.errorInvalidAppWidgetId,
					Toast.LENGTH_SHORT).show();
			finish();
		}
	}

	public void click(View v) {
		Context context = SimpleWidgetConfigure.this;

		// When the configuration is complete, get an instance of the
		// AppWidgetManager
		AppWidgetManager appWidgetManager = AppWidgetManager
				.getInstance(context);

		// Update the App Widget with a RemoteViews layout
		RemoteViews views = SimpleWidget.buildViews(context,
				Calendar.getInstance());
		appWidgetManager.updateAppWidget(mAppWidgetId, views);

		// Finally, create the return Intent, set it with the Activity
		// result, and finish the Activity:
		Intent resultValue = new Intent();
		resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
		setResult(RESULT_OK, resultValue);
		finish();
	}
}
