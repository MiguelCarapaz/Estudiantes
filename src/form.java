import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class form {
    private JPanel panel1;
    private JTextField txtID;
    private JTextField txtNombre;
    private JTextField txtCelular;
    private JTextField txtCorreo;
    private JButton insertarDatosButton;
    private JButton mostrarDatosButton;
    private JButton buscarButton;
    private JButton actualizarButton;
    private JTextField textGenero;
    private JButton eliminarButton;
    private JComboBox txtGenero;
    private JTextField textCarrera;
    private PreparedStatement ps;

    public form(){



        mostrarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDatos();
            }
        });


        insertarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn;
                try{
                    conn = getConnection();
                    ps = conn.prepareStatement("INSERT INTO estudiante(id, nombre, celular, correo, carrera, genero) VALUES(?,?,?,?,?,?)" );
                    try{

                        if( !txtID.getText().matches("[0-9]*") ){
                            throw new SQLException("Ingresa bien los datos");
                        }

                        ps.setString(1, txtID.getText());
                        ps.setString(2, txtNombre.getText());
                        ps.setString(3, txtCelular.getText());
                        ps.setString(4, txtCorreo.getText());
                        ps.setString(5, textCarrera.getText());
                        ps.setString(6, textGenero.getText());

                    }catch (SQLException es){
                        System.out.println("Error: " + es + "||||");
                        JOptionPane.showMessageDialog(null,"Ingrese bien los datos");
                    }


                    System.out.println(ps);
                    int res = ps.executeUpdate();

                    if(res > 0){
                        JOptionPane.showMessageDialog(null, "Persona Ingresada en el sistema");
                    }else{
                        JOptionPane.showMessageDialog(null, "Persona No Ingresada en el sistema");
                    }
                    conn.close();
                }catch (HeadlessException | SQLException f){
                    System.out.println(f);
                }

            }
        });



        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn;

                try{
                    conn = getConnection();
                    ps = conn.prepareStatement("UPDATE estudiante SET id = ?, nombre = ?, celular = ?, correo = ?, carrera = ?, genero=? WHERE id ="+txtID.getText() );
                    try{

                        if( !txtID.getText().matches("[0-9]*") ){
                            throw new SQLException("Ingresa bien los datos");
                        }else{
                            ps.setString(1, txtID.getText());
                            ps.setString(2, txtNombre.getText());
                            ps.setString(3, txtCelular.getText());
                            ps.setString(4, txtCorreo.getText());
                            ps.setString(5, textCarrera.getText());
                            ps.setString(6, textGenero.getText());
                        }



                    }catch (SQLException es){
                        System.out.println("Error: " + es + "||||");
                        JOptionPane.showMessageDialog(null,"Ingrese bien los datos");
                    }


                    System.out.println(ps);
                    int res = ps.executeUpdate();

                    if(res > 0){
                        JOptionPane.showMessageDialog(null, "Persona modificada correctamente");
                    }else{
                        JOptionPane.showMessageDialog(null, "Persona no modificada");
                    }
                    conn.close();
                }catch (HeadlessException | SQLException f){
                    System.out.println(f);
                }
            }
        });
        //ELIMINAR
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn;
                ResultSet rs;
                try{
                    conn = getConnection();


                    ps = conn.prepareStatement("DELETE FROM estudiante WHERE id= ?" );
                    ps.setString(1, txtID.getText());

                    rs = ps.executeQuery();

                    try{

                        if( !txtID.getText().matches("[0-9]*") ){
                            throw new SQLException("Ingresa bien los datos");
                        }else{
                            if(rs.next()){
                                JOptionPane.showMessageDialog(null,"Estudiante eliminado correctamente");
                            }else{
                                System.out.println("Error no funicona");
                            }

                        }


                    }catch (SQLException es){
                        System.out.println("Error: " + es + "||||");
                        JOptionPane.showMessageDialog(null,"Ingrese bien los datos");
                    }



                    conn.close();
                }catch (HeadlessException | SQLException f){
                    System.out.println(f);
                }
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn;
                ResultSet rs;
                try{
                    conn = getConnection();


                    ps = conn.prepareStatement("SELECT * FROM estudiante WHERE id= ?" );
                    ps.setString(1, txtID.getText());

                    rs = ps.executeQuery();

                    try{

                        if( !txtID.getText().matches("[0-9]*") ){
                            throw new SQLException("Ingresa bien los datos");
                        }else{
                            if(rs.next()){
                                txtID.setText( Integer.toString(rs.getInt("id")) );
                                txtNombre.setText(rs.getString("nombre"));
                                txtCelular.setText(rs.getString("celular"));
                                txtCorreo.setText(rs.getString("correo"));
                               // txtCarrera.setText(rs.getString("carrera"));
                                textGenero.setText(rs.getString("genero"));
                            }else{
                                System.out.println("Error no funicona");
                            }

                        }


                    }catch (SQLException es){
                        System.out.println("Error: " + es + "||||");
                        JOptionPane.showMessageDialog(null,"Ingrese bien los datos");
                    }



                    conn.close();
                }catch (HeadlessException | SQLException f){
                    System.out.println(f);
                }
            }
        });
    }//FIN DEL CONSTRUCTOR

    public void mostrarDatos(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/alumnos","root","Migueldark12345");

            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM estudiante");

            while(rs.next()){

                JOptionPane.showMessageDialog(null, rs.getInt("id") + "\n" + rs.getString(2)
                        +"\n"+ rs.getString("celular") +"\n" +
                        rs.getString("correo") + "\n"+
                        rs.getString(5)+ "\n"+ rs.getString(6));

            }

            conexion.close();
        }catch (Exception ex){
            System.out.println(ex);
            ex.printStackTrace();
        }

    }

    public static Connection getConnection(){
        Connection conn = null;
        String base = "ALUMNOS";
        String url = "jdbc:mysql://localhost:3306/" + base;
        String user = "root";
        String password = "Migueldark12345";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
        }catch (ClassNotFoundException | SQLException ex){
            System.out.printf("Error: " + ex);
        }
        return conn;
    }




    public static void main(String[] args) {
        JFrame frame = new JFrame("ALUMNOS");
        frame.setContentPane(new form().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
