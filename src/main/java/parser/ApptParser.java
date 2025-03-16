package parser;

import java.time.LocalDate;
import java.time.LocalTime;

public class ApptParser extends Parser {
    private String command;
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private String notes;

    //Constructor
    public ApptParser(String command, String name, LocalTime startTime, LocalTime endTime,
                      LocalDate date, String notes) {
        this.command = command;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.notes = notes;
    }

    //Extracts all input parameters for Appointment based command
    public static ApptParser extractInputs(String line) {
        line = line.trim();
        line = line.toLowerCase();
        line = line.substring(line.indexOf(" ") + 1);
        String command = "";
        String name = "";
        LocalTime startTime = null;
        LocalTime endTime = null;
        LocalDate date = null;
        String notes = "";

        try {
            //extracts command
            command = line.substring(0, line.indexOf(" "));
            line = line.substring(line.indexOf(" ") + 1);

            //extracts patient name
            name = line.substring(line.indexOf("p/") + 2, line.indexOf("s/") - 1);
            line = line.substring(line.indexOf("s/"));

            //extracts appointment's start time
            startTime = LocalTime.parse(line.substring(2, line.indexOf(" ")));

            //extracts appointment's date
            date = LocalDate.parse(line.substring(line.indexOf("d/") + 2, line.indexOf("d/") + 12));

        } catch (Exception e) {
            System.out.println("Invalid inputs! Please try again.");
            return null;
        }

        if (command.equals("add")) {
            try {
                endTime = LocalTime.parse(line.substring(line.indexOf("e/") + 2, line.indexOf("d/") - 1));
                notes = line.substring(line.indexOf("n/") + 2);
            } catch (Exception e) {
                System.out.println("Invalid inputs! Please try again.");
                return null;
            }
            return new ApptParser(command, name, startTime, endTime, date, notes);
        }
        return new ApptParser(command, name, startTime, endTime, date, notes);
    }

    //Getters
    public String getCommand() {
        return command;
    }

    public String getName() {
        return name;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }
}
