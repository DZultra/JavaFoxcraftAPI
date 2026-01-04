package net.dzultra.jfa.responses;

import com.google.gson.annotations.SerializedName;

public record MojangAPIResponse(@SerializedName("id") String uuid, String name) implements Response{}
