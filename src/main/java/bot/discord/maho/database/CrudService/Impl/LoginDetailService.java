package bot.discord.maho.database.CrudService.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import bot.discord.maho.database.CrudService.CrudService;
import bot.discord.maho.database.Entity.LoginDetail;
import bot.discord.maho.database.Repository.LoginDetailRepository;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class LoginDetailService implements CrudService<LoginDetail> {
	
	private final LoginDetailRepository repo;
	

	@Override
	public List<LoginDetail> findAll() {
		return repo.findAll();
	}

	@Override
	public Optional<LoginDetail> findById(UUID id) {
		return repo.findById(id);
	}

	@Override
	public List<LoginDetail> findByExample(Example<LoginDetail> t) {
		return repo.findAll(t);
	}

	@Override
	public LoginDetail save(LoginDetail t) {
		return repo.save(t);
	}

	@Override
	public List<LoginDetail> saveAll(List<LoginDetail> t) {
		return repo.saveAll(t);
	}

	@Override
	public void delete(LoginDetail t) {
		repo.delete(t);
	}

	@Override
	public void deleteById(UUID id) {
		repo.deleteById(id);
	}

}
