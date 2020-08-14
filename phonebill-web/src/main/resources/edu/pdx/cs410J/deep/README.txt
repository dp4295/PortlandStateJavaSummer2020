This is a README file!
My name is Deep Patel

+ How to run the code:+
$ java edu.pdx.cs410J.deep.Project4 [options] <args>
$ java jar /tar/phonebill-client.jar -host localhost -port 8080 Dee 504-123-1222 123-123-1234 02/27/2020 5:45 pm 02/27/2020 5:50 pm


[options] :
-host hostname : Host computer on which the server runs
-port port     : Port on which the server is listening
-search        : Phone calls should be searched for
-README        : Display readme file
-print         : Prints a description of the new phone call

[args] <in below order>  :
customer
callernumber
calleenumber
start date
start time
start am/pm
end date
end time
end am/pm

+ What is valid format for arguments +
customer     : Multi-word arguments with double quotes
callernumber : nnn-nnn-nnnn . allow only digits
calleenumber : nnn-nnn-nnnn . allow only digits
start date   : mm/dd/yyyy or m/d/yyyy or mm/d/yyyy or m/dd/yyyy
start time   : 12:00
start am/pm  : am/pm
end time     : mm/dd/yyyy or m/d/yyyy or mm/d/yyyy or m/dd/yyyy
end time     : 12:00
end am/pm    : am/pm


+ Valid Command line arguments +

1 argument
-README
Above command display redme file


13 arguments
-host localhost -port 8080 Project3 123-456-7899 123-123-1234 01/08/2020 12:00 pm 01/08/2020 12:15 pm
Above command write to phone call to file

14 arguments
-host localhost -port 8080 -print Project3 123-456-7899 123-123-1234 01/08/2020 12:00 pm 01/08/2020 12:15 pm
Above command write to phone call to file and display pretty print

14 arguments
-host localhost -port 8080 -search Project3 01/08/2020 01/023/2020
Above command display all calls between start and end date range

