public class IsAdmin {
    public static String isAdmin(String name){

        if(CreateExtractor.getDataExtractor().getUsers().get(name).get(2).equals("false")){
            return "user";
        }
        else if (CreateExtractor.getDataExtractor().getUsers().get(name).get(2).equals("true"))
            return  "admin";
        else
            return " ";
    }
}
