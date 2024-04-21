//package bot.discord.maho.database.Entity;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.UUID;
//
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.EntityListeners;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "Role")
//@EntityListeners(AuditingEntityListener.class)
//public class Role {
//	@Id
//	@GeneratedValue(strategy = GenerationType.UUID)
//	private UUID id;
//	private String name;
//	@ManyToMany(mappedBy = "roles")
//	private Set<Function> functions = new HashSet<>();
//	public UUID getId() {
//		return id;
//	}
//	public Role setId(UUID id) {
//		this.id = id;
//		return this;
//	}
//	public String getName() {
//		return name;
//	}
//	public Role setName(String name) {
//		this.name = name;
//		return this;
//	}
//	public Set<Function> getFunctions() {
//		return functions;
//	}
//	public Role setFunctions(Set<Function> functions) {
//		this.functions = functions;
//		return this;
//	}
//	
//	
//
//}
