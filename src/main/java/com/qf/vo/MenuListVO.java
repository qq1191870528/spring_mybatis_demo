package com.qf.vo;

import lombok.Data;

/**
 * Created by DELL on 2019/8/6.
 */
@Data
public class MenuListVO {

    int mid;
    String menuname;
    String menuicon;
    String menu_url;
    int parentid;
    boolean parentMenu;

    String parentType;


    public String getMenu_url() {
        return menu_url==null?"":menu_url;
    }

    public void setParentType(String parentType){
        if(parentMenu){
            this.parentType = "是";
        }else{
            this.parentType = "否";
        }
    }

    public String getParentType(){
        if(parentMenu){
            return "是";
        }else{
            return "否";
        }
    }


}
