/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business;

/**
 *
 * @author Virajee
 */
public class PropertyType {
    private int t_id;
    private String typeName;

    public PropertyType() {
    }

    public PropertyType(int t_id, String typeName) {
        this.t_id = t_id;
        this.typeName = typeName;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getT_id() {
        return t_id;
    }

    public String getTypeName() {
        return typeName;
    }    
    
}
