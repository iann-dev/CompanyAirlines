import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class EmployeeManagementPanel extends FlightManagementSystem{
    JFrame empFrame;
    static int flag =0;
    public static void SetTrue(int n){
        flag = n;
    }
    public EmployeeManagementPanel() throws SQLException{
        empFrame = new JFrame("Portal Gerenciamento de Funcionarios ");
        JLabel welcomeLabel = new JLabel("Portal Gerenciamento de Funcionarios ");
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
        welcomeLabel.setBounds(160, 130, 500, 50); // Adjust the width to accommodate the text
        empFrame.add(welcomeLabel);

        JButton viewEmpButton = new JButton("Ver todos funcionarios");
        viewEmpButton.setBounds(200, 200, 300, 40);
        viewEmpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        viewEmpButton.setFocusPainted(false);
        viewEmpButton.addActionListener(actionEvent -> {
            try {
                viewEmployee();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        });
        empFrame.add(viewEmpButton);

        JButton addEmpButton = new JButton("Adicionar funcionarios");
        addEmpButton.setBounds(200, 270, 300, 40);
        addEmpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addEmpButton.setFocusPainted(false);
        addEmpButton.addActionListener(actionEvent -> {
            try {
                addEmployee();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        });
        empFrame.add(addEmpButton);

        JButton editEmpButton = new JButton("Editar um funcionario");
        editEmpButton.setBounds(200, 340, 300, 40);
        editEmpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        editEmpButton.setFocusPainted(false);
        editEmpButton.addActionListener(actionEvent -> {
            try {
                editEmployee();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        });
        empFrame.add(editEmpButton);

        JButton deleteEmpButton = new JButton("Deletar um funcionario");
        deleteEmpButton.setBounds(200, 410, 300, 40);
        deleteEmpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        deleteEmpButton.setFocusPainted(false);
        deleteEmpButton.addActionListener(deleteEvent -> {
            deleteEmployee();
        });
        empFrame.add(deleteEmpButton);

        JButton exitButton = new JButton("Voltar");
        exitButton.setBounds(200, 480, 300, 40);
        exitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        exitButton.setFocusPainted(false);

        exitButton.addActionListener(actionEvent -> {
            empFrame.dispose();
            if(flag==1){
                ManagerPortal.menuFrame.setVisible(true);
            }
            else if(flag==2) {
                AdminPortal.AdmenuFrame.setVisible(true);
            }
        });
        empFrame.add(exitButton);

        empFrame.setSize(700, 750);
        empFrame.getContentPane().setBackground(new Color(47, 236, 4));
        LineBorder blackBorder = new LineBorder(new Color(3, 86, 1), 7);
        empFrame.getRootPane().setBorder(blackBorder);
        empFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        empFrame.setLayout(null);
        empFrame.setVisible(true);
        empFrame.setLocationRelativeTo(null);
    }

    public void viewEmployee() throws SQLException{
        empFrame.setVisible(false);

        JFrame frame=new JFrame("Lista Funcionarios");
        JPanel panel=new JPanel();
        JdbcConnector jdbcConnector = new JdbcConnector();
        jdbcConnector.connect();
        Connection conn = jdbcConnector.getConnection();
        jdbcConnector.connect();
        Statement statement = conn.createStatement();
        String q = "select * from employee";
        ResultSet resultSet = statement.executeQuery(q);

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
            employeeCard.setBackground(new Color(166, 209, 230));
            employeeCard.setBorder(new EmptyBorder(20, 50, 20, 50));
            GridLayout cardLayout = new GridLayout(0, 2);
            cardLayout.setHgap(5);
            cardLayout.setVgap(10);
            employeeCard.setLayout(cardLayout);
            panel.add(employeeCard);
        }

        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Voltar");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(450, 30, 200,50);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            frame.dispose();
            empFrame.setVisible(true);
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

    public void addEmployee() throws SQLException  {

        empFrame.setVisible(false);

        JFrame frame=new JFrame("Lista Funcionarios");
        JPanel panel=new JPanel();
        JLabel unameLabel = new JLabel("Entrar Usuario");
        unameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField unameVal = new JTextField("");
        unameVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel passLabel = new JLabel("Entrar Senha");
        passLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField passVal = new JTextField("");
        passVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel idLabel = new JLabel("Entrar ID");
        idLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField idVal = new JTextField("");
        idVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        idVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // if it's not a number, ignore the event
                }
            }
        });

        JLabel nameLabel = new JLabel("Inserir Nome");
        nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField nameVal = new JTextField("");
        nameVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel genderLabel = new JLabel("Inserir Generor");
        genderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JComboBox<String> genderVal = new JComboBox<>(new String[]{"Masculino", "Feminino"});
        genderVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel phoneLabel = new JLabel("Inserir numero de telefone");
        phoneLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField phoneVal = new JTextField("");
        phoneVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel emailLabel = new JLabel("Inserir endereço de email");
        emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField emailVal = new JTextField("");
        emailVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel designationLabel = new JLabel("Inserir Designação");
        designationLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField designationVal = new JTextField("");
        designationVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel salaryLabel = new JLabel("Inserir Salario ($)");
        salaryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField salaryVal = new JTextField("");
        salaryVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        salaryVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        panel.add(unameLabel);
        panel.add(unameVal);
        panel.add(passLabel);
        panel.add(passVal);
        panel.add(idLabel);
        panel.add(idVal);
        panel.add(nameLabel);
        panel.add(nameVal);
        panel.add(genderLabel);
        panel.add(genderVal);
        panel.add(phoneLabel);
        panel.add(phoneVal);
        panel.add(emailLabel);
        panel.add(emailVal);
        panel.add(designationLabel);
        panel.add(designationVal);
        panel.add(salaryLabel);
        panel.add(salaryVal);


        GridLayout cardLayout = new GridLayout(0, 2);
        cardLayout.setHgap(60);
        cardLayout.setVgap(40);
        panel.setLayout(cardLayout);

        panel.setSize(1000, 400);
        panel.setBackground(new Color(166, 209, 230));
        panel.setBorder(new EmptyBorder(20, 50, 20, 50));

        JButton backButton = new JButton("Voltar");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(450, 30, 200,50);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            frame.dispose();
            empFrame.setVisible(true);
        });
        panel.add(backButton);

        JButton submitButton = new JButton("Enviar");
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
                String query = "INSERT INTO employee(username, password, id, name, gender, phonenum, email, designation, salary) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                pstmt = conn.prepareStatement(query);

                String uname = unameVal.getText();
                String pass = passVal.getText();
                int id = Integer.parseInt(idVal.getText());
                String name = nameVal.getText();
                String gender = (String) genderVal.getSelectedItem();
                int phonenum = Integer.parseInt(phoneVal.getText());
                String email = emailVal.getText();
                String designation = designationVal.getText();
                int salary = Integer.parseInt(salaryVal.getText());

                pstmt.setString(1, uname);
                pstmt.setString(2, pass);
                pstmt.setInt(3, id);
                pstmt.setString(4, name);
                pstmt.setString(5, gender);
                pstmt.setInt(6, phonenum);
                pstmt.setString(7, email);
                pstmt.setString(8, designation);
                pstmt.setInt(9, salary);

                int rows = pstmt.executeUpdate();

                if (rows > 0) {
                    System.out.println("Funcionario foi adicionado");
                } else {
                    System.out.println("Não foi possivel adicionar funcionario");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            empFrame.setVisible(true);
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

    public void editEmployee() throws SQLException {
        empFrame.setVisible(false);

        JFrame frame = new JFrame("Editar Funcionario");

        JLabel label = new JLabel("Entrar id do funcionario");
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
                    e.consume();
                }
            }
        });

        JButton backButton = new JButton("voltar");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(275, 400, 150,40);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            empFrame.setVisible(true);
            frame.dispose();

        });
        frame.add(backButton);

        JButton submitButton = new JButton("Enviar");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        submitButton.setBounds(525, 400, 150,40);
        submitButton.setFocusPainted(false);

        submitButton.addActionListener(actionEvent -> {
            JdbcConnector jdbcConnector = new JdbcConnector();
            jdbcConnector.connect();
            Connection conn = jdbcConnector.getConnection();
            jdbcConnector.connect();
            int id = Integer.parseInt(idVal.getText());
            String q = "select * from employee where id = ?";
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            try {
                pstmt = conn.prepareStatement(q);
                pstmt.setInt(1, id);
                resultSet = pstmt.executeQuery();
                if (resultSet.next()) {
                    System.out.println(resultSet.getString(2));
//                    return true;
                    editEmployeeHelper(id, frame);
                }
                else{
                    JFrame popup = new JFrame("ID Invalido");
                    JLabel popupMsg = new JLabel("O ID Inserido é invalido");
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
    public void editEmployeeHelper(int id, JFrame parentFrame) throws SQLException {
        JFrame frame = new JFrame("Editar funcionario");
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
        nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField nameVal = new JTextField(resultSet.getString(4));
        nameVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel genderLabel = new JLabel("Genero");
        genderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        String gender = resultSet.getString(5);
        JComboBox<String> genderVal = new JComboBox<>(new String[]{"Masculino", "Feminino"});
        genderVal.setSelectedIndex(gender.equals("Masculino") ? 0 : 1 );
        genderVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel phoneLabel = new JLabel("Numero de telefone");
        phoneLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField phoneVal = new JTextField(String.valueOf(resultSet.getInt(6)));
        phoneVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField emailVal = new JTextField(resultSet.getString(7));
        emailVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel designationLabel = new JLabel("Designação");
        designationLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField designationVal = new JTextField(resultSet.getString(8));
        designationVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel salaryLabel = new JLabel("Salario");
        salaryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField salaryVal = new JTextField(String.valueOf(resultSet.getInt(9)));
        salaryVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        salaryVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });

        panel.add(nameLabel);
        panel.add(nameVal);
        panel.add(genderLabel);
        panel.add(genderVal);
        panel.add(phoneLabel);
        panel.add(phoneVal);
        panel.add(emailLabel);
        panel.add(emailVal);
        panel.add(designationLabel);
        panel.add(designationVal);
        panel.add(salaryLabel);
        panel.add(salaryVal);


        GridLayout cardLayout = new GridLayout(0, 2);
        cardLayout.setHgap(60);
        cardLayout.setVgap(40);
        panel.setLayout(cardLayout);

        panel.setSize(1000, 400);
        panel.setBackground(new Color(166, 209, 230));
        panel.setBorder(new EmptyBorder(20, 50, 20, 50));

        JButton backButton = new JButton("Voltar");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(450, 30, 200,50);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            frame.dispose();
            empFrame.setVisible(true);
        });
        panel.add(backButton);

        JButton submitButton = new JButton("Enviar");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        submitButton.setBounds(450, 30, 200,50);
        submitButton.setFocusPainted(false);

        submitButton.addActionListener(actionListener -> {
            PreparedStatement ps  = null;
            String name = nameVal.getText();
            String genderValSelectedItem = (String) genderVal.getSelectedItem();
            int phoneNum = Integer.parseInt(phoneVal.getText());
            String email = emailVal.getText();
            String designation = designationVal.getText();
            int salary = Integer.parseInt(salaryVal.getText());
            String query = "update employee set name = ?, gender = ?, phoneNum = ?, email = ?, designation = ?, salary = ? where id = ?";
            try {
                ps = conn.prepareStatement(query);
                ps.setString(1, name);
                ps.setString(2, genderValSelectedItem);
                ps.setInt(3, phoneNum);
                ps.setString(4, email);
                ps.setString(5, designation);
                ps.setInt(6, salary);
                ps.setInt(7, id);
                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            empFrame.setVisible(true);
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

    public void deleteEmployee(){
        empFrame.setVisible(false);

        JFrame frame = new JFrame("Deletar Funcionario");

        JLabel label = new JLabel("Enter id do funcionario");
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
                    e.consume();
                }
            }
        });

        JButton backButton = new JButton("Voltar");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(275, 400, 150,40);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            empFrame.setVisible(true);
            frame.dispose();

        });
        frame.add(backButton);

        JButton submitButton = new JButton("Deletar");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        submitButton.setBounds(525, 400, 150,40);
        submitButton.setFocusPainted(false);

        submitButton.addActionListener(actionEvent -> {
            JdbcConnector jdbcConnector = new JdbcConnector();
            jdbcConnector.connect();
            Connection conn = jdbcConnector.getConnection();
            jdbcConnector.connect();
            int id = Integer.parseInt(idVal.getText());
            String q = "select * from employee where id = ?";
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            try {
                pstmt = conn.prepareStatement(q);
                pstmt.setInt(1, id);
                resultSet = pstmt.executeQuery();
                if (resultSet.next()) {
                    System.out.println(resultSet.getString(2));

                    String deleteQuery = "delete from employee where id = ?";
                    pstmt = conn.prepareStatement(deleteQuery);
                    pstmt.setInt(1, id);
                    pstmt.executeUpdate();
                    empFrame.setVisible(true);
                    frame.dispose();

                }
                else{
                    JFrame popup = new JFrame("ID Invalido");
                    JLabel popupMsg = new JLabel("O ID inserido é invalido");
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
