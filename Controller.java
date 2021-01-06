package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import java.io.File;
import javax.swing.*;
import java.util.ResourceBundle;

public class Controller {
    private String path;
    private MediaPlayer mediaPlayer;



    public void HandlePlay(ActionEvent event) {
        mediaPlayer.play();

    }

    public void HandleStop() {
        mediaPlayer.stop();
    }

    public void HandlePause(ActionEvent event) {
        mediaPlayer.pause();
    }
    public void HandleNext() {
        System.out.println("It works.... ");
    }
    public void HandlePrevious() {
        System.out.println("It works.... ");
    }
    public void HandleReplay() {
        System.out.println("It works.... ");
    }
    public void HandleSearch() {
        System.out.println("It works.... ");
    }
    public void HandleAddPlaylist() {
        System.out.println("It works.... ");
    }

    /**
     * Creating a fileChooser in order to select a desired file from your computer
     * We use path in order to get the path where the file is located
     *
     */

    public void HandleChooseFile(ActionEvent event){

    FileChooser fileChooser = new FileChooser();
    File fileToOpen = fileChooser.showOpenDialog(null);
    path = fileToOpen.toURI().toString();

    //Checking if path contains something and is not null
        if(path !=null){
            //The selected file(path) is used as an argument for media
            //The media is then used with Media Player
             Media media = new Media(path);
             mediaPlayer = new MediaPlayer(media);
             //mediaView.setMediaPlayer(mediaPlayer);
            // mediaPlayer.play();

        }



    }



    }



