package com.osiykm.flist.services;

import com.osiykm.flist.services.parser.FanfictionUrlParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * @author osiykm
 * created 27.09.2017 23:55
 */
@Service
public class ProgramsService {

    private final FanfictionUrlParserService urlParserService;

    @Autowired
    public ProgramsService(FanfictionUrlParserService urlParserService) {
        this.urlParserService = urlParserService;
    }

    public void parser() {

    }

    public void updater() {

    }

}
