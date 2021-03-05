package pw.saber.framework.utils;

import java.text.DecimalFormat;

public enum DecimalFormatType {
    MONEY(new DecimalFormat("#,###.##")),
    SECONDS(new DecimalFormat("#.#")),
    LOCATION(new DecimalFormat("#.##"));

    private DecimalFormat format;

    DecimalFormatType(DecimalFormat format) {
        this.format = format;
    }

    public DecimalFormat getFormat() {
        return this.format;
    }

    public String format(Number value) {
        return this.format.format(value);
    }
}