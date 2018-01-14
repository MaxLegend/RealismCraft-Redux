package ru.legendgamer.Realism.Command;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.FMLCommonHandler;
import ru.legendgamer.Realism.Capability.PlayerCAP.IPlayerCap;
import ru.legendgamer.Realism.Capability.PlayerCAP.PlayerCapProvider;
import ru.legendgamer.Realism.Capability.WorldCAP.DateProvider;
import ru.legendgamer.Realism.Capability.WorldCAP.IDate;

public class RealismCommand extends CommandBase {

	public RealismCommand(){}

	@Override
	public String getName() {
		return "real";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "'/real <command> <nickname> <amount> [args]'. Check '/real info' to get list of all available commands.";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		World world = FMLCommonHandler.instance().getMinecraftServerInstance().getServer().getEntityWorld();
		IDate cap = world.getCapability(DateProvider.DATE, null);

		if(args.length == 1 && args[0].equals("info")) {
			EntityPlayer player = getCommandSenderAsPlayer(sender);
			if(player != null){
				player.sendMessage(new TextComponentString("/real set <nickname> month <amount> - set month"));
				player.sendMessage(new TextComponentString("/real set <nickname> year <amount> - set year"));
				player.sendMessage(new TextComponentString("/real set <nickname> day <amount> - set day"));
				player.sendMessage(new TextComponentString("/real set <nickname> temp <amount> - set temp body"));
				player.sendMessage(new TextComponentString("/real set <nickname> weight <amount> - set weight"));
				player.sendMessage(new TextComponentString("/real set <nickname> totaltime <amount> - reset calendar. <amount> - may be any"));
			
			}


		}
		/*	if(args.length >= 1 && args.length < 2) {
			String command = args[0];
			EntityPlayer player = getCommandSenderAsPlayer(sender);
			BlockPos pos = player.getPosition();
			Biome biome = world.getBiome(pos);
			if(command == "enablesnow") {
				String value = args[0];  

					cap.setEnableSnow(Boolean.parseBoolean(value));

			}
		}
		 */

		if(args.length > 2){

			String nickname = args[1];
			String command = args[0];
			String type = args[2];
			EntityPlayer entityplayer = (EntityPlayer)getEntity(server, sender, nickname, EntityPlayer.class);
			EntityPlayer player = getCommandSenderAsPlayer(sender);
			BlockPos pos = entityplayer.getPosition();
			Biome biome = world.getBiome(pos);
			IPlayerCap capplayer = player.getCapability(PlayerCapProvider.LEVEL_CAP, null);
			switch (command) {
			case "set": {
				String value = args[3];    
				if (type.equals("day")) { 
					cap.setDay(Byte.parseByte(value)); 
					player.sendMessage(new TextComponentString("Set the Day: " + Byte.parseByte(value)));
				}
				if (type.equals("month")) {
					cap.setMonth(Byte.parseByte(value));
					player.sendMessage(new TextComponentString("Set the Month: " + Byte.parseByte(value)));
				}
				if (type.equals("year")) { 
					cap.setYear(Short.parseShort(value));
					player.sendMessage(new TextComponentString("Set the Year: " + Short.parseShort(value)));
				}
				if (type.equals("temp")) { 
					capplayer.setTempBody(Float.parseFloat(value));
					player.sendMessage(new TextComponentString("Set TempBody: " + Float.parseFloat(value)));
				}
				if (type.equals("weight")) { 
					capplayer.setWeight(Float.parseFloat(value));
					player.sendMessage(new TextComponentString("Set Weight: " + Float.parseFloat(value)));
				}
				if (type.equals("totaltime")) {
					world.setTotalWorldTime(0);
					world.setWorldTime(0);
					cap.setMonth((byte)0);
					cap.setYear((byte)0);
					cap.setDay((byte)0); 
					player.sendMessage(new TextComponentString("Calendar Reset."));
				}
				break;
			}
			default:
				break;
			}
		}
		try {
		}
		catch(Exception e)
		{
			throw new WrongUsageException(this.getUsage(sender), new Object[0]);
		}

	}


	@Override
	public int getRequiredPermissionLevel()
	{
		return 2;
	}
}