package de.xcuzimsmart.pluginhelper.document;

import de.xcuzimsmart.pluginhelper.plugin.Plugin;

public final class PluginConfigFile extends YamlConfigFile {

    public PluginConfigFile(Plugin plugin, boolean loadOnInit) {
        super(plugin, plugin.getDataFolder(), "config.yml", loadOnInit);
    }

    // returns the String that is set in plugin config.
    public String getPrefix() {
        return readString("prefix");
    }

    // sets the String value in plugin config on key 'prefix'
    public void setPrefix(String v) {
        writeString("prefix", v);
    }
    public void setPrefix() {
        this.setPrefix("");
    }
}
