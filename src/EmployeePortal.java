import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EmployeePortal extends FlightManagementSystem{
    public static JFrame menuFrame;
    private JLabel notificationLabel;
    private JLabel dateLabel;
    private boolean hasNotification = false;

    EmployeePortal() throws SQLException, IOException {

        menuFrame = new JFrame("Funcionario Portal");
        // Set the background image
        String imagePath = "C:/Users/Aluno 3/Downloads/Airlines/src/a.jpg"; // Replace with the path to your image file
        BufferedImage backgroundImage = ImageIO.read(new File(imagePath));
        menuFrame.setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        });
        menuFrame.setLayout(null);

        JdbcConnector jdbcConnector = new JdbcConnector();
        jdbcConnector.connect();
        Connection conn = jdbcConnector.getConnection();
        jdbcConnector.connect();
        int id = Employee.getId();
        System.out.println(id);
        String q = "select name from employee where id = ?";
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        JLabel nameLabel = null;
        try {
            pstmt = conn.prepareStatement(q);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                nameLabel = new JLabel("Olá " + resultSet.getString(1)+",");
                nameLabel.setFont(new Font("Arial", Font.BOLD, 25));
                nameLabel.setBounds(40, 160, 800, 50);
                menuFrame.add(nameLabel);
            }
            resultSet.close();
            pstmt.close();
            conn.close();
            jdbcConnector.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        JLabel welcomeLabel = new JLabel("Bem vindo(a) ao Portal do Funcionario");
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
        welcomeLabel.setBounds(380, 120, 800, 50);
        menuFrame.add(welcomeLabel);

        JButton viewEmpButton = new JButton("Ver Perfil");
        viewEmpButton.setBounds(400, 200, 300, 40);
        viewEmpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        viewEmpButton.setFocusPainted(false);
        viewEmpButton.addActionListener(actionEvent -> {
            try {
                viewProfile();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        });
        menuFrame.add(viewEmpButton);

        JButton editEmpButton = new JButton("Editar perfil");
        editEmpButton.setBounds(400, 270, 300, 40);
        editEmpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        editEmpButton.setFocusPainted(false);
        editEmpButton.addActionListener(actionEvent -> {
            try {
                editProfile();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        });
        menuFrame.add(editEmpButton);

        JButton flightButton = new JButton("Detalhes do voo");
        flightButton.setBounds(400, 340, 300, 40);
        flightButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        flightButton.setFocusPainted(false);

        flightButton.addActionListener(actionEvent -> {
            new flight_info();
            menuFrame.setVisible(false);
        });
        menuFrame.add(flightButton);

        JButton exitButton = new JButton("Sair");
        exitButton.setBounds(400, 410, 300, 40);
        exitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        exitButton.setFocusPainted(false);

        exitButton.addActionListener(actionEvent -> {
            menuFrame.dispose();
        });
        menuFrame.add(exitButton);

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        JPanel datePanel = new JPanel();
        datePanel.add(dateLabel);
        datePanel.setBounds(850, 10, 200, 30);
        menuFrame.add(datePanel);

        Timer timer = new Timer(1000, e -> updateDate());
        timer.start();

        ImageIcon bellIcon = new ImageIcon("C:/Users/Aluno 3/Downloads/Airlines/src/bell_icon.png");
        Image scaledImage = bellIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledBellIcon = new ImageIcon(scaledImage);

        this.notificationLabel = new JLabel(scaledBellIcon);
        notificationLabel.setBounds(800, 120, 50, 50);
        menuFrame.add(notificationLabel);


        notificationLabel.setToolTipText("Nenhuma notificação");
        notificationLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        notificationLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (hasNotification) {
                    showNotifications();
                    hasNotification = false;
                    updateNotificationIcon();
                } else {
                }
            }
        });
        menuFrame.getContentPane().add(this.notificationLabel);

        menuFrame.setSize(1100, 650);
        menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menuFrame.setVisible(true);
        menuFrame.setLocationRelativeTo(null);
        checkForNotifications();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void checkForNotifications() {
        try {
            int id = Employee.getId();
            JdbcConnector jdbcConnector = new JdbcConnector();
            jdbcConnector.connect();
            Connection conn = jdbcConnector.getConnection();
            jdbcConnector.connect();
            PreparedStatement statement = conn.prepareStatement("SELECT fl_code FROM empflightdetails WHERE emp_id = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                hasNotification = true;
                updateNotificationIcon();
            }
            resultSet.close();
            statement.close();
            conn.close();
            jdbcConnector.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateNotificationIcon() {
        ImageIcon bellIcon;
        if (hasNotification) {
            bellIcon = new ImageIcon("C:/Users/Aluno 3/Downloads/Airlines/src/bell_with_notification.png"); // Replace with the path to your bell icon with a notification dot
            notificationLabel.setToolTipText("Clique para ver configurações");
        } else {
            bellIcon = new ImageIcon("C:/Users/Aluno 3/Downloads/Airlines/src/bell_icon.png");
            notificationLabel.setToolTipText("Sem novas notificações");
        }

        Image scaledImage = bellIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        notificationLabel.setIcon(new ImageIcon(scaledImage));
    }
    private void updateDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("DDD, MMM d, ''aa");
        String currentDate = dateFormat.format(new Date());
        dateLabel.setText("Date: " + currentDate);
        dateLabel.setBackground(Color.cyan);
        dateLabel.setForeground(Color.blue);
    }
    private void showNotifications() {
        int id = Employee.getId();
        try {
            JdbcConnector jdbcConnector = new JdbcConnector();
            jdbcConnector.connect();
            Connection conn = jdbcConnector.getConnection();
            jdbcConnector.connect();
            PreparedStatement statement = conn.prepareStatement("SELECT fdate FROM empflightdetails WHERE emp_id = ?");
            statement.setInt(1, id); // Replace with the appropriate employee ID
            ResultSet resultSet = statement.executeQuery();

            StringBuilder notificationText = new StringBuilder();
            String flightDate = null;
            while (resultSet.next()) {
                flightDate = resultSet.getString(1);
            }
            if (flightDate != null) {
                notificationText.append("**Noticia Urgente : \n=> Novo horario de voo em ").append(flightDate).append("\n");
            }
            if (notificationText.length() == 0) {
                JOptionPane.showMessageDialog(null, "Sem novos horarios de voo", "Notificações", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());

                JTextArea textArea = new JTextArea(notificationText.toString());
                textArea.setEditable(false);
                textArea.setFont(new Font("Arial", Font.BOLD, 15)); // Set the desired font and size
                textArea.setForeground(Color.RED); // Set the text color to red
                panel.add(textArea, BorderLayout.CENTER);

                JButton viewDetailsButton = new JButton("Ver Detalhes");
                panel.add(viewDetailsButton, BorderLayout.SOUTH);

                JDialog dialog = new JDialog();
                dialog.setTitle("Notificações");
                dialog.getContentPane().add(panel);
                dialog.setSize(300, 100);
                dialog.setLocationRelativeTo(null);
                dialog.setModal(true);
                dialog.setLocationRelativeTo(null);
                viewDetailsButton.addActionListener(e -> {
                        dialog.dispose();
                        new flight_info();
                });

                dialog.setVisible(true);
            }
            resultSet.close();
            statement.close();
            conn.close();
            jdbcConnector.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void viewProfile() throws SQLException{
        menuFrame.setVisible(false);
        JdbcConnector jdbcConnector = new JdbcConnector();
        jdbcConnector.connect();
        Connection conn = jdbcConnector.getConnection();
        jdbcConnector.connect();
        int id = Employee.getId();
        System.out.println(id);
        JFrame frame=new JFrame("Perfil");
        JPanel panel=new JPanel();
       String q = "select * from employee where id = ?";
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = conn.prepareStatement(q);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                JPanel employeeCard = new JPanel();

                JLabel idLabel = new JLabel("ID");
                idLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                JLabel idVal = new JLabel(String.valueOf(resultSet.getInt(3)));
                idVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                JLabel nameLabel = new JLabel("Nome");
                nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                JLabel nameVal = new JLabel(resultSet.getString(4));
                nameVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                JLabel genderLabel = new JLabel("Genero");
                genderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                JLabel genderVal = new JLabel(resultSet.getString(5));
                genderVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                JLabel phoneLabel = new JLabel("Numero de telefone");
                phoneLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                JLabel phoneVal = new JLabel(String.valueOf(resultSet.getInt(6)));
                phoneVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                JLabel emailLabel = new JLabel("Email");
                emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                JLabel emailVal = new JLabel(resultSet.getString(7));
                emailVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                JLabel designationLabel = new JLabel("Designação");
                designationLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                JLabel designationVal = new JLabel(resultSet.getString(8));
                designationVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                JLabel salaryLabel = new JLabel("Salario");
                salaryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                JLabel salaryVal = new JLabel(String.valueOf(resultSet.getInt(9)));
                salaryVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                employeeCard.add(idLabel);
                employeeCard.add(idVal);
                employeeCard.add(nameLabel);
                employeeCard.add(nameVal);
                employeeCard.add(genderLabel);
                employeeCard.add(genderVal);
                employeeCard.add(phoneLabel);
                employeeCard.add(phoneVal);
                employeeCard.add(emailLabel);
                employeeCard.add(emailVal);
                employeeCard.add(designationLabel);
                employeeCard.add(designationVal);
                employeeCard.add(salaryLabel);
                employeeCard.add(salaryVal);

                employeeCard.setSize(1000, 400);
                employeeCard.setBackground(new Color(9, 245, 215));
                employeeCard.setBorder(new EmptyBorder(20, 50, 20, 50));
                GridLayout cardLayout = new GridLayout(0, 2);
                cardLayout.setHgap(5);
                cardLayout.setVgap(10);
                employeeCard.setLayout(cardLayout);
                panel.add(employeeCard);
            }
            resultSet.close();
            pstmt.close();
            conn.close();
            jdbcConnector.close();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Voltar");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(450, 30, 200,50);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            frame.dispose();
            menuFrame.setVisible(true);
        });
        buttonPanel.add(backButton);
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(new Color(101, 126, 208));
        panel.add(buttonPanel);

        GridLayout layout = new GridLayout(0, 1);
        layout.setVgap(30);
        panel.setLayout(layout);
        panel.setBackground(new Color(101, 126, 208));
        panel.setBorder(new EmptyBorder(50, 0, 50, 0));

        JScrollPane scrollBar=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1100,750));
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void editProfile() throws SQLException{
        menuFrame.setVisible(false);

        JFrame frame = new JFrame("Editar Funcionario");
        JdbcConnector jdbcConnector = new JdbcConnector();
        jdbcConnector.connect();
        Connection conn = jdbcConnector.getConnection();
        jdbcConnector.connect();
        int id = Employee.getId();
            String q = "select * from employee where id = ?";
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            try {
                pstmt = conn.prepareStatement(q);
                pstmt.setInt(1, id);
                resultSet = pstmt.executeQuery();
                if (resultSet.next()) {
                    System.out.println(resultSet.getString(2));
                    editEmployeeHelper(id, frame);
                }
                else{
                    JFrame popup = new JFrame("ID Invalido");
                    JLabel popupMsg = new JLabel("O ID inserido está incorreto");
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
            frame.setLayout(null);
        frame.setSize(new Dimension(1100,750));
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
    }

    public void editEmployeeHelper(int id, JFrame parentFrame) throws SQLException {
        JFrame frame = new JFrame("Editar Funcionario");
        JPanel panel=new JPanel();
        JdbcConnector jdbcConnector = new JdbcConnector();
        jdbcConnector.connect();
        Connection conn = jdbcConnector.getConnection();
        jdbcConnector.connect();
        String q = "select * from employee where id = ?";
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

        JLabel nameLabel = new JLabel("Nome");
        nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        JTextField nameVal = new JTextField(resultSet.getString(4));
        nameVal.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        nameVal.setPreferredSize(new Dimension(200, 25));

        JLabel genderLabel = new JLabel("Genero");
        genderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        String gender = resultSet.getString(5);
        JComboBox<String> genderVal = new JComboBox<>(new String[]{"Masculino", "Feminino"});
        genderVal.setSelectedIndex(gender.equals("Masculino") ? 0 : 1 );
        genderVal.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        genderVal.setPreferredSize(new Dimension(200, 25));

        JLabel phoneLabel = new JLabel("Numero de Telefone");
        phoneLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        JTextField phoneVal = new JTextField(String.valueOf(resultSet.getInt(6)));
        phoneVal.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        phoneVal.setPreferredSize(new Dimension(200, 25));

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        JTextField emailVal = new JTextField(resultSet.getString(7));
        emailVal.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        emailVal.setPreferredSize(new Dimension(200, 25));

        panel.add(nameLabel);
        panel.add(nameVal);
        panel.add(genderLabel);
        panel.add(genderVal);
        panel.add(phoneLabel);
        panel.add(phoneVal);
        panel.add(emailLabel);
        panel.add(emailVal);

        GridLayout cardLayout = new GridLayout(0, 2);
        cardLayout.setHgap(60);
        cardLayout.setVgap(40);
        panel.setLayout(cardLayout);

        panel.setSize(1000, 400);
        panel.setBackground(new Color(166, 209, 230));
        panel.setBorder(new EmptyBorder(20, 50, 20, 50));

        JButton backButton = new JButton("Voltar");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        backButton.setPreferredSize(new Dimension(150, 25));
        backButton.addActionListener(actionListener -> {
            frame.dispose();
            menuFrame.setVisible(true);
        });
        panel.add(backButton);

        JButton submitButton = new JButton("Enviar");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        backButton.setPreferredSize(new Dimension(150, 25));
        submitButton.addActionListener(actionListener -> {
            PreparedStatement ps  = null;
            String name = nameVal.getText();
            String genderValSelectedItem = (String) genderVal.getSelectedItem();
            int phoneNum = Integer.parseInt(phoneVal.getText());
            String email = emailVal.getText();
            String query = "update employee set name = ?, gender = ?, phoneNum = ?, email = ? where id = ?";
            try {
                ps = conn.prepareStatement(query);
                ps.setString(1, name);
                ps.setString(2, genderValSelectedItem);
                ps.setInt(3, phoneNum);
                ps.setString(4, email);
                ps.setInt(5, id);
                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            menuFrame.setVisible(true);
            frame.dispose();

        });
        panel.add(submitButton);

        panel.setBackground(new Color(254, 251, 246));
        panel.setBorder(new EmptyBorder(50, 50, 50, 50));

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600,500));
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        parentFrame.dispose();
    }

}
