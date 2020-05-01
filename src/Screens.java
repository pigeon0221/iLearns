import java.util.HashMap;

/*
Screens is a class for interacting with the various screens of the game.
Screens creates a hash map of active screens which can be accessed with the below methods.
 */
public class Screens {
    public static HashMap<String, Screen> screens = new HashMap<String, Screen>();

    // Returns the screen for the given name.
    public Screen getScreen(String name) {
        //TODO: Implement pages.getOrDefault() to return page or error page
        return screens.get(name);
    }

    // Adds a screen to the HashMap of screens.
    public void addScreen(String name, Screen screen) {
        screens.put(name,screen);
    }

    // Returns all screens in a HashMap
    public HashMap<String, Screen> getAllScreens() {
        return screens;
    }

    // Returns the currently active screen.
    public Screen getActiveScreen(){
        for (Screen x : getAllScreens().values()) {
            if(x.getVisibility()){
                return x;
            }
        }
        return null;
    }

}

