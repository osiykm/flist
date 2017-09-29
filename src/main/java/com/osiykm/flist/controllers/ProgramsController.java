package com.osiykm.flist.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.osiykm.flist.services.SamlibUpdaterService;
import com.osiykm.flist.services.programs.BaseProgram;
import com.osiykm.flist.services.programs.BookParserProgram;
import com.osiykm.flist.services.programs.BookUpdaterProgram;
import com.osiykm.flist.services.programs.SamlibListUpdaterProgram;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/***
 * @author osiykm
 * created 27.09.2017 23:55
 */
@RestController
@RequestMapping(value= "/programs")
public class ProgramsController {
    private final BookParserProgram parserProgram;
    private final SamlibListUpdaterProgram posterProgram;
    private final BookUpdaterProgram updaterProgram;

    @Autowired
    public ProgramsController(BookParserProgram parserProgram, SamlibListUpdaterProgram posterProgram, BookUpdaterProgram updaterProgram) {
        this.parserProgram = parserProgram;
        this.posterProgram = posterProgram;
        this.updaterProgram = updaterProgram;
    }

    @RequestMapping(value= "/parser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> parser(@RequestBody ProgramsRequest request) {
        return programRunner(parserProgram, request.getStatus());
    }

    @RequestMapping(value= "/updater", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> updater(@RequestBody ProgramsRequest request) {
        return programRunner(updaterProgram, request.getStatus());
    }

    @RequestMapping(value= "/poster", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> poster(@RequestBody ProgramsRequest request) {
        return programRunner(posterProgram, request.getStatus());
    }
    private ResponseEntity<Void> programRunner(BaseProgram program, ProgramEnum status) {
        if(program.isAlive() && status.equals(ProgramEnum.STOP)) {
            program.stop();
            return ResponseEntity.ok().build();
        } else if(!program.isAlive() && status.equals(ProgramEnum.START)) {
            program.start();
            return ResponseEntity.ok().build();
        }
        return  ResponseEntity.badRequest().build();
    }
}
@NoArgsConstructor
@Getter
@AllArgsConstructor
@JsonIgnoreProperties
class ProgramsRequest {
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ProgramEnum status;
}
enum ProgramEnum {
    START, STOP
}
