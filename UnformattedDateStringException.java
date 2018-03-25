/*
 * customize an exception class
 * a string that represents a non formatted date string
 * */
class UnformattedDateStringException extends Exception {

    UnformattedDateStringException(String message) {
        super(message);
    }

}
