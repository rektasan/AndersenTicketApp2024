package com.jfb.ticket_app.repository;

import com.jfb.ticket_app.util.storage.DisarrayList;

public interface GeneralDao<T> {
  void save(T o);
  T fetchById(String id);
  DisarrayList<T> selectAll();
}
