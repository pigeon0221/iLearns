import java.util.HashMap;

public class Pages {
    public static HashMap<String, Screen> pages = new HashMap<String, Screen>();
    public Screen getPage(String name) {
        //TODO: Implement pages.getOrDefault() to return page or error page
        return pages.get(name);
    }

    public void setPage(String name, Screen screen) {
        pages.put(name,screen);
    }
    public HashMap<String, Screen> getAllPages() {
        return pages;
    }
    public Screen getActivePage(){
        for (Screen x : getAllPages().values()) {
            if(x.getVisibility()==true){
                return x;
            }
        }
        return null;
    }

}

