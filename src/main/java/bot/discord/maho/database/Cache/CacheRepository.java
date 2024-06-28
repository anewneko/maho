package bot.discord.maho.database.Cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

import bot.discord.maho.core.Model.DbEntity;
import bot.discord.maho.core.Util.Structure;

public abstract class CacheRepository<V extends DbEntity> {
	protected boolean initialized = true;
	protected Map<UUID,V> map = new HashMap<UUID,V>();
	
	
	public synchronized CacheRepository<V> fetch(Supplier<V> fetcher){
		async(() -> {
            var v = fetcher.get();
            if(v != null) 
            	map.putAll(Structure.toKeyValueMap(List.of(v)));
            
        });
		return this;
	}
	
	public synchronized CacheRepository<V> fetchAll(Supplier<Collection<V>> fetcher){
		async(() -> {
			var coll = fetcher.get();
			var list = new ArrayList<V>(coll);
			map.putAll(Structure.toKeyValueMap(list));
		});
		return this;
	}
	
	public synchronized V get(UUID key) {
		return await().map.get(key);
	}
	
	public synchronized V getOrFetch(UUID key, Supplier<V> fetcher) {
		await();
		V v;
		if (!map.containsKey(key)) {
			v = fetcher.get();
			if (v != null)
				map.put(key, v);
		} else {
			v = map.get(key);
		}
		
		return v;
	}
	
	public synchronized Collection<V> values() {
		return await().map.values();
	}
	
	public synchronized Set<UUID> keySet() {
		return await().map.keySet();
	}
	
	protected synchronized void async(Runnable r) {
		initialized = false;
		ExecutorService executor = Executors.newSingleThreadExecutor();
		var future = executor.submit(() -> {
			try {
				r.run();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				this.initialized = true;
			}
		});
		
		executor.shutdown();
		
		try {
			future.get();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			notifyAll();
		}
	}
	
	protected CacheRepository<V> await() {
		while (!initialized) {
			try {
				System.out.println("Waiting for initialization");
				wait(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return this;
	}

}
