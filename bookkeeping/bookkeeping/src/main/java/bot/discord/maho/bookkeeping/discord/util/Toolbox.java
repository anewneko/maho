package bot.discord.maho.bookkeeping.discord.util;

public class Toolbox {
	public static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        char firstChar = input.charAt(0);
        char capitalizedFirstChar = Character.toUpperCase(firstChar);

        return capitalizedFirstChar + input.substring(1);
    }
}
