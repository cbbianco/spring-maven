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

    /**
     * @apiNote  Constante para cuando se gestiona un producto , MESSAGE_OK de tipo {@link String}
     */
    public static final String MESSAGE_PRODUCT_OK = "El producto se ha registrado de forma satisfactoria";

    /**
     * @apiNote  Constante para cuando se gestiona un producto , MESSAGE_PRODUCT_UPDATE_OK de tipo {@link String}
     */
    public static final String MESSAGE_PRODUCT_UPDATE_OK = "El producto se ha podido actualizar de forma satisfactoria";

    /**
     * @apiNote  Constante para cuando se gestiona un producto , MESSAGE_PRODUCT_ERROR de tipo {@link String}
     */
    public static final String MESSAGE_PRODUCT_ERROR = "El producto no ha podido registrarse de forma satisfactoria";

    /**
     * @apiNote  Constante para cuando se gestiona un producto , MESSAGE_PRODUCT_UPDATE_ERROR de tipo {@link String}
     */
    public static final String MESSAGE_PRODUCT_UPDATE_ERROR = "El producto no ha podido actualizar de forma satisfactoria";

    /**
     * @apiNote  Constante para cuando se intenta persistir un mismo producto , MESSAGE_PRODUCT_ERROR_EXIST de tipo {@link String}
     */
    public static final String MESSAGE_PRODUCT_ERROR_EXIST = "El producto no ha podido registrarse, ya se encuentra registrado";

    /**
     * @apiNote  Constante para cuando se intenta actualizar producto que no existe , MESSAGE_PRODUCT_ERROR_NOT_EXIST de tipo {@link String}
     */
    public static final String MESSAGE_PRODUCT_ERROR_NOT_EXIST = "El producto que desea actualizar no se encuentra";
}
