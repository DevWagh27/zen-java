package com.zenith.accountInfo.models;

import java.io.Serializable;

import lombok.Data;


@Data
public class EhfRecord implements Serializable {

    private static final long serialVersionUID = -1317559232336740643L;
    private String statusCode;
    private String statusDescription;
    private String businessDescription;
    private String ehfRef;
    private String ehfDesc;
}
