import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogIn
{
    private JFrame frame;
    private JFrame frame3;
    private Edit editBtn;
    private JTextField textField,textField2,textField3,textField4,textField5;
    private File file;
    private MyStack stack;

    userProfile profile;

    public LogIn()
    {
        stack = new MyStack();
        textField = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextField();
        file = new File(stack);
        editBtn = new Edit(stack);
    }

    public static void main(String[] args)
    {
        LogIn panelsDemo = new LogIn();
        panelsDemo.run();
    }

    public void run(){
        stack = file.readFromAFile();
        frame = new JFrame("Convo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        mainPanel();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void mainPanel(){
        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("Welcome To Convo");
        label.setFont(new Font("Serif", Font.PLAIN, 50));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(label);
        topPanel.add(createTextPanel());

        JButton btn1 = new JButton("Sign Up");
        JButton btn2 = new JButton("Sign In");
        editBtn.btnSmall(btn1,btn2);

        btn1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == btn1){
                    textField2.setText(null);
                    textField3.setText(null);
                    textField4.setText(null);
                    textField5.setText(null);
                    firstButton();
                    frame.dispose();
                }
            }
        });
        btn2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == btn2) {
                    char check = 'c';
                    int ID = 0;
                    try {
                        ID = Integer.parseInt(textField.getText());
                        check = stack.specialCheck(ID);

                    } catch (NumberFormatException nfe) {
                        //
                    }
                    if (check == 'a') {
                        friendSuggestion friendSuggestion = new friendSuggestion(stack, ID);
                        friendSuggestion.suggestion();
                        frame.dispose();
                    } else if(check == 'b'){
                        profile = new userProfile(stack, ID);
                        profile.display();
                        frame.dispose();
                    }else{
                        editBtn.popUpRandom(new JLabel("No User Found"));
                    }
                }
            }
        });


        JPanel btnPanel = new JPanel();
        btnPanel.add(btn1);
        btnPanel.add(btn2);
        btnPanel.setBackground(Color.lightGray);

        topPanel.add(btnPanel);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.PINK);
        topPanel.add(Box.createVerticalGlue());
        frame.add(topPanel, BorderLayout.CENTER);

    }

    public JPanel createTextPanel() {
        JPanel textPanel = new JPanel();
        JLabel textLabel = new JLabel("User ID");
        textLabel.setFont(new Font("Serif", Font.PLAIN, 30));

        textField.setFont(new Font("Serif", Font.PLAIN, 30));
        textField.setPreferredSize(new Dimension(300, 50));

        textPanel.add(textLabel);
        textPanel.add(textField);
        textPanel.setLayout(new FlowLayout(FlowLayout.CENTER , 50, 50));
        textPanel.setBackground(Color.lightGray);
        return textPanel;
    }

    public void firstButton(){
        JButton btn3 = new JButton("Back");
        JButton btn4 = new JButton("Register");
        frame3 = new JFrame("Convo");

        editBtn.registration(textField2,textField3,textField4,textField5,frame3,btn3,btn4);

        btn3.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == btn3){
                    frame3.dispose();
                    textField.setText("");
                    frame.setVisible(true);
                }
            }
        });

        btn4.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == btn4){
                    char ch = editBtn.inputValidation(textField2,textField3,textField4,textField5);
                    if (ch == 'a'){
                        int id = Integer.parseInt(textField2.getText());
                        String name = capital(textField3.getText());
                        String work = capital(textField4.getText());
                        String home = capital(textField5.getText());

                        stack.addToStack(id,name,work,home,"","","");
                        file.addToFile2();
                        JButton btn = new JButton("Back");
                        JFrame ram = new JFrame("Registered");
                        editBtn.popUp(btn,ram,new JLabel("Registration Successful"),300);
                        btn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                ram.dispose();
                                frame3.dispose();
                                frame.setVisible(true);
                            }
                        });
                        ram.setVisible(true);
                    } else if (ch == 'b') {
                        editBtn.popUpRandom(new JLabel("Use Different ID"));

                    }else {
                        editBtn.popUpRandom(new JLabel("Incorrect Information"));
                    }
                }
            }
        });

        frame3.setVisible(true);
    }

    public String capital(String str){
        String capitalizedStr = Character.toUpperCase(str.charAt(0)) + str.substring(1);
        return capitalizedStr;
    }


}
