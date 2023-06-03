package me.aurora.client.features.misc;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;

public class AutoHarp implements Module {
    public String name() {
        return "AutoHarp";
    }

    public boolean toggled() {
        return Config.autoHarp;
    }

}
