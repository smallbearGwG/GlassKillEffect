package com.bearcurb.glasskilleffect;

import com.bearcurb.glasskilleffect.util.EntityUtil;
import com.bearcurb.glasskilleffect.util.SettingUtil;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModMain.MODID, version = ModMain.VERSION, clientSideOnly = true, useMetadata = true)
public class ModMain {
  public static final String NAME = "GlassKillEffect";
  public static final String MODID = "glasskilleffectg";
  public static final String VERSION = "1.5";
  public boolean DEBUG = false;
  public final Minecraft mc = Minecraft.getMinecraft();
  public KillEffectManager killEffectManager;
  public Setting setting;
  public boolean enabled = true;
  public boolean nickModel = false;
  public String nickName = null;

  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    this.killEffectManager = new KillEffectManager(this);
    SettingUtil.loadConfig(event);
    this.setting = new Setting(this);

    //注册命令
    ClientCommandHandler.instance.registerCommand(new Command(this));
    //注册事件
    MinecraftForge.EVENT_BUS.register(new EntityUtil());
    MinecraftForge.EVENT_BUS.register(new Events(this));
  }

  @Mod.EventHandler
  public void init(FMLInitializationEvent event) {
  }

  @Mod.EventHandler
  public void postInit(FMLPostInitializationEvent event) {
  }
}
