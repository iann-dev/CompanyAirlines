import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.lang.*;
import java.util.List;

public class flight_info extends FlightManagementSystem{
    JFrame flightinfo = new JFrame();
    private JTable table;
    private JTextField textField;
    public flight_info(){

        flightinfo.getContentPane().setBackground(new Color(59, 66, 208));
        flightinfo.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));

        flightinfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        flightinfo.setSize(960, 523);
        flightinfo.setLayout(null);
        flightinfo.setVisible(true);

        JLabel FlightDetails = new JLabel("FLIGHT INFORMATION");
        FlightDetails.setFont(new Font("Tahoma", Font.PLAIN, 31));
        FlightDetails.setForeground(new Color(255, 255, 255));
        FlightDetails.setBounds(115, 30, 570, 35);
        flightinfo.add(FlightDetails);

        table = new JTable();
        table.setBackground(new Color(9, 245, 215));
        table.setBounds(23, 150, 520, 300);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(23, 150, 520, 300);
        pane.setBackground(Color.WHITE);
        flightinfo.add(pane);
        int id = FlightManagementSystem.Employee.getId();
        try {
            JdbcConnector jdbcConnector = new JdbcConnector();
            jdbcConnector.connect();
            Connection conn = jdbcConnector.getConnection();
            jdbcConnector.connect();
            String query = "SELECT * FROM empflightdetails WHERE emp_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            int columnCount = resultSet.getMetaData().getColumnCount();
            List<Vector<Object>> flightDataList = new ArrayList<>(1000);
            DefaultTableModel model = new DefaultTableModel();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(i);
            }
            while (resultSet.next()) {
                Vector<Object> rowData = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.add(resultSet.getObject(i));
                }
                model.addRow(rowData);
                flightDataList.add(rowData);
            }

            table.setModel(model);

            resultSet.close();
            statement.close();
            conn.close();
            jdbcConnector.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JLabel Empid = new JLabel("EMP_ID");
        Empid.setFont(new Font("Tahoma", Font.PLAIN, 13));
        Empid.setForeground(Color.WHITE);
        Empid.setBounds(35, 120, 126, 14);
        flightinfo.add(Empid);

        JLabel FlightCode = new JLabel("FL_CODE");
        FlightCode.setFont(new Font("Tahoma", Font.PLAIN, 13));
        FlightCode.setForeground(Color.WHITE);
        FlightCode.setBounds(115, 120, 126, 14);
        flightinfo.add(FlightCode);

        JLabel FlightName = new JLabel("DATE");
        FlightName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        FlightName.setForeground(Color.WHITE);
        FlightName.setBounds(210, 120, 120, 14);
        flightinfo.add(FlightName);

        JLabel Source = new JLabel("SOURCE");
        Source.setFont(new Font("Tahoma", Font.PLAIN, 13));
        Source.setForeground(Color.WHITE);
        Source.setBounds(290, 120, 104, 14);
        flightinfo.add(Source);

        JLabel Destination = new JLabel("DESTINATION");
        Destination.setFont(new Font("Tahoma", Font.PLAIN, 13));
        Destination.setForeground(Color.WHITE);
        Destination.setBounds(370, 120, 120, 14);
        flightinfo.add(Destination);

        JLabel Price = new JLabel("PRICE(in Rs.)");
        Price.setFont(new Font("Tahoma", Font.PLAIN, 13));
        Price.setForeground(Color.WHITE);
        Price.setBounds(480, 120, 80, 14);
        flightinfo.add(Price);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(200, 470, 200,30);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            flightinfo.dispose();
            EmployeePortal.menuFrame.setVisible(true);
        });
        flightinfo.add(backButton);

        flightinfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        flightinfo.setSize(590,650);
        flightinfo.setVisible(true);
        flightinfo.setLocationRelativeTo(null);
    }
}