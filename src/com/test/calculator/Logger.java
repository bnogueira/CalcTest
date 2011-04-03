package com.test.calculator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.widget.TextView;

public class Logger 
{
	String FILENAME = "Logger";
	Context ctx;
	TextView out = null;
	
	public Logger(Context ctx, TextView out)
	{
		this.ctx = ctx;
		this.out = out;
		
		try
		{
			// open file
			FileInputStream fis = ctx.openFileInput(FILENAME);
			
			// create reader
			InputStreamReader inputreader = new InputStreamReader(fis);
			BufferedReader buffreader = new BufferedReader(inputreader);
		 
			String line;
					 
			while ((line = buffreader.readLine()) != null) 
			{
				out.append(line);
				out.append("\n");
			}
			
			fis.close();
			out.scrollTo(out.getMeasuredWidth(), out.getMeasuredHeight()); 
		}
		catch(Exception ex)
		{
		}
	}
	
	public void addText(CharSequence value)
	{
		out.setText(value + "\n" + out.getText());
	}
	
	public void clear()
	{
		out.setText(null);
	}
	
	public void save()
	{
		try
		{
			FileOutputStream fos = ctx.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fos.write(out.getText().toString().getBytes());
			fos.close();
		}
		catch(Exception ex)
		{
		}
	}
}
