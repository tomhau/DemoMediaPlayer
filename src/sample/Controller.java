package sample;

import javafx.scene.media.*;
import javafx.scene.control.Button;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.*;
import java.net.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private MediaView mediaV;

    @FXML
    private Button play;

    private MediaPlayer mp;
    private Media me;


    /**
     * This method is invoked automatically in the beginning. Used for initializing, loading data etc.
     *
     * @param location
     * @param resources
     */
    public void initialize(URL location, ResourceBundle resources){
        // Build the path to the location of the media file
        String path = new File("src/sample/media/file_example_MP4_640_3MG.mp4").getAbsolutePath();
        // Create new Media object (the actual media content)
        me = new Media(new File(path).toURI().toString());
        // Create new MediaPlayer and attach the media to be played
        mp = new MediaPlayer(me);
        //
        mediaV.setMediaPlayer(mp);
        // mp.setAutoPlay(true);
        // If autoplay is turned of the method play(), stop(), pause() etc controls how/when medias are played
        mp.setAutoPlay(false);

    }

    @FXML
    /**
     * Handler for the play button
     */
    private void handlePlay()
    {
        // Play the mediaPlayer with the attached media
        mp.play();
    }

}
