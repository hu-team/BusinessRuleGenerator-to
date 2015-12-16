package com.brg.dao;


import java.util.ArrayList;

public interface DAO {
    ArrayList<?> executeRead(String sql);
}
