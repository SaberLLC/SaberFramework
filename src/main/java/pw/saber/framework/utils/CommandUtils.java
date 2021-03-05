package pw.saber.framework.utils;

import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.Plugin;
import pw.saber.framework.SaberFramework;

import java.lang.reflect.Field;
import java.util.HashMap;

public class CommandUtils {
    private static Object getPrivateField(Object object, String field) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Class<?> clazz = object.getClass();
        Field objectField = clazz.getDeclaredField(field);
        objectField.setAccessible(true);
        Object result = objectField.get(object);
        objectField.setAccessible(false);
        return result;
    }

    public static void unRegisterBukkitCommand(Command cmd) {
        try {
            Object result = getPrivateField(SaberFramework.getInstance().getServer().getPluginManager(), "commandMap");
            SimpleCommandMap commandMap = (SimpleCommandMap) result;
            Object map = getPrivateField(commandMap, "knownCommands");
            HashMap<String, Command> knownCommands = (HashMap<String, Command>) map;
            knownCommands.remove(cmd.getName());
            for (String alias : cmd.getAliases()) {
                if (knownCommands.containsKey(alias) && knownCommands.get(alias).toString().contains(SaberFramework.getInstance().getName()))
                    knownCommands.remove(alias);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unRegisterBukkitCommand(Plugin p, Command cmd) {
        try {
            Object result = getPrivateField(p.getServer().getPluginManager(), "commandMap");
            SimpleCommandMap commandMap = (SimpleCommandMap) result;
            Object map = getPrivateField(commandMap, "knownCommands");
            HashMap<String, Command> knownCommands = (HashMap<String, Command>) map;
            knownCommands.remove(cmd.getName());
            for (String alias : cmd.getAliases()) {
                if (knownCommands.containsKey(alias) && knownCommands.get(alias).toString().contains(p.getName()))
                    knownCommands.remove(alias);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
