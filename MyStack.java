import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyStack {
    private LinkedList<UserDetails> myStack;

    public MyStack(){
        myStack = new LinkedList<UserDetails>();
    }

    public LinkedList<UserDetails> getMyStack() {
        return myStack;
    }

    public void addToStack(UserDetails details) {
        myStack.addFirst(details);
    }

    public UserDetails getStack(int ID){
        for (UserDetails details: myStack) {
            if(ID == details.getID()){
                return details;
            }
        }
        return null;
    }

    public void addToStack(int ID , String name, String work, String home , String friends,
                           String image, String likes){
        UserDetails ud = new UserDetails(ID,name,work,home,friends,image,likes);
        myStack.addFirst(ud);
    }

    public boolean remove(UserDetails o){
        return myStack.remove(o);
    }

    public boolean check(int id){
        for (UserDetails details : myStack){
            if (details.getID() == id){
                return true;
            }
        }
        return false;
    }

    public char specialCheck(int id){
        for (UserDetails details : myStack){
            if (details.getID() == id){
                if(details.getFriends().equals("") || details.getFriends() == null){
                    return 'a';
                }else {
                    return 'b';
                }
            }
        }
        return 'c';
    }

    public UserDetails remove(int id){
        for (UserDetails details : myStack){
            if(details.getID() == id){
                remove(details);
                return details;
            }
        }
        return null;
    }

    public UserDetails removeFromStack(){
        UserDetails remove;
        try {
            remove = myStack.removeFirst();

        }catch (NoSuchElementException e){
            remove = null;
        }
        return remove;
    }

    public boolean isEmpty(){
        return myStack.isEmpty();
    }

    public int getLength(){
        return myStack.size();
    }


}
