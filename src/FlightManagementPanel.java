import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

public class FlightManagementPanel extends FlightManagementSystem {
    JFrame flightFrame;
    static int flag =0;
    public static void SetTrue(int n){
        flag = n;
    }
    public FlightManagementPanel() throws SQLException {
        flightFrame = new JFrame("Voo Portal");
        JLabel welcomeLabel = new JLabel("Portal de Gerenciamento de Voo");
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
        welcomeLabel.setBounds(130, 150, 500, 50);
        flightFrame.add(welcomeLabel);

        JButton bookButton = new JButton("Adicionar Voo");
        bookButton.setBounds(150, 220, 300, 40);
        bookButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        bookButton.setFocusPainted(false);
        bookButton.addActionListener(actionEvent -> {
            try {
                bookFlights();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        });
        flightFrame.add(bookButton);

        JButton cancelButton = new JButton("Deletar Voo");
        cancelButton.setBounds(150, 290, 300, 40);
        cancelButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(actionEvent -> {
            try {
                delEmp();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        });
        flightFrame.add(cancelButton);

        JButton viewButton = new JButton("Detalhes dos Funcionarios em Voo");
        viewButton.setBounds(150, 360, 300, 40);
        viewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        viewButton.setFocusPainted(false);
        viewButton.addActionListener(actionEvent -> {
            try {
                viewEmp();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        });
        flightFrame.add(viewButton);

        JButton backButton = new JButton("Voltar");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(150, 430, 300,40);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            flightFrame.dispose();
            if(flag==1){
                ManagerPortal.menuFrame.setVisible(true);
            }
            else if(flag==2) {
                AdminPortal.AdmenuFrame.setVisible(true);
            }
        });
        flightFrame.add(backButton);

        flightFrame.setSize(650, 750);
        flightFrame.getContentPane().setBackground(new Color(47, 236, 4));
        LineBorder blackBorder = new LineBorder(new Color(3, 86, 1), 7);
        flightFrame.getRootPane().setBorder(blackBorder);
        flightFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        flightFrame.setLayout(null);
        flightFrame.setVisible(true);
        flightFrame.setLocationRelativeTo(null);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void bookFlights() throws SQLException{
        flightFrame.setVisible(false);
        JFrame bframe = new JFrame();
        bframe.setVisible(true);
        bframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JTextField textField,textField_1,textField_2,textField_3,textField_4,textField_5,textField_6,textField_7;
        bframe.getContentPane().setForeground(Color.BLACK);
        bframe.getContentPane().setBackground(new Color(4, 155, 236));
        bframe.setTitle("ADICIONAR DETALHES DE FUNCIONÁRIO");

        bframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        bframe.setSize(500,520);
        bframe.getContentPane().setLayout(null);

        JLabel Passportno = new JLabel("DATA");
        Passportno.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Passportno.setBounds(60, 124, 150, 27);
        bframe.add(Passportno);

        textField = new JTextField();
        textField.setBounds(200, 124, 150, 27);
        bframe.add(textField);

        JButton Next = new JButton("SALVAR");
        Next.setBounds(200, 374, 150, 30);
        Next.setBackground(Color.BLACK);
        Next.setForeground(Color.WHITE);
        bframe.add(Next);

        JButton bButton = new JButton("VOLTAR");
        bButton.setBounds(200, 424, 150,30);
        bButton.setBackground(Color.BLACK);
        bButton.setForeground(Color.WHITE);
        bframe.add(bButton);
        bButton.addActionListener(actionListener -> {
            bframe.dispose();
            flightFrame.setVisible(true);
        });

        JLabel Empno = new JLabel("NÚMERO DO FUNCIONÁRIO");
        Empno.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Empno.setBounds(60, 174, 150, 27);
        bframe.add(Empno);

        textField_1 = new JTextField();
        textField_1.setBounds(200, 174, 150, 27);
        bframe.add(textField_1);

        JLabel From = new JLabel("DE");
        From.setFont(new Font("Tahoma", Font.PLAIN, 17));
        From.setBounds(60, 224, 150, 27);
        bframe.add(From);

        textField_2 = new JTextField();
        textField_2.setBounds(200, 224, 150, 27);
        bframe.add(textField_2);

        JLabel To = new JLabel("PARA");
        To.setFont(new Font("Tahoma", Font.PLAIN, 17));
        To.setBounds(60, 274, 150, 27);
        bframe.add(To);

        textField_3 = new JTextField();
        textField_3.setBounds(200, 274, 150, 27);
        bframe.add(textField_3);

        JLabel Price = new JLabel("PREÇO (em R$)");
        Price.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Price.setBounds(60, 324, 150, 27);
        bframe.add(Price);

        textField_7 = new JTextField();
        textField_7.setBounds(200, 324, 150, 27);
        bframe.add(textField_7);

        bframe.setVisible(true);

        JLabel AddPassengers = new JLabel("ADICIONAR DETALHES DA VIAGEM");
        AddPassengers.setForeground(Color.BLUE);
        AddPassengers.setFont(new Font("Tahoma", Font.PLAIN, 31));
        AddPassengers.setBounds(60, 24, 442, 35);
        bframe.add(AddPassengers);

        JLabel Flightcode = new JLabel("CÓDIGO DE VÔO");
        Flightcode.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Flightcode.setBounds(60, 74, 150, 27);
        bframe.add(Flightcode);

        textField_6 = new JTextField();
        textField_6.setBounds(200, 74, 150, 27);
        bframe.add(textField_6);

        Next.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                JdbcConnector jdbcConnector = new JdbcConnector();
                jdbcConnector.connect();
                Connection conn = jdbcConnector.getConnection();
                jdbcConnector.connect();
                PreparedStatement psts  = null;
                PreparedStatement psts2  = null;
                PreparedStatement p = null;
                try{
                    int emp_id = Integer.parseInt(textField_1.getText());
                    System.out.println(emp_id);
                    int fl_code = Integer.parseInt(textField_6.getText());
                    String fdate = textField.getText();
                    String source =  textField_2.getText();
                    String destination = textField_3.getText();
                    int price = Integer.parseInt(textField_7.getText());
                    String q = "select id from employee where id = ?";
                    ResultSet resultSet = null;
                    p = conn.prepareStatement(q);
                    p.setInt(1, emp_id);
                    resultSet = p.executeQuery();
                    if(resultSet.next()){
                        String query = "INSERT INTO empflightdetails(emp_id, fl_code, fdate, source, destination, price) " +
                                "VALUES (?, ?, ?, ?, ?, ?)";
                        psts = conn.prepareStatement(query);
                        psts.setInt(1, emp_id);
                        psts.setInt(2, fl_code );
                        psts.setString(3, fdate);
                        psts.setString(4, source);
                        psts.setString(5, destination);
                        psts.setInt(6, price);
                        String query2 = "INSERT INTO schedule(emp_id, mdate) " +
                                "VALUES (?, ?)";
                        psts2 = conn.prepareStatement(query2);
                        psts2.setInt(1, emp_id);
                        psts2.setString(2, fdate);

                        int rows = psts.executeUpdate();
                        int rows2 = psts2.executeUpdate();
                        if (rows > 0 && rows2>0) {
                            System.out.println("FUNCIONÁRIO DO VÔO ADICIONADO COM SUCESSO.");
                        } else {
                            System.out.println("NÃO FOI POSSÍVEL ADICIONAR O FUNCIONÁRIO, TENTE NOVAMENTE.");
                        }
                        flightFrame.setVisible(true);
                        bframe.dispose();
                }
                else {
                    bframe.setVisible(false);
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
                        bframe.setVisible(true);
                    });
                    popup.add(button);

                    popup.setLayout(null);
                    popup.setSize(350, 150);
                    popup.setVisible(true);
                }
                    resultSet.close();
                    p.close();
                    conn.close();
                    jdbcConnector.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        bframe.setLocationRelativeTo(null);
    }

    public void viewEmp() throws SQLException{
        flightFrame.setVisible(false);
        Statement st  = null;
        JFrame frame=new JFrame("Journey Details");
        JPanel panel=new JPanel();
        try{
            JdbcConnector jdbcConnector = new JdbcConnector();
            jdbcConnector.connect();
            Connection conn = jdbcConnector.getConnection();
            jdbcConnector.connect();
            st = conn.createStatement();
            String q = "SELECT A.id, A.name, A.gender, A.phonenum, A.email, B.fl_code, B.fdate, B.source, B.destination, B.price " +
                    "FROM employee A " +
                    "RIGHT JOIN empflightdetails B ON B.emp_id = A.id";
            ResultSet resultSet = st.executeQuery(q);

            while (resultSet.next()) {
            JPanel employeeCard = new JPanel();

            JLabel idLabel = new JLabel("ID");
            idLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel idVal = new JLabel(String.valueOf(resultSet.getInt(1)));
            idVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel nameLabel = new JLabel("Nome");
            nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel nameVal = new JLabel(resultSet.getString(2));
            nameVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel genderLabel = new JLabel("Genero");
            genderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel genderVal = new JLabel(resultSet.getString(3));
            genderVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel phoneLabel = new JLabel("Numero de telefone");
            phoneLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel phoneVal = new JLabel(String.valueOf(resultSet.getInt(4)));
            phoneVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel emailLabel = new JLabel("Email");
            emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel emailVal = new JLabel(resultSet.getString(5));
            emailVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel fcodeLabel = new JLabel("Codigo de Vôo");
            fcodeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel fcodeVal = new JLabel(resultSet.getString(6));
            fcodeVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel fdateLabel = new JLabel("Data do proximo vôo");
            fdateLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel fdateVal = new JLabel(resultSet.getString(7));
            fdateVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel sourceLabel = new JLabel("De");
            sourceLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel sourceVal = new JLabel(resultSet.getString(8));
            sourceVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel destinationLabel = new JLabel("Para");
            destinationLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel destinationVal = new JLabel(resultSet.getString(9));
            destinationVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                JLabel PriceLabel = new JLabel("Despesas");
                PriceLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                JLabel PriceVal = new JLabel(String.valueOf(resultSet.getInt(10)));
                PriceVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

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
            employeeCard.add(fcodeLabel);
            employeeCard.add(fcodeVal);
            employeeCard.add(fdateLabel);
            employeeCard.add(fdateVal);
            employeeCard.add(sourceLabel);
            employeeCard.add(sourceVal);
            employeeCard.add(destinationLabel);
            employeeCard.add(destinationVal);
                employeeCard.add(PriceLabel);
                employeeCard.add(PriceVal);

            employeeCard.setSize(1000, 400);
            employeeCard.setBackground(new Color(147, 151, 241));
            employeeCard.setBorder(new EmptyBorder(20, 50, 20, 50));
            GridLayout cardLayout = new GridLayout(0, 2);
            cardLayout.setHgap(5);
            cardLayout.setVgap(10);
            employeeCard.setLayout(cardLayout);
            panel.add(employeeCard);
        }

            resultSet.close();
            st.close();
            conn.close();
            jdbcConnector.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Voltar");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(450, 30, 200,50);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            frame.dispose();
            flightFrame.setVisible(true);
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
    }

    public void delEmp() throws SQLException{
        flightFrame.setVisible(false);
        JFrame frame = new JFrame("Deletar Registro");

        JLabel label = new JLabel("Entre ID do Funcionario");
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
            flightFrame.setVisible(true);
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
            int emp_id = id;
            String q2 = "select * from empflightdetails where emp_id = ?";
            PreparedStatement p2 = null;
            ResultSet r2 = null;
            try {
                p2 = conn.prepareStatement(q2);
                p2.setInt(1, emp_id);
                r2 = p2.executeQuery();
                if (r2.next()) {
                    System.out.println(r2.getString(2));
                    String deleteQuery1 = "delete from employee where id = ?";
                    String deleteQuery2 = "delete from empflightdetails where emp_id = ?";
                    p2 = conn.prepareStatement(deleteQuery2);
                    p2.setInt(1, emp_id);
                    p2.executeUpdate();
                    flightFrame.setVisible(true);
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