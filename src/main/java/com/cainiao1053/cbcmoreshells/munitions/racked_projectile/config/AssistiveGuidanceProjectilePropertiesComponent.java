package com.cainiao1053.cbcmoreshells.munitions.racked_projectile.config;

import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;

public record AssistiveGuidanceProjectilePropertiesComponent(int guidanceStart, float maxG, float guidanceControl, int searchLength, int searchWidth, float scanAngle) {

	public static final AssistiveGuidanceProjectilePropertiesComponent DEFAULT = new AssistiveGuidanceProjectilePropertiesComponent(40, 0.1f, 0.02f, 100, 32, 0.707f);

	public static AssistiveGuidanceProjectilePropertiesComponent fromJson(String id, JsonObject obj) {
		int guidanceStart = Math.max(0, GsonHelper.getAsInt(obj, "guidance_start", 0));
		float maxG = Math.max(0, GsonHelper.getAsFloat(obj, "maxG", 0.1f));
		float guidanceControl = Math.max(0, GsonHelper.getAsFloat(obj, "guidance_control", 0.01f));
		int searchLength = Math.max(0, GsonHelper.getAsInt(obj, "search_length", 64));
		int searchWidth = Math.max(0, GsonHelper.getAsInt(obj, "search_width", 32));
		float scanAngle = Math.max(0, GsonHelper.getAsFloat(obj, "scan_angle", 0.7f));
		return new AssistiveGuidanceProjectilePropertiesComponent(guidanceStart, maxG, guidanceControl, searchLength, searchWidth, scanAngle);
	}

	public static AssistiveGuidanceProjectilePropertiesComponent fromNetwork(FriendlyByteBuf buf) {
		return new AssistiveGuidanceProjectilePropertiesComponent(buf.readInt(), buf.readFloat(), buf.readFloat(), buf.readInt(), buf.readInt(), buf.readFloat());
	}

	public void toNetwork(FriendlyByteBuf buf) {
		buf.writeInt(guidanceStart)
		.writeFloat(maxG)
		.writeFloat(guidanceControl)
		.writeInt(searchLength)
		.writeInt(searchWidth)
		.writeFloat(scanAngle);
	}

}
