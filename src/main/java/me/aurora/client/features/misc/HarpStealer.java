package me.aurora.client.features.misc;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;

@Deprecated
public class HarpStealer implements Module {
    public String name() {
        return "HarpStealer";
    }

    public boolean toggled() {
        return Config.harpStealer;
    }
}
