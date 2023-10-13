package ca.mcgill.ecse.assetplus.controller;

import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.TicketImage;

public class AssetPlusFeatureSet5Controller {

    // Method to validate the inputs
    public static Boolean isValid(String imageURL, int ticketID) throws IllegalArgumentException {

        var error = "";
        if (imageURL == null || imageURL == "") {
            error = "Image Url cannot be empty.";
            throw new IllegalArgumentException(error);
        }

        if (ticketID < 0) {
            error = "Invalid ticket ID";
            throw new IllegalArgumentException(error);
        }

        if (!imageURL.startsWith("http://") && !imageURL.startsWith("https://")) {
            error = "Invalid image URL";
            throw new IllegalArgumentException(error);
        }

        return true;

    }

    public static String addImageToMaintenanceTicket(String imageURL, int ticketID) {

        var error = "";

        if (isValid(imageURL, ticketID)) {
            try {
                MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketID);

                // Check if the imageURL is already in images in the ticket.
                for (TicketImage image : ticket.getTicketImages()) {
                    if (image.getImageURL().equals(imageURL)) {
                        error = "Cannot add the same image twice";
                        throw new IllegalArgumentException(error);
                    }
                }

                // Do I have to do that ?
                if (error.isEmpty()) {
                    // Create a new ticket image object with an existing ticket
                    TicketImage ticketImage = new TicketImage(imageURL, ticket);

                    // Add the ticket image to the ticket
                    ticket.addTicketImage(ticketImage);
                }
            } catch (RuntimeException e) {
                error = e.getMessage();
            }
        }

        return error;
    }

    public static void deleteImageFromMaintenanceTicket(String imageURL, int ticketID) {

        var error = "";

        if (isValid(imageURL, ticketID)) {
            try {
                MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketID);

                // Searches for the correct image to be removed from the ticket. BY cheking the
                // urls of all iamges in the ticket
                boolean imageFound = false;
                for (TicketImage image : ticket.getTicketImages()) {
                    if (image.getImageURL().equals(imageURL)) {
                        // remove the image from the maintenace ticket
                        ticket.removeTicketImage(image);
                        // Deletes the image
                        image.delete();
                        // Should the program exit ?
                        imageFound = true;
                        break; // Should I Exit the loop since the image is found ?
                    }
                }

                if (!imageFound) {
                    error = "Image provided is not in the ticket";
                    throw new IllegalArgumentException(error);
                }

            } catch (RuntimeException e) {
                error = e.getMessage();
            }
        }
    }
}