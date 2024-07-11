package bot.discord.maho.core.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public interface BatchQuery<V,K> {
	Integer LIMIT = 500;
	
	default  List<V> batchQuery(Collection<K> coll , Function<List<K> ,List<V>> function){
		List<V> total = new ArrayList<>();
		if(coll.size() > 0) {
			var keyListList = new ArrayList<List<K>>();
			var list = new ArrayList<K>(coll);
			for (int i = 0; i * LIMIT < list.size(); i++)
				if ((i + 1) * LIMIT > list.size())
					keyListList.add(list.subList(i * LIMIT, coll.size()));
				else
					keyListList.add(list.subList(i * LIMIT, (i + 1) * LIMIT));
			keyListList.forEach(el -> {
				var reslut = function.apply(el);
				total.addAll(reslut);
			});
		}
		return total;
	}

}
