package bot.discord.maho.database.Entity;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "LoginDetail")
@EntityListeners(AuditingEntityListener.class)
@Getter
public class LoginDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Member.id")
	private Member member;
	
	private Date expireTime;
	
	private Boolean isVerify = false;
	
	private Boolean isUsed = false;
	
	private LoginDetail() {
		this.expireTime = new Date(System.currentTimeMillis() + 600000);
	}

	public LoginDetail setId(UUID id) {
		this.id = id;
		return this;
	}
	
	public LoginDetail setMember(Member member) {
		this.member = member;
		return this;
	}

	public LoginDetail setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
		return this;
	}
	
	public LoginDetail setIsVerify(Boolean isVerify) {
		this.isVerify = isVerify;
		return this;
	}

	public LoginDetail setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
		return this;
	}
	
	public <E extends Throwable> void ifUsedThrow(E e) throws E {
		if(Boolean.TRUE.equals(this.isUsed)) throw e;
	}
	
	public <E extends Throwable> void ifExpiredThrow(E e) throws E {
		if(this.getExpireTime().before(new Date())) throw e;
	}
	
	public static LoginDetail getCurrent() {
		return new LoginDetail();
	}
	

}
