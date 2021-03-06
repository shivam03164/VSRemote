 package com.example.remoteacess;

 

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import android.os.AsyncTask;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.example.remoteacess.KeypadHandler;
import com.example.remoteacess.TouchpadHandler;

import com.example.network.NetInput;
import com.example.network.Background;


public class MainActivity extends Activity {
	
	CharSequence cs[] = { "Keyboard", "Live Screen", "Voice" };
	   
	OnClickListener leftEvent = new OnClickListener() {
	        public void onClick(View v) {
	            NetInput.LeftClick();
	        }
	    };

	    OnClickListener rightEvent = new OnClickListener() {
	        public void onClick(View v) {
	            NetInput.RightClick();
	        }
	    };

	    OnClickListener menuEvent = new OnClickListener() {
	        public void onClick(View v) {
	            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
	            builder.setTitle(R.string.app_name)
	                   .setItems(cs, new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int which) {
	                    switch(which) {
	                   

	                    case 0://keyboard
	                        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
	                        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
	                        imm.showSoftInput(layout, InputMethodManager.SHOW_FORCED);
	                        break;
	                        
	                    case 1://Live Screen
	                    		Background task = new Background();
	                    			task.execute("0","0","7");
	                    		startActivity(new Intent(getBaseContext(), Screen.class));
	                    		
	            	    	break;

	                    case 2://Send File
                			startActivity(new Intent(getBaseContext(), Voice.class));
	                        break;
	                    }
	                }
	            });
	            builder.show();
	        }
	    };

	   

	    private Context context;
	    private KeypadHandler keypadHandler = new KeypadHandler();
	    private RelativeLayout layout;
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        context = this;
	        layout = (RelativeLayout)findViewById(R.id.background);
	        final Button leftClick = (Button)findViewById(R.id.leftClick);
	        final Button rightClick = (Button)findViewById(R.id.rightClick);
	        final ImageButton menuClick = (ImageButton)findViewById(R.id.menuButton);
	        layout.setOnTouchListener(new TouchpadHandler());
	        layout.setOnKeyListener(keypadHandler);
	        leftClick.setOnClickListener(leftEvent);
	        rightClick.setOnClickListener(rightEvent);
	        menuClick.setOnClickListener(menuEvent);
	    }	    
	}
