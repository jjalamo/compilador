package compiler.code;

import java.util.LinkedHashMap;


public class TextManager {
	private static LinkedHashMap<String, String> textsHashMap = new LinkedHashMap<String, String>();
	private static int i = 3;

	public static LinkedHashMap<String, String> getText()
	{
		return textsHashMap;
	}

	public static String getKeyText() 
	{
		return ("text" + i++);
	}

	public static void addText(String key, String text) 
	{
		text += "\0";
		textsHashMap.put(key, text);
	}
}
