package com.example.demo;

import javafx.scene.paint.Color;

public class ColorConverter {
    public static Color hexToColor(String hex) {
        return Color.web(hex);
    }
}
