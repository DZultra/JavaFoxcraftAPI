package net.dzultra.jfa.responses;

import com.google.gson.annotations.SerializedName;

public record ErrorResponse(@SerializedName("error") String response) implements Response {}
