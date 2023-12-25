package net.blay09.mods.waystones.api.event;

import net.blay09.mods.balm.api.event.BalmEvent;
import net.blay09.mods.waystones.api.WaystoneTeleportContext;
import net.blay09.mods.waystones.api.TeleportDestination;
import net.blay09.mods.waystones.api.cost.Cost;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public abstract class WaystoneTeleportEvent extends BalmEvent {

    public static class Pre extends WaystoneTeleportEvent {
        private final WaystoneTeleportContext context;
        private EventResult dimensionalTeleportResult = EventResult.DEFAULT;
        private EventResult consumeItemResult = EventResult.DEFAULT;

        public Pre(WaystoneTeleportContext context) {
            this.context = context;
        }

        public WaystoneTeleportContext getContext() {
            return context;
        }

        public Cost getExperienceCost() {
            return context.getExperienceCost();
        }

        public void setExperienceCost(Cost cost) {
            context.setExperienceCost(cost);
        }

        public int getCooldown() {
            return context.getCooldown();
        }

        public void setCooldown(int cooldown) {
            context.setCooldown(cooldown);
        }

        public TeleportDestination getDestination() {
            return context.getDestination();
        }

        public void setDestination(ServerLevel level, Vec3 location, Direction direction) {
            context.setDestination(new TeleportDestination(level, location, direction));
        }

        public void addAdditionalEntity(Entity additionalEntity) {
            context.addAdditionalEntity(additionalEntity);
        }

        public EventResult getDimensionalTeleportResult() {
            return dimensionalTeleportResult;
        }

        public void setDimensionalTeleportResult(EventResult dimensionalTeleportResult) {
            this.dimensionalTeleportResult = dimensionalTeleportResult;
        }

        public EventResult getConsumeItemResult() {
            return consumeItemResult;
        }

        public void setConsumeItemResult(EventResult consumeItemResult) {
            this.consumeItemResult = consumeItemResult;
        }
    }

    public static class Post extends WaystoneTeleportEvent {
        private final WaystoneTeleportContext context;
        private final List<Entity> teleportedEntities;

        public Post(WaystoneTeleportContext context, List<Entity> teleportedEntities) {
            this.context = context;
            this.teleportedEntities = teleportedEntities;
        }

        /**
         * The context that was used for this teleport. Changes made at this point are ignored.
         */
        public WaystoneTeleportContext getContext() {
            return context;
        }

        public List<Entity> getTeleportedEntities() {
            return teleportedEntities;
        }
    }

}