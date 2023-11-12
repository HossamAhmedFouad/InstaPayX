package authentication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static boolean isValidUsername(String username) {
        String regex = "[a-zA-Z- -]+";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(username);

        return matcher.matches();
    }

    public static boolean isComplexPassword(String password) {
        String complexPasswordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        Pattern pattern = Pattern.compile(complexPasswordRegex);

        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
    public static boolean isValidPhone(String phoneNumber){
        String regex = "(01)[0125][0-9]{8}";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }
}
