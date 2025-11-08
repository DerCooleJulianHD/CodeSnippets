package de.xcuzimsmart.pluginhelper.document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.xcuzimsmart.pluginhelper.events.ConnectionEvents;
import de.xcuzimsmart.pluginhelper.plugin.Plugin;
import de.xcuzimsmart.pluginhelper.utils.Abstract;
import org.apache.commons.lang3.Validate;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;

@Abstract
@JsonProperties() /* <-- by default */
public class JsonConfigFile extends Document {

    final Plugin plugin;

    final Gson gson;

    final JsonProperties properties = getClass().getDeclaredAnnotation(JsonProperties.class);

    public JsonConfigFile(Plugin plugin, File dir, String fileName, boolean loadOnInit) {
        super(DocumentType.JSON, dir, fileName, loadOnInit);

        Validate.notNull(properties, getClass().getName() + " misses JsonProperties Annotation!");

        this.plugin = plugin;
        this.gson = JsonConfigurationBuilder.build(properties);

        Objects.requireNonNull(plugin.getListeners("lobby")).get(ConnectionEvents.class);
    }

    // writes an object to a Json-Configuration.
    public final void write(Object o) {
        if (!file.exists())
            return;

        if (!isLoaded())
            load();

        try {
            final FileWriter writer = new FileWriter(file);

            writer.write(gson.toJson(o));
            writer.close();
        } catch (IOException e) {
            plugin.getPluginLogger().log(Level.SEVERE, "Unable to write object to: " + file.getName(), e);
        }
    }

    @Nullable
    public final Object read(Class<?> classOfT) {
        if (!file.exists())
            return null;

        if (!isLoaded())
            return null;

        try {
           final FileReader reader = new FileReader(file);
           final Object o = gson.fromJson(reader, classOfT);
           reader.close();
           return o;
        } catch (IOException e) {
            plugin.getPluginLogger().log(Level.SEVERE, "Unable to read object from: " + file.getName(), e);
        }

        return null;
    }

    @Override
    public final void load() {
        try {
            if (!exists())
                file.createNewFile();

            this.setLoaded(true);
        } catch (IOException e) {
            plugin.getPluginLogger().log(Level.SEVERE, "Unable to load: " + file.getName(), e);
        }
    }

    private static final class JsonConfigurationBuilder {

        public static Gson build(JsonProperties properties) {
            final GsonBuilder builder = new GsonBuilder();

            if (properties.prettyPrinting())
                builder.setPrettyPrinting();

            if (!properties.htmlEscaping())
                builder.disableHtmlEscaping();

            if (!properties.innerClazzSerialisation())
                builder.disableInnerClassSerialization();

            return builder.create();
        }
    }
}
