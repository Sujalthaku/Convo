public class UserDetails {

    private int ID;
    private String name,work,home,friends,image,likes;

    public UserDetails(){
        ID = 0;
        name = "";
        work = "";
        home = "";
        friends = "";
        image = "";
        likes = "";
    }

    public UserDetails(int ID, String name , String work, String home, String friends,
                       String image,String likes){
        this.ID = ID;
        this.name = name;
        this.work = work;
        this.home = home;
        this.friends = friends;
        this.image = image;
        this.likes = likes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }
}
