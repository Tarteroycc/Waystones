package net.blay09.mods.waystones.handler;

import net.blay09.mods.balm.api.event.PlayerLoginEvent;
import net.blay09.mods.waystones.api.IWaystone;
import net.blay09.mods.waystones.api.WaystoneTypes;
import net.blay09.mods.waystones.core.PlayerWaystoneManager;
import net.blay09.mods.waystones.core.WaystoneManager;
import net.blay09.mods.waystones.core.WaystoneSyncManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;

public class LoginHandler {

    public static void onPlayerLogin(PlayerLoginEvent event) {
        ServerPlayer player = event.getPlayer();
        // Introduce all global waystones to this player
        List<IWaystone> globalWaystones = WaystoneManager.get(player.server).getGlobalWaystones();
        for (IWaystone waystone : globalWaystones) {
            if (!PlayerWaystoneManager.isWaystoneActivated(player, waystone)) {
                PlayerWaystoneManager.activateWaystone(player, waystone);
            }
        }

        WaystoneSyncManager.sendSortingIndex(player);
        WaystoneSyncManager.sendActivatedWaystones(player);
        WaystoneSyncManager.sendWaystonesOfType(WaystoneTypes.WARP_PLATE, player);
        WaystoneSyncManager.sendWaystonesOfType(WaystoneTypes.SHARESTONE, player);
        for (ResourceLocation dyedSharestone : WaystoneTypes.DYED_SHARESTONES) {
            WaystoneSyncManager.sendWaystonesOfType(dyedSharestone, player);
        }
        WaystoneSyncManager.sendWaystoneCooldowns(player);
    }

}
