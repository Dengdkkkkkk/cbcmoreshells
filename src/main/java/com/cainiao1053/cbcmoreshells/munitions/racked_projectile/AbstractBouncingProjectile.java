package com.cainiao1053.cbcmoreshells.munitions.racked_projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import rbasamoyai.createbigcannons.munitions.ProjectileContext;
import rbasamoyai.createbigcannons.munitions.config.DimensionMunitionPropertiesHandler;
import rbasamoyai.createbigcannons.munitions.config.FluidDragHandler;
import rbasamoyai.createbigcannons.munitions.config.components.BallisticPropertiesComponent;
import rbasamoyai.createbigcannons.network.ClientboundPlayBlockHitEffectPacket;
import rbasamoyai.createbigcannons.utils.CBCUtils;

public abstract class AbstractBouncingProjectile extends FuzedRackedProjectile {

	public AbstractBouncingProjectile(EntityType<? extends AbstractBouncingProjectile> type, Level level) {
		super(type, level);
	}

	@Override
	protected boolean onImpactFluid(ProjectileContext projectileContext, BlockState blockState, FluidState fluidState, Vec3 impactPos, BlockHitResult fluidHitResult) {
		Vec3 pos = this.position();
		Vec3 accel = this.getForces(this.position(), this.getDeltaMovement());
		Vec3 curVel = this.getDeltaMovement().add(accel);
		Vec3 normal = CBCUtils.getSurfaceNormalVector(this.level(), fluidHitResult);
		double incidence = Math.max((double)0.0F, curVel.normalize().dot(normal.reverse()));
		double velMag = curVel.length();
		double incidentVel = velMag * incidence;
		double projectileDeflection = (double)this.getBallisticProperties().deflection();
		boolean criticalAngle = projectileDeflection > 0.01 && incidence <= projectileDeflection;
		boolean bounced = criticalAngle && velMag > (initialVelocity()+0.8);
		if (bounced) {
			this.setDeltaMovement(fluidHitResult.getLocation().subtract(pos));
			double elasticity = (double)1.8F;
			this.nextVelocity = curVel.subtract(normal.scale(normal.dot(curVel) * elasticity));
		}

		if (!this.level().isClientSide) {
			Vec3 effectNormal = bounced ? normal.scale(incidentVel) : curVel.reverse();
			Vec3 fluidExplosionPos = fluidHitResult.getLocation();
			projectileContext.addPlayedEffect(new ClientboundPlayBlockHitEffectPacket(blockState, this.getType(), bounced, true, fluidExplosionPos.x, fluidExplosionPos.y, fluidExplosionPos.z, (float)effectNormal.x, (float)effectNormal.y, (float)effectNormal.z));
		}

		return bounced;
	}


	@Override
	protected double getDragForce() {
		BallisticPropertiesComponent properties = this.getBallisticProperties();
		double vel = this.getDeltaMovement().length();
		double formDrag = properties.drag();
		double density = DimensionMunitionPropertiesHandler.getProperties(this.level()).dragMultiplier();

		FluidState fluidState = this.level().getFluidState(this.blockPosition());
		if (!fluidState.isEmpty()) {
			density += 0.05*FluidDragHandler.getFluidDrag(fluidState);
		}

		double drag = formDrag * density * vel;
		if (properties.isQuadraticDrag()) {
			drag *= vel;
		}

		return Math.min(drag, vel);
	}
}
