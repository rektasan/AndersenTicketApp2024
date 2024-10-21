package com.jfb.tickets.util.storage;

import com.jfb.tickets.util.interfaces.Identifiable;
import java.util.LinkedList;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class MashSet<T> implements Identifiable {

  private final int classId = generateClassId();
  private static final float LOAD_FACTOR = 0.75f;
  private static final int INITIAL_CAPACITY = 16;

  private LinkedList<T>[] buckets;
  private int size;
  private int capacity;

  public MashSet() {
    this.capacity = INITIAL_CAPACITY;
    this.buckets = new LinkedList[capacity];
    this.size = 0;
  }

  private int hash(T key) {
    return Math.abs(key.hashCode()) % capacity;
  }

  public void put(T key) {
    if (contains(key)) {
      return;
    }

    if ((double) size / capacity >= LOAD_FACTOR) {
      resize();
    }

    int index = hash(key);
    if (buckets[index] == null) {
      buckets[index] = new LinkedList<>();
    }
    buckets[index].add(key);
    size++;
  }

  public boolean contains(T key) {
    int index = hash(key);
    LinkedList<T> bucket = buckets[index];
    if (bucket != null) {
      for (T element : bucket) {
        if (element.equals(key)) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean delete(T key) {
    int index = hash(key);
    LinkedList<T> bucket = buckets[index];
    if (bucket != null) {
      return bucket.remove(key);
    }
    return false;
  }

  private void resize() {
    int newCapacity = capacity * 2;
    LinkedList<T>[] newBuckets = new LinkedList[newCapacity];

    for (LinkedList<T> bucket : buckets) {
      if (bucket != null) {
        for (T key : bucket) {
          int newIndex = Math.abs(key.hashCode()) % newCapacity;
          if (newBuckets[newIndex] == null) {
            newBuckets[newIndex] = new LinkedList<>();
          }
          newBuckets[newIndex].add(key);
        }
      }
    }

    this.buckets = newBuckets;
    this.capacity = newCapacity;
  }

  public int size() {
    return size;
  }

  public Iterable<T> iterator() {
    LinkedList<T> allElements = new LinkedList<>();
    for (LinkedList<T> bucket : buckets) {
      if (bucket != null) {
        allElements.addAll(bucket);
      }
    }
    return allElements;
  }

  @Override
  public int getClassId() {
    return this.classId;
  }

}
