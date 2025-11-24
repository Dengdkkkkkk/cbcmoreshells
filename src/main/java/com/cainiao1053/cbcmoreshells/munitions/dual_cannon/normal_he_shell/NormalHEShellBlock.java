package com.cainiao1053.cbcmoreshells.munitions.dual_cannon.normal_he_shell;

import com.cainiao1053.cbcmoreshells.CBCMSEntityTypes;
import com.cainiao1053.cbcmoreshells.index.CBCMSMunitionPropertiesHandlers;
import com.cainiao1053.cbcmoreshells.munitions.dual_cannon.GeneralDualCannonProjectileBlock;
import net.minecraft.world.entity.EntityType;


public class NormalHEShellBlock extends GeneralDualCannonProjectileBlock<NormalHEShellProjectile> {

	public NormalHEShellBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isBaseFuze() {
		return CBCMSMunitionPropertiesHandlers.RACKED_PROJECTILE.getPropertiesOf(this.getAssociatedEntityType()).fuze().baseFuze();
	}

//	@Override
//	public int getLifetimeFromBlock() {
//		return CBCMSMunitionPropertiesHandlers.RACKED_PROJECTILE.getPropertiesOf(this.getAssociatedEntityType()).lifetime();
//	}

	@Override
	public EntityType<? extends NormalHEShellProjectile> getAssociatedEntityType() {
		return CBCMSEntityTypes.NORMAL_HE_SHELL.get();
	}

}
