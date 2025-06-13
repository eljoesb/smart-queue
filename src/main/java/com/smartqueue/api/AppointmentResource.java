package com.smartqueue.api;

import com.smartqueue.application.ports.in.AppointmentManagementUseCase;
import com.smartqueue.domain.model.Appointment;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.time.LocalDateTime;
import java.util.List;

@Path("/api/appointments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Appointment Management", description = "APIs for managing clinic appointments")
public class AppointmentResource {

    private final AppointmentManagementUseCase appointmentManagementUseCase;

    public AppointmentResource(AppointmentManagementUseCase appointmentManagementUseCase) {
        this.appointmentManagementUseCase = appointmentManagementUseCase;
    }

    @POST
    @Operation(summary = "Schedule a new appointment")
    @APIResponse(responseCode = "201", description = "Appointment scheduled successfully")
    @APIResponse(responseCode = "400", description = "Invalid input")
    public Response scheduleAppointment(
            @QueryParam("patientId") Long patientId,
            @QueryParam("preferredTime") LocalDateTime preferredTime) {
        Appointment appointment = appointmentManagementUseCase.scheduleAppointment(patientId, preferredTime);
        return Response.status(Response.Status.CREATED).entity(appointment).build();
    }

    @PUT
    @Path("/{id}/reschedule")
    @Operation(summary = "Reschedule an existing appointment")
    @APIResponse(responseCode = "200", description = "Appointment rescheduled successfully")
    @APIResponse(responseCode = "404", description = "Appointment not found")
    public Response rescheduleAppointment(
            @PathParam("id") Long appointmentId,
            @QueryParam("newTime") LocalDateTime newTime) {
        Appointment appointment = appointmentManagementUseCase.rescheduleAppointment(appointmentId, newTime);
        return Response.ok(appointment).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Cancel an appointment")
    @APIResponse(responseCode = "204", description = "Appointment cancelled successfully")
    @APIResponse(responseCode = "404", description = "Appointment not found")
    public Response cancelAppointment(@PathParam("id") Long appointmentId) {
        appointmentManagementUseCase.cancelAppointment(appointmentId);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get appointment details")
    @APIResponse(responseCode = "200", description = "Appointment details retrieved successfully")
    @APIResponse(responseCode = "404", description = "Appointment not found")
    public Response getAppointment(@PathParam("id") Long appointmentId) {
        Appointment appointment = appointmentManagementUseCase.getAppointment(appointmentId);
        return Response.ok(appointment).build();
    }

    @GET
    @Path("/patient/{patientId}")
    @Operation(summary = "Get all appointments for a patient")
    @APIResponse(responseCode = "200", description = "Patient appointments retrieved successfully")
    public Response getPatientAppointments(@PathParam("patientId") Long patientId) {
        List<Appointment> appointments = appointmentManagementUseCase.getPatientAppointments(patientId);
        return Response.ok(appointments).build();
    }

    @GET
    @Path("/upcoming")
    @Operation(summary = "Get all upcoming appointments")
    @APIResponse(responseCode = "200", description = "Upcoming appointments retrieved successfully")
    public Response getUpcomingAppointments() {
        List<Appointment> appointments = appointmentManagementUseCase.getUpcomingAppointments();
        return Response.ok(appointments).build();
    }

    @PUT
    @Path("/{id}/status")
    @Operation(summary = "Update appointment status")
    @APIResponse(responseCode = "200", description = "Status updated successfully")
    @APIResponse(responseCode = "404", description = "Appointment not found")
    public Response updateAppointmentStatus(
            @PathParam("id") Long appointmentId,
            @QueryParam("status") Appointment.AppointmentStatus status) {
        appointmentManagementUseCase.updateAppointmentStatus(appointmentId, status);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}/wait-time")
    @Operation(summary = "Get estimated wait time for an appointment")
    @APIResponse(responseCode = "200", description = "Wait time retrieved successfully")
    @APIResponse(responseCode = "404", description = "Appointment not found")
    public Response getEstimatedWaitTime(@PathParam("id") Long appointmentId) {
        int waitTime = appointmentManagementUseCase.getEstimatedWaitTime(appointmentId);
        return Response.ok(waitTime).build();
    }
}