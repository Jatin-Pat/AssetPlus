package ca.mcgill.ecse.assetplus.controller;

import java.sql.Date;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.User;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.HotelStaff;
import ca.mcgill.ecse.assetplus.model.MaintenanceNote;

public class AssetPlusFeatureSet7Controller {

    private static AssetPlus assetplus = AssetPlusApplication.getAssetPlus();

    public static String addMaintenanceNote(Date date, String description, int ticketID, String email) {

        StringBuilder error = new StringBuilder();

        MaintenanceTicket ticket = assetplus.getMaintenanceTicket(ticketID);

        if (ticket == null) {
            error.append("This ticket does not exist.");
        }

        User taker = User.getWithEmail(email);

        if(!(taker.hasWithEmail(email))) {
            error.append("There is no user with this email");
        }

        if(!(taker instanceof HotelStaff)) {
            error.append("This user cannot write a maintenance note");
        }

        if (description.equals("")) {
           error.append("A description must be provided.");
        }

        if (date.before(ticket.getRaisedOnDate())) {
            error.append("A maintenance note cannot be written before an maintenance ticket has been created.");
        }

        MaintenanceNote note = new MaintenanceNote(date, description, ticket, (HotelStaff) taker);

        int index = ticket.numberOfTicketNotes();

        ticket.addTicketNoteAt(note, index);

        return (error.toString());
    }

    // index starts at 0
    public static String updateMaintenanceNote(int ticketID, int index, Date newDate, String newDescription, String newEmail) {

        StringBuilder error = new StringBuilder();

        MaintenanceTicket ticket = assetplus.getMaintenanceTicket(ticketID);

        if (ticket == null) {
            error.append("This ticket does not exist.");
        }

        User taker = User.getWithEmail(newEmail);

        if(!(taker.hasWithEmail(newEmail))) {
            error.append("There is no user with this email");
        }

        if(!(taker instanceof HotelStaff)) {
            error.append("This user cannot write a maintenance note");
        }

        if (newDescription.equals("")) {
            error.append("A description must be provided.");
        }

        if (newDate.before(ticket.getRaisedOnDate())) {
            error.append("A maintenance note cannot be written before an maintenance ticket has been created.");
        }

        if (index + 1 > ticket.numberOfTicketNotes()) {
            error.append("There is no maintenance note at the given index.");
        }

        if (ticket.getTicketNote(index) == null) {
            error.append("The maintenance note is null.");
        }

        MaintenanceNote note = ticket.getTicketNote(index);

        note.setNoteTaker((HotelStaff) taker);
        note.setDate(newDate);
        note.setDescription(newDescription);

        return (error.toString());
    }

    // index starts at 0
    public static void deleteMaintenanceNote(int ticketID, int index) {

        StringBuilder error = new StringBuilder();

        MaintenanceTicket ticket = assetplus.getMaintenanceTicket(ticketID);

        if (ticket == null) {
            error.append("This ticket does not exist.");
        }

        if (index + 1 > ticket.numberOfTicketNotes()) {
            error.append("There is no maintenance note at the given index.");
        }

        if (ticket.getTicketNote(index) == null) {
            error.append("The maintenance note is null.");
        }

        MaintenanceNote note = ticket.getTicketNote(index);

        ticket.removeTicketNote(note);
    }
}