package com.bearcurb.glasskilleffect;

import com.bearcurb.glasskilleffect.util.SettingUtil;

public class Setting {
  private ModMain instance;

  public Setting(ModMain instance) {
    this.instance = instance;
    this.loadSetting();
    this.instance.enabled = this.getEnable();
    this.instance.nickModel = this.getDefaultNickName() != null && !this.getDefaultNickName().equals("");
    this.instance.nickName = getDefaultNickName();
    this.instance.killEffectManager.effectInitStr = getCurrentKillEffect();
  }

  /**
   * 加载配置文件
   */
  public void loadSetting() {
    //检查配置文件
    if (!SettingUtil.cfg.hasCategory("GlassKillEffect")) {
      SettingUtil.writeBoolean("GlassKillEffect", "enable", true, true);
      SettingUtil.writeString("GlassKillEffect", "currentKillEffect", "While", "While");
      SettingUtil.writeString("GlassKillEffect", "nickName", "", "");
    }
    SettingUtil.cfg.save();
  }

  public boolean getEnable() {
    return SettingUtil.getBoolean("GlassKillEffect", "enable", true);
  }

  public void writeEnabled(boolean value) {
    SettingUtil.writeBoolean("GlassKillEffect", "enable", true, value);
  }

  public String getDefaultNickName() {
    return SettingUtil.getString("GlassKillEffect", "nickName", "");
  }

  public void writeNickName(String value) {
    SettingUtil.writeString("GlassKillEffect", "nickName", "", value);
  }

  public String getCurrentKillEffect() {
    return SettingUtil.getString("GlassKillEffect", "currentKillEffect", "While");
  }

  public void writeCurrentKillEffect(String value) {
    SettingUtil.writeString("GlassKillEffect", "currentKillEffect", "While", value);
  }

  public void save() {
    this.writeEnabled(this.instance.enabled);
    this.writeCurrentKillEffect(this.instance.killEffectManager.effecttype.toString());
  }
}
