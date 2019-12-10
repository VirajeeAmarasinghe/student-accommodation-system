/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;

/**
 *
 * @author Virajee
 */
public interface DAOInterface {

    int insert(Object ob);

    int update(Object ob);

    int delete(int id);

    Object get(int id);

    ArrayList<Object> getAll();
}
