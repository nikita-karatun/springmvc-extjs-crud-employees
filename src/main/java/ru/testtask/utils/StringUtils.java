package ru.testtask.utils;

import java.io.UnsupportedEncodingException;

public class StringUtils
{
	private static final char PERCENT_SIGN = '%';
	
	public static String correctEncoding(String string)
	{
		try
		{
			return new String(string.getBytes("ISO-8859-1"), "UTF8");
		} catch (UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static String[] correctEncoding(String[] strings)
	{
		String[] correctedStrings = new String[strings.length];
		for (int i = 0; i < strings.length; i++)
		{
			correctedStrings[i] = correctEncoding(strings[i]);
		}
		return correctedStrings;
	}
	
	public static boolean stringIsNotNullAndNotEmpty(String string)
	{
		if (string == null)
			return false;
		if (string.isEmpty())
			return false;
		return true;
	}
	
	public static String placeBetweenPercentSigns(String string)
	{
		return PERCENT_SIGN + string + PERCENT_SIGN;
	}
}
