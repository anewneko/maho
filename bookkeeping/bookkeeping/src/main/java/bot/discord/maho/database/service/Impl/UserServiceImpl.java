package bot.discord.maho.bookkeeping.database.service.Impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bot.discord.maho.bookkeeping.database.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//					帳號		密碼 	權限清單
		new User(username, username, null);
		return null;
	}


}
