package com.cainiao1053.cbcmoreshells.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.state.BlockState;


public class LootBarrelBlock extends BarrelBlock {

	public LootBarrelBlock(Properties p_49046_) {
		super(p_49046_);
	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			level.removeBlockEntity(pos);
		}
		super.onRemove(state, level, pos, newState, isMoving);
	}
}



