package fuzs.portablehole;

import fuzs.portablehole.config.ServerConfig;
import fuzs.portablehole.init.ModRegistry;
import fuzs.puzzleslib.config.ConfigHolder;
import fuzs.puzzleslib.core.CoreServices;
import fuzs.puzzleslib.core.ModConstructor;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PortableHole implements ModConstructor {
    public static final String MOD_ID = "portablehole";
    public static final String MOD_NAME = "Portable Hole";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    @SuppressWarnings("Convert2MethodRef")
    public static final ConfigHolder CONFIG = CoreServices.FACTORIES.serverConfig(ServerConfig.class, () -> new ServerConfig());

    @Override
    public void onConstructMod() {
        CONFIG.bakeConfigs(MOD_ID);
        ModRegistry.touch();
    }

    @Override
    public void onLootTableModification(LootTablesModifyContext context) {
        if (context.getId().equals(BuiltInLootTables.STRONGHOLD_CORRIDOR)) {
            context.addLootPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootTableReference.lootTableReference(ModRegistry.STRONGHOLD_CORRIDOR_INJECT_LOOT_TABLE)).build());
        }
    }
}
