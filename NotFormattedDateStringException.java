/*
* customize an exception class
* a string that represents a non formatted date string
* */
public class NotFormattedDateStringException extends Exception {

    NotFormattedDateStringException(String message){
        super(message);
    }

}
