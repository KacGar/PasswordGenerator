package GUI;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * This class sets list of all available themes in LaF module. List contains fully qualified names of theme classes.
 */
public class Themes {

    private List<String> allThemes = new ArrayList<>();

    /**
     * Constructor which initializes list
     */
    Themes(){getAllThemes();}

    /**
     * Sets list by searching all classes from LaF jar located in LaF module. Every entry of a list is a fully qualified class path.
     */
    private void getAllThemes(){
        try {
            JarFile jarFile = new JarFile("src/LaF/flatlaf-intellij-themes-2.0.jar");
            Enumeration<JarEntry> e = jarFile.entries();
            URL[] urls = { new URL("jar:file:" + "src/LaF/flatlaf-intellij-themes-2.0.jar" + "!/") };
            URLClassLoader cl = URLClassLoader.newInstance(urls);

            while (e.hasMoreElements()) {
                JarEntry je = e.nextElement();
                if(je.isDirectory() || !je.getName().endsWith("Theme.class")){
                    continue;
                }
                // -6 because of .class
                String className = je.getName().substring(0,je.getName().length()-6);
                className = className.replace('/', '.');
                //Class c = cl.loadClass(className);
                if (!className.contains("materialthemeuilite")) allThemes.add(className);
            }
            Collections.sort(allThemes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns list of all themes.
     * @return List<String> of all themes
     */
    public List<String> allThemes(){
        return allThemes;
    }
}
