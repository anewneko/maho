package bot.discord.maho.database.CrudService.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import bot.discord.maho.database.CrudService.CrudService;
import bot.discord.maho.database.Entity.ConsumptionItem;
import bot.discord.maho.database.Repository.ConsumptionItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsumptionItemService implements CrudService<ConsumptionItem> {
	private final ConsumptionItemRepository repo;

	@Override
	public List<ConsumptionItem> findAll() {
		return repo.findAll();
	}

	@Override
	public Optional<ConsumptionItem> findById(UUID id) {
		return repo.findById(id);
	}

	@Override
	public List<ConsumptionItem> findByExample(Example<ConsumptionItem> t) {
		return repo.findAll(t);
	}

	@Override
	public ConsumptionItem save(ConsumptionItem t) {
		return repo.save(t);
	}

	@Override
	public List<ConsumptionItem> saveAll(List<ConsumptionItem> t) {
		return repo.saveAll(t);
	}

	@Override
	public void delete(ConsumptionItem t) {
		repo.delete(t);
	}

	@Override
	public void deleteById(UUID id) {
        repo.deleteById(id);
	}

}
