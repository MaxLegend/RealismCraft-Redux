package ru.legendgamer.Realism.Events;

import net.minecraftforge.common.MinecraftForge;
import ru.legendgamer.Realism.Events.WaterBarEvent.EventDrinkBar;
import ru.legendgamer.Realism.Events.WaterBarEvent.PlayerCAPEventHandler;

public class RegEvents {
	public static class Server
	{
		public Server()
		{
			register(new PlayerCAPEventHandler());
		}
	}
	public static class Client
	{
		public Client()
		{
			register(new EventDrinkBar());
		}
	}
	static void register(Object object) {
		MinecraftForge.EVENT_BUS.register(object);
	}

}
