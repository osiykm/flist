package com.osiykm.flist.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.osiykm.flist.services.programs.BaseProgram;
import com.osiykm.flist.services.programs.BookParserProgram;
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

    @Autowired
    public ProgramsController(BookParserProgram parserProgram) {
        this.parserProgram = parserProgram;
    }

    @RequestMapping(value= "/parser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> parser(@RequestBody ProgramsRequest request) {
        return programRunner(parserProgram, request.getStatus());
    }


    private ResponseEntity<Void> programRunner(BaseProgram program, ProgramEnum status) {
        if(parserProgram.isAlive() && status.equals(ProgramEnum.STOP)) {
            parserProgram.stop();
            return ResponseEntity.ok().build();
        } else if(!parserProgram.isAlive() && status.equals(ProgramEnum.START)) {
            parserProgram.start();
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
