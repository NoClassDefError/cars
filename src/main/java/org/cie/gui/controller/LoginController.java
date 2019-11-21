package org.cie.gui.controller;

import javafx.event.ActionEvent;
import org.apache.commons.io.IOUtils;
import org.cie.dao.BaseDAO;
import org.cie.dao.BaseDaoImpl;
import org.cie.entities.User;
import org.cie.gui.InfoUI;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Text text;

    public void loginAction(javafx.event.ActionEvent actionEvent) {

        try {
            BaseDAO dao = new BaseDaoImpl();
            User user = new User(usernameField.getText(), passwordField.getText());
            User result = dao.find(user.getUserName());
            if (result == null) {
                System.out.println("login failed");
                text.setText("No such user. \'" + user.getUserName() + "\'");
                //JOptionPane.showMessageDialog(null, "no such user", "login failed", JOptionPane.ERROR_MESSAGE);
            } else if (user.getPasswrd().equals(result.getPasswrd())) {
                System.out.println("login success");

                //javafx 中如何在控制器中实现界面跳转
                InfoUI infoUI = new InfoUI();
                infoUI.start(new Stage());
                Stage stage = (Stage) rootPane.getScene().getWindow();
                stage.close();
                //frame.dispose();//关闭窗口
                new InfoUI();//打开登录后的信息显示窗口
            } else {
                System.out.println("login failed");
                text.setText("Password incorrect. ");
                //JOptionPane.showMessageDialog(null, "password incorrect", "login failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            text.setText("Can not find the correct driver class for database connection, please check the properties settings.");
            //JOptionPane.showMessageDialog(null, "can not find the correct driver class for database connection, " +
            //      "go and check the properties settings", "ClassNotFouund", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, ex, "SQLException", JOptionPane.ERROR_MESSAGE);
            text.setText(ex.toString());
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setting(ActionEvent actionEvent) {
        try {
            Runtime run = Runtime.getRuntime();
            String path=this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
            IOUtils.copy(this.getClass().getResourceAsStream("/db.properties"),
                    new FileOutputStream(path+"\\db.properties"));
            System.out.println(path+"\\db.properties");
            run.exec(path+"\\db.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
