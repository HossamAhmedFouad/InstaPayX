package authentication;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OTPHandler {

    private Map<String, String> otpMap = new HashMap<>();

    public void generateCode(String phone) {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000);
        String code = String.valueOf(otp);

        otpMap.put(phone, code);
        sendOTP(phone,code);
    }

    public boolean verifyCode(String phone, String userCode) {
        String storedCode = otpMap.get(phone);

        return storedCode != null && storedCode.equals(userCode);
    }

    public void sendOTP(String phone,String code) {
        System.out.println("OTP sent to " + phone + ": " + code);
    }
}
