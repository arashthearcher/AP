package CETankGUI;

import java.io.File;

import javax.rmi.CORBA.Util;
import javax.swing.filechooser.FileFilter;

public class FileChooserFilter extends FileFilter {
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        
        String extension = getExtension(f);
        if (extension != null) {
            if (extension.equals("sav")) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    //The description of this filter
    public String getDescription() {
        return ".sav";
    }
    
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

}
class FileChooserFilterMap extends FileFilter {
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        
        String extension = FileChooserFilter.getExtension(f);
        if (extension != null) {
            if (extension.equals("map")) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    //The description of this filter
    public String getDescription() {
        return ".map";
    }
}