package mysql;

/**
 * Enum containing MySQL functions for easier searching.
 * @since       1.0
 * @author      <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public enum MySqlFunctions {

    //---String-Functions-----------------------------------------------------------------------------------------------

    /**
     * Returns the ASCII value for the specific character
     */
    ASCII,

    /**
     * Returns the length of a string (in characters)
     */
    CHAR_LENGTH,

    /**
     * Returns the length of a string (in characters)
     */
    CHARACTER_LENGTH,

    /**
     * Adds two or more expressions together
     */
    CONCAT,

    /**
     * Adds two or more expressions together with a separator
     */
    CONCAT_WS,

    /**
     * Returns the index position of a value in a list of values
     */
    FIELD,

    /**
     * Returns the position of a string within a list of strings
     */
    FIND_IN_SET,

    /**
     * Formats a number to a format like "#,###,###.##", rounded to a specified number of decimal places
     */
    FORMAT,

    /**
     * Inserts a string within a string at the specified position and for a certain number of characters
     */
    INSERT,

    /**
     * Returns the position of the first occurrence of a string in another string
     */
    INSTR,

    /**
     * Converts a string to lower-case
     */
    LCASE,

    /**
     * 	Extracts a number of characters from a string (starting from left)
     */
    LEFT,

    /**
     * Returns the length of a string (in bytes)
     */
    LENGTH,

    /**
     * Returns the position of the first occurrence of a substring in a string
     */
    LOCATE,

    /**
     * Converts a string to lower-case
     */
    LOWER,

    /**
     * 	Left-pads a string with another string, to a certain length
     */
    LPAD,

    /**
     * Removes leading spaces from a string
     */
    LTRIM,

    /**
     * Extracts a substring from a string (starting at any position)
     */
    MID,

    /**
     * 	Returns the position of the first occurrence of a substring in a string
     */
    POSITION,

    /**
     * Repeats a string as many times as specified
     */
    REPEAT,

    /**
     * 	Replaces all occurrences of a substring within a string, with a new substring
     */
    REPLACE,

    /**
     * 	Reverses a string and returns the result
     */
    REVERSE,

    /**
     * 	Extracts a number of characters from a string (starting from right)
     */
    RIGHT,

    /**
     * Right-pads a string with another string, to a certain length
     */
    RPAD,

    /**
     * Removes trailing spaces from a string
     */
    RTRIM,

    /**
     * Returns a string of the specified number of space characters
     */
    SPACE,

    /**
     * Compares two strings
     */
    STRCMP,

    /**
     * Extracts a substring from a string (starting at any position)
     */
    SUBSTR,

    /**
     * Extracts a substring from a string (starting at any position)
     */
    SUBSTRING,

    /**
     * Returns a substring of a string before a specified number of delimiter occurs
     */
    SUBSTRING_INDEX,

    /**
     * Removes leading and trailing spaces from a string
     */
    TRIM,

    /**
     * Converts a string to upper-case
     */
    UCASE,

    /**
     * Converts a string to upper-case
     */
    UPPER,

    //---Number-Functions-----------------------------------------------------------------------------------------------

    /**
     * Returns the absolute value of a number
     */
    ABS,

    /**
     * Returns the arc cosine of a number
     */
    ACOS,

    /**
     * Returns the arc sine of a number
     */
    ASIN,

    /**
     * Returns the arc tangent of one or two numbers
     */
    ATAN,

    /**
     * Returns the arc tangent of two numbers
     */
    ATAN2,

    /**
     * Returns the average value of an expression
     */
    AVG,

    /**
     * Returns the smallest integer value that is >= to a number
     */
    CEIL,

    /**
     * 	Returns the smallest integer value that is >= to a number
     */
    CEILING,

    /**
     * Returns the cosine of a number
     */
    COS,

    /**
     * Returns the cotangent of a number
     */
    COT,

    /**
     * Returns the number of records returned by a select query
     */
    COUNT,

    /**
     * Converts a value in radians to degrees
     */
    DEGREES,

    /**
     * Used for integer division
     */
    DIV,

    /**
     * Returns e raised to the power of a specified number
     */
    EXP,

    /**
     * Returns the largest integer value that is <= to a number
     */
    FLOOR,

    /**
     * 	Returns the greatest value of the list of arguments
     */
    GREATEST,

    /**
     * Returns the smallest value of the list of arguments
     */
    LEAST,

    /**
     * 	Returns the natural logarithm of a number
     */
    LN,

    /**
     * Returns the natural logarithm of a number, or the logarithm of a number to a specified base
     */
    LOG,

    /**
     * Returns the natural logarithm of a number to base 10
     */
    LOG10,

    /**
     * 	Returns the natural logarithm of a number to base 2
     */
    LOG2,

    /**
     * Returns the maximum value in a set of values
     */
    MAX,

    /**
     * 	Returns the minimum value in a set of values
     */
    MIN,

    /**
     * 	Returns the remainder of a number divided by another number
     */
    MOD,

    /**
     * Returns the value of PI
     */
    PI,

    /**
     * Returns the value of a number raised to the power of another number
     */
    POW,

    /**
     * Returns the value of a number raised to the power of another number
     */
    POWER,

    /**
     * Converts a degree value into radians
     */
    RADIANS,

    /**
     * Returns a random number
     */
    RAND,

    /**
     * Rounds a number to a specified number of decimal places
     */
    ROUND,

    /**
     * Returns the sign of a number
     */
    SIGN,

    /**
     * Returns the sine of a number
     */
    SIN,

    /**
     * Returns the square root of a number
     */
    SQRT,

    /**
     * Calculates the sum of a set of values
     */
    SUM,

    /**
     * Returns the tangent of a number
     */
    TAN,

    /**
     * 	Truncates a number to the specified number of decimal places
     */
    TRUNCATE,

    //---Date-Functions-------------------------------------------------------------------------------------------------

    /**
     * Adds a time/date interval to a date and then returns the date
     */
    ADDDATE,

    /**
     * Adds a time interval to a time/datetime and then returns the time/datetime
     */
    ADDTIME,

    /**
     * Returns the current date
     */
    CURDATE,

    /**
     * Returns the current date
     */
    CURRENT_DATE,

    /**
     * Returns the current time
     */
    CURRENT_TIME,

    /**
     * Returns the current date and time
     */
    CURRENT_TIMESTAMP,

    /**
     * Returns the current time
     */
    CURTIME,

    /**
     * Extracts the date part from a datetime expression
     */
    DATE,

    /**
     * Returns the number of days between two date values
     */
    DATEDIFF,

    /**
     * Adds a time/date interval to a date and then returns the date
     */
    DATE_ADD,

    /**
     * Formats a date
     */
    DATE_FORMAT,

    /**
     * 	Subtracts a time/date interval from a date and then returns the date
     */
    DATE_SUB,

    /**
     * Returns the day of the month for a given date
     */
    DAY,

    /**
     * Returns the weekday name for a given date
     */
    DAYNAME,

    /**
     * Returns the day of the month for a given date
     */
    DAYOFMONTH,

    /**
     * Returns the weekday index for a given date
     */
    DAYOFWEEK,

    /**
     * Returns the day of the year for a given date
     */
    DAYOFYEAR,

    /**
     * Extracts a part from a given date
     */
    EXTRACT,

    /**
     * Returns a date from a numeric datevalue
     */
    FROM_DAYS,

    /**
     * Returns the hour part for a given date
     */
    HOUR,

    /**
     * Extracts the last day of the month for a given date
     */
    LAST_DAY,

    /**
     * Returns the current date and time
     */
    LOCALTIME,

    /**
     * Returns the current date and time
     */
    LOCALTIMESTAMP,

    /**
     * Creates and returns a date based on a year and a number of days value
     */
    MAKEDATE,

    /**
     * Creates and returns a time based on an hour, minute, and second value
     */
    MAKETIME,

    /**
     * Returns the microsecond part of a time/datetime
     */
    MICROSECOND,

    /**
     * Returns the minute part of a time/datetime
     */
    MINUTE,

    /**
     * Returns the month part for a given date
     */
    MONTH,

    /**
     * Returns the name of the month for a given date
     */
    MONTHNAME,

    /**
     * Returns the current date and time
     */
    NOW,

    /**
     * Adds a specified number of months to a period
     */
    PERIOD_ADD,

    /**
     * Returns the difference between two periods
     */
    PERIOD_DIFF,

    /**
     * 	Returns the quarter of the year for a given date value
     */
    QUARTER,

    /**
     * Returns the seconds part of a time/datetime
     */
    SECOND,

    /**
     * Returns a time value based on the specified seconds
     */
    SEC_TO_TIME,

    /**
     * Returns a date based on a string and a format
     */
    STR_TO_DATE,

    /**
     * Subtracts a time/date interval from a date and then returns the date
     */
    SUBDATE,

    /**
     * Subtracts a time interval from a datetime and then returns the time/datetime
     */
    SUBTIME,

    /**
     * Returns the current date and time
     */
    SYSDATE,

    /**
     * Extracts the time part from a given time/datetime
     */
    TIME,

    /**
     * Formats a time by a specified format
     */
    TIME_FORMAT,

    /**
     * Converts a time value into seconds
     */
    TIME_TO_SEC,

    /**
     * Returns the difference between two time/datetime expressions
     */
    TIMEDIFF,

    /**
     * Returns a datetime value based on a date or datetime value
     */
    TIMESTAMP,

    /**
     * Returns the number of days between a date and date "0000-00-00"
     */
    TO_DAYS,

    /**
     * Returns the week number for a given date
     */
    WEEK,

    /**
     * Returns the weekday number for a given date
     */
    WEEKDAY,

    /**
     * Returns the week number for a given date
     */
    WEEKOFYEAR,

    /**
     * Returns the year part for a given date
     */
    YEAR,

    /**
     * Returns the year and week number for a given date
     */
    YEARWEEK,

    //---Advanced-Functions---------------------------------------------------------------------------------------------

    /**
     * Returns a binary representation of a number
     */
    BIN,

    /**
     * Converts a value to a binary string
     */
    BINARY,

    /**
     * Goes through conditions and return a value when the first condition is met
     */
    CASE,

    /**
     * Converts a value (of any type) into a specified datatype
     */
    CAST,

    /**
     * Returns the first non-null value in a list
     */
    COALESCE,

    /**
     * Returns the unique connection ID for the current connection
     */
    CONNECTION_ID,

    /**
     * Converts a number from one numeric base system to another
     */
    CONV,

    /**
     * Converts a value into the specified datatype or character set
     */
    CONVERT,

    /**
     * Returns the username and host name for the MySQL account that the server used to authenticate the current client
     */
    CURRENT_USER,

    /**
     * Returns the name of the current database
     */
    DATABASE,

    /**
     * Returns a value if a condition is TRUE, or another value if a condition is FALSE
     */
    IF,

    /**
     * Return a specified value if the expression is NULL, otherwise return the expression
     */
    IFNULL,

    /**
     * Returns 1 or 0 depending on whether an expression is NULL
     */
    ISNULL,

    /**
     * Returns the AUTO_INCREMENT id of the last row that has been inserted or updated in a table
     */
    LAST_INSERT_ID,

    /**
     * Compares two expressions and returns NULL if they are equal. Otherwise, the first expression is returned
     */
    NULLIF,

    /**
     * Returns the current MySQL username and host name
     */
    SESSION_USER,

    /**
     * Returns the current MySQL username and host name
     */
    SYSTEM_USER,

    /**
     * Returns the current MySQL username and host name
     */
    USER,

    /**
     * Returns the current version of the MySQL database
     */
    VERSION;

    @Override
    public String toString() {
        return name().replace("_", " ");
    }
}