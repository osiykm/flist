package com.osiykm.flist.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@JsonIgnoreProperties
public class BookUrlRequest {
    @NotNull
    private String url;
}
