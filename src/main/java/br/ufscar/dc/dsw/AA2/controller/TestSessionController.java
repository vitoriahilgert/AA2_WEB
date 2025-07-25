package br.ufscar.dc.dsw.AA2.controller;

import br.ufscar.dc.dsw.AA2.dtos.testSession.CreateTestSessionRequestDTO;
import br.ufscar.dc.dsw.AA2.dtos.testSession.CreateTestSessionResponseDTO;
import br.ufscar.dc.dsw.AA2.dtos.testSession.GetTestSessionResponseDTO;
import br.ufscar.dc.dsw.AA2.services.TestSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sessions")
public class TestSessionController {
    private TestSessionService testSessionService;

    public TestSessionController(TestSessionService testSessionService) {
        this.testSessionService = testSessionService;
    }

    @PostMapping("/{projectId}")
    public ResponseEntity<CreateTestSessionResponseDTO> create(@RequestBody CreateTestSessionRequestDTO dto, @PathVariable("projectId") UUID projectId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(testSessionService.createTestSession(projectId, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        this.testSessionService.deleteTestSession(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<GetTestSessionResponseDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(testSessionService.getAllTestSessions());
    }

    public ResponseEntity<GetTestSessionResponseDTO> getById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(testSessionService.getTestSessionById(id));
    }


}
