package fuzs.portablehole;

import fuzs.portablehole.config.ClientConfig;
import fuzs.portablehole.config.ServerConfig;
import fuzs.portablehole.init.ModRegistry;
import fuzs.puzzleslib.api.config.v3.ConfigHolder;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.context.BuildCreativeModeTabContentsContext;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import fuzs.puzzleslib.api.event.v1.server.LootTableLoadEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;
import java.util.function.IntPredicate;

public class PortableHole implements ModConstructor {
    public static final String MOD_ID = "portablehole";
    public static final String MOD_NAME = "Portable Hole";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public static final ConfigHolder CONFIG = ConfigHolder.builder(MOD_ID)
            .client(ClientConfig.class)
            .server(ServerConfig.class);

    @Override
    public void onConstructMod() {
        ModRegistry.bootstrap();
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        LootTableLoadEvents.MODIFY.register(
                (ResourceLocation identifier, Consumer<LootPool> addPool, IntPredicate removePool) -> {
                    if (BuiltInLootTables.STRONGHOLD_CORRIDOR.equals(identifier)) {
                        addPool.accept(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(NestedLootTable.lootTableReference(
                                        ModRegistry.STRONGHOLD_CORRIDOR_INJECT_LOOT_TABLE))
                                .build());
                    }
                });
    }

    @Override
    public void onBuildCreativeModeTabContents(BuildCreativeModeTabContentsContext context) {
        context.registerBuildListener(CreativeModeTabs.TOOLS_AND_UTILITIES, (itemDisplayParameters, output) -> {
            output.accept(ModRegistry.PORTABLE_HOLE_ITEM.value());
        });
    }

    public static ResourceLocation id(String path) {
        return ResourceLocationHelper.fromNamespaceAndPath(MOD_ID, path);
    }
}
