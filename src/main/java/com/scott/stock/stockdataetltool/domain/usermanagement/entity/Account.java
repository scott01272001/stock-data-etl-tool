package com.scott.stock.stockdataetltool.domain.usermanagement.entity;

import com.scott.stock.stockdataetltool.domain.VersionEntity;
import java.util.UUID;

public class Account extends VersionEntity {

    private UUID id;

    private String email;

    private String password;

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
