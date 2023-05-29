package com.bearcurb.glasskilleffect.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;

public class GlassUtil {
  public static void BrokenEffectSpan(float x, float y, float z, Block block, IBlockState blockState) {
    Minecraft.getMinecraft()
      .getSoundHandler()
      .playSound(new PositionedSoundRecord(new ResourceLocation(block.stepSound.getBreakSound()), (block.stepSound.getVolume() + 1.0f) / 2.0f, block.stepSound.frequency * 0.8f, x, y, z));
    Minecraft.getMinecraft()
      .getSoundHandler()
      .playSound(new PositionedSoundRecord(new ResourceLocation(block.stepSound.getBreakSound()), (block.stepSound.getVolume() + 1.0f) / 2.0f, block.stepSound.frequency * 0.8f, x, y, z));
    Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(new BlockPos(x, y, z), blockState);
    Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(new BlockPos(x, y, z), blockState);
    Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(new BlockPos(x, y, z), blockState);
    Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(new BlockPos(x, (double) y + 0.5, z), blockState);
    Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(new BlockPos(x, (double) y + 0.5, z), blockState);
    Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(new BlockPos(x, (double) y + 0.5, z), blockState);
    Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(new BlockPos(x, (double) y + 0.8, z), blockState);
    Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(new BlockPos(x, (double) y + 0.8, z), blockState);
    Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(new BlockPos(x, (double) y + 0.8, z), blockState);
    Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(new BlockPos(x, (double) y + 1.0, z), blockState);
    Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(new BlockPos(x, (double) y + 1.0, z), blockState);
    Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(new BlockPos(x, (double) y + 1.0, z), blockState);
  }
}
