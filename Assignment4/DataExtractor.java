import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




public class DataExtractor {
    private  ArrayList<List<String>> backupHolder;// Those are temp lists
    ArrayList<String> valuesOfFilms = new ArrayList<>();// This will hold film's path temporarily


    // This gets data from backup.dat
    // All lists hold their own variables
    private  HashMap<String, ArrayList<String>> users = new HashMap<>();
    private  HashMap<String, ArrayList<String>> films = new HashMap<>();
    private  HashMap<String, ArrayList<ArrayList<String>>> halls = new HashMap<>();


    private  ArrayList<HashMap<String, ArrayList<String>>> seatsList = new ArrayList<>();
    private HashMap<String,String> pathsWay = new HashMap<>();// It holds trailer path that placed into the pc

    private  ArrayList<List<String>> properties;// Data comes from properties.dat

    private Integer blockedTime = 0;
    private String title = " ";
    private Integer maxError = 0;
    private String timeInformation = " ";
    private Integer discount = 0;


    public DataExtractor() {

        Reader backupReader = new Reader(CreateExtractor.path+"data\\backup.dat");
        backupHolder = new ArrayList<>();

        for(String s:backupReader.getReadFile()){
            String[] tempGrid = s.split("\t");
            List<String> tempList = new ArrayList<>();

            for(String i:tempGrid){
                tempList.add(i);
            }
            backupHolder.add(tempList);
        }
        Reader propertiesReader = new Reader(CreateExtractor.path+"data\\properties.dat");
        properties= new ArrayList<>();

        for(String s:propertiesReader.getReadFile()){
            String[] tempGrid = s.split("\n");
            List<String> tempList = new ArrayList<>();

            for(String i:tempGrid){
                tempList.add(i);
            }
            properties.add(tempList);
        }



        for (int i = 0; i < getBackupHolder().size(); i++) {
            if (getBackupHolder().get(i).get(0).equals("user")) {
                ArrayList<String> tempList = new ArrayList<>();
                tempList.add(getBackupHolder().get(i).get(2));
                tempList.add(getBackupHolder().get(i).get(3));
                tempList.add(getBackupHolder().get(i).get(4));
                users.put(getBackupHolder().get(i).get(1), tempList);

            } else if (getBackupHolder().get(i).get(0).equals("film")) {
                ArrayList<String> tempList = new ArrayList<>();
                tempList.add(getBackupHolder().get(i).get(2));
                tempList.add(getBackupHolder().get(i).get(3));
                films.put(getBackupHolder().get(i).get(1), tempList);
            } else if (getBackupHolder().get(i).get(0).equals("hall")) {
                ArrayList<String> tempList = new ArrayList<>();
                tempList.add(getBackupHolder().get(i).get(2));
                tempList.add(getBackupHolder().get(i).get(3));
                tempList.add(getBackupHolder().get(i).get(4));
                tempList.add(getBackupHolder().get(i).get(5));
                ArrayList<ArrayList<String>> tempHallList = new ArrayList<>();
                tempHallList.add(tempList);
                halls.put(getBackupHolder().get(i).get(1), tempHallList);
            } else if (getBackupHolder().get(i).get(0).equals("seat")) {
                HashMap<String, ArrayList<String>> seats = new HashMap<>();
                ArrayList<String> tempList = new ArrayList<>();
                tempList.add(getBackupHolder().get(i).get(2));
                tempList.add(getBackupHolder().get(i).get(3));
                tempList.add(getBackupHolder().get(i).get(4));
                tempList.add(getBackupHolder().get(i).get(5));
                tempList.add(getBackupHolder().get(i).get(6));
                seats.put(getBackupHolder().get(i).get(1), tempList);
                seatsList.add(seats);
            }

        }



        for(ArrayList<String> s :getFilms().values()){
            valuesOfFilms.add(s.get(0));
        }
        for(String s : valuesOfFilms){
            setPaths(s);
        }

        for(String s : getPaths){
            String way= CreateExtractor.path+"trailers\\";
            pathsWay.put(s,(way+s));
        }

        for(List<String> s : properties){
            if(s.get(0).startsWith("#")){
                timeInformation = s.get(0);
            }
            else if(s.get(0).startsWith("maximum-error-without-getting-blocked")){
                String[] divided = s.get(0).split("=");
                maxError = Integer.parseInt(divided[1]);
            }
            else if(s.get(0).startsWith("title")){
                String[] divided = s.get(0).split("=");
                title = divided[1];
            }
            else if(s.get(0).startsWith("discount")){
                String[] divided = s.get(0).split("=");
                discount = Integer.parseInt(divided[1]);
            }
            else if(s.get(0).startsWith("block")){
                String[] divided = s.get(0).split("=");
                blockedTime = Integer.parseInt(divided[1]);
            }

        }

        ArrayList<String> futuresForAdmin = new ArrayList<>();
        futuresForAdmin.add(Base64Thing.hashPassword("password"));
        futuresForAdmin.add("true");
        futuresForAdmin.add("true");
        users.put("admin",futuresForAdmin);


    }

    public HashMap<String, String> getPathsWay() {
        return pathsWay;
    }

    public  HashMap<String, ArrayList<String>> getUsers() {
        return users;
    }

    public  HashMap<String, ArrayList<String>> getFilms() {
        return films;
    }

    public  HashMap<String, ArrayList<ArrayList<String>>> getHalls() {
        return halls;
    }

    public  ArrayList<HashMap<String, ArrayList<String>>> getSeatsList() {
        return seatsList;
    }


    public  ArrayList<List<String>> getBackupHolder() {
        return backupHolder;
    }

    public  ArrayList<List<String>> getProperties() {
        return properties;
    }

    public ArrayList<String> getPaths = new ArrayList<>();


    public void setPaths(String paths) {
        getPaths.add(paths);
    }

    public void setUsers(String name, String password){
        ArrayList<String> futures = new ArrayList<>();
        futures.add(password);
        futures.add("false");
        futures.add("false");
        users.put(name,futures);
    }
    public void deleteFilms(String deletedFilm){
        films.remove(deletedFilm);
    }

    public String addFilms(String name,String path, String duration){
        String message = " ";
        if(name.equals("")|| name.equals(" ")||name.isEmpty()){
            message = "ERROR: Film name could not be empty";ErrorSound.clicked();
        }
        else if(path.equals("")|| path.equals(" ")||path.isEmpty()){
            message = "ERROR: Trailer path cannot be empty";ErrorSound.clicked();
        }
        else if(duration.equals("")||duration.equals(" ")||duration.isEmpty()){
            message = "ERROR: Duration cannot be empty";ErrorSound.clicked();
        }
        else if(!IsThisInteger.isInt(duration)){// Method returns true if duration is integer
            message = "ERROR:Duration has to be positive integer!";ErrorSound.clicked();
        }
        else if (films.containsKey(name)) {
            message ="ERROR:This film is already exist";ErrorSound.clicked();
        }
        else if(!getPaths.contains(path)){
            message = "ERROR:There is no such trailer";ErrorSound.clicked();
        }
        else {
            message = "SUCCESS: Film added successfully";
            ArrayList<String> futures = new ArrayList<>();
            futures.add(path);
            futures.add(duration);
            films.put(name, futures);
        }



        return message;
    }

    public void deleteHalls(String nameOfTheFilm,String nameOfTheHall){

        for(int i = 0;i<CreateExtractor.getDataExtractor().getHalls().get(nameOfTheFilm).size();i++){
            if(CreateExtractor.getDataExtractor().getHalls().get(nameOfTheFilm).get(i).get(0).equals(nameOfTheHall)){
                CreateExtractor.getDataExtractor().getHalls().get(nameOfTheFilm).remove(i);
            }
        }
    }
    public void addHall(String nameOfTheFilm,String nameOfTheHall,String price , String row, String column){
        ArrayList<String> holder = new ArrayList<>();
        holder.add(nameOfTheHall);
        holder.add(price);
        holder.add(row);
        holder.add(column);


        if(CreateExtractor.getDataExtractor().getHalls().containsKey(nameOfTheFilm)){
            CreateExtractor.getDataExtractor().getHalls().get(nameOfTheFilm).add(holder);
        }
        else {
            ArrayList<ArrayList<String >> holderOfholder = new ArrayList<>();
            CreateExtractor.getDataExtractor().getHalls().put(nameOfTheFilm, holderOfholder);
            CreateExtractor.getDataExtractor().getHalls().get(nameOfTheFilm).add(holder);
        }

    }

    public Integer getBlockedTime() {
        return blockedTime;
    }

    public String getTitle() {
        return title;
    }

    public Integer getMaxError() {
        return maxError;
    }

    public String getTimeInformation() {
        return timeInformation;
    }

    public Integer getDiscount() {
        return discount;
    }
}

