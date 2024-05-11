import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class ManagerManagementPanel extends FlightManagementSystem{
    JFrame manFrame;
    public ManagerManagementPanel() throws SQLException{
        manFrame = new JFrame("MG Portal");
        JLabel welcomeLabel = new JLabel("Manager Management Portal");
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
        welcomeLabel.setBounds(160, 130, 500, 50);
        manFrame.add(welcomeLabel);

        JButton viewManButton = new JButton("View all Managers");
        viewManButton.setBounds(200, 200, 300, 40);
        viewManButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        viewManButton.setFocusPainted(false);
        viewManButton.addActionListener(actionEvent -> {
            try {
                viewManager();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        });
        manFrame.add(viewManButton);

        JButton addManButton = new JButton("Add a manager");
        addManButton.setBounds(200, 270, 300, 40);
        addManButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addManButton.setFocusPainted(false);
        addManButton.addActionListener(actionEvent -> {
            try {
                addManager();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        });
        manFrame.add(addManButton);

        JButton editManButton = new JButton("Edit a manager");
        editManButton.setBounds(200, 340, 300, 40);
        editManButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        editManButton.setFocusPainted(false);
        editManButton.addActionListener(actionEvent -> {
            try {
                editManager();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        });
        manFrame.add(editManButton);

        JButton deleteManButton = new JButton("Delete a manager");
        deleteManButton.setBounds(200, 410, 300, 40);
        deleteManButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        deleteManButton.setFocusPainted(false);
        deleteManButton.addActionListener(deleteEvent -> {
            deleteManager();
        });
        manFrame.add(deleteManButton);

        JButton exitButton = new JButton("Back");
        exitButton.setBounds(200, 480, 300, 40);
        exitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        exitButton.setFocusPainted(false);

        exitButton.addActionListener(actionEvent -> {
            manFrame.dispose();
            AdminPortal.AdmenuFrame.setVisible(true);

        });
        manFrame.add(exitButton);

        manFrame.setSize(700, 750);
        manFrame.getContentPane().setBackground(new Color(2, 174, 248));
        LineBorder blackBorder = new LineBorder(new Color(1, 25, 86), 7);
        manFrame.getRootPane().setBorder(blackBorder);
        manFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        manFrame.setLayout(null);
        manFrame.setVisible(true);
        manFrame.setLocationRelativeTo(null);
    }

    public void viewManager() throws SQLException{
        manFrame.setVisible(false);

        JFrame frame=new JFrame("Manager Records");
        JPanel panel=new JPanel();
        JdbcConnector jdbcConnector = new JdbcConnector();
        jdbcConnector.connect();
        Connection conn = jdbcConnector.getConnection();
        jdbcConnector.connect();
        Statement statement = conn.createStatement();
        String q = "select * from manager";
        ResultSet resultSet = statement.executeQuery(q);

        while (resultSet.next()) {
            JPanel managerCard = new JPanel();

            JLabel unameLabel = new JLabel("Username");
            unameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel unameVal = new JLabel(resultSet.getString(1));
            unameVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel passLabel = new JLabel("Password");
            passLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel passVal = new JLabel(resultSet.getString(2));
            passVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel idLabel = new JLabel("ID");
            idLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel idVal = new JLabel(String.valueOf(resultSet.getInt(3)));
            idVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel nameLabel = new JLabel("Name");
            nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel nameVal = new JLabel(resultSet.getString(4));
            nameVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel emailLabel = new JLabel("Email");
            emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel emailVal = new JLabel(resultSet.getString(5));
            emailVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel deptLabel = new JLabel("Department");
            deptLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel deptVal = new JLabel(resultSet.getString(6));
            deptVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            managerCard.add(unameLabel);
            managerCard.add(unameVal);
            managerCard.add(passLabel);
            managerCard.add(passVal);
            managerCard.add(idLabel);
            managerCard.add(idVal);
            managerCard.add(nameLabel);
            managerCard.add(nameVal);
            managerCard.add(emailLabel);
            managerCard.add(emailVal);
            managerCard.add(deptLabel);
            managerCard.add(deptVal);

            managerCard.setSize(1000, 400);
            managerCard.setBackground(new Color(166, 209, 230));
            managerCard.setBorder(new EmptyBorder(20, 50, 20, 50));
            GridLayout cardLayout = new GridLayout(0, 2);
            cardLayout.setHgap(5);
            cardLayout.setVgap(10);
            managerCard.setLayout(cardLayout);
            panel.add(managerCard);
        }

        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(450, 30, 200,50);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            frame.dispose();
            manFrame.setVisible(true);
        });
        buttonPanel.add(backButton);
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(new Color(254, 251, 246));
        panel.add(buttonPanel);


        GridLayout layout = new GridLayout(0, 1);
        layout.setVgap(30);
        panel.setLayout(layout);
        panel.setBackground(new Color(254, 251, 246));
        panel.setBorder(new EmptyBorder(50, 0, 50, 0));

        JScrollPane scrollBar=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1100,750));
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        resultSet.close();
        statement.close();
        conn.close();
        jdbcConnector.close();
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void addManager() throws SQLException  {

        manFrame.setVisible(false);

        JFrame frame=new JFrame("Manager Records");
        JPanel panel=new JPanel();

        JLabel idLabel = new JLabel("Enter ID");
        idLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField idVal = new JTextField("");
        idVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        idVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });

        JLabel unameLabel = new JLabel("Give username");
        unameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField unameVal = new JTextField("");
        unameVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel passLabel = new JLabel("Give password");
        passLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField passVal = new JTextField("");
        passVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel nameLabel = new JLabel("Enter Name");
        nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField nameVal = new JTextField("");
        nameVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel emailLabel = new JLabel("Enter Email Address");
        emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField emailVal = new JTextField("");
        emailVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel deptLabel = new JLabel("Enter Department");
        deptLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField deptVal = new JTextField("");
        deptVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        panel.add(unameLabel);
        panel.add(unameVal);
        panel.add(passLabel);
        panel.add(passVal);
        panel.add(idLabel);
        panel.add(idVal);
        panel.add(nameLabel);
        panel.add(nameVal);
        panel.add(emailLabel);
        panel.add(emailVal);
        panel.add(deptLabel);
        panel.add(deptVal);

        GridLayout cardLayout = new GridLayout(0, 2);
        cardLayout.setHgap(60);
        cardLayout.setVgap(40);
        panel.setLayout(cardLayout);

        panel.setSize(1000, 400);
        panel.setBackground(new Color(166, 209, 230));
        panel.setBorder(new EmptyBorder(20, 50, 20, 50));

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(450, 30, 200,50);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            frame.dispose();
            manFrame.setVisible(true);
        });
        panel.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        submitButton.setBounds(450, 30, 200,50);
        submitButton.setFocusPainted(false);

        submitButton.addActionListener(actionListener -> {
            JdbcConnector jdbcConnector = new JdbcConnector();
            jdbcConnector.connect();
            Connection conn = jdbcConnector.getConnection();
            jdbcConnector.connect();
            PreparedStatement pstmt = null;
            Statement statement = null;
            try {
                String query = "INSERT INTO manager(username, password, id, name, email, department) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
                pstmt = conn.prepareStatement(query);

                String uname = unameVal.getText();
                String pass = passVal.getText();
                int id = Integer.parseInt(idVal.getText());
                String name = nameVal.getText();
                String email = emailVal.getText();
                String department = deptVal.getText();

                pstmt.setString(1, uname);
                pstmt.setString(2, pass);
                pstmt.setInt(3, id);
                pstmt.setString(4, name);
                pstmt.setString(5, email);
                pstmt.setString(6, department);

                int rows = pstmt.executeUpdate();

                if (rows > 0) {
                    System.out.println("Manager added successfully.");
                } else {
                    System.out.println("Unable to add Manager.");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            manFrame.setVisible(true);
            frame.dispose();

        });
        panel.add(submitButton);

        panel.setBackground(new Color(254, 251, 246));
        panel.setBorder(new EmptyBorder(50, 50, 50, 50));

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1100,750));
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void editManager() throws SQLException {
        manFrame.setVisible(false);

        JFrame frame = new JFrame("Edit Manager");

        JLabel label = new JLabel("Enter manager id");
        label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        label.setBounds(250,200,200,50);
        frame.add(label);

        JTextField idVal = new JTextField();
        idVal.setFont(new Font("Times New Roman", Font.BOLD, 20));
        idVal.setBounds(500,200,200,50);
        frame.add(idVal);
        idVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // if it's not a number, ignore the event
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(275, 400, 150,40);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            manFrame.setVisible(true);
            frame.dispose();

        });
        frame.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        submitButton.setBounds(525, 400, 150,40);
        submitButton.setFocusPainted(false);

        submitButton.addActionListener(actionEvent -> {
            JdbcConnector jdbcConnector = new JdbcConnector();
            jdbcConnector.connect();
            Connection conn = jdbcConnector.getConnection();
            jdbcConnector.connect();
            int id = Integer.parseInt(idVal.getText());
            String q = "select * from manager where id = ?";
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            try {
                pstmt = conn.prepareStatement(q);
                pstmt.setInt(1, id);
                resultSet = pstmt.executeQuery();
                if (resultSet.next()) {
                    System.out.println(resultSet.getString(2));
                    editManagerHelper(id, frame);
                }
                else{
                    JFrame popup = new JFrame("Invalid ID");
                    JLabel popupMsg = new JLabel("The ID you entered is invalid.");
                    popupMsg.setBounds(20,10,300,50);
                    popupMsg.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                    popup.add(popupMsg);

                    JButton button = new JButton("OK");
                    button.setBounds(120,60,70,20);
                    button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                    button.addActionListener(actionEvent2 -> {
                        popup.dispose();
                    });
                    popup.add(button);

                    popup.setLayout(null);
                    popup.setSize(350, 150);
                    popup.setVisible(true);

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

        frame.add(submitButton);

        frame.setLayout(null);
        frame.setSize(new Dimension(1100,750));
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    public void editManagerHelper(int id, JFrame parentFrame) throws SQLException {
        JFrame frame = new JFrame("Edit Manager");
        JPanel panel=new JPanel();
        JdbcConnector jdbcConnector = new JdbcConnector();
        jdbcConnector.connect();
        Connection conn = jdbcConnector.getConnection();
        jdbcConnector.connect();
        String q = "select * from manager where id = ?";
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = conn.prepareStatement(q);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        JLabel unameLabel = new JLabel("Username");
        unameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField unameVal = new JTextField(resultSet.getString(1));
        unameVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField passVal = new JTextField(resultSet.getString(2));
        passVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField nameVal = new JTextField(resultSet.getString(4));
        nameVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField emailVal = new JTextField(resultSet.getString(5));
        emailVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel deptLabel = new JLabel("Email");
        deptLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField deptVal = new JTextField(resultSet.getString(6));
        deptVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        panel.add(unameLabel);
        panel.add(unameVal);
        panel.add(passLabel);
        panel.add(passVal);
        panel.add(nameLabel);
        panel.add(nameVal);
        panel.add(emailLabel);
        panel.add(emailVal);
        panel.add(deptLabel);
        panel.add(deptVal);

        GridLayout cardLayout = new GridLayout(0, 2);
        cardLayout.setHgap(60);
        cardLayout.setVgap(40);
        panel.setLayout(cardLayout);

        panel.setSize(1000, 400);
        panel.setBackground(new Color(166, 209, 230));
        panel.setBorder(new EmptyBorder(20, 50, 20, 50));

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(450, 30, 200,50);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            frame.dispose();
            manFrame.setVisible(true);
        });
        panel.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        submitButton.setBounds(450, 30, 200,50);
        submitButton.setFocusPainted(false);

        submitButton.addActionListener(actionListener -> {
            PreparedStatement ps  = null;
            String uname = unameVal.getText();
            String pass = passVal.getText();
            String name = nameVal.getText();
            String email = emailVal.getText();
            String department = deptVal.getText();
            String query = "update manager set username = ?, password = ?, name = ?, email = ?, department = ? where id = ?";
            try {
                ps = conn.prepareStatement(query);
                ps.setString(1, uname);
                ps.setString(2, pass);
                ps.setString(3, name);
                ps.setString(4, email);
                ps.setString(5, department);
                ps.setInt(6, id);
                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            manFrame.setVisible(true);
            frame.dispose();

        });
        panel.add(submitButton);

        panel.setBackground(new Color(254, 251, 246));
        panel.setBorder(new EmptyBorder(50, 50, 50, 50));

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1100,750));
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        parentFrame.dispose();

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void deleteManager(){
        manFrame.setVisible(false);

        JFrame frame = new JFrame("Delete Manager");

        JLabel label = new JLabel("Enter manager id");
        label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        label.setBounds(250,200,200,50);
        frame.add(label);

        JTextField idVal = new JTextField();
        idVal.setFont(new Font("Times New Roman", Font.BOLD, 20));
        idVal.setBounds(500,200,200,50);
        frame.add(idVal);
        idVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // if it's not a number, ignore the event
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(275, 400, 150,40);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            manFrame.setVisible(true);
            frame.dispose();

        });
        frame.add(backButton);

        JButton submitButton = new JButton("Delete");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        submitButton.setBounds(525, 400, 150,40);
        submitButton.setFocusPainted(false);

        submitButton.addActionListener(actionEvent -> {
            JdbcConnector jdbcConnector = new JdbcConnector();
            jdbcConnector.connect();
            Connection conn = jdbcConnector.getConnection();
            jdbcConnector.connect();
            int id = Integer.parseInt(idVal.getText());
            String q = "select * from manager where id = ?";
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            try {
                pstmt = conn.prepareStatement(q);
                pstmt.setInt(1, id);
                resultSet = pstmt.executeQuery();
                if (resultSet.next()) {
                    System.out.println(resultSet.getString(2));

                    String deleteQuery = "delete from manager where id = ?";
                    pstmt = conn.prepareStatement(deleteQuery);
                    pstmt.setInt(1, id);
                    pstmt.executeUpdate();
                    manFrame.setVisible(true);
                    frame.dispose();

                }
                else{
                    JFrame popup = new JFrame("Invalid ID");
                    JLabel popupMsg = new JLabel("The ID you entered is invalid.");
                    popupMsg.setBounds(20,10,300,50);
                    popupMsg.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                    popup.add(popupMsg);

                    JButton button = new JButton("OK");
                    button.setBounds(120,60,70,20);
                    button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                    button.addActionListener(actionEvent2 -> {
                        popup.dispose();
                    });
                    popup.add(button);

                    popup.setLayout(null);
                    popup.setSize(350, 150);
                    popup.setVisible(true);

                }
                resultSet.close();
                pstmt.close();
                conn.close();
                jdbcConnector.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

        frame.add(submitButton);

        frame.setLayout(null);
        frame.setSize(new Dimension(1100,750));
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }
}
