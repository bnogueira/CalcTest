package com.test.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalcTest extends Activity 
{
	MathOperation math = null;
	Logger logger = null;
	boolean expectingNewNumber = true;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TextView tv = (TextView)findViewById(R.id.log); 
        tv.setMovementMethod(new ScrollingMovementMethod()); 
        
        logger = new Logger(this, tv);
        math = new MathOperation(logger);
    }

    /** Calc implementation **/
    public void totalOnClick(View view)
    {
    	expectingNewNumber = true;
    	
    	// get total editText
    	EditText totalEditText = (EditText)findViewById(R.id.total);
    	
    	// feed MathOperation with current value
    	
    	String totalText = totalEditText.getText().toString();
    	
    	double newValue = Double.valueOf(totalText);
    	math.setOperand(newValue);
    	
    	// update value
    	totalEditText.setText(Double.toString(math.getResult()));
    }
    
    public void numberOnClick(View view)
    {
    	// get button pressed
    	Button b = (Button)view;
    	CharSequence buttonDesc = b.getText();
    	
    	// get total editText
    	EditText totalEditText = (EditText)findViewById(R.id.total);
    	
    	if (expectingNewNumber)
    	{
    		totalEditText.setText(buttonDesc);
    	}
    	else
    	{
    		totalEditText.setText(totalEditText.getText().append(buttonDesc));
    		
    	}
    	
    	expectingNewNumber = false;
    }
    
    public void operationOnClick(View view)
    {
    	expectingNewNumber = true;
    	EditText totalEditText = (EditText)findViewById(R.id.total);
    	
    	// get button pressed
    	Button b = (Button)view;
    	
    	// get operation
    	char operation = b.getText().charAt(0);

    	// is it clear?
    	if (operation == 'C')
    	{
    		math = new MathOperation(logger);
    		totalEditText.setText("0.0");
    		return;
    	}
    	
    	// get the value
    	double newValue = Double.valueOf(totalEditText.getText().toString());

    	// use 
    	math.setOperand(newValue);
    	math.setOperator(operation);
    }
    
    /** Main menu **/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.clear_log:
            logger.clear();
        	return true;
        case R.id.save_log:
        	logger.save();
            return true;
        case R.id.exit:
        	finish();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}













