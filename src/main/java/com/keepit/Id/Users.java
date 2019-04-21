package com.keepit.Id;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="user")
public class Users {

    private List<Id> ids;

    public Users() {
        ids = new ArrayList<>();
    }
}
