package ru.legendgamer.Realism.NewInventory.CAPforINV;

import ru.legendgamer.Realism.NewInventory.CustomInventory;

public class CAPCustomInventory implements ICAPCustomInventory {

    //��������� ������, ��� ���������� � ���������� ICAPCustomInventory

    //������� ������ ������ ���������. �� ����� ��������� � ���� ���'�
    public final CustomInventory inventory = new CustomInventory();

    /**
     * �����, ������� ���������� ������ ��������� inventory
     */
    public CustomInventory getInventory(){
        return this.inventory;
    }

    /**
     * �����, ��� ����������� ���������� �� ������� ���������, �������� ��� ������������
     */
    @Override
    public void copyInventory(ICAPCustomInventory inventory) {
        this.inventory.copy(inventory.getInventory());
    }

}