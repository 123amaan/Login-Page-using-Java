package com.LoginPage.LoginPage;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class _5Message {
    // 5.

    private String content;
    private String type;

    public _5Message(String content, String type) {
        this.content = content;
        this.type = type;
    }
}
