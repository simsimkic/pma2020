package com.pma.running.dto;

import lombok.Data;

import javax.swing.text.StyledEditorKit;
import java.io.Serializable;

@Data
public class FriendDto implements Serializable {
    private String name;
    private String username;
    private int friend;  //1-prijatelji, 0-nisu, 2-ceka se odgovor, 3-on poslao ulogvanom zahtev

    public FriendDto(String name, String username, int friend) {
        this.name =  name;
        this.username= username;
        this.friend = friend;
    }
}
