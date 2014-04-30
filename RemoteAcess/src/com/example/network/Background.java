package com.example.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;
	 
    public class Background extends AsyncTask<String, String, String> {
		 @Override
		      protected String doInBackground(String... arg0) {
		    try {
		 
				String ipcadd;
				String x;
				String y;
				String z;
				String lelo;

		    	ipcadd = "172.17.91.166";
				 
				x = arg0[0].toString();
				y = arg0[1].toString();
				z = arg0[2].toString();
				
				
				Socket client = new Socket(ipcadd, 4420);  //connect to server
				PrintWriter printwriter = new PrintWriter(client.getOutputStream(),true);
		    
				if(z == "0" || z == "8")//if keyboard or voice message
				{
					 lelo = arg0[3].toString();
					 printwriter.write(x+"@");
				     printwriter.write(y+"@");
				     printwriter.write(z+"@");
				     printwriter.write(lelo+"@");
				}
		     
				else// for mouse message
				{
				
					printwriter.write(x+"@");
					printwriter.write(y+"@");
					printwriter.write(z+"@");
				}
		     
				printwriter.flush();
				printwriter.close();
				client.close();
		 
		    } catch (UnknownHostException e) {
		    		e.printStackTrace();
		    } catch (IOException e) {
		     e.printStackTrace();
		    }
		    return null;
		   }
};

