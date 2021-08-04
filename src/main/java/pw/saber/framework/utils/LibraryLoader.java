package pw.saber.framework.utils;

import org.apache.commons.io.FileUtils;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLClassLoader;

public class LibraryLoader {
    private static final Method ADD_URL_METHOD;

    static {
        try {
            ADD_URL_METHOD = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            ADD_URL_METHOD.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static void load(String fileName, String givenUrl, JavaPlugin javaPlugin) {
        boolean isCreating = false;
        File saveLocation = new File(getLibFolder(javaPlugin), fileName + ".jar");
        if (!saveLocation.exists())
            try {
                InputStream inputStream;
                Logger.print(" ", Logger.PrefixType.WARNING);
                Logger.print("Downloading " + fileName + "...", Logger.PrefixType.WARNING);
                if (givenUrl.toLowerCase().contains("https://")) {
                    HttpsURLConnection urlConnection = (HttpsURLConnection)(new URL(givenUrl)).openConnection();
                    urlConnection.addRequestProperty("User-Agent", "Mozilla/4.76");
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                } else {
                    HttpURLConnection urlConnection = (HttpURLConnection)(new URL(givenUrl)).openConnection();
                    urlConnection.addRequestProperty("User-Agent", "Mozilla/4.76");
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
                FileUtils.copyInputStreamToFile(inputStream, saveLocation.toPath().toFile());
                isCreating = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        if (!saveLocation.exists())
            throw new RuntimeException(ChatColor.RED + "Unable to download libraries");
        URLClassLoader classLoader = (URLClassLoader)javaPlugin.getClass().getClassLoader();
        try {
            ADD_URL_METHOD.invoke(classLoader, saveLocation.toURI().toURL());
        } catch (Exception e) {
            throw new RuntimeException("Unable to load libraries: " + saveLocation.toString(), e);
        }
        if (isCreating) {
            Logger.print("Success!", Logger.PrefixType.WARNING);
            Logger.print(" ", Logger.PrefixType.WARNING);
        } else {
            Logger.print("- `" + fileName + "` is installed", Logger.PrefixType.WARNING);
        }
    }

    private static File getLibFolder(JavaPlugin javaPlugin) {
        File pluginDataFolder = javaPlugin.getDataFolder();
        File pluginsDir = pluginDataFolder.getParentFile();
        File helperDir = new File(pluginsDir, "SaberFramework");
        File libs = new File(helperDir, "libraries");
        libs.mkdirs();
        return libs;
    }
}