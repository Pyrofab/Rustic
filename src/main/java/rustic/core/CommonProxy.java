package rustic.core;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Map;

import rustic.client.GuiProxy;
import rustic.client.renderer.LayerIronSkin;
import rustic.common.Config;
import rustic.common.blocks.ModBlocks;
import rustic.common.crafting.Recipes;
import rustic.common.items.ModItems;
import rustic.common.network.PacketHandler;
import rustic.common.potions.PotionTypesRustic;
import rustic.common.world.WorldGeneratorRustic;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
	
	public static Configuration config;

	public void preInit(FMLPreInitializationEvent event) {
		
		PacketHandler.registerMessages();
		
		
		File directory = event.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "rustic.cfg"));
        Config.readConfig();
        
        ModBlocks.init();
        ModItems.init();
        
        PotionTypesRustic.init();

        Recipes.init();
        
        GameRegistry.registerWorldGenerator(new WorldGeneratorRustic(), 0);
        
    }

    public void init(FMLInitializationEvent event) {
    	NetworkRegistry.INSTANCE.registerGuiHandler(Rustic.instance, new GuiProxy());
    }

    public void postInit(FMLPostInitializationEvent event) {
    	if (config.hasChanged()) {
            config.save();
        }
    	
    }
	
}