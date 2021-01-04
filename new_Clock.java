package sample;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.util.Duration;

public class Clock extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        EventHandler<ActionEvent> eventHandler = e -> {
            primaryStage.setTitle("Analog Clock");
            Pane layout = new Pane();
            Scene scene = new Scene(layout, 600, 600);
            primaryStage.setScene(scene);

            Circle circle = new Circle(); // dış daire
            circle.setCenterX(300);
            circle.setCenterY(300);
            circle.setRadius(200);
            circle.setStroke(Color.web("#f1ff2f"));
            layout.getChildren().add(circle);

            Circle circle1 = new Circle(0, Color.web("#f1ff2f")); //içerideki sarı daire
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
            int second = calendar.get(Calendar.SECOND);

            float hourAngle = (((float) hour * 30) + ((float) minute / 2)) % 360; // açıyı bulmak için hesaplamalar
            float minuteAngle = ((float) minute * 6) % 360;

            line.getTransforms().add(new Rotate(hourAngle, 300, 300)); // açı kadar döndürme
            line1.getTransforms().add(new Rotate(minuteAngle, 300, 300));
            double secondX = 250 + (100 * 0.8) * Math.sin(second * (2 * Math.PI / 60));
            double secondY = 250 - (100 * 0.8) * Math.cos(second * (2 * Math.PI / 60));

            Line line3 = new Line(300, 300, secondX, secondY);

            Text text = new Text(200, 430, String.valueOf(date)); // makine zamanını ekrana bastım
            layout.getChildren().add(text);

            primaryStage.show();
            layout.getChildren().clear();
            layout.getChildren().addAll(circle, circle1, line1, line, text, line3);

        };
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
