public class LogIn {
    public static boolean logIn(String name,String password){

        boolean ishere = false;

        if(CreateExtractor.getDataExtractor().getUsers().containsKey(name)) {

            if (CreateExtractor.getDataExtractor().getUsers().get(name).get(0).equals(password)) {
                ishere = true;
            }
            else
                ishere = false;
        }
        return ishere;
    }
    public static boolean change(String name){
        boolean ishere = false;

        if(CreateExtractor.getDataExtractor().getUsers().containsKey(name)) {
            ishere = true;
        }
        return ishere;
    }
}
