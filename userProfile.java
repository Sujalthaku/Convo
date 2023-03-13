import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Objects;
import java.util.Stack;

public class userProfile {
    JFrame frame,frame2,homePage,backFrame;
    Edit editBtn;
    File file;
    JPanel imagee;
    JButton but1;
    boolean bull;
    private final MyStack Stack;

    int id,userID;
    private String  name1,
            name2,
            ID,
            homeTown,
            friends,
            workPlace,
            ImG,LIKE,userFriend;

    public userProfile(MyStack stack,int id,JFrame homePage,JFrame backFrame,int userID,String userFriend){
        this.Stack = stack;
        this.id = id;
        editBtn = new Edit(Stack);
        file = new File(Stack);
        imagee = new JPanel();
        bull = true;
        friends = "";
        this.homePage = homePage;
        this.backFrame = backFrame;
        UserDetails details = Stack.getStack(id);
        this.userID = userID;
        this.userFriend = userFriend;
        if (details != null) {
            ID = id + "";
            name1 = details.getName();
            workPlace = details.getWork();
            homeTown = details.getHome();
            friends = details.getFriends();
            String[] list = friends.split(":");
            name2 = list.length + " Friends";
            ImG = details.getImage();
            LIKE = details.getLikes();
        }else {
            System.out.println("error2");
        }
    }
    public userProfile(MyStack Stack, int id){
        this.Stack = Stack;
        this.id = id;
        editBtn = new Edit(Stack);
        file = new File(Stack);
        imagee = new JPanel();
        bull = true;
        friends = "";

        UserDetails details = Stack.getStack(id);
        if (details != null) {
            ID = id + "";
            name1 = details.getName();
            workPlace = details.getWork();
            homeTown = details.getHome();
            friends = details.getFriends();
            String[] list = friends.split(":");
            name2 = list.length + " Friends";
            ImG = details.getImage();
            LIKE = details.getLikes();
        }else {
            System.out.println("error2");
        }
    }

    public void display(){
        frame = new JFrame("Convo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        heading head = new heading("Home");

        JPanel panel;
        panel = head.panel();

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(buttons(true), BorderLayout.NORTH);

        imagee = image(new JPanel(),true);
        panel2.add(imagee, BorderLayout.CENTER);

        panel.add(panel2);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void display2(){
        frame = new JFrame("Convo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        heading head = new heading("Friends Page");

        JPanel panel;
        panel = head.panel();

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(buttons(false), BorderLayout.NORTH);

        imagee = image(new JPanel(),false);
        panel2.add(imagee, BorderLayout.CENTER);

        panel.add(panel2);

        frame.add(panel);
        frame.setVisible(true);
    }


    public JPanel buttons(boolean boo){
        String name = name1 + " Profile";
        but1 = new JButton(name);
        JButton btn2 = new JButton(name2);

        editBtn.btn(but1,btn2);

        but1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == but1) {
                    frame2 = new JFrame("Convo");
                    frame2.setSize(800, 600);
                    frame2.setLocationRelativeTo(null);

                    heading heading = new heading("Profile");

                    JPanel panel = new JPanel();
                    panel = heading.panel();
                    panel.add(btn1(boo));


                    frame2.add(panel);

                    frame.dispose();
                    frame2.setVisible(true);
                }
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btn2){
                    if(boo) {
                        Friends friend = new Friends(Stack, id, frame, btn2);
                        friend.run(true);
                    }else {
                        Friends friend = new Friends(Stack, id, homePage, btn2,frame,userID,userFriend);
                        friend.run(false);
                    }

                    frame.dispose();
                }
            }
        });
        ButtonGroup group = new ButtonGroup();
        group.add(but1);
        group.add(btn2);

        JPanel btnpanel = new JPanel();
        btnpanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnpanel.add(but1);
        btnpanel.add(btn2);

        btnpanel.setBackground(Color.green);
        return btnpanel;
    }

    public JPanel btn1(boolean boo){
        JPanel main = new JPanel();

        JPanel textPanel = new JPanel();
        JPanel textPanel2 = new JPanel();
        JPanel textPanel3 = new JPanel();
        JPanel textPanel4 = new JPanel();
        JPanel textPanel5 = new JPanel();

        JLabel textLabel = new JLabel("User ID => ");
        JLabel textLabel2 = new JLabel("User Name => ");
        JLabel textLabel3 = new JLabel("WorkPlace => ");
        JLabel textLabel4 = new JLabel("Home Town => ");

        JLabel textLabel5 = new JLabel(ID);
        JLabel textLabel6 = new JLabel(name1);
        JLabel textLabel7 = new JLabel(workPlace);
        JLabel textLabel8 = new JLabel(homeTown);

        textLabel.setFont(new Font("Times New Roman", Font.PLAIN,30));
        textLabel2.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        textLabel3.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        textLabel4.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        textLabel5.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        textLabel6.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        textLabel7.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        textLabel8.setFont(new Font("Times New Roman", Font.PLAIN, 30));



        textPanel.add(textLabel);
        textPanel.add(textLabel5);
        textPanel.setLayout(new FlowLayout(FlowLayout.CENTER , 90, 10));

        textPanel2.add(textLabel2);
        textPanel2.add(textLabel6);
        textPanel2.setLayout(new FlowLayout(FlowLayout.CENTER , 51, 10));

        textPanel3.add(textLabel3);
        textPanel3.add(textLabel7);
        textPanel3.setLayout(new FlowLayout(FlowLayout.CENTER , 52, 10));

        textPanel4.add(textLabel4);
        textPanel4.add(textLabel8);
        textPanel4.setLayout(new FlowLayout(FlowLayout.CENTER , 39, 10));

        JButton btn1 = new JButton("Back");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btn1){
                    frame2.dispose();
                    frame.setVisible(true);
                }
            }
        });
        if (boo){
            JButton btn2 = new JButton("Edit");
            editBtn.btn(btn1,btn2);



            btn2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == btn2){
                        JFrame frame1 = new JFrame("Convo");
                        JPanel panel = new JPanel();
                        JButton btn1 = new JButton("Back");
                        JButton btn2 = new JButton("Ok");
                        JTextField field = new JTextField();
                        JTextField field2 = new JTextField();
                        JTextField field3 = new JTextField();
                        JTextField field4 = new JTextField();
                        panel = editBtn.registration(field,field2,field3,field4,frame1,btn1,btn2);

                        btn1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if(e.getSource() == btn1){
                                    frame1.dispose();
                                    frame2.setVisible(true);
                                }
                            }
                        });

                        btn2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if(e.getSource()==btn2){

                                    char ch = editBtn.inputValidation(field,field2,field3,field4);
                                    JFrame frame3 = new JFrame("Error");
                                    JButton buttonn = new JButton("Try Again");
                                    JLabel text;

                                    buttonn.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if(e.getSource() == buttonn){
                                                frame3.dispose();
                                                frame1.setVisible(true);
                                            }
                                        }
                                    });

                                    if(ch == 'a'){
                                        UserDetails details = Stack.remove(id);
                                        if (details != null) {
                                            int i = Integer.parseInt(field.getText());
                                            id = i;
                                            ID = field.getText();
                                            name1 = field2.getText();
                                            workPlace = field3.getText();
                                            homeTown = field4.getText();
                                            Stack.addToStack(i, field2.getText(), field3.getText(),
                                                    field4.getText(), details.getFriends(),
                                                    details.getImage(), details.getLikes());
                                            file.addToFile2();
                                        }else {
                                            System.out.println("error3");
                                        }
                                        but1.setText(name1 + " Profile");
                                        textLabel5.setText(field.getText());
                                        textLabel6.setText(field2.getText());
                                        textLabel7.setText(field3.getText());
                                        textLabel8.setText(field4.getText());
                                        frame3.dispose();
                                        frame1.dispose();
                                    } else if (ch == 'b') {
                                        text = new JLabel("Use Different ID");
                                        editBtn.popUp(buttonn,frame3,text,250);
                                        frame3.setVisible(true);
                                    }else{
                                        text = new JLabel("Incorrect Information");
                                        editBtn.popUp(buttonn,frame3,text,250);
                                        frame3.setVisible(true);
                                    }
                                }
                            }
                        });

                        frame1.add(panel);
                        frame1.setLocationRelativeTo(null);
                        frame1.setVisible(true);
                    }
                }
            });
            textPanel5.add(btn1);
            textPanel5.add(btn2);
        }else {
            editBtn.btn(btn1);
        }
        if (!boo){
            textPanel5.add(btn1);
        }

        textPanel5.setLayout(new FlowLayout(FlowLayout.CENTER , 39, 10));

        main.add(textPanel);
        main.add(textPanel2);
        main.add(textPanel3);
        main.add(textPanel4);
        main.add(textPanel5);

        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        textPanel.setBackground(Color.CYAN);
        textPanel2.setBackground(Color.CYAN);
        textPanel3.setBackground(Color.CYAN);
        textPanel4.setBackground(Color.CYAN);
        textPanel5.setBackground(Color.GREEN);

        return main;
    }

    public JPanel image(JPanel image,boolean boo){

        JButton Nothing;

        if(!ImG.equals("")){
            image.setLayout(new BoxLayout(image , BoxLayout.Y_AXIS));
            String [] list = ImG.split(":");
            String [] love = LIKE.split(":");
            JPanel iii = new JPanel();
            iii.setLayout(new BoxLayout(iii,BoxLayout.Y_AXIS));
            iii.setBackground(Color.CYAN);
            iii.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            for (int i = 0; i < list.length ; i++) {
                String im = list[i];
                String l = love[i];
                iii.add(likeAndImage(im , l));
            }
            if(boo) {
                Nothing = new JButton("Add More Image");
                iii.add(addImage(Nothing));
            }
            image.add(iii);
        }else{
            image = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            if(boo) {
                Nothing = new JButton("Add Image");
                image.add(addImage(Nothing),gbc);
            }else {

                JLabel lab = new JLabel("No Posts Yet");
                lab.setHorizontalAlignment(SwingConstants.CENTER);
                lab.setVerticalAlignment(SwingConstants.CENTER);
                lab.setFont(new Font("Serif", Font.PLAIN, 30));
                image.add(lab, gbc);
            }
        }
        image.setBackground(Color.CYAN);
        JScrollPane scrollPanel = new JScrollPane(image);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPanel,BorderLayout.CENTER);

        if (!boo) {
            JButton backButton = new JButton("Back");
            JButton Home = new JButton("Go Home");
            editBtn.btn(backButton,Home);
            JPanel p = new JPanel();

            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == backButton) {
                        frame.dispose();
                        backFrame.setVisible(true);
                    }
                }
            });

            Home.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == Home) {
                        frame.dispose();
                        homePage.setVisible(true);
                    }
                }
            });
            p.setBackground(Color.PINK);

            p.add(backButton);
            p.add(Home);

            panel.add(p, BorderLayout.SOUTH);
        }
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        scrollPanel.setBorder(border);
        panel.setBorder(border);

        return panel;
    }

    public JPanel likeAndImage(String name , String heart){
        Photo image = new Photo(name);
        image.setBackground(Color.CYAN);
        image.setPreferredSize(new Dimension(700, 420));

        JPanel imagePanel  = new JPanel();
        imagePanel.add(image);
        imagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePanel.setBackground(Color.cyan);

        ImageIcon icon = new ImageIcon("../Convo app/images/like.png");
        Image img = icon.getImage().getScaledInstance(50, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);
        JButton like = new JButton(heart,scaledIcon);
        like.setFont(new Font(heart, Font.PLAIN, 24));
        like.setBackground(Color.RED);
        like.setAlignmentX(Component.CENTER_ALIGNMENT);

        like.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == like) {
                        if(bull) {
                            like.setText(editBtn.likes(id, name, 1));
                            bull = false;
                        }else {
                            like.setText(editBtn.likes(id, name, -1));
                            bull = true;
                        }
                    }
            }
        });

        JPanel images = new JPanel();
        images.setLayout(new BoxLayout(images,BoxLayout.Y_AXIS));
        images.add(imagePanel);
        images.add(like);
        images.setBackground(Color.CYAN);
        images.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return images;
    }

    public void actionListener(JButton btn){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btn) {
                    UserDetails details = Stack.remove(id);
                    if(details != null) {
                        id = details.getID();
                        name1 = details.getName();
                        workPlace = details.getWork();
                        homeTown = details.getHome();
                        ImG = details.getImage() + btn.getText() + ".jpg" + ":";
                        LIKE = details.getLikes() + 0 + ":";
                        Stack.addToStack(id, name1, workPlace, homeTown, details.getFriends(),
                                ImG, LIKE);
                        file.addToFile2();
                    }else{
                        System.out.println("error");
                    }

                    btn.setBackground(Color.orange);

                    btn.setEnabled(false);

                    imagee.removeAll();

                    imagee.add(image(new JPanel(),true));
                }
            }
        });

    }

    public JPanel addImage(JButton Nothing){
        JPanel image = new JPanel();
        editBtn.btn(Nothing);
        image.add(Nothing);
        image.setBackground(Color.cyan);

        Nothing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == Nothing) {
                    ArrayList<String> myList = new ArrayList<String>();
                    myList.add("Tree");
                    myList.add("Dog");
                    myList.add("Boat");
                    myList.add("Gym");
                    myList.add("Food");
                    myList.add("Tree");
                    myList.add("Tree");
                    myList.add("Tree");
                    myList.add("Tree");
                    myList.add("Tree");
                    myList.add("Tree");
                    myList.add("Tree");

                    JFrame frame1 = new JFrame("Convo");
                    JButton button = new JButton("Back");
                    JPanel panel = new JPanel();
                    JPanel panel2 = new JPanel();

                    frame1.setSize(500, 400);
                    frame1.setLocationRelativeTo(null);

                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                    panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
                    panel2.setPreferredSize(new Dimension(150, 100));
                    panel2.setBackground(Color.PINK);

                    editBtn.btn(button);

                    ButtonGroup group = new ButtonGroup();
                    JButton button1;
                    for (int i = 0; i < myList.size(); i++) {
                        button1 = new JButton(myList.get(i));
                        editBtn.btn2(button1);
                        button1.setPreferredSize(new Dimension(100, 50));
                        group.add(button1);
                        panel2.add(button1);
                        actionListener(button1);
                    }

                    JScrollPane scrollPanel1 = new JScrollPane(panel2);
                    scrollPanel1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    scrollPanel1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                    JScrollPane pane = new JScrollPane();

                    panel.add(scrollPanel1);
                    panel.add(Box.createVerticalStrut(10));
                    panel.add(button);
                    panel.add(Box.createVerticalStrut(20));
                    panel.setBackground(Color.cyan);

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frame1.dispose();
                            frame.setVisible(true);
                        }
                    });

                    frame1.add(panel);
                    frame1.setVisible(true);
                }
            }
        });
        return image;
    }

}
