package com.app.helpdesk.domain.dtos;

public class CredencialsDTO {

	private String email;
	private String senha;
	
	public CredencialsDTO() {
		super();
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
