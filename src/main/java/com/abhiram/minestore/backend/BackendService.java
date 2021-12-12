package com.abhiram.minestore.backend;

import java.util.List;

public interface BackendService<T> {
    void update();
    String getName();
    List<T> getData();
}
