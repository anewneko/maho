package bot.discord.maho.security.Service;

import org.springframework.stereotype.Service;

import bot.discord.maho.database.CrudService.Impl.LoginDetailService;
import bot.discord.maho.database.CrudService.Impl.MemberService;
import bot.discord.maho.database.Entity.LoginDetail;
import bot.discord.maho.database.Entity.Member;
import bot.discord.maho.security.Model.DiscordUser;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateMemberService {
	final private MemberService memberService;
	final private LoginDetailService loginDetailService;

	
	public LoginDetail createOrUpdate(DiscordUser user) {
        var member =  memberService.findByDiscordId(user.getId());
        
        if(member == null)
            member = memberService.save(Member.of(user));
        if(member.update(user))
            memberService.save(member);
        
        return loginDetailService.save(LoginDetail.getCurrent()
												  .setMember(member)
												  .setIsUsed(true)
												  .setIsVerify(true));
	}
}
