package com.example.app;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.res.Configuration;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
import android.hardware.input.InputManager;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.debug_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/** Called when the user clicks the Send button */
	public void forceClose(View view) {
	    // Do something in response to button
		String force_close = null;
		force_close.charAt(0);
		
	}
	
	/** Called when the user clicks the Send button */
	public void anr(View view) {
	    // Do something in response to button
		  try {
			    Thread.sleep(60*1000);
			  } catch (InterruptedException e) {
			    e.printStackTrace();
			  }
	}
	
	/** Called when the user clicks the Send button */
	@SuppressLint("NewApi")
	public void getDisplaySize(View view) {
	   TextView v = (TextView) this.findViewById(R.id.display_size);
	   Display display = getScreenDisplaySize();
	   int rotation = display.getRotation();
	   float refersh_rate = display.getRefreshRate();
	   String rstr;
	   switch(rotation){
	       case 0:{
	    	   rstr = "rotation:(0) portrait";
	       } break;
	       case 1:{
	    	   rstr = "rotation:(90)landscape";
	       } break;	       
	       case 2:{
	    	   rstr = "rotation:(180) reverse portrait";
	       } break;	       
	       case 3:{
	    	   rstr = "rotation:(270) reverse landscape";
	       } break;
	       default: rstr="error";
	   }
	   
	   Point p = new Point();
	   display.getRealSize(p);
	   DisplayMetrics metrics = new DisplayMetrics();
	   display.getMetrics(metrics);
	   //
	   Point p_dp = new Point();
	   display.getRealSize(p_dp);
	   DisplayMetrics dp_metrics = new DisplayMetrics();
	   display.getRealMetrics(dp_metrics);
	   float dpx = p_dp.x / metrics.density;
	   float dpy = p_dp.y / metrics.density;
	   p_dp.x = Math.round(dpx);
	   p_dp.y = Math.round(dpy);
	   
	   v.setText("display size in pixel: width=" + p.x + ", height=" + p.y + ")" + " \n " 
	              + rstr + " \n " 
			      + "refresh rate:"+refersh_rate+" \n "
	              + "dpi:("+ metrics.xdpi + ","+ metrics.ydpi+")" + "\n"
	              + "display size in dp: width="+ p_dp.x + ", height=" +  p_dp.y + "\n"
	              + "sdk version:" + getSdkVersion() + "\n"
	              + "product: "+ getProductName());
	}
	
	/** private help method to get screen size*/
	private Display getScreenDisplaySize() {
		WindowManager windowManager = (WindowManager)this.getSystemService(Service.WINDOW_SERVICE);
		return windowManager.getDefaultDisplay();
	}
	
	/** */
	private int getSdkVersion() {
		return Build.VERSION.SDK_INT;
	}
	
	/** */
	private String getProductName() {
		return  Build.PRODUCT;
	}
	
	/** */
	private boolean monkey() {
		return true;
	}
	
	private boolean injects(){
		return false;
	}
	
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);

	    // Checks the orientation of the screen
	    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
	        Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
	    } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
	        Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
	    }
	}
}
