/** General exception for a Phase Ten Error.
 *  Call .getMessage() to get the error message.
 */
class PTException extends RuntimeException {

        /** A PhaseTenException with no message. */
        PTException() {
            super();
        }

        /** A PhaseTenException, MSG as its message. */
        PTException(String msg) {
            super(msg);
        }
}
