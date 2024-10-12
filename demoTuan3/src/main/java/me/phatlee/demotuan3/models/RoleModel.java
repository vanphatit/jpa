package me.phatlee.demotuan3.models;

public class RoleModel {
    private int roleid;
    private String name;

    public RoleModel() {
    }

    public RoleModel(int roleid, String name) {
        this.roleid = roleid;
        this.name = name;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RoleModel{" +
                "roleid=" + roleid +
                ", name='" + name + '\'' +
                '}';
    }
}
