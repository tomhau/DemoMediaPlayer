package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class Controller implements Initializable {

    @FXML
    private ListView<String> ListViewAllSongs;

    @FXML
    private  ListView<String> ListViewPlayListNames;

    @FXML
    private ListView<String> ListViewPlayList;



    private String path;
    private MediaPlayer mediaPlayer;


    public void HandleDeletePlaylist(ActionEvent actionEvent) {
    }

    public void HandleNewPlaylist(ActionEvent actionEvent) {
    }


    public void HandleRemoveSongFromPlaylist(ActionEvent actionEvent) {
    }


    public void HandleAddSongToPlaylist(ActionEvent actionEvent) {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String SongNameData;
        String PlayListsData;
        String PlaylistContents;

        //Select Tbl_PlaylistContents from the Database
        DB.selectSQL("Select fld_SongName from tbl_PlaylistContents");

        //Get the data

        do {
            PlaylistContents = DB.getDisplayData();
            if (PlaylistContents.equals(DB.NOMOREDATA)) {
                break;
            } else {
                //Created the List for the ListView
                ObservableList<String> list = FXCollections.<String>observableArrayList(PlaylistContents);

                //Created the ListView
                ListView<String> data3 = new ListView<>(list);


                //Display the ListView
                ListViewPlayList.setItems(list);

                System.out.print(PlaylistContents);
            }
        } while (true);





        //Select Tbl_Songs from the Database
        DB.selectSQL("Select fld_SongName, fld_Album, fld_Artist, fld_Year, fld_Length from tbl_Songs");

        //Get the data

        do {
            SongNameData = DB.getDisplayData();
            if (SongNameData.equals(DB.NOMOREDATA)) {
                break;
            } else {
                //Created the List for the ListView
                ObservableList<String> list = FXCollections.<String>observableArrayList(SongNameData);

                //Created the ListView
                ListView<String> data = new ListView<>(list);


                //Display the ListView
                ListViewAllSongs.setItems(list);


                System.out.print(SongNameData);
            }
        } while (true);




        //Select Tbl_Playlists from the Database
        DB.selectSQL("Select fld_Name from tbl_Playlists");

        //Get the data

        do {
            PlayListsData = DB.getDisplayData();
            if (PlayListsData.equals(DB.NOMOREDATA)) {
                break;
            } else {

                //Created the List for the ListView
                ObservableList<String> list = FXCollections.<String>observableArrayList(PlayListsData);

                //Created the ListView
                ListView<String> data3 = new ListView<>(list);


                //Display the ListView
                ListViewPlayListNames.setItems(list);


                System.out.print(PlayListsData);
            }
        } while (true);







    }


}




