package src;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private Pane root = new Pane();
    private TranslateTransition tt;


    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createContent());

        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            System.out.println("mouse click detected! " + mouseEvent.getSource());
            addText("*", mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getX(), mouseEvent.getY(), 0, 100);
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Parent createContent() {
        root.setPrefSize(1600, 900);
        return root;
    }

    private void addText(String message, double mX, double mY, double x, double y, double angle, double fontSize) {
        if (fontSize < 5)
            return;

        Text text = new Text(message);
        text.setFont(Font.font(fontSize));

        root.getChildren().add(text);

        tt = new TranslateTransition(Duration.seconds(0.4), text);
        tt.setFromX(mX);
        tt.setFromY(mY);
        tt.setToX(x);
        tt.setToY(y);

        double delay = 1 - fontSize / 150;

        tt.setDelay(Duration.seconds(delay * 2.5));
        tt.play();

        Point2D vector = new Point2D(Math.cos(angle), Math.sin(angle)).multiply(fontSize * 3.5);

        char c = message.charAt(0);
        //c++;

        addText(String.valueOf(c), mX, mY, x + vector.getX(), y + vector.getY(), angle + 90, fontSize - 1);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
