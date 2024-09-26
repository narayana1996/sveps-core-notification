package in.co.sveps.service;

import com.github.f4b6a3.uuid.UuidCreator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class CustomPasswordEncoder  implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        String salt = generateSalt();
        String pass= hashWithSalt(rawPassword.toString(), salt) + "$" + salt;
        return pass;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String[] parts = encodedPassword.split("\\$");
        String hashedPassword = parts[0]; // The hashed password without salt
        String salt = parts[1]; // The salt
        String hashedInputPassword = hashWithSalt(rawPassword.toString(), salt);
        return hashedPassword.equals(hashedInputPassword); // Compare only the hashes
    }

    private String hashWithSalt(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword); // Only return the hash
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateSalt() {
        return UuidCreator.getTimeBased().toString(); // Generate a random UUID as salt
    }


}
