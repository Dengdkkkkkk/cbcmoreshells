package com.cainiao1053.cbcmoreshells.munitions.dual_cannon.normal_apbc_shell;

import com.cainiao1053.cbcmoreshells.CBCMSEntityTypes;
import com.cainiao1053.cbcmoreshells.index.CBCMSMunitionPropertiesHandlers;
import com.cainiao1053.cbcmoreshells.munitions.dual_cannon.GeneralDualCannonProjectileBlock;
import net.minecraft.world.entity.EntityType;


public class NormalAPBCShellBlock extends GeneralDualCannonProjectileBlock<NormalAPBCShellProjectile> {

	public NormalAPBCShellBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isBaseFuze() {
		return CBCMSMunitionPropertiesHandlers.DUAL_CANNON_PROPERTIES.getPropertiesOf(this.getAssociatedEntityType()).fuze().baseFuze();
	}

//	@Override
//	public int getLifetimeFromBlock() {
//		return CBCMSMunitionPropertiesHandlers.RACKED_PROJECTILE.getPropertiesOf(this.getAssociatedEntityType()).lifetime();
//	}

	@Override
	public EntityType<? extends NormalAPBCShellProjectile> getAssociatedEntityType() {
		return CBCMSEntityTypes.NORMAL_APBC_SHELL.get();
	}

}
