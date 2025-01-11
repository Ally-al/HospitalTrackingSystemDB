package app.tables;

import app.Const;
import app.DBHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class Users {
    private static final Logger logger = LogManager.getLogger(Users.class);
    public static void signUpUser(String login, String password) {
        try (Connection connection = DBHandler.getDbConnection()) {
            byte[] salt = generateSalt();
            String hashedPassword = hashPassword(password, salt);

            String insert = "INSERT INTO " + Const.USERS_TABLE + " (" + Const.USERS_LOGIN +
                    ", " + Const.USERS_PASSWORD +
            ", " + Const.USERS_SALT + ") " + "VALUES(?,?,?)";

            PreparedStatement prSt = connection.prepareStatement(insert);
            prSt.setString(1, login);
            prSt.setString(2, hashedPassword);
            prSt.setBytes(3, salt);
            logger.info("SQL запрос: " + prSt);

            prSt.executeUpdate();
        } catch (NoSuchAlgorithmException | SQLException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    public static boolean isUserExist (String login, String password) {
        try (Connection connection = DBHandler.getDbConnection()) {
            String query = "SELECT " + Const.USERS_PASSWORD + ", " + Const.USERS_SALT + " FROM " +
                    Const.USERS_TABLE + " WHERE " + Const.USERS_LOGIN + " = ?";

            PreparedStatement prSt = connection.prepareStatement(query);
            prSt.setString(1, login);
            logger.info("SQL запрос: " + prSt);

            ResultSet resultSet = prSt.executeQuery();
            if (resultSet.next()) {
                byte[] salt = resultSet.getBytes(Const.USERS_SALT);
                String hashedPasswordInDB = resultSet.getString(Const.USERS_PASSWORD);
                String hashedPassword = hashPassword(password, salt);
                return hashedPasswordInDB.equals(hashedPassword);
            }
        } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return false;
    }


    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    private static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 10000;
        int keyLength = 256;

        char[] passwordChars = password.toCharArray();
        PBEKeySpec spec = new PBEKeySpec(passwordChars, salt, iterations, keyLength);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = keyFactory.generateSecret(spec).getEncoded();

        return Base64.getEncoder().encodeToString(hash);
    }
}
