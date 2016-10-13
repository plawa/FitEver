package database.entities;

import logic.Encrypter;

// Generated 2016-09-29 20:51:33 by Hibernate Tools 5.2.0.Beta1

/**
 * Shadow generated by hbm2java
 */
public class Shadow extends Entity {

	private static final long serialVersionUID = -1566651635897799137L;
	private int userId;
	private User user;
	private String login;
	private String encryptedPass;

	public Shadow() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEncryptedPass() {
		return this.encryptedPass;
	}

	public void setEncryptedPass(String pass) {
		this.encryptedPass = pass;
	}
	
	public void encryptAndSetPass(String pass){
		this.encryptedPass = Encrypter.encryptWithMD5(pass);
	}
	
	public boolean equals(Shadow other){
		if (this.encryptedPass.equals(other.encryptedPass))
			return true;
		else
			return false;
	}

}
