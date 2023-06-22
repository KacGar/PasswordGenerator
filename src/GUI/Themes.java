package GUI;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Themes {

    private List<String> allThemes = new ArrayList<>();

    Themes(){getAllThemes();}

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

    public List<String> allThemes(){
        return allThemes;
    }
}
