import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Friends {
    JFrame frame,HomeFrame,BackFrame;
    Edit edit;
    MyStack stack;
    JButton CountFriends;
    int id,userID;
    String friends,UserFriend;
    friendSuggestion friendSuggestion;
    GridBagConstraints gbc;

    public Friends(MyStack stack,int id,JFrame HomeFrame,JButton CountFriends
            ,JFrame BackFrame,int userID,String UserFriend){
        friendSuggestion = new friendSuggestion(stack,userID);
        frame = new JFrame("Convo");
        edit = new Edit();
        this.stack = stack;
        this.id = id;
        this.userID = userID;
        this.CountFriends = CountFriends;
        this.UserFriend = UserFriend;
        for (UserDetails details : stack.getMyStack()){
            if(id == details.getID()){
                friends = details.getFriends();
            }
        }
        this.HomeFrame = HomeFrame;
        this.BackFrame = BackFrame;
    }
    public Friends(MyStack stack,int id,JFrame HomeFrame,JButton CountFriends){
        friendSuggestion = new friendSuggestion(stack,id);
        frame = new JFrame("Convo");
        edit = new Edit();
        this.stack = stack;
        this.id = id;
        this.CountFriends = CountFriends;
        for (UserDetails details : stack.getMyStack()){
            if(id == details.getID()){
                friends = details.getFriends();
            }
        }
        this.HomeFrame = HomeFrame;
    }

    public void run(boolean boo){
        frame = new JFrame("Convo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        heading head = new heading("Friends");

        JPanel panel = head.panel();

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        JButton btnFilter = new JButton("Filter");
        panel2.add(button(btnFilter), BorderLayout.NORTH);



        JButton BackBtn = new JButton("Back");
        if(boo) {
            panel2.add(main(true), BorderLayout.CENTER);
            panel2.add(button(BackBtn), BorderLayout.SOUTH);
            back(BackBtn);
        }else{
            panel2.add(main(false), BorderLayout.CENTER);
            JButton HomeBtn = new JButton("Go Home");
            edit.btn(BackBtn,HomeBtn);
            JPanel panel1 = new JPanel();
            panel1.setBackground(Color.GREEN);
            panel1.add(BackBtn);
            panel1.add(HomeBtn);
            panel2.add(panel1,BorderLayout.SOUTH);
            BackBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == BackBtn) {
                        frame.dispose();
                        BackFrame.setVisible(true);
                    }
                }
            });

            HomeBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == HomeBtn) {
                        frame.dispose();
                        HomeFrame.setVisible(true);
                    }
                }
            });

        }

        btnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(boo) {
                    filter(true);
                }else {
                    filter(false);
                }
            }
        });

        panel.add(panel2 );
        frame.add(panel);
        frame.setVisible(true);
    }

    public JPanel main(boolean boo){
        JPanel check = new JPanel();
        check.setLayout(new BoxLayout(check,BoxLayout.Y_AXIS));
        String[] frnd = friends.split(":");
        int length = frnd.length;
        if (frnd[length-1].equals("") || frnd[length-1] == null){
            length--;
        }
        JPanel friend = new JPanel();
        friend.setBackground(Color.cyan);
        friend.setLayout(new GridLayout(length,10,10,10));

        if(!boo){
            check = main2(frnd,friend,check);
            return check;
        }

        JPanel suggestion = new JPanel();
        suggestion.setBackground(Color.cyan);
        suggestion.setLayout(new GridLayout(stack.getLength() - length - 1, 1, 10, 10));
        for (UserDetails details: stack.getMyStack()) {
            boolean foundFriend = false;
            if(details.getID() != id) {
                for (int i = 0; i < frnd.length; i++) {
                    int userFriend = Integer.parseInt(frnd[i]);
                    if (details.getID() == userFriend) {
                        JButton inspect = new JButton("Inspect");
                        JButton Remove = new JButton("Remove");
                        inspect.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                userProfile profile = new userProfile(stack, details.getID(),
                                        HomeFrame,frame,id,friends);
                                frame.dispose();
                                profile.display2();
                            }
                        });
                        removeAction(Remove,details.getID());
                        friend.add(edit(details.getName(), inspect, Remove));
                        foundFriend = true;
                    }
                }
                if (!foundFriend) {
                    JButton inspect = new JButton("Inspect");
                    JButton add = new JButton("Add");
                    inspect.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (e.getSource() == inspect) {
                                friendSuggestion.inspect(details.getName(), details.getWork(), details.getHome(), frame);
                            }
                        }
                    });
                    addAction(add,details.getID());
                    suggestion.add(edit(details.getName(), inspect, add));
                }
            }
        }
        check.add(list("Your Friends" , friend));
        check.add(list("Suggestions" , suggestion));
        return check;
    }

    public JPanel main2(String [] frnd,JPanel friend, JPanel check){
        String[] buddy = UserFriend.split(":");
        for (UserDetails details: stack.getMyStack()) {

            if (details.getID() != id) {
                for (int i = 0; i < frnd.length; i++) {
                    int userFriend = Integer.parseInt(frnd[i]);
                    if (details.getID() == userFriend) {
                        if (details.getID() != userID) {
                            boolean foundFriend = false;
                            JButton inspect = new JButton("Inspect");
                            for (int j = 0; j < buddy.length; j++) {
                                int buddyID = Integer.parseInt(buddy[j]);
                                if (buddyID == details.getID()) {
                                    JButton Add = new JButton("Remove");
                                    removeAction(Add, details.getID());
                                    friend.add(edit(details.getName(), inspect, Add));
                                    foundFriend = true;
                                }
                            }
                            if (!foundFriend) {
                                JButton Add = new JButton("Add");
                                addAction(Add, details.getID());
                                friend.add(edit(details.getName(), inspect, Add));
                            }
                            inspect.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    userProfile profile = new userProfile(stack, details.getID(), HomeFrame,
                                            frame, userID, UserFriend);
                                    frame.dispose();
                                    profile.display2();
                                }
                            });
                        }else {
                            JButton youBtn = new JButton("You");
                            friend.add(edit(details.getName(), youBtn));
                            youBtn.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (e.getSource() == youBtn) {
                                        frame.dispose();
                                        HomeFrame.setVisible(true);
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
        check.add(list("Your Friends" , friend));
        return check;
    }

    public JPanel button(JButton filterButton){
        edit.btn(filterButton);
        JPanel btnpanel = new JPanel();
        btnpanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnpanel.add(filterButton);
        btnpanel.setBackground(Color.green);
        return btnpanel;
    }

    public JScrollPane list(String name,JPanel pnl){

        JLabel headingLabel = new JLabel(name);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel Panel = new JPanel();
        Panel.setBackground(Color.cyan);
        Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
        Panel.add(headingLabel);
        Panel.add(pnl);

        JScrollPane scrollPane = new JScrollPane(Panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(800, 500));

        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        scrollPane.setBorder(border);

        return scrollPane;
    }

    public JPanel edit(String name ,JButton inspect , JButton btn2){
        JPanel friendPanel = editCombination(name);

        JPanel buttonPanel = editCombo();

        edit.btnSmall(inspect,btn2);

        buttonPanel.add(inspect);
        buttonPanel.add(btn2);

        friendPanel.add(buttonPanel, gbc);
        friendPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        return friendPanel;
    }

    public JPanel edit(String name, JButton button) {
        JPanel friendPanel = editCombination(name);
        edit.btn3(button);
        JPanel buttonPanel = editCombo();
        buttonPanel.add(button);
        friendPanel.add(buttonPanel, gbc);
        friendPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        return friendPanel;
    }

    public JPanel editCombo(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.cyan);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        return buttonPanel;
    }
    public JPanel editCombination(String name){
        JPanel friendPanel = new JPanel(new GridBagLayout());
        friendPanel.setBackground(Color.cyan);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        friendPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.weightx = 0.0;
        return friendPanel;
    }

    public void back(JButton btn){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btn){
                    frame.dispose();
                    String [] counting = friends.split(":");
                    int length = counting.length;
                    if(counting[length-1].equals("") || counting[length-1] == null){
                        length--;
                    }
                    CountFriends.setText(counting.length + " Friends");
                    HomeFrame.setVisible(true);
                }
            }
        });
    }

    public void filter(boolean boo){
        JFrame popup = new JFrame("Convo");
        edit.PopUpFrame(popup, 400);
        ButtonGroup group = new ButtonGroup();
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel1.setBackground(Color.PINK);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setBackground(Color.CYAN);
        panel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        if(boo) {
            JTextField search = new JTextField();
            search.setFont(new Font("Serif", Font.PLAIN, 30));
            search.setPreferredSize(new Dimension(300, 50));
            panel2.add(search);
        }else {
            JButton button1 = new JButton("Same Friends");
            group.add(button1);
            edit.btn2(button1);
            panel1.add(button1);
            sameFriend(button1);
        }

        JButton button2 = new JButton("Work");
        JButton button3 = new JButton("Home");
        JButton button4 = new JButton("Go");

        work(button2);
        home(button3);

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button4) {
                    popup.dispose();
                    frame.setVisible(true);
                }
            }
        });

        group.add(button2);
        group.add(button3);
        group.add(button4);

        edit.btn2(button2);
        edit.btn2(button3);
        edit.btn2(button4);

        panel1.add(button2);
        panel1.add(button3);



        panel2.add(Box.createRigidArea(new Dimension(0, 10)));
        panel2.add(panel1);
        panel2.add(Box.createRigidArea(new Dimension(0, 10)));
        panel2.add(button4);

        popup.add(panel2);
        popup.pack();
        popup.setLocationRelativeTo(null);
        popup.setVisible(true);
    }

    public void addAction(JButton add, int idd){
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == add) {
                    friends = friendSuggestion.actionListener(idd, add,true);
                    frame.revalidate();
                    frame.repaint();
                }
            }
        });
    }

    public void removeAction(JButton Remove,int iddd){
        Remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                friends = friendSuggestion.actionListener(iddd,Remove,false);
            }
        });
    }

    public void sameFriend(JButton btn){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn){

                }
            }
        });
    }

    public void work(JButton btn){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn){

                }
            }
        });
    }

    public void home(JButton btn){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn){

                }
            }
        });
    }
}
