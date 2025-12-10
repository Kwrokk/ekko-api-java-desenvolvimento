package com.openphilosophy.api.models.post;

public enum PostReaction {
    LIKE("\uD83D\uDC4D"),
    HEART("❤\uFE0F"),
    DISLIKE("\uD83D\uDC4E"),
    WOW("\uD83D\uDE2E");

    private String emoji;

    private PostReaction(String emoji) {
        this.emoji = emoji;
    }
}
