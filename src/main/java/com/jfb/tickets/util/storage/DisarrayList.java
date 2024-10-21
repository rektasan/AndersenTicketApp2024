package com.jfb.tickets.util.storage;

import com.jfb.tickets.util.interfaces.Identifiable;

import java.util.Arrays;

import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@ToString
@EqualsAndHashCode
public class DisarrayList<T> implements Identifiable {

  private final int CLASS_ID = generateClassId();
  private final int INITIAL_CAPACITY = 10;
  private final int GROWTH_FACTOR = 2;

  private Object[] elementArray;
  @Getter
  private int size;
  private int currentCapacity;

  public DisarrayList() {
    currentCapacity = INITIAL_CAPACITY;
    elementArray = new Object[currentCapacity];
    size = 0;
  }

  public void put(T o) {
    if (size == currentCapacity) {
      grow();
    }
    elementArray[size] = o;
    size++;
  }

  public T get(int index) {
    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
    }
    return (T) elementArray[index];
  }

  public void delete(int index) {
    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
    }
    for (int i = index; i < size - 1; i++) {
      elementArray[i] = elementArray[i + 1];
    }
    elementArray[size - 1] = null;
    size--;
  }

  public void grow() {
    currentCapacity *= GROWTH_FACTOR;
    elementArray = Arrays.copyOf(elementArray, currentCapacity);
  }

  @Override
  public int getClassId() {
    return this.CLASS_ID;
  }
}
