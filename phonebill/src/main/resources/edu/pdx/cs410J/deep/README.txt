This is a README file!
My name is Deep Patel
In this program you add phonebill information from
Command line.

+ How to run the code:+
$ java edu.pdx.cs410J.deep.Project1 [options] <args>
[options] :
-README : Display readme file
-print  : use this option with below arguments to print
-TextFile file: Read/Write the phone bill
-pretty file: Pretty print the phone bill to a text file or standard out

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
Above argument display readme.txt file


12 arguments
-textFile deep/deep.txt -pretty Project3 123-456-7899 123-123-1234 01/08/2020 12:00 pm 01/08/2020 12:15 pm
Above command write to file and read from file

13 arguments
-textFile deep/deep.txt -pretty deep/pretty.txt Project3 123-456-7899 123-123-1234 01/08/2020 12:00 pm 01/08/2020 12:15 pm


13 arguments
-textFile deep/deep.txt -pretty - Project3 123-456-7899 123-123-1234 01/08/2020 12:00 pm 01/08/2020 12:15 pm


