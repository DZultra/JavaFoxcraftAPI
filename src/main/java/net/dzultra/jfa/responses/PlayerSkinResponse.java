package net.dzultra.jfa.responses;

import org.jetbrains.annotations.Nullable;

public record PlayerSkinResponse(byte @Nullable [] png) implements Response{}
