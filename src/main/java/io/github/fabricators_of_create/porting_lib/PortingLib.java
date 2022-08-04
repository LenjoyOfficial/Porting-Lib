package io.github.fabricators_of_create.porting_lib;

import io.github.fabricators_of_create.porting_lib.loot.LootTableIdCondition;
import io.github.fabricators_of_create.porting_lib.util.CanToolPerformAction;
import net.minecraft.core.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.fabricators_of_create.porting_lib.attributes.PortingLibAttributes;
import io.github.fabricators_of_create.porting_lib.crafting.CraftingHelper;
import io.github.fabricators_of_create.porting_lib.data.ConditionalRecipe;
import io.github.fabricators_of_create.porting_lib.transfer.TransferUtil;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemItemStorages;
import io.github.fabricators_of_create.porting_lib.util.ServerLifecycleHooks;
import io.github.fabricators_of_create.porting_lib.util.TierSortingRegistry;
import io.github.fabricators_of_create.porting_lib.util.TrueCondition;
import io.github.fabricators_of_create.porting_lib.util.PortingHooks;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;

public class PortingLib implements ModInitializer {
	public static final String ID = "porting_lib";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	@Override
	public void onInitialize() {
		ServerLifecycleHooks.init();
		PortingLibAttributes.init();
		TierSortingRegistry.init();
		ConditionalRecipe.init();
		ItemItemStorages.init();
		TransferUtil.initApi();
		CraftingHelper.init();
		TrueCondition.init();
		PortingHooks.init();
		// can be used to force all mixins to apply
		// MixinEnvironment.getCurrentEnvironment().audit();

		Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation("forge:loot_table_id"), LootTableIdCondition.LOOT_TABLE_ID);
		Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation("forge:can_tool_perform_action"), CanToolPerformAction.LOOT_CONDITION_TYPE);
	}

	public static ResourceLocation id(String path) {
		return new ResourceLocation(ID, path);
	}
}
