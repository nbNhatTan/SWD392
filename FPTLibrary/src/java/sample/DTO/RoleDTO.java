/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DTO;

/**
 *
 * @author NhatTan
 */
public class RoleDTO {

    private int roleID;
    private String rolelName;

    public RoleDTO(int roleID, String rolelName) {
        this.roleID = roleID;
        this.rolelName = rolelName;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRolelName() {
        return rolelName;
    }

    public void setRolelName(String rolelName) {
        this.rolelName = rolelName;
    }

}
