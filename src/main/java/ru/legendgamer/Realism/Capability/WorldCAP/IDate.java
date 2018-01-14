package ru.legendgamer.Realism.Capability.WorldCAP;

public interface IDate
{
	
    void addDay(byte day);
    void addWeek(byte week);
    void addMonth(byte month);
    void addYear(short year);

    void remDay(byte day);
    void remWeek(byte week);
    void remMonth(byte month);


    void setDay(byte day);
    void setWeek(byte week);
    void setMonth(byte month);
    void setYear(short year);

    
    byte getDay();
    byte getWeek();
    byte getMonth();
    short getYear();
    
    boolean setEnableSnow(boolean isSnow);
    boolean getEnableSnow();
}