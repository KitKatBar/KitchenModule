package com.example.demo.resource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match!"),
})
@Entity
@Table(name="kitchens")
public class Kitchen implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	@NotNull
	@Size(min = 1, max = 50)
	private String name;
	
	@Column(unique = true)
	@NotNull
	@Size(min = 1, max = 50)
	private String email;
	
	@NotNull
	@Min(value = 10)
	private String password;
	
	@NotNull
	@Min(value = 10)
	private String confirmPassword;
	
	//private List<Boolean> workingDays; // for storing days of the week

	@OneToMany(mappedBy = "kitchen", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MenuItem> menu;

	public Kitchen() {
		
	}
	
	public Kitchen(String name, String email, String password) {
		super();
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    /*
    public List<Boolean> getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(List<Boolean> workingDays) {
		this.workingDays = workingDays;
	}
	*/
	public List<MenuItem> getMenu() {
		return menu;
	}

	public void setMenu(List<MenuItem> menu) {
		this.menu = menu;
	}
}
