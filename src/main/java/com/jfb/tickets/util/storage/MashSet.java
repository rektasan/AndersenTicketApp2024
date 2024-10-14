package com.jfb.tickets.util.storage;

import com.jfb.tickets.util.interfaces.Identifiable;
import java.util.HashMap;

public class MashSet<T> implements Identifiable {

  private final int CLASS_ID = generateClassId();
  static final Object PRESENT = new Object();

  private HashMap<T, Object> internalMap;

  public MashSet() {
    internalMap = new HashMap<>();
  }

  public void put(T o) {
    internalMap.put(o, PRESENT);
  }

  public void delete(T o) {
    internalMap.remove(o);
  }

  public boolean contains(T o) {
    return internalMap.containsKey(o);
  }

  public Iterable<T> iterator() {
    return internalMap.keySet();
  }

  public int size() {
    return internalMap.size();
  }

  @Override
  public int getClassId() {
    return this.CLASS_ID;
  }
}
