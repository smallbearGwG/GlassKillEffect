package com.bearcurb.glasskilleffect;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Command extends CommandBase {

  private final ModMain instance;
  private String commandName = "glasseffect";
  private String errorInfo = "Usage: /glasseffect <state, help, toggle, set, setnick,preview>";

  public Command(ModMain instance) {
    this.instance = instance;
  }

  @Override
  public int getRequiredPermissionLevel() {
    return 0;
  }

  @Override
  public String getCommandName() {
    return commandName;
  }

  @Override
  public String getCommandUsage(ICommandSender iCommandSender) {
    return errorInfo;
  }

  @Override
  public void processCommand(ICommandSender sender, String[] args) throws CommandException {
    if (sender == null || args.length == 0) {
      this.showErrorInfo(sender);
      return;
    }
    String option = args[0];
    switch (option) {
      case "state":
        sendMessage(sender, ChatFormatting.RED + "---------------state---------------");
        sendMessage(sender, ChatFormatting.RED + "modName: " + this.instance.NAME);
        sendMessage(sender, ChatFormatting.RED + "version: " + this.instance.VERSION);
        sendMessage(sender, ChatFormatting.RED + "debug: " + this.instance.DEBUG);
        sendMessage(sender, ChatFormatting.RED + "enable: " + this.instance.enabled);
        sendMessage(sender, ChatFormatting.RED + "effecttype: " + this.instance.killEffectManager.effecttype);
        sendMessage(sender, ChatFormatting.RED + "effectInitStr :" + this.instance.killEffectManager.effectInitStr);
        sendMessage(sender, ChatFormatting.RED + "nickModel: " + this.instance.nickModel);
        sendMessage(sender, ChatFormatting.RED + "nickName: " + this.instance.nickName);
        sendMessage(sender, ChatFormatting.RED + "-----------------------------------");
        break;
      case "help":
        sendMessage(sender, ChatFormatting.YELLOW + "---------------Help: GlassKillEffect---------------");
        sendMessage(sender, ChatFormatting.YELLOW + "/killeffect toggle: Toggle This Mod. \n" + ChatFormatting.YELLOW + "/killeffect preview: Preview Current Killeffect. \n" + ChatFormatting.YELLOW + "/killeffect set <killEffectName>: Set Killeffect. \n" + ChatFormatting.YELLOW + "/killeffect setnick <NickName>: Set nick Name.");
        break;
      //----------------------------------------------------------------------------------------------------------------------------------------------------------//
      case "preview":
        if (args.length == 1) {
          this.instance.killEffectManager.spawnEffect(Minecraft.getMinecraft().thePlayer);
        } else this.showErrorInfo(sender);
        break;
      //----------------------------------------------------------------------------------------------------------------------------------------------------------//
      case "set":
        if (args.length == 1 || args.length == 2) {
          try {
            if (args.length == 1) throw new Exception();
            String effectTypeStr = args[1];
            Effecttype effecttype = Effecttype.valueOf(effectTypeStr.toUpperCase());
            this.sendMessage(sender, EnumChatFormatting.GREEN + "KillEffect is now: " + effecttype);
            this.instance.killEffectManager.effecttype = effecttype;
            this.instance.killEffectManager.effectInitStr = effectTypeStr;
            this.instance.setting.save();
          } catch (Exception e) {
            //显示所有的颜色
            Effecttype[] arrays = Effecttype.values();
            String message = "";
            this.sendMessage(sender, EnumChatFormatting.RED + "Invalid killEffect Name!");
            int t = 1;
            int i = 0;
            while (i < arrays.length) {
              message = message + EnumChatFormatting.YELLOW + "-" + arrays[i] + "-";
              if (t >= 3) {
                message = message + "\n";
                t = 0;
              }
              ++i;
              ++t;
            }
            if (t <= 3) {
              message = message + "\n";
            }
            message = message + EnumChatFormatting.YELLOW + "-------------------------------";
            this.sendMessage(sender, EnumChatFormatting.YELLOW + "----------killEffects----------\n" + message);
          }
        }
        break;
      //----------------------------------------------------------------------------------------------------------------------------------------------------------//
      case "setnick":
        if (args.length == 1 || args.length == 2) {
          if (args.length < 2) {
            this.sendMessage(sender, EnumChatFormatting.YELLOW + "Nick Modle is Stop");
            this.instance.nickName = "";
            this.instance.nickModel = false;
            this.instance.setting.writeNickName("");
          } else {
            String nickName = args[1];
            this.sendMessage(sender, EnumChatFormatting.GREEN + "Nick Modle is Start currentName is " + nickName);
            this.instance.nickName = nickName;
            this.instance.nickModel = true;
            this.instance.setting.writeNickName(nickName);
          }
        } else this.showErrorInfo(sender);
        break;
      //----------------------------------------------------------------------------------------------------------------------------------------------------------//
      case "toggle":
        this.instance.enabled = !this.instance.enabled;
        if (this.instance.enabled) {
          this.sendMessage(sender, EnumChatFormatting.GREEN + "GLASSEFFECT START!!");
        } else {
          this.sendMessage(sender, EnumChatFormatting.RED + "GLASSEFFECT STOP!!");
        }
        this.instance.setting.save();
        break;
      //----------------------------------------------------------------------------------------------------------------------------------------------------------//
      case "debug":
        this.instance.DEBUG = !this.instance.DEBUG;
        this.sendMessage(sender, "TOGGLE!! DEBUG IS:" + this.instance.enabled);
        break;
      //----------------------------------------------------------------------------------------------------------------------------------------------------------//
      default:
        this.showErrorInfo(sender);
    }
  }


  protected void sendMessage(ICommandSender sender, String message) {
    sender.addChatMessage(new ChatComponentText(message));
  }

  public void showErrorInfo(ICommandSender sender) {
    sendMessage(sender, EnumChatFormatting.RED + this.errorInfo);
  }
}
