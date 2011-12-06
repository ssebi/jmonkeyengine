/**
 * Common_profile_input.java This file was generated by XMLSpy 2006sp2
 * Enterprise Edition. YOU SHOULD NOT MODIFY THIS FILE, BECAUSE IT WILL BE
 * OVERWRITTEN WHEN YOU RE-RUN CODE GENERATION. Refer to the XMLSpy
 * Documentation for further details. http://www.altova.com/xmlspy
 */

package com.jmex.model.collada.schema;

import com.jmex.model.collada.types.SchemaNMToken;

public class Common_profile_input extends SchemaNMToken {

    private static final long serialVersionUID = 1L;
    public static final int EBINORMAL = 0; /* BINORMAL */
    public static final int ECOLOR = 1; /* COLOR */
    public static final int EIMAGE = 2; /* IMAGE */
    public static final int EIN_TANGENT = 3; /* IN_TANGENT */
    public static final int EINPUT = 4; /* INPUT */
    public static final int EINTERPOLATION = 5; /* INTERPOLATION */
    public static final int EINV_BIND_MATRIX = 6; /* INV_BIND_MATRIX */
    public static final int EJOINT = 7; /* JOINT */
    public static final int ENORMAL = 8; /* NORMAL */
    public static final int EOUTPUT = 9; /* OUTPUT */
    public static final int EOUT_TANGENT = 10; /* OUT_TANGENT */
    public static final int EPOSITION = 11; /* POSITION */
    public static final int ETANGENT = 12; /* TANGENT */
    public static final int ETEXCOORD = 13; /* TEXCOORD */
    public static final int ETEXTURE = 14; /* TEXTURE */
    public static final int EUV = 15; /* UV */
    public static final int EVERTEX = 16; /* VERTEX */
    public static final int EWEIGHT = 17; /* WEIGHT */

    public static String[] sEnumValues = { "BINORMAL", "COLOR", "IMAGE",
            "IN_TANGENT", "INPUT", "INTERPOLATION", "INV_BIND_MATRIX", "JOINT",
            "NORMAL", "OUTPUT", "OUT_TANGENT", "POSITION", "TANGENT",
            "TEXCOORD", "TEXTURE", "UV", "VERTEX", "WEIGHT", };

    public Common_profile_input() {
        super();
    }

    public Common_profile_input(String newValue) {
        super(newValue);
        validate();
    }

    public Common_profile_input(SchemaNMToken newValue) {
        super(newValue);
        validate();
    }

    public static int getEnumerationCount() {
        return sEnumValues.length;
    }

    public static String getEnumerationValue(int index) {
        return sEnumValues[index];
    }

    public static boolean isValidEnumerationValue(String val) {
        for (int i = 0; i < sEnumValues.length; i++) {
            if (val.equals(sEnumValues[i]))
                return true;
        }
        return false;
    }

    public void validate() {

        if (!isValidEnumerationValue(toString()))
            throw new com.jmex.model.collada.xml.XmlException(
                    "Value of Common_profile_input is invalid.");
    }
}