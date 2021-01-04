package com.wildcard.buddycards.items;

import com.wildcard.buddycards.BuddyCards;
import com.wildcard.buddycards.container.BinderContainer;
import com.wildcard.buddycards.inventory.BinderInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BinderItem extends Item {
    public BinderItem() {
        super(new Item.Properties().group(BuddyCards.TAB).maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        if(playerIn instanceof ServerPlayerEntity) {
            //Open the GUI on server side
            NetworkHooks.openGui((ServerPlayerEntity) playerIn, new SimpleNamedContainerProvider(
                    (id, playerInventory, entity) -> new BinderContainer(id, playerIn.inventory, new BinderInventory())
                    , playerIn.getHeldItem(handIn).getDisplayName()));
        }
        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }
}
