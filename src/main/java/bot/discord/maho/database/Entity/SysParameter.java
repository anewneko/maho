//package bot.discord.maho.database.Entity;
//
//import java.util.UUID;
//
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.EntityListeners;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "Sys_Parameter")
//@EntityListeners(AuditingEntityListener.class)
//public class SysParameter {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.UUID)
//	private UUID id;
//	private String paraGroup;
//	private String paraName;
//	private String paraValue;
//	public UUID getId() {
//		return id;
//	}
//	public SysParameter setId(UUID id) {
//		this.id = id;
//		return this;
//	}
//	public String getParaGroup() {
//		return paraGroup;
//	}
//	public SysParameter setParaGroup(String paraGroup) {
//		this.paraGroup = paraGroup;
//		return this;
//	}
//	public String getParaName() {
//		return paraName;
//	}
//	public SysParameter setParaName(String paraName) {
//		this.paraName = paraName;
//		return this;
//	}
//	public String getParaValue() {
//		return paraValue;
//	}
//	public SysParameter setParaValue(String paraValue) {
//		this.paraValue = paraValue;
//		return this;
//	}
//	
//	
//
//}
