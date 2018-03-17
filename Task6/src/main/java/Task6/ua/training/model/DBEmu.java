package Task6.ua.training.model;


import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * DB emulation with some mockdata.
 *
 * 0 : String subscriberSurname;
 * 1 : String subscriberName;
 * 2 : String subscriberPatronymic;
 * 3 : String generatedInitials;
 * 4 : String login;
 * 5 : String comment;
 * 6 : List<String> groups;
 * 7 : String homeTelNumber;
 * 8 : String mobileTelNumber;
 * 9 : String mobileTelNumber2;
 * 10 : String email;
 * 11 : String skype;
 * 12 : HashMap<String, String> addressSeparated;
 * 13 : String addressFull;
 * 14 : GregorianCalendar creationDate;
 * 15 : GregorianCalendar lastChangeDate;
 */
public class DBEmu {

    private Map<Integer, Object> recordEmulation1 = new TreeMap<>();
    private Map<Integer, Object> recordEmulation2 = new TreeMap<>();

    public Map<Integer, Object> getRecordEmulation1() {
        return recordEmulation1;
    }

    public Map<Integer, Object> getRecordEmulation2() {
        return recordEmulation2;
    }

    /**
     * Initialize record with some mockdata
     */
    public void initializeRecord1(){
        recordEmulation1.put(0, "Petrov");
        recordEmulation1.put(1, "Petro");
        recordEmulation1.put(2, "Petrovich");
        recordEmulation1.put(3, "Petrov P.");
        recordEmulation1.put(4, "Petrov111");
        recordEmulation1.put(5, "Petrov is cool");
        recordEmulation1.put(6, "Management");
        recordEmulation1.put(7, "380441234567");
        recordEmulation1.put(8, "380441234567");
        recordEmulation1.put(9, "380441234567");
        recordEmulation1.put(10, "Petrov@cool.ua");
        recordEmulation1.put(11, "Petrov1");
        recordEmulation1.put(12, new HashMap<String, String>(){
            {
                put("index", "01111");
                put("city", "Kyiv");
                put("street", "Lvivska");
                put("building", "1");
                put("apartment", "11");
            }
        });
        recordEmulation1.put(13, "01111; Kyiv; Lvivska; 1; 11");
        recordEmulation1.put(14, new GregorianCalendar());
        recordEmulation1.put(15, new GregorianCalendar());
    }

    /**
     * Initialize record with some mockdata
     */
    public void initializeRecord2(){
        recordEmulation2.put(0, "Ivanov");
        recordEmulation2.put(1, "Ivan");
        recordEmulation2.put(2, "");
        recordEmulation2.put(3, "Ivanov I.");
        recordEmulation2.put(4, "Ivanovmegadj");
        recordEmulation2.put(5, "");
        recordEmulation2.put(6, "");
        recordEmulation2.put(7, "380441234567");
        recordEmulation2.put(8, "380441234567");
        recordEmulation2.put(9, "");
        recordEmulation2.put(10, "IvanDJ@cool.ua");
        recordEmulation2.put(11, "zzz");
        recordEmulation2.put(12, new HashMap<String, String>(){
            {
                put("index", "01141");
                put("city", "Kyiv");
                put("street", "10-ya Sadovaya");
                put("building", "5");
                put("apartment", "");
            }
        });
        recordEmulation2.put(13, "01141; Kyiv; 10-ya Sadovaya; 5; ");
        recordEmulation2.put(14, new GregorianCalendar());
        recordEmulation2.put(15, new GregorianCalendar());
    }
}
