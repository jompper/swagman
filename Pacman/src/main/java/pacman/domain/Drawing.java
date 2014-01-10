/*
 * Pacman - Drawing
 * 21.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.domain;

import java.awt.Graphics;

/**
 * For objects that should be drawn in a way or another
 * @author Joni
 */
public interface Drawing {
    /**
     * Drawn almost anything with
     * @param g Graphics
     */
    public void draw(Graphics g);
}
