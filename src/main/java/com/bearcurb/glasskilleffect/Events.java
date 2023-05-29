package com.bearcurb.glasskilleffect;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

import java.util.HashMap;
import java.util.Map;

public class Events {
  private ModMain instance;
  private World lastWorld = null;
  private Map<String, EntityPlayer> playeys = new HashMap<>();

  public Events(ModMain instance) {
    this.instance = instance;
  }

  @SubscribeEvent
  public void onPlayerJoin(EntityJoinWorldEvent event) {
    if (this.lastWorld != null && !this.lastWorld.equals(event.world)) {
      this.playeys.clear();
    }
    if (event.entity instanceof EntityPlayer) {
      this.playeys.put(event.entity.getName(), (EntityPlayer) event.entity);
    }
    this.lastWorld = event.world;
  }

  @SubscribeEvent
  public void onPlayerLogout(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
    this.instance.setting.save();
  }

  @SubscribeEvent
  public void EnglishEvent(ClientChatReceivedEvent event) {
    String[] triggers = new String[]{"was slain by", "was killed by", "was shot by", "wrapped up by", "was glued up by", "was filled full of lead by", "lost the draw to", "was bit by", "was thrown a frisbee by", "was crushed into moon dust by", "was hit by an asteroid from", "was chewed up by", "was squeaked from a distance by", "was locked outside during a snow storm by", "was hit with a snowball from", "was ripped to shreds by", "was pounced on by", "be sent to Davy Jones' locker by", "be shot and killed by", "was trampled by", "was impaled from a distance by", "was oinked by", "got attacked by a carrot from", "got rekt by", "got bamboozled by", "was crusaded by the knight", "was shot to the knee by", "was given the cold shoulder by", "was struck with Cupid's arrow by", "was exterminated by", "was tranquilized by", "died in close combat to", "fell to the great marksmanship of", "was turned into space dust by", "was hit by an asteroid from", "became victim #??? of", "was bow kill #??? of", "was struck down by", "was melted by", "was smothered in holiday cheer by", "was sniped by a missile of festivity by", "was backstabbed by", "was brutally shot by", "was painted pretty by", "was made sunny side up by", "was deleted by", "was rm -rf by", "was put on the naughty list by", "was turned to gingerbread by", "was whacked with a party balloon by", "was shot with a roman candle by", "was buzzed to death by", "was startled from a distance by", "was socked by", "was sent into a daze by", "was mushed by", "got banana pistol'd by", "was glazed in BBQ sauce by", "was thrown chili powder at by"};
    if (!this.instance.enabled) {
      return;
    }
    String message = event.message.getUnformattedText();
    for (String currentTrigger : triggers) {
      //nick
      if (instance.nickModel) {
        if (!message.contains(currentTrigger) || !message.contains(this.instance.nickName)) continue;
      } else {
        //包含触发词语+包含自己的名字
        if (!message.contains(currentTrigger) || !message.contains(this.instance.mc.thePlayer.getName())) continue;
      }

      String[] useTriggerStrSplit = message.split(currentTrigger);
      String myselfStr = useTriggerStrSplit.length >= 2 ? useTriggerStrSplit[1].replace(" ", "") : "";
      myselfStr = myselfStr.replace(".", "").replace(" ", "");
      String killedStr = useTriggerStrSplit.length >= 1 ? useTriggerStrSplit[0].replace(" ", "") : "";

      if (this.instance.DEBUG) {
        System.err.println("~~~~~~~~~~~~~DEBUG~~~~~~~~~~~~~");
        System.err.println("触发词: " + currentTrigger);
        System.err.println("自己: " + myselfStr);
        System.err.println("被杀者: " + killedStr);
        System.err.println("~~~~~~~~~~~~~~END~~~~~~~~~~~~~~");
      }

      EntityPlayer targetPlayer = this.playeys.get(killedStr);
      if (targetPlayer == null) {
        return;
      }
      this.instance.killEffectManager.spawnEffect(targetPlayer);
    }
  }

  @SubscribeEvent
  public void skyWarChinese(ClientChatReceivedEvent event) {
    String[][] triggers = new String[][]{{"被", "击杀。"}, {"被", "射杀。"}, {"被", "捣成泥巴。"}, {"被", "的香蕉手枪射杀。"}, {"被", "用滑铲搞到冰滑梯上去了。"}, {"被", "扔的雪球砸中。"}, {"被", "化作宇宙尘埃。"}, {"被", "的陨石击中。"}, {"被", "背刺一刀。"}, {"被", "残忍地射杀。"}, {"因", "中箭无数。"}, {"输掉了与", "的对决。"}, {"被", "完爆。"}, {"被", "捉弄致死。"}, {"被", "撕为碎片。"}, {"被", "突袭。"}, {"被", "淹没于节日欢呼声中。"}, {"被", "的庆典飞弹锁定。"}, {"被", "删除。"}, {"被", "执行了删除命令。"}, {"被", "践踏。"}, {"被", "在远处刺穿。"}, {"受到", "的冷淡。"}, {"被", "的丘比特之箭射杀。"}, {"被", "烤成了肉串。"}, {"被", "的辣椒粉砸中。"}, {"被", "击倒。"}, {"被", "熔化。"}, {"被", "塞进了戴维·琼斯的箱子。"}, {"被", "射杀。"}, {"被", "撕咬致死。"}, {"被", "的飞盘击中。"}, {"被", "消灭。"}, {"被", "沉默了。"}, {"被", "猛击。"}, {"被", "而恍惚。"}, {"被", "打包成了礼物。"}, {"被", "粘住了。"}, {"被", "浓妆艳抹。"}, {"被", "列入淘气名单。"}, {"被", "做成姜饼。"}, {"被", "吵死了。"}, {"被", "远远地吓到了。"}, {"因", "而崩溃。"}, {"被远处", "的尖叫吓到。"}, {"被", "化作月尘。"}, {"被来自", "的陨石击中。"}, {"死于与", "的近战。"}, {"死于与", "的近身格斗。"}, {"被远处", "的百步穿杨之术射杀。"}, {"被骑士", "讨伐。"}, {"被", "射中膝盖。"}, {"被", "的呼噜声击败了。"}, {"被", "的萝卜击中。"}};
    if (!this.instance.enabled) {
      return;
    }
    String message = event.message.getUnformattedText();
    for (String[] currentTrigger : triggers) {
      //触发词过滤
      if (!message.contains(currentTrigger[0]) || !message.contains(currentTrigger[1])) continue;
      String myselfStr = message.substring(message.indexOf(currentTrigger[0]) + currentTrigger[0].length(), message.indexOf(currentTrigger[1]));
      String killedStr = message.substring(0, message.indexOf(currentTrigger[0]));

      //判断自己是否是击杀者
      if (instance.nickModel) {
        if (!myselfStr.equals(this.instance.nickName)) continue;
      }

      if (this.instance.DEBUG) {
        System.err.println("~~~~~~~~~~~~~DEBUG~~~~~~~~~~~~~");
        System.err.println("触发词: " + currentTrigger);
        System.err.println("自己: " + myselfStr);
        System.err.println("被杀者: " + killedStr);
        System.err.println("~~~~~~~~~~~~~~END~~~~~~~~~~~~~~");
      }

      EntityPlayer targetPlayer = this.playeys.get(killedStr);
      if (targetPlayer == null) {
        return;
      }
      this.instance.killEffectManager.spawnEffect(targetPlayer);
    }
  }
}
