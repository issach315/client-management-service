package com.clientmanagement.controller;

import com.clientmanagement.dto.request.ClientFilterParams;
import com.clientmanagement.dto.request.ClientRequestDto;
import com.clientmanagement.dto.response.ClientResponseDto;
import com.clientmanagement.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://client-management-service-app-ui-production.up.railway.app"
})
@RequestMapping("/api/clients")
public class ClientController {

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "clientName", "email", "industry", "status", "location", "createdAt"
    );

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // ── CREATE ──────────────────────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<ClientResponseDto> createClient(
            @RequestBody ClientRequestDto requestDto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientService.createClient(requestDto));
    }

    // ── LIST + SEARCH (unified) ──────────────────────────────────────────────
    // GET /api/clients?clientName=acme&industry=IT&page=0&size=10&sort=clientName,asc
    @GetMapping
    public ResponseEntity<Page<ClientResponseDto>> listClients(
            @ModelAttribute ClientFilterParams filters,
            @RequestParam(defaultValue = "0")   int page,
            @RequestParam(defaultValue = "10")  int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {

        if (size > 100) {
            throw new IllegalArgumentException("Page size cannot exceed 100");
        }

        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            throw new IllegalArgumentException(
                    "Invalid sort field: '" + sortBy + "'. Allowed: " + ALLOWED_SORT_FIELDS);
        }

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        return ResponseEntity.ok(
                clientService.searchClients(filters, PageRequest.of(page, size, sort)));
    }

    // ── READ ────────────────────────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> getClientById(@PathVariable String id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    // ── UPDATE ──────────────────────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDto> updateClient(
            @PathVariable String id,
            @RequestBody ClientRequestDto requestDto) {

        return ResponseEntity.ok(clientService.updateClient(id, requestDto));
    }

    // ── DELETE ──────────────────────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable String id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();  // 204 is correct, not 200 + string
    }
}