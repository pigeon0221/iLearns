/*
Interface for enabling a screen to receive input from USB port. Input is then accessible in the String argument -> tag.
 */
public interface TagScreen extends Screen {
   void scanTag(String tag);
}
