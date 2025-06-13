package com.smartqueue.api;

import com.smartqueue.application.ports.in.PatientManagementUseCase;
import com.smartqueue.domain.model.Patient;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/api/patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Patient Management", description = "APIs for managing patients")
public class PatientResource {

    private final PatientManagementUseCase patientManagementUseCase;

    public PatientResource(PatientManagementUseCase patientManagementUseCase) {
        this.patientManagementUseCase = patientManagementUseCase;
    }

    @POST
    @Operation(summary = "Create a new patient")
    @APIResponse(responseCode = "201", description = "Patient created successfully")
    @APIResponse(responseCode = "400", description = "Invalid input")
    public Response createPatient(Patient patient) {
        Patient createdPatient = patientManagementUseCase.createPatient(patient);
        return Response.status(Response.Status.CREATED).entity(createdPatient).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get patient details")
    @APIResponse(responseCode = "200", description = "Patient details retrieved successfully")
    @APIResponse(responseCode = "404", description = "Patient not found")
    public Response getPatient(@PathParam("id") Long id) {
        Patient patient = patientManagementUseCase.getPatient(id);
        return Response.ok(patient).build();
    }
}