package Steg;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String args[]){
        launch(args);
    }

    public Model makeModel(){
        return new Model(new Basic_encoder(),new Basic_decoder());
    }

    public void start(Stage window) throws Exception{
        window.setTitle("Image Stegnography");
        Scene scene = new Scene(new View(new Controller(makeModel())));
        window.setScene(scene);
        window.show();

    }

}
