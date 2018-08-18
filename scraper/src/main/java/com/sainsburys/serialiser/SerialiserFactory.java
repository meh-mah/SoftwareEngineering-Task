package com.sainsburys.serialiser;

/**
 * Factory to create serialisers.
 *
 * @author Mehran
 *
 */
public final class SerialiserFactory {

    /**
     * Method for returning the requested serialiser.
     *
     * @param serialiserType Requested type
     * @return Requested serialiser
     */
    public static ISerialiser getSerialiser(final String serialiserType) {
        if (serialiserType.equalsIgnoreCase("JSON")) {
            return JsonSerialiser.getInstance();
        }

        // else return JsonSerialiser as default as it is
        // likely going to be our most popular option
        return JsonSerialiser.getInstance();
    }
}
