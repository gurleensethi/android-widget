package com.thetehnocafe.gurleensethi.widgets;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;

public class ClickIntentService extends IntentService {

    public static final String ACTION_CLICK = "com.thetehnocafe.gurleensethi.widgets.click";

    public ClickIntentService() {
        super("ClickIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();

            if (ACTION_CLICK.equals(action)) {
                handleClick();
            }
        }
    }

    private void handleClick() {
        int clicks = getSharedPreferences("sp", MODE_PRIVATE).getInt("clicks", 0);
        clicks++;
        getSharedPreferences("sp", MODE_PRIVATE)
                .edit()
                .putInt("clicks", clicks)
                .commit();

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] widgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, MyAppWidget.class));
        for (int appWidgetId : widgetIds) {
            MyAppWidget.updateAppWidget(getApplicationContext(), appWidgetManager, appWidgetId);
        }
    }
}
