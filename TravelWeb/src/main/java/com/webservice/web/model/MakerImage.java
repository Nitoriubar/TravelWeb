package com.webservice.web.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MakerImage {
	private int postNo;
    private String path;
    private String name;
    private String extension;
    private Timestamp createdDate;
    private Timestamp updatedDate;
}
