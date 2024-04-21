//package bot.discord.maho.database.Entity;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.UUID;
//
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EntityListeners;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "Function")
//@EntityListeners(AuditingEntityListener.class)
//public class Function {
//	@Id
//	@GeneratedValue(strategy = GenerationType.UUID)
//	private UUID id;
//	private String value;
//	
//	@ManyToMany(cascade = { CascadeType.ALL })
//	@JoinTable( name = "Function_Detail", joinColumns = { @JoinColumn(name = "function_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
//	private Set<Role> roles = new HashSet<>();
//	
//	public UUID getId() {
//		return id;
//	}
//	public Function setId(UUID id) {
//		this.id = id;
//		return this;
//	}
//	public String getValue() {
//		return value;
//	}
//	public Function setValue(String value) {
//		this.value = value;
//		return this;
//	}
//	public Set<Role> getRoles() {
//		return roles;
//	}
//	public Function setRoles(Set<Role> roles) {
//		this.roles = roles;
//		return this;
//	}
//	
//	
//	
//}
