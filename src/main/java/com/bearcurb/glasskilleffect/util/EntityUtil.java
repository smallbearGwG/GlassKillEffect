package com.bearcurb.glasskilleffect.util;

import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityUtil {
  public static float partialTicks;

  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void tickHook(RenderWorldLastEvent renderWorldLastEvent) {
    partialTicks = renderWorldLastEvent.partialTicks;
  }

  public static float getX(Entity entity) {
    if (entity == null) {
      return -10000.0f;
    }
    return (float) (entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double) partialTicks);
  }

  public static float getY(Entity entity) {
    if (entity == null) {
      return -10000.0f;
    }
    return (float) (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double) partialTicks);
  }

  public static float getZ(Entity entity) {
    if (entity == null) {
      return -10000.0f;
    }
    return (float) (entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double) partialTicks);
  }
}
