package use_case.play;

import entity.Game;
import entity.Track;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.JavaSoundAudioDevice;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import javax.print.attribute.standard.Media;
import java.io.*;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;



public class PlayInteractor implements PlayInputBoundary{
    final Track track = null;
    private final PlayOutputBoundary playPresenter;
    private Game game = new Game();

    private AudioDevice device;
    private FloatControl volControl;


    public PlayInteractor(PlayOutputBoundary playPresenter){
        this.playPresenter = playPresenter;
    }
    @Override
    public void execute(PlayInputData playInputData) {

        /*if (!dataAccess.play(playInputData.getTrack(), playInputData.getTryNumber() * 2000, 0)){
            playPresenter.prepareFailView("Track failed to play");
        }
        else{
            playPresenter.prepareSuccessView();
        }
        */

        try{
            String audioFilePath = playInputData.getTrack().getAudioFile();

            FileInputStream fis = new FileInputStream(audioFilePath);

            this.device = FactoryRegistry.systemRegistry().createAudioDevice();

            AdvancedPlayer player = new AdvancedPlayer(fis, device);
            player.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent evt) {
                }
            });

            playPresenter.prepareSuccessView(new PlayOutputData(false, playInputData.getTryNumber()));
            new PlayerThread(player).start();
            int millis = playInputData.getTryNumber() * 3000;
            Thread.sleep(millis);
            player.stop();





           /* InputStream inputStream = getClass().getClassLoader().getResourceAsStream("./src/data_access/While I Can.mp3");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);

            AudioFormat audioFormat = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.addLineListener(this);
            audioClip.open(audioStream);
            audioClip.start();

            audioClip.close();
            audioStream.close();
            */


        } catch (IOException e){
            System.out.println("IOException");
            playPresenter.prepareFailView();
        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
            playPresenter.prepareFailView();
        }

    }
    public void setVolume(float gain){
        if(this.volControl == null) {
            Class<JavaSoundAudioDevice> clazz = JavaSoundAudioDevice.class;
            Field[] fields = clazz.getDeclaredFields();
            try{
                SourceDataLine source = null;
                for(Field field : fields) {
                    if("source".equals(field.getName())) {
                        field.setAccessible(true);
                        source = (SourceDataLine) field.get(this.device);
                        field.setAccessible(false);
                        this.volControl = (FloatControl) source.getControl(FloatControl.Type.MASTER_GAIN);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.volControl != null) {
            float newGain = Math.min(Math.max(gain, volControl.getMinimum()), volControl.getMaximum());
            System.out.println("Was: " + volControl.getValue() + " Will be: " + newGain);

            volControl.setValue(newGain);
        }
    }


}
class PlayerThread extends Thread{
    private final AdvancedPlayer player;
    public PlayerThread(AdvancedPlayer player){
        this.player = player;
    }
    public void run(){
        try{
            Thread.sleep(100);
            player.play(700, Integer.MAX_VALUE);
        } catch (JavaLayerException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
