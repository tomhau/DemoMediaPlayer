package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


//This item is for the ListView all the song


public class Controller {


    private String path;
    private MediaPlayer mediaPlayer;



    public void ListViewAllSongs(Stage stage) throws Exception {

        //Select Data from the Database
        DB.selectSQL("Select * from tbl_Songs");

        //Get the data

        do {
            String SongNameData = DB.getDisplayData();
            if (SongNameData.equals(DB.NOMOREDATA)) {
                break;
            } else {
                System.out.print(SongNameData);
            }
        } while (true);


        //Create the ListView Control:

        //1. Create the List for the Listview
        ObservableList<String> NamesList = FXCollections.observableArrayList("Test Test Test");

        //2. Create the ListView
        ListView TestList= new ListView();


        //3. Creating a scene


}

public void ListViewPlayList(Stage primaryStage) throws Exception {

}

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
     */

    public void HandleChooseFile(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        File fileToOpen = fileChooser.showOpenDialog(null);
        path = fileToOpen.toURI().toString();

        //Checking if path contains something and is not null
        if (path != null) {
            //The selected file(path) is used as an argument for media
            //The media is then used with Media Player
            Media media = new Media(path);
            mediaPlayer = new MediaPlayer(media);


        }


    }

}




