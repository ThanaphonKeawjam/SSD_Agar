package agarssd.client;

public class Main {
    public static void main(String[] args) {
        GameClient gameClient = GameClient.getInstance();
        Gui gui = new Gui();
        gui.setVisible(true);
        gameClient.addObserver(gui);
        gameClient.start();
    }
}
