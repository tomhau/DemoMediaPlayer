package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class Controller implements Initializable
{

    @FXML
    private ListView<String> ListViewAllSongs;

    @FXML
    private  ListView<String> ListViewPlayListNames;

    @FXML
    private ListView<String> ListViewPlayList;

    @FXML
    private Slider ProgressBar;
    @FXML
    private Slider VolumeSlider;



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
        HandleProgressBar();
        HandleVolumeSlider();

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

    /**
     * Makes the progress Bar functional and scalable with interface
     * If the user presses somewhere on the progress bar, the song will jump to that time
     * As well as if the user dragged the progress bar, the song will move in accordance
     */

    public void HandleProgressBar(){

        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                ProgressBar.setValue(newValue.toSeconds());
                //Making the progress bar scalable with interface
                Duration total = mediaPlayer.getTotalDuration();
                ProgressBar.setMax(total.toSeconds());

            }
        });

        //If the user clicks somewhere on the progress Bar the song should jump to that time
        ProgressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Gets the time where the user clicks and sets it
                mediaPlayer.seek(Duration.seconds(ProgressBar.getValue()));
            }
        });

        //Used if the user dragged the progress bar in order to set the time
        ProgressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Gets the time where the user clicks and sets it
                mediaPlayer.seek(Duration.seconds(ProgressBar.getValue()));
            }
        });
    }

    /**
    * Makes the volume slider functional
    * if the volume slider is dragged or clicked the volume will change accordingly
    */

    public void HandleVolumeSlider(){
        VolumeSlider.setValue(mediaPlayer.getVolume() * 100);
        VolumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(VolumeSlider.getValue()/100);
            }
        });
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




