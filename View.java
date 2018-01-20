package Steg;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;

public class View extends Pane {

    private Controller controller;

    public View(Controller controller){
        setPrefSize(640*2,510);
        this.controller = controller;

        Image image = new Image("Steg/a.jpg");
        ImageView original = new ImageView(image);

        ImageView modified = new ImageView();
        modified.setTranslateX(640);

        TextField text = new TextField();
        text.setPromptText("Enter text here");
        text.setTranslateY(482);
        text.setOnAction(event -> controller.onEncode());

        Button Decodebt = new Button("Decode");
        Decodebt.setTranslateX(640);
        Decodebt.setTranslateY(482);
        Decodebt.setOnAction(event -> controller.onDecode());

        controller.injectUI(original,modified,text);

        getChildren().addAll(original,modified,text,Decodebt);


    }



}
