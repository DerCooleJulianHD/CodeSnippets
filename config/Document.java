package de.xcuzimsmart.pluginhelper.document;

import de.xcuzimsmart.pluginhelper.utils.Loadable;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public abstract class Document implements Loadable {

    protected final DocumentType type;

    protected final File dir, file;

    boolean loaded = false;

    public Document(@NotNull DocumentType type, File dir, @NotNull String fileName, boolean loadOnInit) {
        this.dir = dir;
        this.type = type;
        this.file = new File(dir, fileName);
        Validate.isTrue(hasEnding(type.ending()), "file must be correct type.");
        if (loadOnInit) load();
    }

    public Document(@NotNull DocumentType type, String dir, @NotNull String fileName, boolean loadOnInit) {
        this(type, new File(dir), fileName, loadOnInit);
    }

    public static Document of(@NotNull DocumentType type, File dir, @NotNull String fileName, boolean loadOnInit) {
        return new Document(type, dir, fileName, loadOnInit) {
            @Override
            public void load() {}
        };
    }

    // returns true if config file does end with 'filename{".ending"}'
    public final boolean hasEnding(String[] v) {
        final String fileName = file.getName();

        if (v.length == 0)
            return false;

        if (v.length < 2) {
            for (String ending : v)
                if (fileName.endsWith(ending))
                    return true;
        }

        return false;
    }

    @Override
    // returns true when config has been loaded.
    public final boolean isLoaded() {
        return loaded;
    }

    @Override
    // sets if config has been loaded or not.
    public final void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    // returns true when 'getDir()' does not return null and file is exist.
    public final boolean exists() {
        return dir != null && dir.exists() && file.exists();
    }

    // the folder the config file is in.
    public final File getDir() {
        return dir;
    }

    // returns the config file.
    public final File getFile() {
        return file;
    }
}

