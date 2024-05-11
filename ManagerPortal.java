import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ManagerPortal extends FlightManagementSystem{
    static JFrame menuFrame;
    ManagerPortal() throws SQLException {
        menuFrame = new JFrame("Portal Gerente");

        JLabel welcomeLabel = new JLabel("Bem vindo(a) ao portal de Gerente");
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
        welcomeLabel.setBounds(180, 90, 800, 50);
        menuFrame.add(welcomeLabel);


        JButton flightButton = new JButton("Detalhes do Voo");
        flightButton.setBounds(200, 170, 300, 40);
        flightButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        flightButton.setFocusPainted(false);

        flightButton.addActionListener(actionEvent -> {
            try {
                FlightManagementPanel.SetTrue(1);
                new FlightManagementPanel();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            menuFrame.setVisible(false);
        });
        menuFrame.add(flightButton);

        JButton empButton = new JButton("Detalhes do Funcionario");
        empButton.setBounds(200, 240, 300, 40);
        empButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        empButton.setFocusPainted(false);

        empButton.addActionListener(actionEvent -> {
            try {
                EmployeeManagementPanel.SetTrue(1);
                new EmployeeManagementPanel();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            menuFrame.setVisible(false);
        });
        menuFrame.add(empButton);

        JButton exitButton = new JButton("Sair");
        exitButton.setBounds(200, 310, 300, 40);
        exitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        exitButton.setFocusPainted(false);

        exitButton.addActionListener(actionEvent -> {
            menuFrame.dispose();
        });
        menuFrame.add(exitButton);

        menuFrame.setSize(750, 500);
        menuFrame.getContentPane().setBackground(new Color(250, 143, 5));
        LineBorder blackBorder = new LineBorder(new Color(246, 2, 22), 7);
        menuFrame.getRootPane().setBorder(blackBorder);
        menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menuFrame.setLayout(null);
        menuFrame.setVisible(true);
        menuFrame.setLocationRelativeTo(null);

    }
}