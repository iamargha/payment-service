package com.egov.customerlservice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// CRUD operations on a particular table
public interface CredentialRepository extends JpaRepository<Credential, UUID> {


}