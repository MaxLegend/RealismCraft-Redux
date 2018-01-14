package ru.legendgamer.Realism.Capability.WorldCAP;

public final class Date implements IDate
{
	private boolean isSnow;
    private short year;
    private byte day, week, month;

    @Override
    public void addDay(byte day)
    {
        if (this.day <= 30)
            this.day += day;
    }

    @Override
    public void addWeek(byte week)
    {
        if (this.week <= 4)
            this.week += week;
    }

    @Override
    public void addMonth(byte month)
    {
        if (this.month <= 12)
            this.month += month;
    }

    @Override
    public void addYear(short year)
    {
        this.year += year;
    }

    @Override
    public void remDay(byte day)
    {
        if (this.day != 0)
            this.day -= day;
    }

    @Override
    public void remWeek(byte week)
    {
        if (this.week != 0)
            this.week -= week;
    }

    @Override
    public void remMonth(byte month)
    {
        if (this.month != 0)
            this.month -= month;
    }

    @Override
    public void setDay(byte day)
    {
        this.day = day;
    }

    @Override
    public void setWeek(byte week)
    {
        this.week = week;
    }

    @Override
    public void setMonth(byte month)
    {
        this.month = month;
    }

    @Override
    public void setYear(short year)
    {
        this.year = year;
    }

    @Override
    public byte getDay()
    {
        return day;
    }

    @Override
    public byte getWeek()
    {
        return week;
    }

    @Override
    public byte getMonth()
    {
        return month;
    }

    @Override
    public short getYear()
    {
        return year;
    }

	@Override
	public boolean setEnableSnow(boolean isSnow) {
		
		return this.isSnow = isSnow;
	}

	@Override
	public boolean getEnableSnow() {

		return isSnow;
	}
}