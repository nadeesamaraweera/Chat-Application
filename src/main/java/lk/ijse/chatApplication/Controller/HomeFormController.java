package lk.ijse.chatApplication.Controller;

import javafx.animation.ScaleTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class HomeFormController {
    public TextField txtjon;
    public ImageView btnjon;
    static String name="";

    @FXML
    void initialize(){
        try {
            setServerUI();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//       new Thread(ChatServer::start).start();
    }

    private void setServerUI() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/client_form.fxml"));
        stage.getIcons().add(new Image("assets/icons8-send-30.png"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setResizable(false);
        stage.setTitle("ChatServer");
        stage.setOnCloseRequest(Event::consume);
        stage.show();
    }

    public void txtjonOnActon() {
        btnjonClick();
    }

    public void btnjonClick() {
        String name = txtjon.getText();

        String[] words = name.split(" ");

        HomeFormController.name =words[0];

        if (txtjon.getText().equals("stop")){
            System.exit(0);
            return;
        }

        if (txtjon.getText().equals("") || txtjon.getText().equals("please enter your name !")){
            txtjon.setStyle("-fx-border-color: red");
            txtjon.setText("please enter your name !");
            txtjon.selectAll();
            return;
        }
//        Detail.name=txtjon.getText();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/client_form.fxml"));
        stage.getIcons().add(new Image("assets/icons8-send-30.png"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setResizable(false);
        stage.setTitle(HomeFormController.name +" in your chat");
        stage.show();
        stage.centerOnScreen();
        stage.setOnCloseRequest(Event::consume);
        txtjon.clear();
    }

    public void mouseEnterAnim(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
//            glow.setColor(Color.valueOf("#EF233C"));
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(15);
            glow.setHeight(15);
            glow.setRadius(15);
            icon.setEffect(glow);
        }
    }

    public void mouseExitAnim(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();
            icon.setEffect(null);
        }
    }
}
