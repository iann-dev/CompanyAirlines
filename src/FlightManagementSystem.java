import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class FlightManagementSystem extends JFrame implements ActionListener {
    private final JButton employeeButton;
    private final JButton managerButton;
    private final JButton administratorButton;

    public FlightManagementSystem() {
        super("Sistema de Gerenciamento de Voo");
        setLayout(new GridLayout(4, 1));

        JLabel welcomeLabel = new JLabel("Sistema de Gerenciamento de Voo");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        LineBorder blackBorder = new LineBorder(new Color(9, 51, 218),7);
        getRootPane().setBorder(blackBorder);
        welcomeLabel.setForeground(Color.BLUE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeLabel);

        employeeButton = new JButton("Funcionario Login");
        employeeButton.addActionListener(this);
        add(employeeButton);

        managerButton = new JButton("Gerente Login");
        managerButton.addActionListener(this);
        add(managerButton);

        administratorButton = new JButton("Administrador Login");
        administratorButton.addActionListener(this);
        add(administratorButton);

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().setBackground(Color.white);
        setLocationRelativeTo(null);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static class Login extends JFrame{

        static JLabel l1,l2,l3;
        static JTextField t1;
        static JPasswordField t2;
        static JButton b1,b2;
        Login(){
            Font f= new Font("Arial",Font.BOLD,25);
            f.isItalic();
            l1=new JLabel("Login");
            l1.setFont(f);
            l2=new JLabel("Usuario");
            t1=new JTextField();
            l3=new JLabel("Senha");
            t2=new JPasswordField();
            b1=new JButton("Enviar");
            b2=new JButton("Voltar");
            l1.setBounds(60,40,200,40);
            l2.setBounds(60,100,100,20);
            t1.setBounds(60,120,200,30);
            l3.setBounds(60,170,100,20);
            t2.setBounds(60,190,200,30);
            b1.setBounds(60,250,75,30);
            b2.setBounds(60,300,75,30);
            b1.setHorizontalAlignment(SwingConstants.CENTER);
            b2.setHorizontalAlignment(SwingConstants.CENTER);
            add(l1);
            add(t1);
            add(l2);
            add(t2);
            add(l3);
            add(b1);
            add(b2);
            setLayout(null);
            setVisible(true);
            setSize(350,400);
            getContentPane().setBackground(Color.WHITE);
            LineBorder blackBorder = new LineBorder(new Color(9, 51, 218),7);
            getRootPane().setBorder(blackBorder);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == employeeButton) {
            Login Loginframe = new Login();
            setVisible(false);
            Login.l1.setText("Login Funcionario");
            Login.l1.setForeground(Color.BLUE);
            Login.b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                        String username = Login.t1.getText();
                        String password = Login.t2.getText();
                        Employee employee = getEmployee(username, password);
                        if (employee != null) {
                            Loginframe.setVisible(false);
                            Login.t1.setText("");
                            Login.t2.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "Usuario ou Senha está incorreto", "Erro", JOptionPane.ERROR_MESSAGE);
                            Login.t1.setText("");
                            Login.t2.setText("");
                            Loginframe.setVisible(true);
                        }
                    }
            });
            Login.b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Loginframe.setVisible(false);
                    setVisible(true);
                }
            });
        } else if (e.getSource() == managerButton) {
            setVisible(false);
            Login Loginframe = new Login();
            setVisible(false);
            Login.l1.setText("Login Gerente");
            Login.l1.setForeground(Color.BLUE);
            Login.b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    String username = Login.t1.getText();
                    String password = Login.t2.getText();
                    Manager manager = getManager(username, password);
                    if (manager != null) {
                        Loginframe.setVisible(false);
                        Login.t1.setText("");
                        Login.t2.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario ou Senha está incorreto", "Erro", JOptionPane.ERROR_MESSAGE);
                        Login.t1.setText("");
                        Login.t2.setText("");
                        Loginframe.setVisible(true);
                    }
                }
            });
            Login.b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Loginframe.setVisible(false);
                    setVisible(true);
                }
            });
        } else if (e.getSource() == administratorButton) {
            Login Loginframe = new Login();
            setVisible(false);
            Login.l1.setText("Login de Administrador");
            Login.l1.setForeground(Color.BLUE);
            Login.b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    String username = Login.t1.getText();
                    String password = Login.t2.getText();
                    Admin admin = getAdmin(username, password);
                    if (admin != null) {
                        Loginframe.setVisible(false);
                        Login.t1.setText("");
                        Login.t2.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario ou Senha está incorreto", "Erro", JOptionPane.ERROR_MESSAGE);
                        Login.t1.setText("");
                        Login.t2.setText("");
                        Loginframe.setVisible(true);
                    }
                }
            });
            Login.b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Loginframe.setVisible(false);
                    setVisible(true);
                }
            });
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////
    private Employee getEmployee(String username, String password) {
//        Login login = new Login();
        Employee employee = null;
        try {
            JdbcConnector jdbcConnector = new JdbcConnector();
            jdbcConnector.connect();
            Connection conn = jdbcConnector.getConnection();
            jdbcConnector.connect();
            String sql = "SELECT * FROM employee WHERE username = '"+username+"' AND password = '"+password+"'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                setVisible(false);
                String name = rs.getString("nome");
                int empId = rs.getInt("id");
                employee = new Employee(name, empId, username, password);
                new EmployeePortal();
            }
            rs.close();
            pstmt.close();
            conn.close();
            jdbcConnector.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return employee;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public class Employee {
        private String name;
        private static int empId;
        private String usname;
        private String pass;
        public Employee(String name,int empId,String usname, String pass) {
            this.name = name;
            this.empId = empId;
            this.usname = usname;
            this.pass = pass;
        }
        public static int getId(){
            return empId;
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////
    private Manager getManager(String username, String password) {
//        Login login = new Login();
        Manager manager = null;
        try {
            JdbcConnector jdbcConnector = new JdbcConnector();
            jdbcConnector.connect();
            Connection conn = jdbcConnector.getConnection();
            jdbcConnector.connect();
            String sql = "SELECT * FROM manager WHERE username = '"+username+"' AND password = '"+password+"'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                setVisible(false);
                new ManagerPortal();
                String name = rs.getString("nome");
                manager = new Manager(name, username, password);
            }
            rs.close();
            pstmt.close();
            conn.close();
            jdbcConnector.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return manager;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public class Manager {
        private String name;
        private String usname;
        private String pass;
        public Manager(String name,String usname, String pass) {
            this.name = name;
            this.usname = usname;
            this.pass = pass;
        }
        public String getName() {
            return name;
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////
    private Admin getAdmin(String username, String password) {
        Admin admin = null;
        try {
            JdbcConnector jdbcConnector = new JdbcConnector();
            jdbcConnector.connect();
            Connection conn = jdbcConnector.getConnection();
            jdbcConnector.connect();
            String sql = "SELECT * FROM admins WHERE username = '"+username+"' AND password = '"+password+"'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                setVisible(false);
                new AdminPortal();
                String name = rs.getString("nome");
                admin = new Admin(name, username, password);
            }
            rs.close();
            pstmt.close();
            conn.close();
            jdbcConnector.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return admin;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public class Admin {
        private String name;
        private String usname;
        private String pass;
        public Admin(String name,String usname, String pass) {
            this.name = name;
            this.usname = usname;
            this.pass = pass;
        }
        public String getName() {
            return name;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        new FlightManagementSystem();
    }
}