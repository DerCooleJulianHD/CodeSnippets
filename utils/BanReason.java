package de.xcuzimsmart.pluginhelper.code.main.java.utils;


import javax.annotation.Nullable;

public enum BanReason {

    CHEATING(0),
    CHAT(1);

    private final int id;

    BanReason(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getIdString() {
        return String.valueOf(getId());
    }

    public static String of(final BanReason reason) {
        return reason.getIdString();
    }

    @Nullable
    public static BanReason parse(String id) {
        final int idInt = Integer.parseInt(id);

        for (BanReason value : BanReason.values()) {
            if (idInt == value.id)
                return value;
        }

        return null;
    }
}
