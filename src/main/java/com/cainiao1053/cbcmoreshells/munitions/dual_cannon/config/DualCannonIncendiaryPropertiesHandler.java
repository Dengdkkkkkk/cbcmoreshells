package com.cainiao1053.cbcmoreshells.munitions.dual_cannon.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import rbasamoyai.createbigcannons.munitions.big_cannon.config.BigCannonFuzePropertiesComponent;
import rbasamoyai.createbigcannons.munitions.config.EntityPropertiesTypeHandler;
import rbasamoyai.createbigcannons.munitions.config.components.BallisticPropertiesComponent;
import rbasamoyai.createbigcannons.munitions.config.components.EntityDamagePropertiesComponent;
import rbasamoyai.createbigcannons.munitions.config.components.ExplosionPropertiesComponent;

public class DualCannonIncendiaryPropertiesHandler extends EntityPropertiesTypeHandler<DualCannonIncendiaryProperties> {

	private static final DualCannonIncendiaryProperties DEFAULT = new DualCannonIncendiaryProperties(BallisticPropertiesComponent.DEFAULT,
		EntityDamagePropertiesComponent.DEFAULT, DualCannonPropertiesComponent.DEFAULT, BigCannonFuzePropertiesComponent.DEFAULT,
		ExplosionPropertiesComponent.DEFAULT, 4, DualCannonIncendiaryPropertiesComponent.DEFAULT);

	@Override
	protected DualCannonIncendiaryProperties parseJson(ResourceLocation location, JsonObject obj) throws JsonParseException {
		String id = location.toString();
		BallisticPropertiesComponent ballistics = BallisticPropertiesComponent.fromJson(id, obj);
		EntityDamagePropertiesComponent damage = EntityDamagePropertiesComponent.fromJson(id, obj);
		DualCannonPropertiesComponent dualCannonProperties = DualCannonPropertiesComponent.fromJson(id, obj);
		BigCannonFuzePropertiesComponent fuze = BigCannonFuzePropertiesComponent.fromJson(id, obj);
		ExplosionPropertiesComponent explosion = ExplosionPropertiesComponent.fromJson(id, obj);
		int explosionCooldown = Math.max(-1, getOrWarn(obj, "explosion_cooldown", id, 15, JsonElement::getAsInt));
		DualCannonIncendiaryPropertiesComponent incendiary = DualCannonIncendiaryPropertiesComponent.fromJson(id, obj);
		return new DualCannonIncendiaryProperties(ballistics, damage, dualCannonProperties, fuze, explosion, explosionCooldown, incendiary);
	}

	@Override
	protected DualCannonIncendiaryProperties readPropertiesFromNetwork(EntityType<?> entityType, FriendlyByteBuf buf) {
		BallisticPropertiesComponent ballistics = BallisticPropertiesComponent.fromNetwork(buf);
		EntityDamagePropertiesComponent damage = EntityDamagePropertiesComponent.fromNetwork(buf);
		DualCannonPropertiesComponent dualCannonProperties = DualCannonPropertiesComponent.fromNetwork(buf);
		BigCannonFuzePropertiesComponent fuze = BigCannonFuzePropertiesComponent.fromNetwork(buf);
		ExplosionPropertiesComponent explosion = ExplosionPropertiesComponent.fromNetwork(buf);
		int explosionCooldown = buf.readInt();
		DualCannonIncendiaryPropertiesComponent incendiary = DualCannonIncendiaryPropertiesComponent.fromNetwork(buf);
		return new DualCannonIncendiaryProperties(ballistics, damage, dualCannonProperties, fuze, explosion, explosionCooldown, incendiary);
	}

	@Override
	protected void writePropertiesToNetwork(DualCannonIncendiaryProperties properties, FriendlyByteBuf buf) {
		properties.ballistics().toNetwork(buf);
		properties.damage().toNetwork(buf);
		properties.dualCannonProperties().toNetwork(buf);
		properties.fuze().toNetwork(buf);
		properties.explosion().toNetwork(buf);
		buf.writeInt(properties.explosionCooldown());
		properties.incendiary().toNetwork(buf);
	}

	@Override protected DualCannonIncendiaryProperties getNoPropertiesValue() { return DEFAULT; }

}
