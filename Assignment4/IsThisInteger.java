public class IsThisInteger {
    public static boolean isInt(String number){
        try {
            Integer.parseInt(number);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }
}
