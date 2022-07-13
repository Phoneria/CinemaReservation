public class EditUserClass {
    private String admin;
    private String member;
    private String name;
    public EditUserClass() {
        this.admin = "false";
        this.member = "false";
        this.name = " ";
    }

    public EditUserClass(String name, String member,String admin) {
        this.admin = admin;
        this.member = member;
        this.name = name;
    }

    public String isAdmin() {
        return admin;
    }
    public String isMember() {
        return member;
    }

    public static void setAdmin(String user) {
        if(CreateExtractor.getDataExtractor().getUsers().get(user).get(2).equals("true")){
            CreateExtractor.getDataExtractor().getUsers().get(user).set(2,"false");
        }
        else {
            CreateExtractor.getDataExtractor().getUsers().get(user).set(2,"true");
        }
    }


    public static void setMember(String user) {
        if(CreateExtractor.getDataExtractor().getUsers().get(user).get(1).equals("true")){
            CreateExtractor.getDataExtractor().getUsers().get(user).set(1,"false");
        }
        else {
            CreateExtractor.getDataExtractor().getUsers().get(user).set(1,"true");
        }
    }

    public String getName() {
        return name;
    }

    public String getAdmin() {
        return admin;
    }

    public String getMember() {
        return member;
    }

    public void setName(String name) {
        this.name = name;
    }
}