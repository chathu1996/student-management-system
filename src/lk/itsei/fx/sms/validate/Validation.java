package lk.itsei.fx.sms.validate;

public class Validation {
    public static boolean isNumberValid(String text) {
        String regEX = "\\d{10}";
        return text.matches (regEX);
    }

    public static boolean isNameValid(String text) {
        String regEX = "^[A-Za-z]+([ A-Za-z]+)*";
        return text.matches (regEX);
    }

    public static boolean isStudentIDValid(String text) {
        String  regEX = "S*00[0-9]{3}";
        return text.matches (regEX);
    }

    public static boolean isValidPassword(String text) {
        String regEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})";
        return text.matches(regEX);
    }

    public static boolean isValidEmail(String text) {
        String regEX = "^[A-Za-z0-9+_.-]+@(.+)$";
        return text.matches (regEX);
    }




}
