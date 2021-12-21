package com.devsuperior.movieflix.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity //definindo entity
@Table (name = "tb_user") //criando tabela
public class User implements UserDetails, Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER) //forçar que sempre buscar um usuario no banco vai vim também os roles, pelfis desse usuario
	@JoinTable(name = "tb_user_role",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>(); //Associação muitos para muitos- coloca o Set para garantir que nao vai ter repetição do mesmo role no mesmo usuario o Set mesmo ja garante que nao pode ter repeção
	
	@OneToMany(mappedBy = "user")//tem que colocar o nome para dizer que se refere a course a variavel
	private List<Review> Reviews = new ArrayList<>();
	
	
	public User () {
		
	}

	public User(Long id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	

	public List<Review> getReviews() {
		return Reviews;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return id == other.id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //vai ser para converter a lista de cada elemento do tipo role em elemento do tipo grantedAutority
		
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())) // return apartir da lista de role .stream() para começar fazer lambda .map() para transformar cada elemento (pra cada elemento role eu vou instanciar um novo simpleGrantedAuthority ele é uma classe comcreta que implementa o grandetdAuthority que é uma interface e ele recebe um string como argumento o role (acessar o nome do perfil e instanciar um simpleGrantedAuthority 
				.collect(Collectors.toList()); //collect para voltar a coleção -- retornamos aqui uma coleção de GrantedAuthority que é na verdade um SimpleGrantedAuthority
	}


	@Override
	public String getUsername() {
	
		return email;   //username é o email do usuario, isso no securit
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	public boolean isEnabled() {
		return true;
	} 
	
	public boolean hasHole(String roleName) { //testar se usuario é admin
		for (Role role : roles) { //para cada role roles na coleção faça- esse "roles" é o Set la de cima - 
			
			if(role.getAuthority().equals(roleName)) { //se encontrar um role e for igual rolename que foi solicitado
				return true;
			}
		}
		return false;
	}

}
