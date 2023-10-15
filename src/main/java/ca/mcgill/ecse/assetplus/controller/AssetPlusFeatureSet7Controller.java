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

        MaintenanceTicket ticket = assetplus.getMaintenanceTicket(ticketID);

        if (ticket == null) {
            throw new IllegalArgumentException("This ticket does not exist.");
        }

        User taker = User.getWithEmail(email);

        if(!(taker.hasWithEmail(email))) {
            throw new IllegalArgumentException("There is no user with this email");
        }

        if(!(taker instanceof HotelStaff)) {
            throw new IllegalArgumentException("This user cannot write a maintenance note");
        }

        if (description.equals("")) {
           throw new IllegalArgumentException("A description must be provided.");
        }

        if (date.before(ticket.getRaisedOnDate())) {
            throw new IllegalArgumentException("A maintenance note cannot be written before an maintenance ticket has been created.");
        }

        MaintenanceNote note = new MaintenanceNote(date, description, ticket, (HotelStaff) taker);

        int index = ticket.numberOfTicketNotes();

        ticket.addTicketNoteAt(note, index);

        return (note.toString());
    }

    // index starts at 0
    public static String updateMaintenanceNote(int ticketID, int index, Date newDate, String newDescription, String newEmail) {

        MaintenanceTicket ticket = assetplus.getMaintenanceTicket(ticketID);

        if (ticket == null) {
            throw new IllegalArgumentException("This ticket does not exist.");
        }

        User taker = User.getWithEmail(newEmail);

        if(!(taker.hasWithEmail(newEmail))) {
            throw new IllegalArgumentException("There is no user with this email");
        }

        if(!(taker instanceof HotelStaff)) {
            throw new IllegalArgumentException("This user cannot write a maintenance note");
        }

        if (newDescription.equals("")) {
            throw new IllegalArgumentException("A description must be provided.");
        }

        if (newDate.before(ticket.getRaisedOnDate())) {
            throw new IllegalArgumentException("A maintenance note cannot be written before an maintenance ticket has been created.");
        }

        if (index + 1 > ticket.numberOfTicketNotes()) {
            throw new IllegalArgumentException("There is no maintenance note at the given index.");
        }

        if (ticket.getTicketNote(index) == null) {
            throw new IllegalArgumentException("The maintenance note is null.");
        }

        MaintenanceNote note = ticket.getTicketNote(index);

        note.setNoteTaker((HotelStaff) taker);
        note.setDate(newDate);
        note.setDescription(newDescription);

        return (note.toString());
    }

    // index starts at 0
    public static void deleteMaintenanceNote(int ticketID, int index) {

        MaintenanceTicket ticket = assetplus.getMaintenanceTicket(ticketID);

        if (ticket == null) {
            throw new IllegalArgumentException("This ticket does not exist.");
        }

        if (index + 1 > ticket.numberOfTicketNotes()) {
            throw new IllegalArgumentException("There is no maintenance note at the given index.");
        }

        if (ticket.getTicketNote(index) == null) {
            throw new IllegalArgumentException("The maintenance note is null.");
        }

        MaintenanceNote note = ticket.getTicketNote(index);

        ticket.removeTicketNote(note);
    }
}