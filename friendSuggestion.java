import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ConcurrentModificationException;

public class friendSuggestion {
    private JFrame frame;
    private Edit editBtn;
    private boolean check;
    private int id;
    private MyStack myStack;
    private File file;



    public friendSuggestion(MyStack myStack, int ID){
        editBtn = new Edit();
        this.myStack = myStack;
        this.id = ID;
        file = new File(myStack,ID);
        check = false;
    }

    public void suggestion(){
        frame = new JFrame("Convo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        mainPanel();
        frame.setVisible(true);
    }

    public void mainPanel(){
        heading head = new heading("Suggestion");
        JPanel MainPanel = head.panel();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        for (UserDetails detail: myStack.getMyStack()) {
            if(id != detail.getID()){
                panel.add(friends(detail.getName() , detail.getID() , detail.getWork(), detail.getHome()));
            }
        }

        JScrollPane scrollPanel = new JScrollPane(panel);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        JButton button = new JButton("OK");
        editBtn.btn(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == button){
                    userProfile profile = new userProfile(myStack,id);
                    if(check) {
                        frame.dispose();
                        profile.display();
                    }else {
                        JFrame frame1 = new JFrame("Convo");
                        JButton errorButton = new JButton("Try Again");
                        JLabel errorMsg = new JLabel("Please Add At Least One Friend To Proceed");
                        editBtn.popUp(errorButton,frame1,errorMsg,400);
                        errorButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if(e.getSource() == errorButton){
                                    frame1.dispose();
                                    frame.setVisible(true);
                                }
                            }
                        });
                        frame1.setVisible(true);
                    }
                }
            }
        });

        MainPanel.add(scrollPanel);
        MainPanel.add(button);
        frame.add(MainPanel);
    }

    public JPanel friends(String name , int ID, String work, String home){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel nameLbl = new JLabel(name);
        nameLbl.setFont(new Font("Serif", Font.PLAIN, 30));

        JButton addBtn = new JButton("Add");
        JButton InspectBtn = new JButton("Inspect");
        editBtn.btnSmall(addBtn,InspectBtn);

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == addBtn) {
                    actionListener(ID,addBtn,true);
                }
            }
        });

        InspectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == InspectBtn) {
                    inspect(name,work,home,frame);
                }
            }
        });


        GridBagConstraints NameContainer = new GridBagConstraints();
        NameContainer.gridx = 0;
        NameContainer.gridy = 0;
        NameContainer.weightx = 1.0;
        NameContainer.fill = GridBagConstraints.HORIZONTAL;
        NameContainer.anchor = GridBagConstraints.WEST;
        NameContainer.insets = new Insets(10, 100, 10, 10);

        GridBagConstraints BtnContainer = new GridBagConstraints();
        BtnContainer.gridx = 1;
        BtnContainer.gridy = 0;
        BtnContainer.weightx = 0.0;
        BtnContainer.insets = new Insets(10, 10, 10, 50);

        GridBagConstraints Btn2Container = new GridBagConstraints();
        Btn2Container.gridx = 2;
        Btn2Container.gridy = 0;
        Btn2Container.weightx = 0.0;
        Btn2Container.insets = new Insets(10, 10, 10, 100);


        panel.add(nameLbl, NameContainer);
        panel.add(InspectBtn, BtnContainer);
        panel.add(addBtn, Btn2Container);
        panel.setBackground(Color.cyan);


        return panel;
    }

    public void inspect(String name , String work, String home, JFrame frame){
        String Name = "User Name => " + name;
        String Work = "User WorkPlace => " + work;
        String Home = "User HomePlace => " + home;

        JFrame frame2 = new JFrame("Convo");
        JButton btn = new JButton("Back");
        JLabel label = new JLabel(Name);
        JLabel label2 = new JLabel(Work);
        JLabel label3 = new JLabel(Home);

        editBtn.PopUpFrame(frame2, 350);
        editBtn.btn(btn);
        editBtn.PopUpText(label);
        editBtn.PopUpText(label2);
        editBtn.PopUpText(label3);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.add(Box.createVerticalGlue());
        panel1.add(label);
        panel1.add(label2);
        panel1.add(label3);
        panel1.add(Box.createVerticalStrut(10));
        panel1.add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn) {
                    frame2.dispose();
                    frame.setVisible(true);
                }
            }
        });

        panel1.add(Box.createVerticalGlue());
        panel1.setBackground(Color.cyan);

        frame2.add(panel1);
        frame2.setVisible(true);
    }

    public String actionListener(int ID , JButton addBtn,boolean bull){
        String frnd = "";
        try {
            for (UserDetails details : myStack.getMyStack()) {
                if (details.getID() == id) {
                    myStack.remove(details);
                    if(bull) {
                        frnd = addFriend(details, ID, id);
                    }else{
                        frnd = removeFriend(details,ID,id);
                    }
                }
            }
        }catch (ConcurrentModificationException c){
            System.out.println(c);
        }

        try {
            for (UserDetails details : myStack.getMyStack()) {
                if (details.getID() == ID) {
                    myStack.remove(details);
                    if(bull) {
                        addFriend(details, id, ID);
                    }else {
                        removeFriend(details,id,ID);
                    }
                }
            }
        }catch (ConcurrentModificationException c){
            System.out.println(c);
        }

        file.addToFile2();
        if(bull) {
            addBtn.setText("Added");
        }else {
            addBtn.setText("Removed");
        }
        check = true;
        addBtn.setBackground(Color.red);
        addBtn.setEnabled(false);
        return frnd;
    }

    public String addFriend(UserDetails details, int ID,int id){
        String friend1 = details.getFriends() + ID + ":";
        myStack.addToStack(id, details.getName(), details.getWork(),
                details.getHome(), friend1, details.getImage(), details.getLikes());
        return friend1;
    }

    public String removeFriend(UserDetails details,int ID,int id){
        String friend1 = "";
        String [] frnd = details.getFriends().split(":");
        for (int i = 0; i <frnd.length ; i++) {
            int unqID = Integer.parseInt(frnd[i]);
            if(unqID != ID){
                friend1 = friend1 + unqID + ":";
            }
        }
        myStack.addToStack(id, details.getName(), details.getWork(),
                details.getHome(), friend1, details.getImage(), details.getLikes());
        return friend1;
    }

}
