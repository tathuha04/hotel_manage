package rikkei.academy.validate;

public class Validate {
    public static boolean checkEmail(String email) {
        String regex = "[a-zA-Z]+@gmail\\.com";
        return email.matches(regex);
    }

    public static boolean checkPassword(String password) {
        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{1,10}";
        return password.matches(regexPassword);
    }

    public static boolean checkName(String name){
        String regexName = "[A-Z][a-zA-Z[\\s]]{1,40}";
        return name.matches(regexName);
    }
    public static boolean checkUserName(String username){
        String regexUserName = "[\\w]{1,30}";
        return username.matches(regexUserName);
    }

    public static boolean checkLogin(String password){
//        String regex = "\\S[a-zA-Z0-9@$!%*?&]";
        String regex = "\\S+";
        return password.matches(regex);
    }
    public static boolean checkLoginUsername(String username){
        String regex = "\\S+";
//        String regex = "[A-Za-z\\d]+";
        return username.matches(regex);
    }
    public static boolean checkPrice(String price){
        String regex = "\\d+";
        return price.matches(regex);
    }
    public static boolean checkYear(String year){
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        return year.matches(regex);
    }
}
