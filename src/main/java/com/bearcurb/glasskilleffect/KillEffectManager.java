package com.bearcurb.glasskilleffect;

import com.bearcurb.glasskilleffect.util.EntityUtil;
import com.bearcurb.glasskilleffect.util.GlassUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.GameData;


public class KillEffectManager {
  private ModMain instance;
  public Effecttype effecttype = Effecttype.WHITE;
  public String effectInitStr;

  public KillEffectManager(ModMain main) {
    this.instance = main;
  }

  public void spawnEffect(Entity target) {
    // 修复游戏初始化时读取不到enum
    try {
      Effecttype effecttype = Effecttype.valueOf(effectInitStr.toUpperCase());
      this.instance.killEffectManager.effecttype = effecttype;
    } catch (Exception ok) {
      System.err.println(ok);
    }

    int id = 0;
    switch (effecttype) {
      case WHITE:
        id = 0;
        break;
      case ORANGE:
        id = 1;
        break;
      case MAGENTA:
        id = 2;
        break;
      case LIGHTBLUE:
        id = 3;
        break;
      case YELLOW:
        id = 4;
        break;
      case LIME:
        id = 5;
        break;
      case PINK:
        id = 6;
        break;
      case GRAY:
        id = 7;
        break;
      case SILVER:
        id = 8;
        break;
      case CYAN:
        id = 9;
        break;
      case PURPLE:
        id = 10;
        break;
      case BLUE:
        id = 11;
        break;
      case BROWN:
        id = 12;
        break;
      case GREEN:
        id = 13;
        break;
      case RED:
        id = 14;
        break;
      case BLACK:
        id = 15;
        break;
    }
    float x = EntityUtil.getX(target);
    float y = EntityUtil.getY(target);
    float z = EntityUtil.getZ(target);
    Block block = GameData.getBlockRegistry().getObjectById(95);
    GlassUtil.BrokenEffectSpan(x, y, z, block, block.getStateFromMeta(id));
  }
}
