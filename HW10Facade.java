/*import java.util.Scanner;
public class HW10Facade {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        TV tv = new TV();
        AudioSystem audioSystem = new AudioSystem();
        DVDPlayer dvdPlayer = new DVDPlayer();
        GameConsole gameConsole = new GameConsole();

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(tv, audioSystem, dvdPlayer, gameConsole);


        homeTheater.watchMovie();
        homeTheater.setVolume(10);
        homeTheater.endMovie();

        homeTheater.playGame();

        homeTheater.listenToMusic();
        homeTheater.setVolume(7);

        homeTheater.shutdownSystem();

        in.close();
    }
}
class TV {
    private boolean isOn;
    private int channel;

    public void on() {
        isOn = true;
        System.out.println("TV is turned on.");
    }

    public void off() {
        isOn = false;
        System.out.println("TV is turned off.");
    }

    public void setChannel(int channel) {
        this.channel = channel;
        System.out.println("TV channel set to " + channel);
    }
}

class AudioSystem {
    private boolean isOn;
    private int volume;

    public void on() {
        isOn = true;
        System.out.println("Audio system is turned on.");
    }

    public void off() {
        isOn = false;
        System.out.println("Audio system is turned off.");
    }

    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("Audio volume set to " + volume);
    }
}

class DVDPlayer {
    private boolean isPlaying;

    public void play() {
        isPlaying = true;
        System.out.println("DVD Player is playing.");
    }

    public void pause() {
        isPlaying = false;
        System.out.println("DVD Player is paused.");
    }
    public void stop() {
        isPlaying = false;
        System.out.println("DVD Player is stopped.");
    }
}

class GameConsole{
    private boolean isOn;
    public void on() {
        isOn = true;
        System.out.println("Game Console is turned on.");
    }
    public void startGame() {
        if (isOn) {
            System.out.println("Game has started.");
        } else {
            System.out.println("Please turn on the Game Console first.");
        }
    }
}

class HomeTheaterFacade {
    private TV tv;
    private AudioSystem audioSystem;
    private DVDPlayer dvdPlayer;
    private GameConsole gameConsole;

    public HomeTheaterFacade(TV tv, AudioSystem audioSystem, DVDPlayer dvdPlayer, GameConsole gameConsole) {
        this.tv = tv;
        this.audioSystem = audioSystem;
        this.dvdPlayer = dvdPlayer;
        this.gameConsole = gameConsole;
    }

    public void watchMovie() {
        System.out.println("Getting ready to watch a movie...");
        tv.on();
        audioSystem.on();
        dvdPlayer.play();
        System.out.println("Movie is now playing.");
    }

    public void endMovie() {
        System.out.println("Shutting down movie mode...");
        dvdPlayer.stop();
        audioSystem.off();
        tv.off();
    }

    public void playGame() {
        System.out.println("Getting ready to play a game...");
        tv.on();
        gameConsole.on();
        gameConsole.startGame();
    }

    public void listenToMusic() {
        System.out.println("Getting ready to listen to music...");
        tv.on();
        audioSystem.on();
        tv.setChannel(0);
        System.out.println("Music is now playing.");
    }

    public void setVolume(int volume) {
        audioSystem.setVolume(volume);
    }

    public void shutdownSystem() {
        System.out.println("Shutting down the entire system...");
        dvdPlayer.stop();
        audioSystem.off();
        tv.off();
        gameConsole.on();
    }
}
*/