package authentication;

import informations.User;

import java.util.Scanner;


public class RegisterAuthenticator implements Authenticator {
    private User user;
    private final OTPHandler otpHandler;

    public RegisterAuthenticator(User user, OTPHandler otpHandler) {
        this.user = user;
        this.otpHandler = otpHandler;
    }

    @Override
    public boolean verify() {
        boolean isValidPhone = Validation.isValidPhone(user.getPhone());
        if(!isValidPhone) {
            System.out.println("invalid phone number\n");
            return false;
        }
        otpHandler.generateCode(user.getPhone());
        System.out.println("Enter otp: \n");
        Scanner scanner = new Scanner(System.in);
        String otp = scanner.nextLine();
        boolean isOtpValid = otpHandler.verifyCode(user.getPhone(), otp);

        if (!isOtpValid) {
            System.out.println("Invalid otp\n");
            return false;
        }
        boolean isUsernameValid = Validation.isValidUsername(user.getUsername());
        boolean isComplexPassword = Validation.isComplexPassword(user.getPassword());

        if (!isUsernameValid){
            System.out.println("User name invalid");
            return false;
        }
        else if (!isComplexPassword){
            System.out.println("not complex password");
            return false;
        }
        return true;
    }


}
