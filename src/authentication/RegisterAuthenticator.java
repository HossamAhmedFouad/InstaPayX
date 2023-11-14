package authentication;

import informations.User;

import java.util.Scanner;


public class RegisterAuthenticator implements Authenticator {
    private User user;
    private final OTPHandler otpHandler;
    private String phone;


    public RegisterAuthenticator(User user, OTPHandler otpHandler) {
        this.user = user;
        this.otpHandler = otpHandler;
    }

    @Override
    public boolean verify() {
        boolean isValidPhone = Validation.isValidPhone(phone);
        if(!isValidPhone) {
            System.out.println("invalid phone number\n");
            return false;
        }
        otpHandler.generateCode(phone);
        System.out.println("Enter otp: \n");
        Scanner scanner = new Scanner(System.in);
        String otp = scanner.nextLine();
        boolean isOtpValid = otpHandler.verifyCode(phone, otp);

        if (!isOtpValid) {
            System.out.println("Invalid otp\n");
            return false;
        }
        boolean isUsernameValid = Validation.isValidUsername(user.getUsername());
        boolean isComplexPassword = Validation.isComplexPassword(user.getPassword());

        return isUsernameValid && isComplexPassword;
    }


}
