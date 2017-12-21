package com.yogeshbirthdaywisher.birthdaywisher.background;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.yogeshbirthdaywisher.birthdaywisher.background.Background;

public class BootUpReceiver extends BroadcastReceiver{
	@Override
	public void onReceive(Context context,Intent intent)
	{
		final Context c = context;
		try{
	        	Intent i = new Intent(context,Background.class);
	        	context.startService(i);
		}catch(Exception e)
		{
			Toast.makeText(c, e.toString(), Toast.LENGTH_LONG).show();
		}
	}
}