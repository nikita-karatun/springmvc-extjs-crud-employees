package ru.testtask.utils;

import java.io.UnsupportedEncodingException;

public class StringUtils
{
	private static final char PERCENT_SIGN = '%';

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
