package com.bearcurb.glasskilleffect.util;


import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class SettingUtil {

  final public static String CFG_VERSION = "1.2";

  public static Configuration cfg;

  public static void loadConfig(FMLPreInitializationEvent event) {
    cfg = new Configuration(event.getSuggestedConfigurationFile(), CFG_VERSION, true);
  }

  public static void writeBoolean(String category, String key, boolean defaultBoolean, boolean value) {
    try {
      cfg.load();
      cfg.get(category, key, defaultBoolean).set(value);
    } catch (Exception e) {
      System.out.println("Error: config cannot load!");
    } finally {
      cfg.save();
    }
  }

  public static boolean getBoolean(String category, String key, boolean defaultBoolean) {
    try {
      cfg.load();
      boolean bl = cfg.get(category, key, defaultBoolean).getBoolean();
      return bl;
    } catch (Exception e) {
      System.out.println("Error: config cannot load!");
    } finally {
      cfg.save();
    }
    return false;
  }

  public static void writeString(String category, String key, String defaultString, String value) {
    try {
      cfg.load();
      cfg.get(category, key, defaultString).set(value);
    } catch (Exception e) {
      System.out.println("Error: config cannot load!");
    } finally {
      cfg.save();
    }
  }

  public static String getString(String category, String key, String defaultString) {
    try {
      cfg.load();
      String string = cfg.get(category, key, defaultString).getString();
      return string;
    } catch (Exception e) {
      System.out.println("Error: config cannot load!");
    } finally {
      cfg.save();
    }
    return null;
  }

  public static void writeArrayString(String category, String key, String[] defaultStrings, String[] value) {
    try {
      cfg.load();
      cfg.get(category, key, defaultStrings).set(value);
    } catch (Exception e) {
      System.out.println("Error: config cannot load!");
    } finally {
      cfg.save();
    }
  }

  public static String[] getArrayString(String category, String key, String[] defaultStrings) {
    try {
      cfg.load();
      String[] stringArray = cfg.get(category, key, defaultStrings).getStringList();
      return stringArray;
    } catch (Exception e) {
      System.out.println("Error: config cannot load!");
    } finally {
      cfg.save();
    }
    return null;
  }
}
