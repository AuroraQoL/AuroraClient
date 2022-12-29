package me.aurora.client.utils;

import me.aurora.client.config.Config;

import java.awt.*;

public class CurrentColor {
    public static int currentColorGet(float offset) {
        int rbw;
        if(Config.colors==0) {
            rbw = ColorUtils.getRainbow2(-4000, (int) offset*100).getRGB();
        } else if (Config.colors==1) {
            rbw = getGradientOffset(new Color(223, 94, 255), new Color(0, 170, 255).brighter(), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + offset).getRGB();
        } else if (Config.colors==2) {
            rbw = getGradientOffset(new Color(255, 0, 255), new Color(255, 255, 0), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + offset).getRGB();

        } else if (Config.colors==3) {
            rbw = getGradientOffset(new Color(0, 132, 99), new Color(255, 255, 99), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + offset).getRGB();

        } else if (Config.colors==4) {
            rbw = getGradientOffset(new Color(255, 0, 0), new Color(255, 255, 0), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + offset).getRGB();

        } else if (Config.colors==5) {
            rbw = getGradientOffset(new Color(0, 0, 255), new Color(0, 255, 132), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + offset).getRGB();

        } else if (Config.colors==6) {
            rbw = getGradientOffset(new Color(255, 255, 255), new Color(0, 0, 0), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + offset).getRGB();
        } else if (Config.colors==7) {
            rbw = getGradientOffset(new Color(231, 239, 239), new Color(16, 16, 24), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + offset).getRGB();
        } else if (Config.colors==8) {
            rbw = getGradientOffset(new Color(255, 198, 123), new Color(24, 8, 8), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + offset).getRGB();
        } else {
            rbw = 255;

        }
        return rbw;
    }

    protected static Color getGradientOffset(final Color color1, final Color color2, double offset) {
        if (offset > 1.0) {
            final double left = offset % 1.0;
            final int off = (int)offset;
            offset = ((off % 2 == 0) ? left : (1.0 - left));
        }
        final double inverse_percent = 1.0 - offset;
        final int redPart = (int)(color1.getRed() * inverse_percent + color2.getRed() * offset);
        final int greenPart = (int)(color1.getGreen() * inverse_percent + color2.getGreen() * offset);
        final int bluePart = (int)(color1.getBlue() * inverse_percent + color2.getBlue() * offset);
        return new Color(redPart, greenPart, bluePart);
    }

}
