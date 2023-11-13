package use_case.play;

import entity.Track;

import javax.print.attribute.standard.Media;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;


public class PlayInteractor implements PlayInputBoundary{
    final Track track = null;
    final PlayOutputBoundary playPresenter;
    final PlayDataAccessInterface dataAccess;

    public PlayInteractor(PlayOutputBoundary playPresenter, PlayDataAccessInterface dataAccess){
        this.playPresenter = playPresenter;
        this.dataAccess = dataAccess;
    }
    @Override
    public void execute(PlayInputData playInputData) {

        if (!dataAccess.play(playInputData.getTrack(), playInputData.getTryNumber() * 2000, 0)){
            playPresenter.prepareFailView("Track failed to play");
        }
        else{
            playPresenter.prepareSuccessView();
        }

        /*try{

            URL url = new URL(playInputData.getTrack().getAudioLink());
            File f = new File("./src/interface_adapter/play/sound.wav");

            urlToFile(url, f);
            mp3ToWav(f);

            AudioInputStream audioInput = AudioSystem.getAudioInputStream(f);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            int length = clip.getFrameLength();
            int startPos = (length / 30) * playInputData.getTryNumber();
            clip.setFramePosition(startPos);
            clip.start();

        } catch(MalformedURLException e){
            System.out.println("MalformedURLException");;
        } catch (UnsupportedAudioFileException e) {
            System.out.println("UnsupportedAudioFileException");
        } catch (IOException e){
            System.out.println("IOException");
        } catch (LineUnavailableException e) {
            System.out.println("LineUnavailableException");
        }
*/
    }
    public static void urlToFile (URL url, File file){
        try (BufferedInputStream in = new BufferedInputStream(url.openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void mp3ToWav(File mp3Data) throws UnsupportedAudioFileException, IOException {
        // open stream
        AudioInputStream mp3Stream = AudioSystem.getAudioInputStream(mp3Data);
        AudioFormat sourceFormat = mp3Stream.getFormat();
        // create audio format object for the desired stream/audio format
        // this is *not* the same as the file format (wav)
        AudioFormat convertFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                sourceFormat.getSampleRate(), 16,
                sourceFormat.getChannels(),
                sourceFormat.getChannels() * 2,
                sourceFormat.getSampleRate(),
                false);
        // create stream that delivers the desired format
        AudioInputStream converted = AudioSystem.getAudioInputStream(convertFormat, mp3Stream);
        // write stream into a file with file format wav
        AudioSystem.write(converted, AudioFileFormat.Type.WAVE, new File("C:\\temp\\out.wav"));
    }
}
