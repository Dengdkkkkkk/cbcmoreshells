package com.cainiao1053.cbcmoreshells.items.cannon_combo;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class SingleCannonComboItem extends CannonComboItem {

	public SingleCannonComboItem(Properties properties) {
		super(properties);
	}


	@Override
	protected String getCanonType() {
		return "_single_cannon_";
	}
}



