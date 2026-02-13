package com.renthouses.contract.controller;

import com.renthouses.contract.domain.RentalContract;
import com.renthouses.contract.service.ContractService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

  private final ContractService contractService;

  public ContractController(ContractService contractService) {
    this.contractService = contractService;
  }

  @PostMapping
  public RentalContract create(@RequestBody RentalContract contract) { return contractService.createContract(contract); }

  @PostMapping("/{id}/terminate")
  public RentalContract terminate(@PathVariable String id) { return contractService.terminateContract(id); }

  @GetMapping
  public List<RentalContract> all() { return contractService.listAll(); }
}
