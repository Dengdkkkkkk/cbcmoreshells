package com.cainiao1053.cbcmoreshells.munitions.racked_projectile.depth_charge;

import com.cainiao1053.cbcmoreshells.CBCMSEntityTypes;
import com.cainiao1053.cbcmoreshells.index.CBCMSMunitionPropertiesHandlers;
import com.cainiao1053.cbcmoreshells.munitions.racked_projectile.GeneralRackedProjectileBlock;
import net.minecraft.world.entity.EntityType;


public class DepthChargeBlock extends GeneralRackedProjectileBlock<DepthChargeProjectile> {

	public DepthChargeBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isBaseFuze() {
		return CBCMSMunitionPropertiesHandlers.RACKED_SHRAPNEL_PROJECTILE.getPropertiesOf(this.getAssociatedEntityType()).fuze().baseFuze();
	}

	@Override
	public int getLifetimeFromBlock() {
		return CBCMSMunitionPropertiesHandlers.RACKED_SHRAPNEL_PROJECTILE.getPropertiesOf(this.getAssociatedEntityType()).lifetime();
	}

	@Override
	public EntityType<? extends DepthChargeProjectile> getAssociatedEntityType() {
		return CBCMSEntityTypes.DEPTH_CHARGE.get();
	}

}
