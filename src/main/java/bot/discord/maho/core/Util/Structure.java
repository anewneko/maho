package bot.discord.maho.core.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import bot.discord.maho.core.Model.DbEntity;

public class Structure {
	
	/** data :
	 * {
	 *	 key1:"value1",
	 *	 key2:"value2",
	 *   key3:"value3",
	 *   key4:"value4",
	 * }
	 */	
	public static <T extends DbEntity> Map<UUID, T> toKeyValueMap(List<T> list) {
		return list.stream()
				   .filter(el -> el != null)
				   .filter(el -> el.getId() != null)
				   .collect(Collectors.toMap(el -> el.getId(), 
										  	 el -> el, 
										  	 (v1, v2) -> v1));
	}
	
	/** data :
	 * {
	 *	 key:"key1",
	 *	 value:"value1"
	 * },
	 * {
 *  	 key:"key2",
	 *   value:"value2"
	 * }
	 */	
	public static <T extends DbEntity> List<Map<String, String>> toNumberValueMap(List<T> list , Function<T, String> vFunction) {
		return list.stream()
				   .filter(el -> el != null)
				   .map(el -> {
					   Map<String,String> obj = new HashMap<>();
					   obj.put("key", el.getId().toString());
					   obj.put("value", vFunction.apply(el));
					   return obj;
				   })
				   .collect(Collectors.toList());
	}
	

}
