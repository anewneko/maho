package bot.discord.maho.core.Util;

import java.util.UUID;

public class StringTool {
	public static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) 
            return input;
        char firstChar = input.charAt(0);
        char capitalizedFirstChar = Character.toUpperCase(firstChar);
        return capitalizedFirstChar + input.substring(1);
    }
	
	public static Boolean isUUID(String input) {
		try {
			UUID.fromString(input);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}
}
