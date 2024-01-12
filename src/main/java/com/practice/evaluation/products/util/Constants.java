package com.practice.evaluation.products.util;

/**
 * @apiNote Constants,  'literales' Encargada de Gestionar o llevar los mensajes del proyecto
 *
 * @version 1.0.0
 */
public class Constants {

    private Constants()
    {
        throw new IllegalStateException("Util Class");
    }

    /**
     * @apiNote  Constante para cuando se suministre un campo en el request de forma nula , NOT_NULL de tipo {@link String}
     */
    public static final String NOT_NULL = "El campo es obligatorio";

    /**
     * @apiNote  Constante para cuando no se suministre detalles a su producto , NOT_SIZE de tipo {@link String}
     */
    public static final String NOT_SIZE = "Lo sentimos, Debe ingresar una información válida.";

    /**
     * @apiNote  Constante para cuando se suministre un campo en el request que sea de tipo string de forma vacia , ERROR_IS_STRING de tipo {@link String}
     */
    public static final String ERROR_IS_STRING = "Lo sentimos, Debe ingresar una información válida.";
}
