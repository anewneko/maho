package bot.discord.maho.database.CrudService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Example;

public interface CrudService<T> {
	
	public List<T> findAll();
	
	public Optional<T> findById(UUID id);
	
	public List<T> findByExample(Example<T> t);
	
	public T save(T t);
	
	public List<T> saveAll(List<T> t);
	
	public void delete(T t);
	
	public void deleteById(UUID id);
	
}
