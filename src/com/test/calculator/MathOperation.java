package com.test.calculator;

public class MathOperation 
{
	private double operand1;
	private double operand2;
	private char operator;
	
	private Logger logger;
	
	public MathOperation(Logger logger)
	{
		this.logger = logger;
		operand1 = Double.MIN_VALUE;
		operand2 = Double.MIN_VALUE;
		operator = '+';
	}
	
	public boolean setOperand(double value)
	{
		if (operand1 == Double.MIN_VALUE)
		{
			operand1 = value;
			return true;
		}

		if (operand2 == Double.MIN_VALUE)
		{
			operand2 = value;
			return true;
		}
		
		operand1 = operand2;
		operand2 = value;
		return true;
	}
	
	public boolean setOperator(char operator)
	{
		this.operator = operator;
		return true;
	}
	
	public double getResult()
	{
		if (operand1 == Double.MIN_VALUE)
		{
			logger.addText("0.0");
			return 0.0;
		}
		
		if (operand2 == Double.MIN_VALUE)
		{
			logger.addText(Double.toString(operand1));
			return operand1;
		}
		
		double total = 0.0;
		
		switch(operator)
		{
			case '+':
			{
				total = operand1 + operand2;
				break;
			}
			case '-':
			{
				total = operand1 - operand2;
				break;
			}
			case '*':
			{
				total = operand1 * operand2;
				break;
			}
			case '/':
			{
				total = operand1 / operand2;
				break;
			}
		}
		
		logger.addText(String.format("%.2f %c %.2f = %.2f", operand1, operator, operand2, total));
		
		operand1 = total;
		operand2 = Double.MIN_VALUE;
		
		return total;
	}
}
