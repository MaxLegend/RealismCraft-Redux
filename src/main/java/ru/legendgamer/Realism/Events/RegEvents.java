package ru.legendgamer.Realism.Events;

import net.minecraftforge.common.MinecraftForge;
import ru.legendgamer.Realism.Events.CAP.PlayerCAPEventHandler;
import ru.legendgamer.Realism.Events.CAP.WorldCAPEventHandler;
import ru.legendgamer.Realism.Events.Calendar.CalendarRenderEvent;
import ru.legendgamer.Realism.Events.GameEvents.HandAndNoItemEvent;
import ru.legendgamer.Realism.Events.GameEvents.IsSleepingNightEvent;
import ru.legendgamer.Realism.Events.GameEvents.RenderEvents.LayerEvent;
import ru.legendgamer.Realism.Events.SystemWeight.EventWeight;
import ru.legendgamer.Realism.Events.SystemWeight.PlayerWeight;
import ru.legendgamer.Realism.Events.Thermometr.ThermometrRenderEvent;
import ru.legendgamer.Realism.Events.WaterBarEvent.EventDrinkBar;
import ru.legendgamer.Realism.RealismCore.Particle.ParticleEvent;

public class RegEvents {
	public static class Server
	{
		public Server()
		{
			register(new PlayerCAPEventHandler());
			register(new WorldCAPEventHandler());
			register(new SeasonEventer());
			register(new PlayerWeight());
			register(new EventWeight());
			register(new DamageFoodEvent());
		
			register(new IsSleepingNightEvent());
			
		}
	}
	public static class Client
	{
		public Client()
		{
			register(new HandAndNoItemEvent());
			register(new LayerEvent());
			register(new CalendarRenderEvent());
			register(new ParticleEvent());
		//	register(new CancelVanillaInv());
			register(new EventDrinkBar());
			register(new ThermometrRenderEvent());
		}
	}
	static void register(Object object) {
		MinecraftForge.EVENT_BUS.register(object);
	}

}
