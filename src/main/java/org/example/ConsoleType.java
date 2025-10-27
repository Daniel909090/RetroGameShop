package org.example;

public enum ConsoleType {
    PLAYSTATION("🎮"),
    XBOX("🧩"),
    NINTENDO("🍄"),
    SEGA("🕹️"),
    PC("💻");

    private final String emoji;

    // Constructor for enum
    ConsoleType(String emoji) {
        this.emoji = emoji;
    }

    // Getter method
    public String getEmoji() {
        return emoji;
    }
}
