package it.unibo.oop.lab.mvcio;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import it.unibo.oop.lab.iogui.BadIOGUI;

/**
 * 
 */
public class Controller {

    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * 2) A method for getting the current File
     * 
     * 3) A method for getting the path (in form of String) of the current File
     * 
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */

     private File file;
     private static final String PATH = System.getProperty("user.home")
             + System.getProperty("file.separator")
             + "output.txt";

     public Controller() {
         this.file = new File(PATH);
     }
     /**
      * 
      * @return the file
      */
    public final File getCurrentFile() {
        return file;
    }
    /**
     * 
     * 
     * @param file
     *           set file to current
     */
    public final void setFile(final File file) {
        this.file = file;
    }
    /**
     * 
     * @param file
     * @return the String of the path
     */
    public final String getPath(final File file) {
        return file.getPath();
    }
    /**
     * 
     * @param text you want to write in file
     * @throws IOException
     */
    public final void writeToFile(final String text) throws IOException {
        try (
              DataOutputStream f = new DataOutputStream(
                new BufferedOutputStream(
                  new FileOutputStream(file)));
        ) {
            f.writeUTF(text);
        }
    }


}
