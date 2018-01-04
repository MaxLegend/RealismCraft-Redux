package ru.legendgamer.Realism.API;
/**
 * API.
 * A small class designed for tying the timer code to the global coordinates of a particular block. Examples of using it in growing trees.
 * Небольшой класс, предназначенный для привязывания таймера кода к мировым координатам конкретного блока. Примеры использования смотрите в растущих деревьях.
 * @author LegendGemer
 */
public class TimerForCoord {
	public int x;
	public int y;
	public int z;
	public int time;

	public TimerForCoord(int x, int y, int z, int time){
		this.x = x;
		this.y= y;
		this.z = z;
		this.time = time;
	}


}

