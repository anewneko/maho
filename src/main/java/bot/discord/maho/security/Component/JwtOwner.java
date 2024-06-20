package bot.discord.maho.security.Component;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import bot.discord.maho.database.Entity.Member;
import lombok.Getter;
import lombok.Setter;

@Component
@RequestScope
@Getter
public class JwtOwner {
	private MemberVO member;
	
	
	@Getter
	@Setter
	public class MemberVO {
		private UUID id;
		private String username;
		private String avatar;
		private String email;
		private Boolean isManager;
		private Date createTime;
		
		public MemberVO() {}
	}
	
	public JwtOwner of(Member member) {
		var vo = new MemberVO();
		vo.setId(member.getId());
		vo.setUsername(member.getUsername());
		vo.setAvatar(member.getAvatar());
		vo.setEmail(member.getEmail());
		vo.setIsManager(member.getIsManager());
		vo.setCreateTime(member.getCreateTime());
		this.member = vo;
		return this;
	}
}
