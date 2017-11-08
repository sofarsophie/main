
package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.PasswordAcceptedEvent;
import seedu.address.commons.util.FileUtil;

//@@author syy94

/**
 * Accesses and edits the password.
 */
public class SecurityManager {
    private static String path = "data/pass";

    public static void setPasswordStoragePath(String path) {
        SecurityManager.path = path;
    }

    /**
     * Saves password
     */
    public static void savePass(String pass) throws IOException, NoSuchAlgorithmException {
        requireNonNull(pass);
        final File passFile = new File(path);

        final String trimmedPass = pass.trim();

        if (trimmedPass.length() != 0) {
            final String encodedPass = encryptPass(trimmedPass);
            FileUtil.createIfMissing(passFile);
            FileUtil.writeToFile(passFile, encodedPass);
        }
    }

    /**
     * Simple Hashing of the password
     */
    private static String encryptPass(String pass) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(hash);
    }

    /**
     * Checks if given password is correct and posts and event to enable the application features.
     */
    public static boolean unlock(String pass) throws IOException, NoSuchAlgorithmException {
        boolean isUnlocked = checkPass(pass);
        if (isUnlocked) {
            EventsCenter.getInstance().post(new PasswordAcceptedEvent());
        }
        return isUnlocked;
    }

    /**
     * Checks if given password is correct.
     */
    public static boolean checkPass(String pass) throws IOException, NoSuchAlgorithmException {
        return getPass().equals(encryptPass(pass));
    }

    /**
     * removes the need for password on application start.
     */
    public static void removePass() {
        final File passFile = new File(path);
        if (FileUtil.isFileExists(passFile)) {
            passFile.delete();
        }
    }

    /**
     * Checks if there is a password.
     */
    public static boolean passExists() {
        final File passFile = new File(path);

        return FileUtil.isFileExists(passFile);
    }

    /**
     * Retrieves the Password.
     */
    private static String getPass() throws IOException {
        final File file = new File(path);
        return FileUtil.readFromFile(file);
    }
}
