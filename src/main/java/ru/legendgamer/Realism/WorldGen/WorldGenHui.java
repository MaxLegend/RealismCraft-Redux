package ru.legendgamer.Realism.WorldGen;

import java.util.Random;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.IWorldGenerator;
import ru.legendgamer.Realism.RealismCore.Realism;

public class WorldGenHui implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

		switch (world.provider.getDimension()){
		case 0:
			generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
			break;
		}
	}
	public void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider){		 
		generateSalt(world, random, chunkX, chunkZ);
	}
	private void generateSalt(World world, Random random, int chunkX, int chunkZ){
		 int x = (chunkX << 4) + 8;
		 int z = (chunkZ << 4) + 8;
		 int y = 100;
		 BlockPos position = new BlockPos(x, y, z);
		WorldServer worldServer = (WorldServer)world;
		MinecraftServer minecraftServer = world.getMinecraftServer();
		TemplateManager templateManager = worldServer.getStructureTemplateManager();
		Template template = templateManager.get(minecraftServer, new ResourceLocation(Realism.MODID + ":hui"));
		PlacementSettings settings = new PlacementSettings();
		template.addBlocksToWorld(world, position, settings);
	}

}