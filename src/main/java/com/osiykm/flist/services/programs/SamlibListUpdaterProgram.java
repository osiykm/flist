package com.osiykm.flist.services.programs;

import com.osiykm.flist.services.ListCreatorService;
import com.osiykm.flist.services.SamlibUpdaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/***
 * @author osiykm
 * created 29.09.2017 22:27
 */
@Component
public class SamlibListUpdaterProgram extends BaseProgram{
    private final ListCreatorService listCreatorService;
    private final SamlibUpdaterService samlibUpdaterService;
    private final String annotation = "Fanfics list ";
    @Autowired
    public SamlibListUpdaterProgram(ListCreatorService listCreatorService, SamlibUpdaterService samlibUpdaterService) {
        this.listCreatorService = listCreatorService;
        this.samlibUpdaterService = samlibUpdaterService;
    }

    @Override
    public void run() {
        samlibUpdaterService.listUpdate(
                annotation + new SimpleDateFormat("dd/mm/yyyy").format(Calendar.getInstance().getTime()),
                listCreatorService.createList()
        );
        stop();
    }
}
