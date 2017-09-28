package com.osiykm.flist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * @author osiykm
 * created 27.09.2017 23:55
 */
@Service
public class ProgramsService {

    private final UrlParserService urlParserService;

    @Autowired
    public ProgramsService(UrlParserService urlParserService) {
        this.urlParserService = urlParserService;
    }

    public void parser() {

    }

    public void updater() {

    }

}
