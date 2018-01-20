package Steg;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Controller {
    private Model model;
    private ImageView originalView,modifiedView;
    private TextField text;

    public Controller(Model model){
        this.model = model;
    }

    public void injectUI(ImageView original, ImageView modified,TextField text){
        this.originalView = original;
        this.modifiedView = modified;
        this.text = text;
    }

    public void onEncode(){
        Image modified = model.encode(originalView.getImage(),text.getText());
        modifiedView.setImage(modified);
    }
    public void onDecode(){
        String message = model.decode(modifiedView.getImage());
        System.out.println(message);
    }


}
