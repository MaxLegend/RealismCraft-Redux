package ru.legendgamer.Realism.Events.CAP;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import ru.legendgamer.Realism.Capability.WorldCAP.DateProvider;
import ru.legendgamer.Realism.Capability.WorldCAP.IDate;
import ru.legendgamer.Realism.RealismCore.Realism;

public class WorldCAPEventHandler {

	@SubscribeEvent
	public void onWorldDate(AttachCapabilitiesEvent<World> e)
	{
		if (e.getObject() != null)
			e.addCapability(Realism.DATE, new DateProvider()); 
	}

	int count;   
	byte day;
	byte month;
	short year;
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent e)
	{



		count++;
		if (e.phase != e.phase.END) { return; }	    	
		if (e.world != null)
		{
			IDate date = e.world.getCapability(DateProvider.DATE, null);
			if(e.world.getTotalWorldTime() % 24000 == 0 && count % 6 == 0) 
			{//24000
				day = (byte)(date.getDay() + 1);
				date.setDay(day);
			}
			if(e.world.getTotalWorldTime() % 24000 == 0 && count % 6 == 0) 
			{
				if(date.getDay() % 30 == 0 && count % 6 == 0) 
				{
					month = (byte)(date.getMonth() + 1);
					date.setMonth(month);
					date.setDay((byte)0);

				}
			}

			if(count % 6 == 0) {
				switch(date.getMonth()) {
				case 11: 
					if(e.world.getTotalWorldTime() % 24000 == 0 && count % 6 == 0) 
					{
						year = (short)(date.getYear() + 1);
						date.setYear(year);
						date.setMonth((byte)0);
					}
					break;
				default: 
					break;
				}
			}

		}
	}
}
