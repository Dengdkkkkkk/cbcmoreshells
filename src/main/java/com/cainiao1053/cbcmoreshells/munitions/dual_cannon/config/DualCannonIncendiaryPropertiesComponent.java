package com.cainiao1053.cbcmoreshells.munitions.dual_cannon.config;

import static rbasamoyai.createbigcannons.munitions.config.PropertiesTypeHandler.getOrWarn;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;

public record DualCannonIncendiaryPropertiesComponent(float fireChance, int fireRange) {

	public static final DualCannonIncendiaryPropertiesComponent DEFAULT = new DualCannonIncendiaryPropertiesComponent(0.1f,3);

	public static DualCannonIncendiaryPropertiesComponent fromJson(String id, JsonObject obj) {
		float fireChance = Math.max(0, getOrWarn(obj, "fire_chance", id, 0.1f, JsonElement::getAsFloat));
		int fireRange = Math.max(0, getOrWarn(obj, "fire_range", id, 3, JsonElement::getAsInt));
		return new DualCannonIncendiaryPropertiesComponent(fireChance, fireRange);
	}

	public static DualCannonIncendiaryPropertiesComponent fromNetwork(FriendlyByteBuf buf) {
		return new DualCannonIncendiaryPropertiesComponent(buf.readFloat(), buf.readInt());
	}

	public void toNetwork(FriendlyByteBuf buf) {
		buf.writeFloat(this.fireChance);
		buf.writeInt(this.fireRange);
	}

}
