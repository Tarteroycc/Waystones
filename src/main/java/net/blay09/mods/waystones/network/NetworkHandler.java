package net.blay09.mods.waystones.network;

import net.blay09.mods.waystones.Waystones;
import net.blay09.mods.waystones.network.handler.HandlerConfig;
import net.blay09.mods.waystones.network.handler.HandlerFreeWarpReturn;
import net.blay09.mods.waystones.network.handler.HandlerTeleportEffect;
import net.blay09.mods.waystones.network.handler.HandlerWarpStone;
import net.blay09.mods.waystones.network.handler.HandlerWaystoneName;
import net.blay09.mods.waystones.network.handler.HandlerWaystones;
import net.blay09.mods.waystones.network.message.MessageConfig;
import net.blay09.mods.waystones.network.message.MessageTeleportEffect;
import net.blay09.mods.waystones.network.message.MessageWarpReturn;
import net.blay09.mods.waystones.network.message.MessageWarpStone;
import net.blay09.mods.waystones.network.message.MessageWaystoneName;
import net.blay09.mods.waystones.network.message.MessageWaystones;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NetworkHandler {

	public static final SimpleNetworkWrapper channel = NetworkRegistry.INSTANCE.newSimpleChannel(Waystones.MOD_ID);

	public static void init() {
		channel.registerMessage(HandlerConfig.class, MessageConfig.class, 0, Side.CLIENT);
		channel.registerMessage(HandlerWaystones.class, MessageWaystones.class, 1, Side.CLIENT);
		channel.registerMessage(HandlerFreeWarpReturn.class, MessageWarpReturn.class, 2, Side.SERVER);
		channel.registerMessage(HandlerWaystoneName.class, MessageWaystoneName.class, 3, Side.SERVER);
		channel.registerMessage(HandlerWarpStone.class, MessageWarpStone.class, 4, Side.SERVER);
		channel.registerMessage(HandlerTeleportEffect.class, MessageTeleportEffect.class, 5, Side.CLIENT);
	}

	public static IThreadListener getThreadListener(MessageContext ctx) {
		return ctx.side == Side.SERVER ? (WorldServer) ctx.getServerHandler().playerEntity.worldObj : getClientThreadListener();
	}

	@SideOnly(Side.CLIENT)
	public static IThreadListener getClientThreadListener() {
		return Minecraft.getMinecraft();
	}
}