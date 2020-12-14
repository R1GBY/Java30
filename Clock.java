package sample;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.Calendar;
import java.util.Date;
import javafx.scene.paint.Color;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Analog Clock");
        Pane layout = new Pane();
        Scene scene = new Scene(layout, 600, 600);
        primaryStage.setScene(scene);

        Circle circle = new Circle(); // dışarıdaki siyah daire
        circle.setCenterX(300);
        circle.setCenterY(300);
        circle.setRadius(200);
        layout.getChildren().add(circle);

        Circle circle1 = new Circle( 0, Color.web("#f1ff2f") ); //içerideki sarı daire
        circle1.setCenterX(300);
        circle1.setCenterY(300);
        circle1.setRadius(180);

        layout.getChildren().add(circle1);

        Line line = new Line(); //akrep
        line.setStartX(300);
        line.setStartY(220);
        line.setEndX(300);
        line.setEndY(300);

        layout.getChildren().add(line);

        Line line1 = new Line(); //yelkovan
        line1.setStartX(300);
        line1.setStartY(200);
        line1.setEndX(300);
        line1.setEndY(300);

        layout.getChildren().add(line1);

        Date date = new Date();   // cihazdaki zaman
        Calendar calendar = Calendar.getInstance(); // yeni takvim oluşturuyorum
        calendar.setTime(date);   // takvime zamanı atıyorum

        int hour = calendar.get(Calendar.HOUR); // 12lik formatta saati alıyor
        int minute = calendar.get(Calendar.MINUTE);

        float hourAngle = (((float)hour * 30) + ((float)minute / 2) ) % 360; // açıyı bulmak için hesaplamalar
        float minuteAngle = ((float)minute * 6)  % 360;

        line.getTransforms().add(new Rotate(hourAngle,300,300)); // açı kadar döndürme
        line1.getTransforms().add(new Rotate(minuteAngle,300,300));

        Text text = new Text( 200, 430 ,String.valueOf(date)); // makine zamanını ekrana bastım
        layout.getChildren().add(text);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
