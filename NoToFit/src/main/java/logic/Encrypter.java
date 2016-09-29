package logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypter {

	private MessageDigest md;
	
	public String encryptWithMD5(String pass) {
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] passBytes = pass.getBytes();
			md.reset();
			byte[] digested = md.digest(passBytes);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < digested.length; i++)
				sb.append(Integer.toHexString(0xff & digested[i]));
			return sb.toString();
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		Encrypter myEncrypter = new Encrypter();
		System.out.print(myEncrypter.encryptWithMD5("mojeHaslo1!@"));
	}

}
