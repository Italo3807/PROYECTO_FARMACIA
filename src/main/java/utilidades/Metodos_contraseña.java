package utilidades;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Metodos_contraseña {
	public static boolean verificarContraseña(String contraseñaIngresada, String hashAlmacenado, String salt)
			throws NoSuchAlgorithmException {
		String contraseñaConSalt = contraseñaIngresada;
		String hashGenerado = hashearContraseña(contraseñaConSalt, salt);
		System.out.println(hashGenerado + "\n" + hashAlmacenado);
		return hashGenerado.equals(hashAlmacenado);
	}

	public static String generadorSalt() {
		try {
			SecureRandom secureRandom = SecureRandom.getInstanceStrong();
			byte[] saltBytes = new byte[16];
			secureRandom.nextBytes(saltBytes);
			return new String(Base64.getEncoder().encode(saltBytes), StandardCharsets.UTF_8);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String hashearContraseña(String contraseña, String salt) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(Base64.getDecoder().decode(salt));
			byte[] bytes = md.digest(contraseña.getBytes(StandardCharsets.UTF_8));
			return new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
