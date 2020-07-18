This is a README file!
My name is Deep Patel
In this program you add phonebill information from
Command line.

+ How to run the code:+
$ java edu.pdx.cs410J.deep.Project1 [options] <args>
[options] :
-README : Display readme file
-print  : use this option with below arguments to print
-TextFile: Read/Write the phone bill
[args] <in below order>  :
customer
callernumber
calleenumber
start date
start time
end date
end time

+ What is valid format for arguments +
customer     : Multi-word arguments with double quotes
callernumber : nnn-nnn-nnnn . allow only digits
calleenumber : nnn-nnn-nnnn . allow only digits
start date   : mm/dd/yyyy or m/d/yyyy or mm/d/yyyy or m/dd/yyyy
start time   : 24:00
end time     : mm/dd/yyyy or m/d/yyyy or mm/d/yyyy or m/dd/yyyy
end time     : 24:00


+ Valid Command line arguments +

1 argument
-README
Above argument display readme.txt file

7 argument
"Deep Patel" 123-123-1234 123-123-1234 1/7/2020 10:39  1/7/2020 10:41
Above argument only add record to PhoneBill object. It will not display PhoneBill object

8 argument
-print "Deep Patel" 123-123-1234 123-123-1234 1/7/2020 10:39  1/7/2020 10:41
This add phonebill and display the phonebill record

9 arguments
-textFile "src/main/resources/edu/pdx/cs410J/deep/file.txt" "Deep Patel" 123-123-1234 123-123-1234 1/7/2020 10:39  1/7/2020 10:41
Above command write to file and read from file

