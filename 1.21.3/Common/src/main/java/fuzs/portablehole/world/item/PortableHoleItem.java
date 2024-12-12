package fuzs.portablehole.world.item;

import fuzs.portablehole.PortableHole;
import fuzs.portablehole.config.ServerConfig;
import fuzs.portablehole.world.level.block.entity.TemporaryHoleBlockEntity;
import fuzs.puzzleslib.api.core.v1.Proxy;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.List;

public class PortableHoleItem extends Item {

    public PortableHoleItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        Player player = context.getPlayer();
        Direction clickedFace = context.getClickedFace();
        if (TemporaryHoleBlockEntity.isValidHolePosition(level, clickedPos)) {
            if (!level.isClientSide) {
                List<BlockPos> positionsInPlane = BlockPos.betweenClosedStream(-1, -1, -1, 1, 1, 1)
                        .filter((BlockPos pos) -> {
                            return clickedFace.getAxis().choose(pos.getX(), pos.getY(), pos.getZ()) == 0;
                        })
                        .map(BlockPos::immutable)
                        .toList();
                for (BlockPos pos : positionsInPlane) {
                    TemporaryHoleBlockEntity.setTemporaryHoleBlock(level, pos.offset(clickedPos),
                            clickedFace.getOpposite(), PortableHole.CONFIG.get(ServerConfig.class).temporaryHoleDepth
                    );
                }
                level.playSound(null, clickedPos.getX(), clickedPos.getY(), clickedPos.getZ(),
                        SoundEvents.ENDERMAN_TELEPORT, SoundSource.NEUTRAL, 0.5F,
                        0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
                );
                player.getCooldowns()
                        .addCooldown(this, PortableHole.CONFIG.get(ServerConfig.class).portableHoleCooldown);
                player.awardStat(Stats.ITEM_USED.get(this));
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.addAll(Proxy.INSTANCE.splitTooltipLines(this.getDescriptionComponent()));
    }

    public Component getDescriptionComponent() {
        return Component.translatable(this.getDescriptionId() + ".description").withStyle(ChatFormatting.GOLD);
    }
}
