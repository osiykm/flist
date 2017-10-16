package com.osiykm.flist.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.osiykm.flist.services.programs.BaseProgram;
import com.osiykm.flist.services.programs.BookParserProgram;
import com.osiykm.flist.services.programs.BookUpdaterProgram;
import com.osiykm.flist.services.programs.SamlibListUpdaterProgram;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/***
 * @author osiykm
 * created 27.09.2017 23:55
 */
@RestController
@RequestMapping(value = "/programs")
@Slf4j
public class ProgramsController {
    private final Map<String, BaseProgram> programs;

    @Autowired
    public ProgramsController(BookParserProgram parserProgram, SamlibListUpdaterProgram posterProgram, BookUpdaterProgram updaterProgram) {
        Map<String, BaseProgram> programs = new HashMap<>();
        programs.put("parser", parserProgram);
        programs.put("updater", updaterProgram);
        programs.put("poster", posterProgram);
        this.programs = programs;
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.POST)
    @ResponseBody
    private ResponseEntity<Void> programRunner(@RequestBody ProgramsRequest request, @PathVariable("code") String code) {
        log.info(code);
        BaseProgram program = programs.get(code);
        if (program == null)
            return ResponseEntity.notFound().build();
        if (program.isAlive() && request.getAction().equals(ProgramEnum.STOP)) {
            program.stop();
            return ResponseEntity.ok().build();
        } else if (!program.isAlive() && request.getAction().equals(ProgramEnum.START)) {
            program.start();
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    private ResponseEntity<ProgramStatusResponse> status(@PathVariable String code) {
        BaseProgram program = programs.get(code);
        if (program == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new ProgramStatusResponse(code, program.isAlive() ? ProgramStatus.RUNNING : ProgramStatus.STOPPED));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    private ResponseEntity<Set<ProgramStatusResponse>> get() {
        return ResponseEntity.ok(
                programs.entrySet()
                        .stream()
                        .map((k) -> new ProgramStatusResponse(k.getKey(), k.getValue().isAlive() ? ProgramStatus.RUNNING : ProgramStatus.STOPPED))
                        .collect(Collectors.toSet())
        );
    }
}

@NoArgsConstructor
@Getter
@AllArgsConstructor
@JsonIgnoreProperties
class ProgramsRequest {
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ProgramEnum action;
}

enum ProgramEnum {
    START, STOP
}

enum ProgramStatus {
    RUNNING, STOPPED
}

@NoArgsConstructor
@Getter
@AllArgsConstructor
@JsonIgnoreProperties
class ProgramStatusResponse {
    private String code;
    private ProgramStatus status;
}
