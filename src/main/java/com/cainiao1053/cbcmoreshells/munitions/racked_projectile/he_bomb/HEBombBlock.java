package com.cainiao1053.cbcmoreshells.munitions.racked_projectile.he_bomb;

import com.cainiao1053.cbcmoreshells.CBCMSEntityTypes;
import com.cainiao1053.cbcmoreshells.index.CBCMSMunitionPropertiesHandlers;
import com.cainiao1053.cbcmoreshells.munitions.racked_projectile.GeneralRackedProjectileBlock;
import net.minecraft.world.entity.EntityType;


public class HEBombBlock extends GeneralRackedProjectileBlock<HEBombProjectile> {

	public HEBombBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isBaseFuze() {
		return CBCMSMunitionPropertiesHandlers.RACKED_PROJECTILE.getPropertiesOf(this.getAssociatedEntityType()).fuze().baseFuze();
	}

	@Override
	public int getLifetimeFromBlock() {
		return CBCMSMunitionPropertiesHandlers.RACKED_PROJECTILE.getPropertiesOf(this.getAssociatedEntityType()).lifetime();
	}

	@Override
	public EntityType<? extends HEBombProjectile> getAssociatedEntityType() {
		return CBCMSEntityTypes.HE_BOMB.get();
	}

}
